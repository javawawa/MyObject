package com.jeecg.ljcj.controller;

import com.alibaba.fastjson.JSON;
import com.jeecg.ljcj.entity.LjCmsActivityEntity;
import com.jeecg.ljcj.entity.LjCmsAdvertEntity;
import com.jeecg.ljcj.entity.LjCmsContentEntity;
import com.jeecg.ljcj.service.LjCmsActivityServiceI;
import com.jeecg.ljcj.utils.QiNiuUtil;
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
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.text.SimpleDateFormat;
import java.util.*;

/**   
 * @Title: Controller
 * @Description: 活动管理表
 * @author zhangdaihao
 * @date 2018-11-12 16:53:50
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/ljCmsActivityController")
public class LjCmsActivityController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LjCmsActivityController.class);

	@Autowired
	private LjCmsActivityServiceI ljCmsActivityService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("sysConfig");


	/**
	 * 活动管理表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/java/ljcj/ljCmsActivityList");
	}

	@RequestMapping(params = "activitySelect")
	public ModelAndView activitySelect(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("com/java/ljcj/LjCmsActivity-select");
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
	public void datagrid(LjCmsActivityEntity ljCmsActivity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LjCmsActivityEntity.class, dataGrid);
		//查询条件组装器
		if(ljCmsActivity.getActivityTitle()!=null&&!"".equals(ljCmsActivity.getActivityTitle())){
			ljCmsActivity.setActivityTitle("*"+ljCmsActivity.getActivityTitle()+"*");
		}
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, ljCmsActivity, request.getParameterMap());
		try{
			//自定义追加查询条件
			cq.eq("deleted",0);
			cq.addOrder("sort",SortDirection.desc);
			cq.addOrder("activityTime",SortDirection.desc);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.ljCmsActivityService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	@RequestMapping(params = "selectDatagrid")
	public void selectDatagrid(LjCmsActivityEntity ljCmsActivity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LjCmsActivityEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, ljCmsActivity, request.getParameterMap());
		try{
			//自定义追加查询条件
			cq.eq("deleted",0);
			cq.eq("status","t");
			cq.addOrder("sort",SortDirection.desc);
			cq.addOrder("activityTime",SortDirection.desc);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.ljCmsActivityService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}


	/**
	 * 删除活动管理表
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(LjCmsActivityEntity ljCmsActivity, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		ljCmsActivity = systemService.getEntity(LjCmsActivityEntity.class, ljCmsActivity.getId());
		message = "活动管理删除成功";
		try{
			ljCmsActivity.setDeleted(1);
			ljCmsActivityService.saveOrUpdate(ljCmsActivity);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "活动管理删除失败";
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
	public AjaxJson save(LjCmsActivityEntity ljCmsActivity, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
        TSUser user = ResourceUtil.getSessionUser();
		if (StringUtil.isNotEmpty(ljCmsActivity.getId())) {
			message = "活动管理表更新成功";
			LjCmsActivityEntity t = ljCmsActivityService.get(LjCmsActivityEntity.class, ljCmsActivity.getId());
			try {
                ljCmsActivity.setUpdateBy(user.getId());
				MyBeanUtils.copyBeanNotNull2Bean(ljCmsActivity, t);
				ljCmsActivityService.saveOrUpdate(t);
				String sql = "update lj_cms_activity t set t.update_by = '" + user.getId() + "' where t.id ='" + ljCmsActivity.getId() + "'";
				ljCmsActivityService.updateBySqlString(sql);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "活动管理表更新失败";
			}
		} else {
			message = "活动管理表添加成功";
            ljCmsActivity.setDeleted(0);
            ljCmsActivity.setStat(0);
            ljCmsActivity.setCreateBy(user.getId());
			ljCmsActivityService.save(ljCmsActivity);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 上传图片
	 * @param shareurl
	 * @return
	 */
	@RequestMapping(params = "uploadImage")
	@ResponseBody
	public AjaxJson uploadImage(String shareurl) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.setSuccess(true);
		ajaxJson.setMsg("活动图片上传成功!");
		Map<String, Object> ajaxMap = new HashMap<String, Object>();
		try {
			String message = null;
			shareurl = shareurl.split(",")[1].toString();
			byte[] buffer = new BASE64Decoder().decodeBuffer(shareurl);
			String filename = "image/activityImg/Img"+DateUtils.getDataString(DateUtils.yyyymmddhhmmss) + StringUtil.random(4)+".png";
			int recode = QiNiuUtil.uploadFileByByte(buffer,filename);
			if(recode != 200 ){
				throw new BusinessException("活动图片上传失败!"+recode);
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
	 * 活动管理表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(LjCmsActivityEntity ljCmsActivity, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(ljCmsActivity.getId())) {
			ljCmsActivity = ljCmsActivityService.getEntity(LjCmsActivityEntity.class, ljCmsActivity.getId());
			req.setAttribute("ljCmsActivityPage", ljCmsActivity);
		}
		return new ModelAndView("com/java/ljcj/ljCmsActivity");
	}

	@RequestMapping(params = "goAudit")
	public ModelAndView goAudit(LjCmsActivityEntity ljCmsActivity, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(ljCmsActivity.getId())) {
			ljCmsActivity = ljCmsActivityService.getEntity(LjCmsActivityEntity.class, ljCmsActivity.getId());
			req.setAttribute("ljCmsActivityPage", ljCmsActivity);
		}
		return new ModelAndView("com/java/ljcj/ljCmsGoAuditActivity");
	}

	/**
	 * 审核
	 * @param ljCmsActivity
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "doAudit")
	@ResponseBody
	public AjaxJson doAudit(LjCmsActivityEntity ljCmsActivity, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		ljCmsActivity = systemService.getEntity(LjCmsActivityEntity.class, ljCmsActivity.getId());
		message = "操作成功";
		try{
			String status = request.getParameter("status");
			String auditMessage = request.getParameter("auditMessage");
			if("t".equals(status)){
				ljCmsActivity.setStatus("t");
				Boolean flag = sendActivityMessage(ljCmsActivity);
				if(!flag){
					message = "活动id为"+ljCmsActivity.getId()+"推送失败!请联系管理员!";
					systemService.addLog(message, Globals.Log_Type_PUSH, Globals.Log_Leavel_ERROR);
					throw new BusinessException(message);
				}
				message = "审核通过成功!";
			}else if("1".equals(status)){
				ljCmsActivity.setStatus("1");
				message = "审核驳回成功!";
			}
			ljCmsActivity.setAuditMessage(auditMessage);
			ljCmsActivityService.saveOrUpdate(ljCmsActivity);
		}catch(Exception e){
			e.printStackTrace();
			message = "审核失败，请联系管理员!";
			systemService.addLog(message, Globals.Log_Type_AUDIT, Globals.Log_Leavel_ERROR);
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * type 2 表示活动
	 * @param ljCmsActivity
	 * @return
	 */
	public Boolean  sendActivityMessage(LjCmsActivityEntity ljCmsActivity){
		String url = "http://localhost:8081/jpush/JpushByPost";
		url = bundle.getString("jpushurl");
		String type ="2";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年mm月dd日");
		String actTime = sdf.format(ljCmsActivity.getActivityTime());
		String actAlert="有新活动了!"+ljCmsActivity.getActivityTitle()+"将于"+actTime+"举行!";
		Integer acrId = ljCmsActivity.getId();
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
	 * 查看详情
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "viewDetails")
	public ModelAndView viewDetails(LjCmsActivityEntity ljCmsActivity, HttpServletRequest request) {
		ljCmsActivity = ljCmsActivityService.getEntity(LjCmsActivityEntity.class, ljCmsActivity.getId());
		request.setAttribute("ljCmsActivityPage", ljCmsActivity);
		return new ModelAndView("com/java/ljcj/ljCmsActivity-details");
	}

	/**
	 * 跳转排序页面
	 * @param ljCmsActivity
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "goSortList")
	public ModelAndView goSortList(LjCmsActivityEntity ljCmsActivity, HttpServletRequest request) {
		Integer activityId = ljCmsActivity.getId();
		String querySql = "select t.id,t.sort,t.activityTitle from LjCmsActivityEntity t where t.sort between 1 and 10 order by t.sort desc";
		List<Map<String,Object>> queryList = ljCmsActivityService.findByQueryString(querySql);
		request.setAttribute("queryList",queryList);
		request.setAttribute("activityId",activityId);
		return new ModelAndView("com/java/ljcj/ljCmsActivity-sortList");
	}

	/**
	 * 排序
	 * @param ljCmsActivity
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "doSortList")
	@ResponseBody
	public AjaxJson doSortList(LjCmsActivityEntity ljCmsActivity, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		String activityId = request.getParameter("activityId");
		String sort = request.getParameter("sort");
		String uniqueSql = "select t.id from LjCmsActivityEntity t where t.sort ="+sort;
		List<LjCmsActivityEntity> queryList = ljCmsActivityService.findByQueryString(uniqueSql);
		System.out.println(activityId+"-=-=-"+sort);
		try {
			if (queryList != null && queryList.size() > 0) {
				message = "排序覆盖成功!";
				LjCmsActivityEntity oldAct = ljCmsActivityService.get(LjCmsActivityEntity.class,queryList.get(0));
				oldAct.setSort(0);
				ljCmsActivityService.saveOrUpdate(oldAct);
				LjCmsActivityEntity newAct = ljCmsActivityService.get(LjCmsActivityEntity.class,Integer.parseInt(activityId));
				newAct.setSort(Integer.parseInt(sort));
				ljCmsActivityService.saveOrUpdate(newAct);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} else {
				message = "排序设置成功!";
				LjCmsActivityEntity newAct = ljCmsActivityService.get(LjCmsActivityEntity.class,Integer.parseInt(activityId));
				newAct.setSort(Integer.parseInt(sort));
				ljCmsActivityService.saveOrUpdate(newAct);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "活动排序失败!";
			systemService.addLog(message, Globals.Log_Type_SORT, Globals.Log_Leavel_ERROR);
		}
		j.setMsg(message);
		return j;
	}


	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<LjCmsActivityEntity> list() {
		List<LjCmsActivityEntity> listljCmsActivitys=ljCmsActivityService.getList(LjCmsActivityEntity.class);
		return listljCmsActivitys;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		LjCmsActivityEntity task = ljCmsActivityService.get(LjCmsActivityEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}



	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody LjCmsActivityEntity ljCmsActivity) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LjCmsActivityEntity>> failures = validator.validate(ljCmsActivity);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		ljCmsActivityService.saveOrUpdate(ljCmsActivity);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		ljCmsActivityService.deleteEntityById(LjCmsActivityEntity.class, id);
	}
}
