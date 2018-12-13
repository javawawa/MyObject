package com.jeecg.ljcj.controller;

import com.jeecg.ljcj.entity.LjCmsAdvertEntity;
import com.jeecg.ljcj.utils.QiNiuUtil;
import com.jeecg.ljcj.service.LjCmsAdvertServiceI;
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
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/**   
 * @Title: Controller
 * @Description: 广告配置
 * @author zhangdaihao
 * @date 2018-11-12 16:54:24
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/ljCmsAdvertController")
public class LjCmsAdvertController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LjCmsAdvertController.class);
	private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("sysConfig");

	@Autowired
	private LjCmsAdvertServiceI ljCmsAdvertService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 广告配置列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/java/ljcj/ljCmsAdvertList");
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
	public void datagrid(LjCmsAdvertEntity ljCmsAdvert, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LjCmsAdvertEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, ljCmsAdvert, request.getParameterMap());
		try{

			//自定义追加查询条件
			cq.eq("deleted",0);
			cq.addOrder("createAt",SortDirection.desc);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.ljCmsAdvertService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除广告配置
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(LjCmsAdvertEntity ljCmsAdvert, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		ljCmsAdvert = systemService.getEntity(LjCmsAdvertEntity.class, ljCmsAdvert.getId());
		message = "广告配置删除成功";
		try{
			ljCmsAdvert.setDeleted(1);
			ljCmsAdvertService.saveOrUpdate(ljCmsAdvert);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "广告配置删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 上传pc图片
	 * @param
	 * @return
	 */
	@RequestMapping(params = "uploadImagePc")
	@ResponseBody
	public AjaxJson uploadImagePc(String shareurl) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.setSuccess(true);
		ajaxJson.setMsg("PC图片上传成功!");
		Map<String, Object> ajaxMap = new HashMap<String, Object>();
		try {
			String message = null;
			shareurl = shareurl.split(",")[1].toString();
			byte[] buffer = new BASE64Decoder().decodeBuffer(shareurl);
			String filename = "image/advertImg/Img"+DateUtils.getDataString(DateUtils.yyyymmddhhmmss) + StringUtil.random(4)+".png";
			int recode = QiNiuUtil.uploadFileByByte(buffer,filename);
			if(recode != 200 ){
				throw new BusinessException("PC图片上传失败!"+recode);
			}
			String lastFilename = "/"+filename;
			ajaxJson.setObj(lastFilename);
		} catch (Exception e) {
			systemService.addLog(e.getMessage(), Globals.Log_Type_UPLOAD, Globals.Log_Leavel_ERROR);
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg(e.getMessage());
		}
		return ajaxJson;
	}

	/**
	 * 上传pc图片
	 * @param
	 * @return
	 */
	@RequestMapping(params = "uploadImageMb")
	@ResponseBody
	public AjaxJson uploadImageMb(String shareurl) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.setSuccess(true);
		ajaxJson.setMsg("移动图片上传成功!");
		Map<String, Object> ajaxMap = new HashMap<String, Object>();
		try {
			String message = null;
			shareurl = shareurl.split(",")[1].toString();
			byte[] buffer = new BASE64Decoder().decodeBuffer(shareurl);
			String filename = "image/advertImg/Img"+DateUtils.getDataString(DateUtils.yyyymmddhhmmss) + StringUtil.random(4)+".png";
			int recode = QiNiuUtil.uploadFileByByte(buffer,filename);
			if(recode != 200 ){
				throw new BusinessException("移动图片上传失败!"+recode);
			}
			String lastFilename = "/"+filename;
			ajaxJson.setObj(lastFilename);
		} catch (Exception e) {
			systemService.addLog(e.getMessage(), Globals.Log_Type_UPLOAD, Globals.Log_Leavel_ERROR);
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg(e.getMessage());
		}
		return ajaxJson;
	}

	/**
	 * 修改状态
	 */
	@RequestMapping(params = "changeStatus")
	@ResponseBody
	public AjaxJson changeStatus(LjCmsAdvertEntity ljCmsAdvert, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		String status = ljCmsAdvert.getStatus();
		ljCmsAdvert = systemService.getEntity(LjCmsAdvertEntity.class, ljCmsAdvert.getId());
		message = "广告状态修改成功";
		try{
			if("t".equals(status)){
				ljCmsAdvert.setStatus("f");
			}else{
				ljCmsAdvert.setStatus("t");
			}
			ljCmsAdvertService.saveOrUpdate(ljCmsAdvert);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "广告状态修改失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 添加广告配置
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(LjCmsAdvertEntity ljCmsAdvert, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		TSUser user = ResourceUtil.getSessionUser();
		if(ljCmsAdvert.getTarget()!=null&&!"".equals(ljCmsAdvert.getTarget())){
			if(!"1".equals(ljCmsAdvert.getTargetType())){
				if(ljCmsAdvert.getTarget().startsWith("http")){
					String[] end = ljCmsAdvert.getTarget().split("/");
					String newstr = "/";
					for (int i = 3; i <end.length ; i++) {
						newstr += end[i];
						if(i!=end.length-1){
							newstr += "/";
						}
					}
					ljCmsAdvert.setTarget(newstr);
				}
			}

		}
		stBeforeSave(ljCmsAdvert.getSort(),ljCmsAdvert.getPosition());
		if (StringUtil.isNotEmpty(ljCmsAdvert.getId())) {
			message = "广告配置更新成功";
			LjCmsAdvertEntity t = ljCmsAdvertService.get(LjCmsAdvertEntity.class, ljCmsAdvert.getId());
			try {
				t.setUpdateBy(user.getId());
				MyBeanUtils.copyBeanNotNull2Bean(ljCmsAdvert, t);
				ljCmsAdvertService.saveOrUpdate(t);
				String sql = "update lj_cms_advert t set t.update_by = '" + user.getId() + "' where t.id ='" + ljCmsAdvert.getId() + "'";
				ljCmsAdvertService.updateBySqlString(sql);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "广告配置更新失败";
			}
		} else {
			ljCmsAdvert.setDeleted(0);
			ljCmsAdvert.setClickQantity(0);
			ljCmsAdvert.setStatus("t");
			ljCmsAdvert.setCreateBy(user.getId());
			message = "广告配置添加成功";
			ljCmsAdvertService.save(ljCmsAdvert);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 排序校验 如果存在将之前的置为0
	 */
	public void stBeforeSave(Integer sortNum,String position){
		String hqlsql = "select t.id,t.sort from LjCmsAdvertEntity t where t.deleted = 0 and t.verifyStatus = 2 and t.status = 't' and t.position ='"+position+"' and t.sort ='"+sortNum+"'";
		List<Object[]> queryList = systemService.findByQueryString(hqlsql);
		if(queryList!=null&&queryList.size()>0){
			Integer id = (Integer) queryList.get(0)[0];
			String sql = "update lj_cms_advert t set t.sort = '0' where t.id ='" + id + "'";
			ljCmsAdvertService.updateBySqlString(sql);
		}
	}

	/**
	 * 广告配置列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(LjCmsAdvertEntity ljCmsAdvert, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(ljCmsAdvert.getId())) {
			ljCmsAdvert = ljCmsAdvertService.getEntity(LjCmsAdvertEntity.class, ljCmsAdvert.getId());
			req.setAttribute("ljCmsAdvertPage", ljCmsAdvert);
		}
		return new ModelAndView("com/java/ljcj/ljCmsAdvert");
	}

	/**
	 * 查看详情
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "viewDetails")
	public ModelAndView viewDetails(LjCmsAdvertEntity ljCmsAdvert, HttpServletRequest request) {
		ljCmsAdvert = ljCmsAdvertService.getEntity(LjCmsAdvertEntity.class, ljCmsAdvert.getId());
		request.setAttribute("ljCmsAdvertPage", ljCmsAdvert);
		return new ModelAndView("com/java/ljcj/ljCmsAdvert-details");
	}

	@RequestMapping(params = "goAudit")
	public ModelAndView goAudit(LjCmsAdvertEntity ljCmsAdvert, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(ljCmsAdvert.getId())) {
			ljCmsAdvert = ljCmsAdvertService.getEntity(LjCmsAdvertEntity.class, ljCmsAdvert.getId());
			req.setAttribute("ljCmsAdvertPage", ljCmsAdvert);
		}
		//此处进行审核数的判断
		String position = ljCmsAdvert.getPosition();
		boolean auditFlag = true;
		String faildMsg = "";
		if(position!=null){
			String querySql = "from LjCmsAdvertEntity t where t.deleted = 0 and t.verifyStatus = 2 and t.position = "+position;
			if("1".equals(position)){
				List<Object> queryThree = ljCmsAdvertService.findByQueryString(querySql);
				if(queryThree!=null && queryThree.size()>=3){
					auditFlag = false;
					faildMsg = "审核通过的首页轮播大图不能超过三个!";
				}
			}else if("2".equals(position)){
				List<Object> queryThree = ljCmsAdvertService.findByQueryString(querySql);
				if(queryThree!=null && queryThree.size()>=3){
					auditFlag = false;
					faildMsg = "审核通过的首页小图不能超过三个!";
				}
			}else if("3".equals(position)){
				List<Object> queryOne = ljCmsAdvertService.findByQueryString(querySql);
				if(queryOne!=null && queryOne.size()>=1){
					auditFlag = false;
					faildMsg = "审核通过的首页文章列表图不能超过一个!";
				}
			}
		}
		req.setAttribute("auditFlag", auditFlag);
		req.setAttribute("faildMsg", faildMsg);
		return new ModelAndView("com/java/ljcj/ljCmsGoAuditAdvert");
	}

	/**
	 * 审核
	 * @param ljCmsAdvert
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "doAudit")
	@ResponseBody
	public AjaxJson doAudit(LjCmsAdvertEntity ljCmsAdvert, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		String status = ljCmsAdvert.getStatus();
		ljCmsAdvert = systemService.getEntity(LjCmsAdvertEntity.class, ljCmsAdvert.getId());
		message = "操作成功";
		try{
			String verifyStatus = request.getParameter("verifyStatus");
			String auditMessage = request.getParameter("auditMessage");
			if("2".equals(verifyStatus)){
				ljCmsAdvert.setVerifyStatus(2);
				message = "审核通过成功!";
			}else if("3".equals(verifyStatus)){
				ljCmsAdvert.setVerifyStatus(3);
				message = "审核驳回成功!";
			}
			ljCmsAdvert.setAuditMessage(auditMessage);
			ljCmsAdvertService.saveOrUpdate(ljCmsAdvert);
		}catch(Exception e){
			e.printStackTrace();
			message = "广告审核失败，请联系管理员!";
			systemService.addLog(message, Globals.Log_Type_AUDIT, Globals.Log_Leavel_ERROR);
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<LjCmsAdvertEntity> list() {
		List<LjCmsAdvertEntity> listljCmsAdverts=ljCmsAdvertService.getList(LjCmsAdvertEntity.class);
		return listljCmsAdverts;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		LjCmsAdvertEntity task = ljCmsAdvertService.get(LjCmsAdvertEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}



	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody LjCmsAdvertEntity ljCmsAdvert) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LjCmsAdvertEntity>> failures = validator.validate(ljCmsAdvert);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		ljCmsAdvertService.saveOrUpdate(ljCmsAdvert);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		ljCmsAdvertService.deleteEntityById(LjCmsAdvertEntity.class, id);
	}
}
