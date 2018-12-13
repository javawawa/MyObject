package com.jeecg.ljcj.controller;

import com.alibaba.fastjson.JSON;
import com.jeecg.ljcj.entity.LjCmsLiveEntity;
import com.jeecg.ljcj.service.LjCmsLiveServiceI;
import com.jeecg.ljcj.utils.QiNiuUtil;
import com.jeecg.ljcj.utils.createImg.ImageCreate;
import com.jeecg.ljcj.utils.jpushUtils.HttpUtil;
import com.jeecg.ljcj.utils.jpushUtils.JpushRequestEntity;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


/**   
 * @Title: Controller  
 * @Description: lj_cms_live
 * @author onlineGenerator
 * @date 2018-11-09 11:57:05
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/ljCmsLiveController")
public class LjCmsLiveController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(LjCmsLiveController.class);
	private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("sysConfig");

	@Autowired
	private LjCmsLiveServiceI ljCmsLiveService;
	@Autowired
	private SystemService systemService;
	


	/**
	 * lj_cms_live列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/java/ljcj/ljCmsLiveList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(LjCmsLiveEntity ljCmsLive, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LjCmsLiveEntity.class, dataGrid);
		//查询条件组装器
		if(ljCmsLive.getTitle()!=null&&!"".equals(ljCmsLive.getTitle())){
			ljCmsLive.setTitle(ljCmsLive.getTitle()+"*");
		}
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, ljCmsLive, request.getParameterMap());
		try{
		//自定义追加查询条件
			cq.eq("deleted",0);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.ljCmsLiveService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除lj_cms_live
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(LjCmsLiveEntity ljCmsLive, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		ljCmsLive = systemService.getEntity(LjCmsLiveEntity.class, ljCmsLive.getId());
		message = "快讯删除成功";
		try{
			ljCmsLive.setDeleted(1);
			ljCmsLiveService.saveOrUpdate(ljCmsLive);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "快讯删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除lj_cms_live
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "lj_cms_live删除成功";
		try{
			for(String id:ids.split(",")){
				LjCmsLiveEntity ljCmsLive = systemService.getEntity(LjCmsLiveEntity.class, 
				Integer.parseInt(id)
				);
				ljCmsLiveService.delete(ljCmsLive);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "lj_cms_live删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 发布
	 *
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "publishLive")
	@ResponseBody
	public AjaxJson publishLive(String id, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "发布快讯成功";
			try {
				//上传分享图片
				LjCmsLiveEntity ljCmsLive = systemService.getEntity(LjCmsLiveEntity.class, Integer.parseInt(id));
				Date livetime = ljCmsLive.getLiveTime();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String sliveTime = sdf.format(livetime);
				BufferedImage shareImgbi = ImageCreate.createShareImg(ljCmsLive.getTitle(), ljCmsLive.getContent(), sliveTime);
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				ImageIO.write(shareImgbi, "png", out);
				byte[] buffer = out.toByteArray();
				String filename = "image/shareImg/Img"+DateUtils.getDataString(DateUtils.yyyymmddhhmmss) + StringUtil.random(4)+".png";
				int recode = QiNiuUtil.uploadFileByByte(buffer,filename);
				if(recode != 200 ){
					throw new BusinessException("快讯分享图片上传失败!"+recode);
				}
				String lastFilename = "/"+filename;
				ljCmsLive.setStatus("t");
				ljCmsLive.setShareLink(lastFilename);
				ljCmsLiveService.saveOrUpdate(ljCmsLive);
				//消息推送
				Boolean flag = sendLiveMessage(ljCmsLive);
				if(!flag){
					message = "快讯id为"+id+"推送失败!请联系管理员!";
					throw new BusinessException(message);
				}
				systemService.addLog(message, Globals.Log_Type_OTHER, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "发布快讯失败，id为"+id;
				throw new BusinessException(e.getMessage());
			}
		j.setMsg(message);
		return j;
	}

	/**
	 * type 3 表示快讯
	 * @param ljCmsLive
	 * @return
	 */
	public Boolean  sendLiveMessage(LjCmsLiveEntity ljCmsLive){
		String url = "http://localhost:8081/jpush/JpushByPost";
		url = bundle.getString("jpushurl");
		String type ="3";
		String actAlert=ljCmsLive.getTitle();
		Integer acrId = ljCmsLive.getId();
		JpushRequestEntity jpush = new JpushRequestEntity();
		jpush.setPushType("1");
		jpush.setType(type);
		jpush.setId(acrId.toString());
		jpush.setAlert(actAlert);
		String dataJson = JSON.toJSONString(jpush);
		Boolean flag = HttpUtil.httpPostWithJson(dataJson, url);
		return flag;
	}

	/**
	 * 上传分享图片到七牛
	 * @param shareurl
	 * @return
	 * @throws Exception
	 */
	public String uploadShareImg(String shareurl) throws Exception{
		String message = null;
		shareurl = shareurl.split(",")[1].toString();
		byte[] buffer = new BASE64Decoder().decodeBuffer(shareurl);
		String filename = "image/shareImg/Img"+DateUtils.getDataString(DateUtils.yyyymmddhhmmss) + StringUtil.random(4)+".png";
		int recode = QiNiuUtil.uploadFileByByte(buffer,filename);
		if(recode != 200 ){
			throw new BusinessException("快讯分享图片上传失败!"+recode);
		}
		String lastFilename = "/"+filename;
		return lastFilename;
	}


    /**
     * 下架
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(params = "soldoutLive")
    @ResponseBody
    public AjaxJson soldoutLive(String id,HttpServletRequest request){
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "下架成功";
        try{
//            for(String id:ids.split(",")){
                LjCmsLiveEntity ljCmsLive = systemService.getEntity(LjCmsLiveEntity.class, Integer.parseInt(id));
                ljCmsLive.setStatus("f");
                ljCmsLiveService.saveOrUpdate(ljCmsLive);
                systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
//            }
        }catch(Exception e){
            e.printStackTrace();
            message = "下架失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    /**
     * 标红
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(params = "redLive")
    @ResponseBody
    public AjaxJson redLive(String id,HttpServletRequest request){
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "标红成功";
        try{
//            for(String id:ids.split(",")){
                LjCmsLiveEntity ljCmsLive = systemService.getEntity(LjCmsLiveEntity.class, Integer.parseInt(id));
                ljCmsLive.setIsImportant("1");
                ljCmsLiveService.saveOrUpdate(ljCmsLive);
                systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
//            }
        }catch(Exception e){
            e.printStackTrace();
            message = "标红失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }


    /**
     * 跳转点评页面
     * @param ljCmsLive
     * @param req
     * @return
     */
    @RequestMapping(params = "goRemarkLive")
    public ModelAndView goRemarkLive(LjCmsLiveEntity ljCmsLive, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(ljCmsLive.getId())) {
            ljCmsLive = ljCmsLiveService.getEntity(LjCmsLiveEntity.class, ljCmsLive.getId());
            req.setAttribute("ljCmsLivePage", ljCmsLive);
        }
        return new ModelAndView("com/java/ljcj/ljCmsLive-remark");
    }

    /**
     *  点评
     * @param
     * @param request
     * @return
     */
    @RequestMapping(params = "remarkLive")
    @ResponseBody
    public AjaxJson remarkLive(LjCmsLiveEntity ljCmsLive,HttpServletRequest request){
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "点评成功";
        LjCmsLiveEntity t = ljCmsLiveService.get(LjCmsLiveEntity.class, ljCmsLive.getId());
        try {
            if(ljCmsLive.getCommentContent()!=null && !"".equals(ljCmsLive.getCommentContent())){
                ljCmsLive.setIsComment("1");
            }
            TSUser user = ResourceUtil.getSessionUser();
            ljCmsLive.setUpdateBy(user.getId());
            MyBeanUtils.copyBeanNotNull2Bean(ljCmsLive, t);
            ljCmsLiveService.saveOrUpdate(t);
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch(Exception e){
            e.printStackTrace();
            message = "点评失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }
	/**
	 * 添加lj_cms_live
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(LjCmsLiveEntity ljCmsLive, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "快讯添加成功";
		try{
		    if(ljCmsLive.getCommentContent()!=null && !"".equals(ljCmsLive.getCommentContent())){
                ljCmsLive.setIsComment("1");
            }
            TSUser user = ResourceUtil.getSessionUser();
            ljCmsLive.setSourceId(0);
            ljCmsLive.setCreateBy(user.getId());
            ljCmsLive.setLiveTime(new Date());
			ljCmsLive.setDeleted(0);
            ljCmsLive.setStat(0);
            ljCmsLive.setStatus("a");
			ljCmsLive.setIsImportant("0");
			ljCmsLiveService.save(ljCmsLive);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "快讯添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新lj_cms_live
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(LjCmsLiveEntity ljCmsLive, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "快讯更新成功";
		LjCmsLiveEntity t = ljCmsLiveService.get(LjCmsLiveEntity.class, ljCmsLive.getId());
		try {
            if(ljCmsLive.getCommentContent()!=null && !"".equals(ljCmsLive.getCommentContent())){
                ljCmsLive.setIsComment("1");
            }
            TSUser user = ResourceUtil.getSessionUser();
            ljCmsLive.setUpdateBy(user.getId());
			MyBeanUtils.copyBeanNotNull2Bean(ljCmsLive, t);
			ljCmsLiveService.saveOrUpdate(t);
			String sql = "update lj_cms_live t set t.update_by = '" + user.getId() + "' where t.id ='" + ljCmsLive.getId() + "'";
			ljCmsLiveService.updateBySqlString(sql);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "快讯更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * lj_cms_live新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(LjCmsLiveEntity ljCmsLive, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(ljCmsLive.getId())) {
			ljCmsLive = ljCmsLiveService.getEntity(LjCmsLiveEntity.class, ljCmsLive.getId());
			req.setAttribute("ljCmsLivePage", ljCmsLive);
		}
		return new ModelAndView("com/java/ljcj/ljCmsLive-add");
	}
	/**
	 * lj_cms_live编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(LjCmsLiveEntity ljCmsLive, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(ljCmsLive.getId())) {
			ljCmsLive = ljCmsLiveService.getEntity(LjCmsLiveEntity.class, ljCmsLive.getId());
			req.setAttribute("ljCmsLivePage", ljCmsLive);
		}
		return new ModelAndView("com/java/ljcj/ljCmsLive-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","ljCmsLiveController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(LjCmsLiveEntity ljCmsLive,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(LjCmsLiveEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, ljCmsLive, request.getParameterMap());
		List<LjCmsLiveEntity> ljCmsLives = this.ljCmsLiveService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"lj_cms_live");
		modelMap.put(NormalExcelConstants.CLASS,LjCmsLiveEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("lj_cms_live列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,ljCmsLives);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(LjCmsLiveEntity ljCmsLive,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"lj_cms_live");
    	modelMap.put(NormalExcelConstants.CLASS,LjCmsLiveEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("lj_cms_live列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<LjCmsLiveEntity> listLjCmsLiveEntitys = ExcelImportUtil.importExcel(file.getInputStream(),LjCmsLiveEntity.class,params);
				for (LjCmsLiveEntity ljCmsLive : listLjCmsLiveEntitys) {
					ljCmsLiveService.save(ljCmsLive);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(e.getMessage());
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
	
	
}
