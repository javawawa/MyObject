package com.jeecg.ljcj.controller;

import com.jeecg.ljcj.entity.LjCmsActivityEntity;
import com.jeecg.ljcj.entity.LjCmsContentEntity;
import com.jeecg.ljcj.utils.ImgInfo;
import com.jeecg.ljcj.utils.QiNiuUtil;
import com.jeecg.ljcj.service.LjCmsContentServiceI;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.log4j.Logger;
import org.jeecgframework.core.beanvalidator.BeanValidators;
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
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.File;
import java.util.*;

/**   
 * @Title: Controller
 * @Description: 内容管理
 * @author zhangdaihao
 * @date 2018-11-12 16:55:39
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/ljCmsContentController")
public class LjCmsContentController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LjCmsContentController.class);
	private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("sysConfig");

	@Autowired
	private LjCmsContentServiceI ljCmsContentService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 内容管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/java/ljcj/ljCmsContentList");
	}

	@RequestMapping(params = "reportList")
	public ModelAndView reportList(HttpServletRequest request) {
		return new ModelAndView("com/java/ljcj/ljCmsContentReportList");
	}

	@RequestMapping(params = "testWang")
	public ModelAndView testWang(HttpServletRequest request) {
		return new ModelAndView("com/java/ljcj/testWang");
	}

	@RequestMapping(params = "articleSelect")
	public ModelAndView activitySelect(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("com/java/ljcj/LjCmsArticle-select");
		return mv;
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
	public void datagrid(LjCmsContentEntity ljCmsContent, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LjCmsContentEntity.class, dataGrid);
		//查询条件组装器
		if(ljCmsContent.getContentTitle()!=null && !"".equals(ljCmsContent.getContentTitle())){
			ljCmsContent.setContentTitle(ljCmsContent.getContentTitle()+"*");
		}
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, ljCmsContent, request.getParameterMap());
		try{
			//自定义追加查询条件
			cq.eq("deleted",0);
			cq.addOrder("createAt",SortDirection.desc);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.ljCmsContentService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	@RequestMapping(params = "reportDatagrid")
	public void reportDatagrid(LjCmsContentEntity ljCmsContent, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LjCmsContentEntity.class, dataGrid);
		//查询条件组装器
		if(ljCmsContent.getContentTitle()!=null && !"".equals(ljCmsContent.getContentTitle())){
			ljCmsContent.setContentTitle(ljCmsContent.getContentTitle()+"*");
		}
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, ljCmsContent, request.getParameterMap());
		try{
			//自定义追加查询条件
			cq.eq("deleted",0);
			cq.eq("status","t");
			cq.addOrder("updateAt",SortDirection.desc);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.ljCmsContentService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 选择标签的页面
	 * @param ljCmsContent
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "selectDatagrid")
	public void selectDatagrid(LjCmsContentEntity ljCmsContent, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LjCmsContentEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, ljCmsContent, request.getParameterMap());
		try{
			//自定义追加查询条件
			cq.eq("deleted",0);
			cq.eq("status","t");
			cq.addOrder("sort",SortDirection.desc);
			cq.addOrder("createAt",SortDirection.desc);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.ljCmsContentService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除内容管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(LjCmsContentEntity ljCmsContent, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		ljCmsContent = systemService.getEntity(LjCmsContentEntity.class, ljCmsContent.getId());
		message = "内容管理删除成功";
		try{
			ljCmsContent.setDeleted(1);
			ljCmsContentService.saveOrUpdate(ljCmsContent);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "内容管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加内容管理
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(LjCmsContentEntity ljCmsContent, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
        TSUser user = ResourceUtil.getSessionUser();
		if (StringUtil.isNotEmpty(ljCmsContent.getId())) {
			message = "内容管理更新成功";
			LjCmsContentEntity t = ljCmsContentService.get(LjCmsContentEntity.class, ljCmsContent.getId());
			try {
				t.setUpdateBy(user.getId());
				MyBeanUtils.copyBeanNotNull2Bean(ljCmsContent, t);
				ljCmsContentService.saveOrUpdate(t);
				String sql = "update lj_cms_content t set t.update_by = '" + user.getId() + "' where t.id ='" + ljCmsContent.getId() + "'";
				ljCmsContentService.updateBySqlString(sql);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "内容管理更新失败";
			}
		} else {
			message = "内容管理添加成功";
			ljCmsContent.setDeleted(0);
			ljCmsContent.setPushTop(0);
			ljCmsContent.setSort(0);
			ljCmsContent.setAuditMessage("");
			ljCmsContent.setCreateBy(user.getId());
			ljCmsContentService.save(ljCmsContent);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 上传图片
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "uploadImage")
	@ResponseBody
	public AjaxJson uploadImage(MultipartHttpServletRequest request) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.setSuccess(true);
		ajaxJson.setMsg("上传文件成功!");
        Map<String, Object> ajaxMap = new HashMap<String, Object>();
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile multipartFile=multipartRequest.getFile("file");// 获取上传文件对象
			String fname = multipartFile.getOriginalFilename();
			String filename = "con"+DateUtils.getDataString(DateUtils.yyyymmddhhmmss) + StringUtil.random(4);
            String ext = fname.substring(fname.lastIndexOf(".") + 1);
			String prefix = "image/contentImg/";
			if(bundle.getString("contentUploadUrl")!=null && !"".equals(bundle.getString("contentUploadUrl"))){
				prefix = bundle.getString("contentUploadUrl");
			}
			filename=prefix+filename+"."+ext;
			CommonsMultipartFile cf = (CommonsMultipartFile)multipartFile;
			//这个myfile是MultipartFile的
			DiskFileItem fi = (DiskFileItem) cf.getFileItem();
			File file = fi.getStoreLocation();
//			BufferedImage sourceImg = ImageIO.read(new FileInputStream(file));
//			double width = sourceImg.getWidth();
//			double height = sourceImg.getHeight();
            ajaxJson.setObj(filename);
            ajaxMap.put("filename", filename);
            ajaxJson.setAttributes(ajaxMap);
			int recode = QiNiuUtil.uploadFile(file,filename);
			if(recode != 200 ){
				ajaxJson.setMsg("文件上传失败！错误码为:"+recode);
				ajaxJson.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg(e.getMessage());
		}
		return ajaxJson;
	}

	@RequestMapping(params = "uploadImageByEditor")
	@ResponseBody
	public ImgInfo uploadImageByEditor(@RequestParam("imgFile") MultipartFile file,MultipartHttpServletRequest request) {
		ImgInfo imgInfo = new ImgInfo();
		try {
			String fname = file.getOriginalFilename();
			String filename = "editor"+DateUtils.getDataString(DateUtils.yyyymmddhhmmss) + StringUtil.random(4);
			String ext = fname.substring(fname.lastIndexOf(".") + 1);
			String prefix = "image/editorImg/";
			if(bundle.getString("editorImg")!=null && !"".equals(bundle.getString("editorImg"))){
				prefix = bundle.getString("editorImg");
			}
			filename=prefix+filename+"."+ext;
			CommonsMultipartFile cf = (CommonsMultipartFile)file;
			//这个myfile是MultipartFile的
			DiskFileItem fi = (DiskFileItem) cf.getFileItem();
			File files = fi.getStoreLocation();
			int recode = QiNiuUtil.uploadFile(files,filename);
			String value = "http://static.unicoin.top/" + filename;
			String[] values = { value };
			int errcode = 0;
			imgInfo.setUrl(values);
			if(recode != 200 ){
				errcode = recode;
			}
			imgInfo.setError(errcode);
		} catch (Exception e) {
			imgInfo.setError(440);
			e.printStackTrace();
		}
		return imgInfo;
	}



	/**
	 * 内容管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(LjCmsContentEntity ljCmsContent, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(ljCmsContent.getId())) {
			ljCmsContent = ljCmsContentService.getEntity(LjCmsContentEntity.class, ljCmsContent.getId());
			req.setAttribute("ljCmsContentPage", ljCmsContent);
		}
		return new ModelAndView("com/java/ljcj/ljCmsContent");
	}

    /**
     * 查看详情
     * @param request
     * @return
     */
    @RequestMapping(params = "viewDetails")
    public ModelAndView viewDetails(LjCmsContentEntity ljCmsContent,HttpServletRequest request) {
        ljCmsContent = ljCmsContentService.getEntity(LjCmsContentEntity.class, ljCmsContent.getId());
        request.setAttribute("ljCmsContentPage", ljCmsContent);
        return new ModelAndView("com/java/ljcj/ljCmsContent-details");
    }
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<LjCmsContentEntity> list() {
		List<LjCmsContentEntity> listljCmsContents=ljCmsContentService.getList(LjCmsContentEntity.class);
		return listljCmsContents;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		LjCmsContentEntity task = ljCmsContentService.get(LjCmsContentEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}



	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody LjCmsContentEntity ljCmsContent) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LjCmsContentEntity>> failures = validator.validate(ljCmsContent);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		ljCmsContentService.saveOrUpdate(ljCmsContent);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		ljCmsContentService.deleteEntityById(LjCmsContentEntity.class, id);
	}
}
