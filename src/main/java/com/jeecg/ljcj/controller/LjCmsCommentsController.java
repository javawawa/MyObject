package com.jeecg.ljcj.controller;

import com.jeecg.ljcj.entity.LjCmsCommentsEntity;
import com.jeecg.ljcj.entity.LjCmsContentEntity;
import com.jeecg.ljcj.service.LjCmsCommentsServiceI;
import com.jeecg.ljcj.service.LjCmsContentServiceI;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;
import java.util.ResourceBundle;

/**   
 * @Title: Controller
 * @Description: 内容审批
 * @author zhangdaihao
 * @date 2018-11-12 16:55:39
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/ljCmsCommentsController")
public class LjCmsCommentsController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LjCmsCommentsController.class);
	private static final ResourceBundle bundle = ResourceBundle.getBundle("sysConfig");

	@Autowired
	private LjCmsCommentsServiceI ljCmsCommentsService;
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
		return new ModelAndView("com/java/ljcj/ljCmsCommentsList");
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
	public void datagrid(LjCmsCommentsEntity ljCmsComments, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LjCmsCommentsEntity.class, dataGrid);
		//查询条件组装器
		if(ljCmsComments.getUserNickname()!=null && !"".equals(ljCmsComments.getUserNickname())){
			ljCmsComments.setUserNickname("*"+ljCmsComments.getUserNickname()+"*");
		}
		if(ljCmsComments.getPostsTittle()!=null && !"".equals(ljCmsComments.getPostsTittle())){
			ljCmsComments.setPostsTittle("*"+ljCmsComments.getPostsTittle()+"*");
		}
		if(ljCmsComments.getContent()!=null && !"".equals(ljCmsComments.getContent())){
			ljCmsComments.setContent("*"+ljCmsComments.getContent()+"*");
		}
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, ljCmsComments, request.getParameterMap());
		try{
			//自定义追加查询条件
			cq.eq("deleted",0);
			cq.addOrder("createTime",SortDirection.desc);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.ljCmsCommentsService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(LjCmsCommentsEntity ljCmsComments, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		ljCmsComments = systemService.getEntity(LjCmsCommentsEntity.class, ljCmsComments.getId());
		message = "评论管理删除成功";
		try{
			ljCmsComments.setDeleted(1);
			ljCmsCommentsService.saveOrUpdate(ljCmsComments);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "评论管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

}
