package com.jeecg.ljcj.controller;

import com.alibaba.fastjson.JSON;
import com.jeecg.ljcj.entity.LjCmsActivityEntity;
import com.jeecg.ljcj.entity.LjCmsAdvertEntity;
import com.jeecg.ljcj.entity.LjCmsNoticeEntity;
import com.jeecg.ljcj.service.LjCmsNoticeServiceI;
import com.jeecg.ljcj.utils.jpushUtils.HttpUtil;
import com.jeecg.ljcj.utils.jpushUtils.JpushRequestEntity;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

/**   
 * @Title: Controller
 * @Description: 公告管理
 * @author zhangdaihao
 * @date 2018-11-19 10:26:17
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/ljCmsNoticeController")
public class LjCmsNoticeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LjCmsNoticeController.class);
	private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("sysConfig");

	@Autowired
	private LjCmsNoticeServiceI ljCmsNoticeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 公告管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/java/ljcj/ljCmsNoticeList");
	}

	@RequestMapping(params = "goNoticeEditor")
	public ModelAndView goNoticeEditor(HttpServletRequest request) {
		return new ModelAndView("com/java/ljcj/ljCmsNoticeEditor");
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
	public void datagrid(LjCmsNoticeEntity ljCmsNotice,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LjCmsNoticeEntity.class, dataGrid);
		//查询条件组装器
		if(ljCmsNotice.getTittle()!=null && !"".equals(ljCmsNotice.getTittle())){
			ljCmsNotice.setTittle("*"+ljCmsNotice.getTittle()+"*");
		}
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, ljCmsNotice, request.getParameterMap());
		try{
			//自定义追加查询条件
			cq.eq("deleted",0);
			cq.addOrder("createAt",SortDirection.desc);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.ljCmsNoticeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 修改状态
	 */
	@RequestMapping(params = "changeStatus")
	@ResponseBody
	public AjaxJson changeStatus(LjCmsNoticeEntity ljCmsNotice, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		String status = ljCmsNotice.getStatus();
		ljCmsNotice = systemService.getEntity(LjCmsNoticeEntity.class, ljCmsNotice.getId());
		message = "公告状态修改成功";
		try{
			if("1".equals(status)){
				ljCmsNotice.setStatus("0");
			}else{
				ljCmsNotice.setStatus("1");
			}
			ljCmsNoticeService.saveOrUpdate(ljCmsNotice);
		}catch(Exception e){
			e.printStackTrace();
			message = "公告状态修改失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 删除公告管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(LjCmsNoticeEntity ljCmsNotice, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		ljCmsNotice = systemService.getEntity(LjCmsNoticeEntity.class, ljCmsNotice.getId());
		message = "公告管理删除成功";
		try {
			ljCmsNotice.setDeleted(1);
			ljCmsNoticeService.saveOrUpdate(ljCmsNotice);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "广告配置删除失败";
			throw new BusinessException(e.getMessage());
		}

		j.setMsg(message);
		return j;
	}

	@RequestMapping(params = "goAudit")
	public ModelAndView goAudit(LjCmsNoticeEntity ljCmsNotice, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(ljCmsNotice.getId())) {
			ljCmsNotice = ljCmsNoticeService.getEntity(LjCmsNoticeEntity.class, ljCmsNotice.getId());
			req.setAttribute("ljCmsNoticePage", ljCmsNotice);
		}
		return new ModelAndView("com/java/ljcj/ljCmsGoAuditNotice");
	}

	/**
	 * 审核
	 * @param ljCmsNotice
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "doAudit")
	@ResponseBody
	public AjaxJson doAudit(LjCmsNoticeEntity ljCmsNotice, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		ljCmsNotice = systemService.getEntity(LjCmsNoticeEntity.class, ljCmsNotice.getId());
		message = "操作成功";
		try{
			String auditStatus = request.getParameter("auditStatus");
			String auditMessage = request.getParameter("auditMessage");
			if("t".equals(auditStatus)){
				ljCmsNotice.setAuditStatus("t");
				Boolean flag = sendActivityMessage(ljCmsNotice);
				if(!flag){
					message = "公告id为"+ljCmsNotice.getId()+"推送失败!请联系管理员!";
					systemService.addLog(message, Globals.Log_Type_OTHER, Globals.Log_Leavel_INFO);
					throw new BusinessException(message);
				}
				message = "审核通过成功!";
			}else if("1".equals(auditStatus)){
				ljCmsNotice.setAuditStatus("1");
				message = "审核驳回成功!";
			}
			ljCmsNotice.setAuditMessage(auditMessage);
			ljCmsNoticeService.saveOrUpdate(ljCmsNotice);
		}catch(Exception e){
			e.printStackTrace();
			message = "审核失败，请联系管理员!";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * type 4 表示公告
	 * @param ljCmsNotice
	 * @return
	 */
	public Boolean  sendActivityMessage(LjCmsNoticeEntity ljCmsNotice){
		String url = "http://localhost:8081/jpush/JpushByPost";
		url = bundle.getString("jpushurl");
		String type ="4";
		String actAlert="公告:"+ljCmsNotice.getTittle();
		Integer acrId = ljCmsNotice.getId();
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
	 * 添加公告管理
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(LjCmsNoticeEntity ljCmsNotice, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		TSUser user = ResourceUtil.getSessionUser();
		if (StringUtil.isNotEmpty(ljCmsNotice.getId())) {
			message = "公告管理更新成功";
			LjCmsNoticeEntity t = ljCmsNoticeService.get(LjCmsNoticeEntity.class, ljCmsNotice.getId());
			try {
				t.setDeleted(0);
				t.setUpdateUser(user.getId());
				MyBeanUtils.copyBeanNotNull2Bean(ljCmsNotice, t);
				ljCmsNoticeService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "公告管理更新失败";
			}
		} else {
			message = "公告管理添加成功";
			ljCmsNotice.setDeleted(0);
			ljCmsNotice.setCreateBy(user.getId());
			ljCmsNoticeService.save(ljCmsNotice);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 公告管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(LjCmsNoticeEntity ljCmsNotice, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(ljCmsNotice.getId())) {
			ljCmsNotice = ljCmsNoticeService.getEntity(LjCmsNoticeEntity.class, ljCmsNotice.getId());
			req.setAttribute("ljCmsNoticePage", ljCmsNotice);
		}
		return new ModelAndView("com/java/ljcj/ljCmsNotice");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<LjCmsNoticeEntity> list() {
		List<LjCmsNoticeEntity> listljCmsNotices=ljCmsNoticeService.getList(LjCmsNoticeEntity.class);
		return listljCmsNotices;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		LjCmsNoticeEntity task = ljCmsNoticeService.get(LjCmsNoticeEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}



	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody LjCmsNoticeEntity ljCmsNotice) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LjCmsNoticeEntity>> failures = validator.validate(ljCmsNotice);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		ljCmsNoticeService.saveOrUpdate(ljCmsNotice);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		ljCmsNoticeService.deleteEntityById(LjCmsNoticeEntity.class, id);
	}
}
