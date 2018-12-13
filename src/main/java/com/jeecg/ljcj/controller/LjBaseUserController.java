package com.jeecg.ljcj.controller;

import com.jeecg.ljcj.entity.LjBaseUserEntity;
import com.jeecg.ljcj.entity.LjCmsAuthorEntity;
import com.jeecg.ljcj.service.LjBaseUserServiceI;
import com.jeecg.ljcj.service.LjCmsAuthorServiceI;
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
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

/**   
 * @Title: Controller
 * @Description: 活动管理表
 * @author zhangdaihao
 * @date 2018-11-12 16:53:50
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/ljBaseUserController")
public class LjBaseUserController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LjBaseUserController.class);

	@Autowired
	private LjBaseUserServiceI ljBaseUserService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private LjCmsAuthorServiceI ljCmsAuthorService;

	private static final ResourceBundle bundle = ResourceBundle.getBundle("sysConfig");


	/**
	 * 活动管理表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/java/ljcj/ljBaseUserList");
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
	public void datagrid(LjBaseUserEntity ljBaseUser, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LjBaseUserEntity.class, dataGrid);
		//查询条件组装器
		if (ljBaseUser.getNickName() != null && !"".equals(ljBaseUser.getNickName())) {
			ljBaseUser.setNickName("*" + ljBaseUser.getNickName() + "*");
		}
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, ljBaseUser, request.getParameterMap());
		try{
			//自定义追加查询条件
			cq.eq("deleted",0);
			cq.addOrder("createAt",SortDirection.desc);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.ljBaseUserService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除活动管理表
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(LjBaseUserEntity ljBaseUser, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		ljBaseUser = systemService.getEntity(LjBaseUserEntity.class, ljBaseUser.getId());
		message = "用户管理删除成功";
		try{
			ljBaseUser.setDeleted(1);
			ljBaseUserService.saveOrUpdate(ljBaseUser);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "用户管理删除失败";
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
	public AjaxJson changeStatus(LjBaseUserEntity ljBaseUser, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		String status = ljBaseUser.getStatus();
		ljBaseUser = systemService.getEntity(LjBaseUserEntity.class, ljBaseUser.getId());
		message = "用户状态修改成功";
		try{
			if("1".equals(status)){
				ljBaseUser.setStatus("0");
			}else{
				ljBaseUser.setStatus("1");
			}
			ljBaseUserService.saveOrUpdate(ljBaseUser);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "用户状态修改失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}




	/**
	 * 添加活动管理表
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(LjBaseUserEntity ljBaseUser, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
        TSUser user = ResourceUtil.getSessionUser();
		if (StringUtil.isNotEmpty(ljBaseUser.getId())) {
			message = "用户管理表更新成功";
			LjBaseUserEntity t = ljBaseUserService.get(LjBaseUserEntity.class, ljBaseUser.getId());
			try {
                ljBaseUser.setUpdateBy(user.getId());
				MyBeanUtils.copyBeanNotNull2Bean(ljBaseUser, t);
				ljBaseUserService.saveOrUpdate(t);
				String sql = "update lj_base_user t set t.update_by = '" + user.getId() + "' where t.id ='" + ljBaseUser.getId() + "'";
				ljBaseUserService.updateBySqlString(sql);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "用户管理表更新失败";
			}
		} else {
			message = "用户管理表添加成功";
            ljBaseUser.setDeleted(0);
            ljBaseUser.setCreateBy(user.getId());
			ljBaseUserService.save(ljBaseUser);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 活动管理表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(LjBaseUserEntity ljBaseUser, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(ljBaseUser.getId())) {
			ljBaseUser = ljBaseUserService.getEntity(LjBaseUserEntity.class, ljBaseUser.getId());
			req.setAttribute("ljCmsActivityPage", ljBaseUser);
		}
		return new ModelAndView("com/java/ljcj/ljBaseUser");
	}

	/**
	 * 查看详情
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "viewDetails")
	public ModelAndView viewDetails(LjBaseUserEntity ljBaseUser, HttpServletRequest request) {
		ljBaseUser = ljBaseUserService.getEntity(LjBaseUserEntity.class, ljBaseUser.getId());
		request.setAttribute("LjBaseUserPage", ljBaseUser);
		return new ModelAndView("com/java/ljcj/ljBaseUser-details");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<LjBaseUserEntity> list() {
		List<LjBaseUserEntity> listljCmsActivitys=ljBaseUserService.getList(LjBaseUserEntity.class);
		return listljCmsActivitys;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		LjBaseUserEntity task = ljBaseUserService.get(LjBaseUserEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}



	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody LjBaseUserEntity ljBaseUser) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LjBaseUserEntity>> failures = validator.validate(ljBaseUser);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		ljBaseUserService.saveOrUpdate(ljBaseUser);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		ljBaseUserService.deleteEntityById(LjBaseUserEntity.class, id);
	}
}
