package com.jeecg.ljcj.controller;
import com.jeecg.ljcj.entity.LjCmsTagsEntity;
import com.jeecg.ljcj.service.LjCmsTagsServiceI;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**   
 * @Title: Controller  
 * @Description: lj_cms_tags
 * @author onlineGenerator
 * @date 2018-11-08 17:58:06
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/ljCmsTagsController")
public class LjCmsTagsController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(LjCmsTagsController.class);

	@Autowired
	private LjCmsTagsServiceI ljCmsTagsService;
	@Autowired
	private SystemService systemService;
	


	/**
	 * lj_cms_tags列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/java/ljcj/ljCmsTagsList");
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
	public void datagrid(LjCmsTagsEntity ljCmsTags, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LjCmsTagsEntity.class, dataGrid);
		//查询条件组装器
		if(ljCmsTags.getName()!=null && !"".equals(ljCmsTags.getName())){
			ljCmsTags.setName(ljCmsTags.getName()+"*");
		}
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, ljCmsTags, request.getParameterMap());
		try{
		//自定义追加查询条件
			cq.eq("deleted",0);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.ljCmsTagsService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除lj_cms_tags
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(LjCmsTagsEntity ljCmsTags, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		ljCmsTags = systemService.getEntity(LjCmsTagsEntity.class, ljCmsTags.getId());
		message = "标签删除成功";
		try{
			ljCmsTags.setDeleted(1);
			ljCmsTagsService.saveOrUpdate(ljCmsTags);
//			ljCmsTagsService.delete(ljCmsTags);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "标签删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 修改状态
	 */
	@RequestMapping(params = "changeStatus")
	@ResponseBody
	public AjaxJson changeStatus(LjCmsTagsEntity ljCmsTags, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		String status = ljCmsTags.getStatus();
		ljCmsTags = systemService.getEntity(LjCmsTagsEntity.class, ljCmsTags.getId());
		message = "标签状态修改成功";
		try{
		    if("t".equals(status)){
                ljCmsTags.setStatus("f");
            }else{
                ljCmsTags.setStatus("t");
            }
			ljCmsTagsService.saveOrUpdate(ljCmsTags);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "标签状态修改失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 批量删除lj_cms_tags
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "lj_cms_tags删除成功";
		try{
			for(String id:ids.split(",")){
				LjCmsTagsEntity ljCmsTags = systemService.getEntity(LjCmsTagsEntity.class, 
				Integer.parseInt(id)
				);
				ljCmsTagsService.delete(ljCmsTags);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "lj_cms_tags删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加lj_cms_tags
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(LjCmsTagsEntity ljCmsTags, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "标签添加成功";
		TSUser user = ResourceUtil.getSessionUser();
		try{
			ljCmsTags.setCreateBy(user.getId());
			ljCmsTags.setCreateAt(new Date());
			ljCmsTags.setDeleted(0);
			ljCmsTags.setStat(0);
			ljCmsTagsService.save(ljCmsTags);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "标签添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新lj_cms_tags
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(LjCmsTagsEntity ljCmsTags, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "标签更新成功";
		LjCmsTagsEntity t = ljCmsTagsService.get(LjCmsTagsEntity.class, ljCmsTags.getId());
		TSUser user = ResourceUtil.getSessionUser();
		try {
			t.setUpdateBy(user.getId());
            ljCmsTags.setUpdateBy(user.getId());
			t.setUpdateAt(new Date());
			MyBeanUtils.copyBeanNotNull2Bean(ljCmsTags, t);
			ljCmsTagsService.saveOrUpdate(t);
			String sql = "update lj_cms_tags t set t.update_by = '" + user.getId() + "' where t.id ='" + ljCmsTags.getId() + "'";
			ljCmsTagsService.updateBySqlString(sql);
//            System.out.println(t.getUpdateBy());
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "标签更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * lj_cms_tags新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(LjCmsTagsEntity ljCmsTags, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(ljCmsTags.getId())) {
			ljCmsTags = ljCmsTagsService.getEntity(LjCmsTagsEntity.class, ljCmsTags.getId());
			req.setAttribute("ljCmsTagsPage", ljCmsTags);
		}
		return new ModelAndView("com/java/ljcj/ljCmsTags-add");
	}
	/**
	 * lj_cms_tags编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(LjCmsTagsEntity ljCmsTags, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(ljCmsTags.getId())) {
			ljCmsTags = ljCmsTagsService.getEntity(LjCmsTagsEntity.class, ljCmsTags.getId());
			req.setAttribute("ljCmsTagsPage", ljCmsTags);
		}
		return new ModelAndView("com/java/ljcj/ljCmsTags-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","ljCmsTagsController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(LjCmsTagsEntity ljCmsTags,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(LjCmsTagsEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, ljCmsTags, request.getParameterMap());
		List<LjCmsTagsEntity> ljCmsTagss = this.ljCmsTagsService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"lj_cms_tags");
		modelMap.put(NormalExcelConstants.CLASS,LjCmsTagsEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("lj_cms_tags列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,ljCmsTagss);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(LjCmsTagsEntity ljCmsTags,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"lj_cms_tags");
    	modelMap.put(NormalExcelConstants.CLASS,LjCmsTagsEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("lj_cms_tags列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<LjCmsTagsEntity> listLjCmsTagsEntitys = ExcelImportUtil.importExcel(file.getInputStream(),LjCmsTagsEntity.class,params);
				for (LjCmsTagsEntity ljCmsTags : listLjCmsTagsEntitys) {
					ljCmsTagsService.save(ljCmsTags);
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
