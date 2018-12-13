package com.jeecg.ljcj.controller;

import com.alibaba.fastjson.JSON;
import com.jeecg.ljcj.entity.LjBaseUserEntity;
import com.jeecg.ljcj.entity.LjCmsAdvertEntity;
import com.jeecg.ljcj.entity.LjCmsAuthorEntity;
import com.jeecg.ljcj.entity.LjCmsLiveEntity;
import com.jeecg.ljcj.service.LjBaseUserServiceI;
import com.jeecg.ljcj.service.LjCmsAuthorServiceI;
import com.jeecg.ljcj.utils.jpushUtils.HttpUtil;
import com.jeecg.ljcj.utils.jpushUtils.JpushRequestEntity;
import com.jeecg.ljcj.utils.smsUtils.exception.SmsException;
import com.jeecg.ljcj.utils.smsUtils.mail.SendSMS;
import com.jeecg.ljcj.utils.smsUtils.util.ResponseData;
import org.apache.log4j.Logger;
import org.hibernate.transform.Transformers;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.DynamicDBUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
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
import java.util.*;

/**   
 * @Title: Controller
 * @Description: 作者管理
 * @author zhangdaihao
 * @date 2018-11-12 16:55:02
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/ljCmsAuthorController")
public class LjCmsAuthorController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LjCmsAuthorController.class);
	private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("sysConfig");

	@Autowired
	private LjCmsAuthorServiceI ljCmsAuthorService;
	@Autowired
	private LjBaseUserServiceI ljBaseUserService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 作者管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/java/ljcj/ljCmsAuthorList");
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
	public void datagrid(LjCmsAuthorEntity ljCmsAuthor, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LjCmsAuthorEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, ljCmsAuthor, request.getParameterMap());
		try{
			//自定义追加查询条件
			cq.eq("deleted",0);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.ljCmsAuthorService.getDataGridReturn(cq, true);
        for (Object o : dataGrid.getResults()) {
            if (o instanceof LjCmsAuthorEntity) {
                LjCmsAuthorEntity author = (LjCmsAuthorEntity) o;
                if (author.getUserId() != null && !"".equals(author.getUserId())) {
                    List<LjBaseUserEntity> busers = ljBaseUserService.findByProperty(
                            LjBaseUserEntity.class, "id", author.getUserId());
                    if (busers.size() > 0) {
                        author.setNickName(busers.get(0).getNickName());
						author.setIsVerify(busers.get(0).getIsVerify().toString());
						author.setTrueName(busers.get(0).getTrueName());
						author.setLoginMobile(busers.get(0).getLoginMobile());
                    }
                }
            }
        }
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 根据sql获取数据
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "mainDatagrid")
	public void mainDatagrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String nickName = request.getParameter("nickName");
		String isVerify = request.getParameter("isVerify");
		StringBuffer sql = new StringBuffer("select bu.id as uid,bu.login_mobile as loginMobile,bu.nick_name as nickName,bu.true_name as trueName,bu.is_verify as isVerify,ca.type as type,ca.id as id,ca.label as label,ca.clicks_sum as clicksSum,ca.works_sum as worksSum,ca.status as status,ca.create_at as createAt from lj_base_user bu left join lj_cms_author ca on bu.id = ca.user_id and bu.deleted = 0 ");
		if(isVerify!=null&&!"".equals(isVerify)&&!"0".equals(isVerify)) {
			sql.append(" where bu.is_verify = '" + isVerify + "'");
		}else{
			sql.append(" where bu.is_verify != '0'");
		}
		if (nickName != null) {
			sql.append(" and bu.nick_name like '" + nickName + "%'");
		}
		sql.append(" order by ca.create_at DESC");
		List<Map<String, Object>> data = systemService.findForJdbc(sql.toString());
		List<Map<String, Object>> showList = null;
		int showlen = dataGrid.getRows();
		int totalPage = data.size() % showlen == 0 ? data.size() / showlen : data.size() / showlen + 1;
		if(dataGrid.getPage()==1){
			if(showlen>data.size()){
				showlen = data.size();
			}
			showList = data.subList(0, showlen);
		}else{
			if(dataGrid.getPage()==totalPage){
				showList = data.subList((dataGrid.getPage() - 1)*showlen, data.size());
			}else{
				showList = data.subList((dataGrid.getPage() - 1) * showlen, ((dataGrid.getPage() - 1) * showlen + showlen));
			}

		}
		dataGrid.setResults(showList);
		dataGrid.setTotal(data.size());
		TagUtil.datagrid(response, dataGrid);
	}


	@RequestMapping(params = "goCertification")
	public ModelAndView goCertification(LjBaseUserEntity ljBaseUser, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(ljBaseUser.getId())) {
			ljBaseUser = ljBaseUserService.getEntity(LjBaseUserEntity.class, ljBaseUser.getId());
			req.setAttribute("ljBaseUserPageC", ljBaseUser);
		}
		return new ModelAndView("com/java/ljcj/ljCmsAuthorCertification");
	}

	@RequestMapping(params = "goReject")
	public ModelAndView goReject(LjBaseUserEntity ljBaseUser, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(ljBaseUser.getId())) {
			ljBaseUser = ljBaseUserService.getEntity(LjBaseUserEntity.class, ljBaseUser.getId());
			req.setAttribute("ljBaseUserPageR", ljBaseUser);
		}
		return new ModelAndView("com/java/ljcj/ljCmsAuthorReject");
	}
	/**
	 * 实名认证
	 *
	 * @param
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "certification")
	@ResponseBody
	public AjaxJson certification(String id, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "实名认证成功";
		String auditMessage = request.getParameter("auditMessage");
		try {
			LjBaseUserEntity ljBaseUser = systemService.getEntity(LjBaseUserEntity.class, Integer.parseInt(id));
			ljBaseUser.setIsVerify(1);
			ljBaseUser.setAuditMessage(auditMessage);
			ljBaseUser.setType("2");
			ljBaseUserService.saveOrUpdate(ljBaseUser);
			LjCmsAuthorEntity ljCmsAuthor = new LjCmsAuthorEntity();
			TSUser user = ResourceUtil.getSessionUser();
			ljCmsAuthor.setUserId(Integer.parseInt(id));
			ljCmsAuthor.setCreateBy(user.getId());
			ljCmsAuthor.setDeleted(0);
			ljCmsAuthor.setClicksSum(0);
			ljCmsAuthor.setStatus("t");
			ljCmsAuthor.setWorksSum(0);
			ljCmsAuthor.setType("2");
			ljCmsAuthorService.save(ljCmsAuthor);
			Map<String, String> params = new HashMap<String, String>();
			String mobile = ljBaseUser.getLoginMobile();
			String nickName = ljBaseUser.getNickName();
			params.put("name",nickName);
			ResponseData res = SendSMS.send(22369, mobile, params);
			systemService.addLog(message, Globals.Log_Type_AUDIT, Globals.Log_Leavel_INFO);
		} catch (SmsException smse){
			smse.printStackTrace();
			message = "实名认证短信发送失败！";
			systemService.addLog(message, Globals.Log_Type_SEND, Globals.Log_Leavel_ERROR);
			throw new BusinessException(smse.getMessage());
		} catch(Exception e) {
			e.printStackTrace();
			message = "实名认证失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 驳回
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "reject")
	@ResponseBody
	public AjaxJson reject(String id, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "驳回成功";
		String auditMessage = request.getParameter("auditMessage");
		try {
			LjBaseUserEntity ljBaseUser = systemService.getEntity(LjBaseUserEntity.class, Integer.parseInt(id));
			ljBaseUser.setIsVerify(3);
			ljBaseUser.setAuditMessage(auditMessage);
			ljBaseUserService.saveOrUpdate(ljBaseUser);
			Map<String, String> params = new HashMap<String, String>();
			String mobile = ljBaseUser.getLoginMobile();
			String nickName = ljBaseUser.getNickName();
			String audititMessage = ljBaseUser.getAuditMessage();
			params.put("name",nickName);
			params.put("reason",audititMessage);
			ResponseData res = SendSMS.send(22370, mobile, params);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		} catch (SmsException smse){
			smse.printStackTrace();
			message = "驳回短信发送失败！";
			systemService.addLog(message, Globals.Log_Type_SEND, Globals.Log_Leavel_ERROR);
			throw new BusinessException(smse.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			message = "驳回失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * type 5 表示作者审核
	 * @param ljBaseUser
	 * @return
	 */
	public Boolean  sendAuthorMessage(LjBaseUserEntity ljBaseUser,String Atype){
		String url = "http://localhost:8081/jpush/JpushByPost";
		url = bundle.getString("jpushurl");
		String type ="5";
		String actAlert = "";
		if("0".equals(Atype)){
			actAlert="亲爱的"+ljBaseUser.getNickName()+"，您已经通过认证，可以发表文章，谢谢";
		}else{
			actAlert="亲爱的"+ljBaseUser.getNickName()+"，您的认证审核被驳回，原因："+ljBaseUser.getAuditMessage()+ "，请重新认证，谢谢";
		}
		Integer acrId = ljBaseUser.getId();
		String[] ids = new String[2];
		//此处应该是 和用户绑定的极光推送的id
		ids[0]=ljBaseUser.getId().toString();
		JpushRequestEntity jpush = new JpushRequestEntity();
		jpush.setPushType("0");
		jpush.setRegistrationId(ids);
		jpush.setType(type);
		jpush.setId(acrId.toString());
		jpush.setAlert(actAlert);
		String dataJson = JSON.toJSONString(jpush);
		Boolean flag = HttpUtil.httpPostWithJson(dataJson, url);
		return flag;
	}

	/**
	 * 删除作者管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(LjCmsAuthorEntity ljCmsAuthor, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		ljCmsAuthor = systemService.getEntity(LjCmsAuthorEntity.class, ljCmsAuthor.getId());
		message = "作者删除成功";
		try{
			ljCmsAuthor.setDeleted(1);
			ljCmsAuthorService.saveOrUpdate(ljCmsAuthor);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "作者删除失败";
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
	public AjaxJson changeStatus(LjCmsAuthorEntity ljCmsAuthor, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		String status = ljCmsAuthor.getStatus();
		ljCmsAuthor = systemService.getEntity(LjCmsAuthorEntity.class, ljCmsAuthor.getId());
		message = "作者状态修改成功";
		try{
			if("t".equals(status)){
				ljCmsAuthor.setStatus("f");
			}else{
				ljCmsAuthor.setStatus("t");
			}
			ljCmsAuthorService.saveOrUpdate(ljCmsAuthor);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "作者状态修改失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加作者管理
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(LjCmsAuthorEntity ljCmsAuthor, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		ljCmsAuthor.setDeleted(0);
//		String Aname = request.getParameter("Aname");
//		ljCmsAuthor.setName(Aname);
		TSUser user = ResourceUtil.getSessionUser();
		if (StringUtil.isNotEmpty(ljCmsAuthor.getId())) {
			message = "作者管理更新成功";
			LjCmsAuthorEntity t = ljCmsAuthorService.get(LjCmsAuthorEntity.class, ljCmsAuthor.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(ljCmsAuthor, t);
//				ljCmsAuthor.setUpdateBy(user.getId());
				t.setUpdateBy(user.getId());
//				ljCmsAuthorService.updateEntitie(t);
				ljCmsAuthorService.saveOrUpdate(t);
				String sql = "update lj_cms_author t set t.update_by = '" + user.getId() + "' where t.id ='" + ljCmsAuthor.getId() + "'";
				ljCmsAuthorService.updateBySqlString(sql);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "作者管理更新失败";
			}
		} else {
			message = "作者管理添加成功";
			ljCmsAuthor.setCreateBy(user.getId());
			ljCmsAuthorService.save(ljCmsAuthor);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 *  选择标签
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "tags")
	public ModelAndView tags(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("com/java/ljcj/LjCmsTags-select");
//		String ids = oConvertUtils.getString(request.getParameter("ids"));
//		mv.addObject("ids", ids);
		return mv;
	}

	/**
	 * 作者管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(LjCmsAuthorEntity ljCmsAuthor, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(ljCmsAuthor.getId())) {
			ljCmsAuthor = ljCmsAuthorService.getEntity(LjCmsAuthorEntity.class, ljCmsAuthor.getId());
            List<LjBaseUserEntity> busers = ljBaseUserService.findByProperty(
                    LjBaseUserEntity.class, "authorId", ljCmsAuthor.getId());
            if(busers!=null && busers.size()>0){
                ljCmsAuthor.setNickName(busers.get(0).getNickName());
            }

			req.setAttribute("ljCmsAuthorPage", ljCmsAuthor);
		}
		return new ModelAndView("com/java/ljcj/ljCmsAuthor");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<LjCmsAuthorEntity> list() {
		List<LjCmsAuthorEntity> listljCmsAuthors=ljCmsAuthorService.getList(LjCmsAuthorEntity.class);
		return listljCmsAuthors;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		LjCmsAuthorEntity task = ljCmsAuthorService.get(LjCmsAuthorEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}



	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody LjCmsAuthorEntity ljCmsAuthor) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LjCmsAuthorEntity>> failures = validator.validate(ljCmsAuthor);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		ljCmsAuthorService.saveOrUpdate(ljCmsAuthor);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		ljCmsAuthorService.deleteEntityById(LjCmsAuthorEntity.class, id);
	}
}
