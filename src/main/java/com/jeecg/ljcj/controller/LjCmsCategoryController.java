package com.jeecg.ljcj.controller;

import com.jeecg.ljcj.entity.LjCmsCategoryEntity;
import com.jeecg.ljcj.service.LjCmsCategoryServiceI;
import org.apache.log4j.Logger;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**   
 * @Title: Controller
 * @Description: 频道管理
 * @author zhangdaihao
 * @date 2018-11-09 14:53:26
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/ljCmsCategoryController")
public class LjCmsCategoryController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LjCmsCategoryController.class);

	@Autowired
	private LjCmsCategoryServiceI ljCmsCategoryService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 频道管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/java/ljcj/ljCmsCategoryList");
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
	public void datagrid(LjCmsCategoryEntity ljCmsCategory, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LjCmsCategoryEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, ljCmsCategory, request.getParameterMap());
		//查询条件组装器
		try{
			//自定义追加查询条件
			cq.eq("deleted",0);
			cq.addOrder("sort",SortDirection.desc);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.ljCmsCategoryService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除频道管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(LjCmsCategoryEntity ljCmsCategory, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		ljCmsCategory = systemService.getEntity(LjCmsCategoryEntity.class, ljCmsCategory.getId());
		message = "频道管理删除成功";
		try{
			ljCmsCategory.setDeleted(1);
			ljCmsCategoryService.saveOrUpdate(ljCmsCategory);
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
	 *  新增页面跳转
	 * @param
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(LjCmsCategoryEntity ljCmsCategory, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(ljCmsCategory.getId())) {
			ljCmsCategory = ljCmsCategoryService.getEntity(LjCmsCategoryEntity.class, ljCmsCategory.getId());
			req.setAttribute("ljCmscategoryPage", ljCmsCategory);
		}
		return new ModelAndView("com/java/ljcj/ljCmsCategory-add");
	}


	/**
	 * 新增操作
	 * @param ljCmsCategory
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(LjCmsCategoryEntity ljCmsCategory, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "频道添加成功";
		TSUser user = ResourceUtil.getSessionUser();
		try{
			ljCmsCategory.setCreateBy(user.getId());
			ljCmsCategory.setCreateAt(new Date());
			ljCmsCategory.setDeleted(0);
			ljCmsCategoryService.save(ljCmsCategory);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "频道添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 修改页面跳转
	 * @param ljCmsCategory
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(LjCmsCategoryEntity ljCmsCategory, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(ljCmsCategory.getId())) {
			ljCmsCategory = ljCmsCategoryService.getEntity(LjCmsCategoryEntity.class, ljCmsCategory.getId());
			req.setAttribute("ljCmsCategoryPage", ljCmsCategory);
		}
		return new ModelAndView("com/java/ljcj/ljCmsCategory-update");
	}

	/**
	 * 修改操作
	 * @param ljCmsCategory
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(LjCmsCategoryEntity ljCmsCategory, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "频道更新成功";
		LjCmsCategoryEntity t = ljCmsCategoryService.get(LjCmsCategoryEntity.class, ljCmsCategory.getId());
		TSUser user = ResourceUtil.getSessionUser();
		try {
			t.setUpdateBy(user.getId());
			ljCmsCategory.setUpdateBy(user.getId());
			t.setUpdateAt(new Date());
			MyBeanUtils.copyBeanNotNull2Bean(ljCmsCategory, t);
			ljCmsCategoryService.saveOrUpdate(t);
			String sql = "update lj_cms_category t set t.update_by = '" + user.getId() + "' where t.id ='" + ljCmsCategory.getId() + "'";
			ljCmsCategoryService.updateBySqlString(sql);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "频道更新失败";
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
	public AjaxJson changeStatus(LjCmsCategoryEntity ljCmsCategory, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		String status = ljCmsCategory.getStatus();
		ljCmsCategory = systemService.getEntity(LjCmsCategoryEntity.class, ljCmsCategory.getId());
		message = "频道状态修改成功";
		try{
			if("t".equals(status)){
				ljCmsCategory.setStatus("f");
			}else{
				ljCmsCategory.setStatus("t");
			}
			ljCmsCategoryService.saveOrUpdate(ljCmsCategory);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "频道状态修改失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<LjCmsCategoryEntity> list() {
		List<LjCmsCategoryEntity> listljCmsCategorys=ljCmsCategoryService.getList(LjCmsCategoryEntity.class);
		return listljCmsCategorys;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		LjCmsCategoryEntity task = ljCmsCategoryService.get(LjCmsCategoryEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}



	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody LjCmsCategoryEntity ljCmsCategory) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LjCmsCategoryEntity>> failures = validator.validate(ljCmsCategory);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		ljCmsCategoryService.saveOrUpdate(ljCmsCategory);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		ljCmsCategoryService.deleteEntityById(LjCmsCategoryEntity.class, id);
	}
}
