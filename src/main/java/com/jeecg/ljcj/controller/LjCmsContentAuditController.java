package com.jeecg.ljcj.controller;

import com.alibaba.fastjson.JSON;
import com.jeecg.ljcj.entity.LjBaseUserEntity;
import com.jeecg.ljcj.entity.LjCmsAuthorEntity;
import com.jeecg.ljcj.entity.LjCmsContentEntity;
import com.jeecg.ljcj.service.LjCmsContentServiceI;
import com.jeecg.ljcj.utils.jpushUtils.HttpUtil;
import com.jeecg.ljcj.utils.jpushUtils.JpushRequestEntity;
import org.apache.log4j.Logger;
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
import java.text.SimpleDateFormat;
import java.util.*;

/**   
 * @Title: Controller
 * @Description: 内容审批
 * @author zhangdaihao
 * @date 2018-11-12 16:55:39
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/ljCmsContentAuditController")
public class LjCmsContentAuditController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LjCmsContentAuditController.class);
	private static final ResourceBundle bundle = ResourceBundle.getBundle("sysConfig");

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
		return new ModelAndView("com/java/ljcj/ljCmsContentAuditList");
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
		//自定义追加查询条件
		if(ljCmsContent.getContentTitle()!=null && !"".equals(ljCmsContent.getContentTitle())){
			ljCmsContent.setContentTitle("*"+ljCmsContent.getContentTitle()+"*");
		}
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, ljCmsContent, request.getParameterMap());
		try{
			//此处只显示 待审核和审核不通过的数据
//			cq.or(Restrictions.eq("status","a"),Restrictions.eq("status","n"));
			String[] statusval ={"a","n","t"};
			cq.in("status",statusval);
			cq.eq("deleted",0);
			cq.addOrder("publishTime",SortDirection.desc);
//			cq.addOrder("pushTop",SortDirection.desc);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.ljCmsContentService.getDataGridReturn(cq, true);
		List<LjCmsContentEntity> cfeList = new ArrayList<LjCmsContentEntity>();
		for (Object o : dataGrid.getResults()) {
			if (o instanceof LjCmsContentEntity) {
				LjCmsContentEntity cfe = (LjCmsContentEntity) o;
				if (cfe.getId() != null && !"".equals(cfe.getId())) {
					String queryAql = "select t.id,t.nick_name from lj_base_user t right JOIN lj_cms_author a on t.id = a.user_id where t.is_verify =1 and t.deleted = 0 ";
					List<Object[]> authorList = systemService.findListbySql(queryAql);
					if (authorList.size() > 0) {
						String nickname = (String) authorList.get(0)[1];
						cfe.setAuthorName(nickname);
					}
				}
				cfeList.add(cfe);
			}
		}
		TagUtil.datagrid(response, dataGrid);
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

	@RequestMapping(params = "goAudit")
	public ModelAndView goAudit(LjCmsContentEntity ljCmsContent, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(ljCmsContent.getId())) {
			ljCmsContent = ljCmsContentService.getEntity(LjCmsContentEntity.class, ljCmsContent.getId());
			req.setAttribute("ljCmsAuditContentPage", ljCmsContent);
		}
		return new ModelAndView("com/java/ljcj/ljCmsGoAuditContent");
	}

	@RequestMapping(params = "modifyTag")
	public ModelAndView modifyTag(LjCmsContentEntity ljCmsContent, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(ljCmsContent.getId())) {
			ljCmsContent = ljCmsContentService.getEntity(LjCmsContentEntity.class, ljCmsContent.getId());
			req.setAttribute("ljCmsContentModifyTag", ljCmsContent);
		}
		return new ModelAndView("com/java/ljcj/ljCmsContentModifyTag");
	}

	/**
	 * 添加内容管理
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(params = "modify")
	@ResponseBody
	public AjaxJson modify(LjCmsContentEntity ljCmsContent, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		TSUser user = ResourceUtil.getSessionUser();
		if (StringUtil.isNotEmpty(ljCmsContent.getId())) {
			message = "内容审批修改成功";
			LjCmsContentEntity t = ljCmsContentService.get(LjCmsContentEntity.class, ljCmsContent.getId());
			try {
				t.setUpdateBy(user.getId());
				t.setUpdateAt(new Date());
				MyBeanUtils.copyBeanNotNull2Bean(ljCmsContent, t);
				ljCmsContentService.saveOrUpdate(t);
				String sql = "update lj_cms_content t set t.update_by = '" + user.getId() + "' where t.id ='" + ljCmsContent.getId() + "'";
				ljCmsContentService.updateBySqlString(sql);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "内容审批修改失败";
			}
		}
		j.setMsg(message);
		return j;
	}



	/**
	 * 审核
	 * @param ljCmsContent
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "doAudit")
	@ResponseBody
	public AjaxJson doAudit(LjCmsContentEntity ljCmsContent, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		ljCmsContent = systemService.getEntity(LjCmsContentEntity.class, ljCmsContent.getId());
		message = "操作成功";
		try{
			String auditType = request.getParameter("auditType");
			String auditMessage = request.getParameter("auditMessage");
			Integer authorId = ljCmsContent.getAuthor();
			Integer articleId = ljCmsContent.getId();
			LjCmsAuthorEntity author = systemService.getEntity(LjCmsAuthorEntity.class, authorId);
			Integer userId = author.getUserId();
			if("t".equals(auditType)){
				ljCmsContent.setStatus("t");
				message = "审核通过成功!";
				addWorkSum(authorId, articleId);
				AddUserScore(userId);
				addScore(userId,articleId);
				Boolean recode = sendContentMessage(ljCmsContent);
				if(!recode){
					message = "推送失败，请联系管理员!";
					throw new BusinessException(message);
				}
			}else if("n".equals(auditType)){
				ljCmsContent.setStatus("n");
				message = "审核驳回成功!";
				subWorkSum(authorId, articleId);
				subUserScore(userId);
				subScore(userId,articleId);
			}
			ljCmsContent.setAuditMessage(auditMessage);
			ljCmsContent.setUpdateAt(new Date());
			ljCmsContentService.saveOrUpdate(ljCmsContent);
		}catch(Exception e){
			e.printStackTrace();
			message = "审核失败，请联系管理员!";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	@RequestMapping(params = "testAudit")
	@ResponseBody
	public AjaxJson testAudit(LjCmsContentEntity ljCmsContent, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		ljCmsContent = systemService.getEntity(LjCmsContentEntity.class, ljCmsContent.getId());
		Boolean recode = sendContentMessage(ljCmsContent);

		j.setMsg(message);
		return j;
	}

	/**
	 * type 1 表示文章
	 * @param ljCmsContent
	 * @return
	 */
	public Boolean  sendContentMessage(LjCmsContentEntity ljCmsContent){
		String url = "http://localhost:8081/jpush/JpushByPost";
		url = bundle.getString("jpushurl");
		String type ="1";
		String actAlert=ljCmsContent.getContentTitle();
		Integer acrId = ljCmsContent.getId();
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
	 * 校验每日增加积分是否超过三次 没超过则用户表加积分
	 * @param
	 * @return
	 */
	public void AddUserScore(Integer userId){
		String upadteUserScoreSql = "select * from lj_user_score t where TO_DAYS(t.create_at) = TO_DAYS(NOW()) and t.user_id = '"+userId+"' and t.job_type = '3' ";
		List<Object> listbySql = systemService.findListbySql(upadteUserScoreSql);
		if(listbySql==null || listbySql.size()<3){
			String updateScore = "update lj_base_user set score=score+60 where id='"+userId+"'";
			systemService.executeSql(updateScore);
		}
	}

	/**
	 * 减去用户表的积分
	 * @param userId
	 */
	public void subUserScore(Integer userId){
		String updateScore = "update lj_base_user set score=if(score<60,0,score-60) where id='"+userId+"'";
		systemService.executeSql(updateScore);
	}

	/**
	 * 审核通过后 添加积分的逻辑
	 * @param
	 */
	public void addScore(Integer userId,Integer articleId){
		Map sqlMap = new HashMap<String,Object>();
		sqlMap.put("userId", userId);
		sqlMap.put("articleId", articleId);
		sqlMap.put("score", "60");
		sqlMap.put("optionType", "3");
		sqlMap.put("remark", "投稿增加积分");
		String insertScoreSql = "insert into lj_user_score  set user_id=:userId,target_id=:articleId,score=:score,job_type=:optionType,message=:remark";
		systemService.executeSql(insertScoreSql,sqlMap);
	}

	/**
	 * 积分表加一条减去的数据
	 * @param userId
	 * @param articleId
	 */
	public void subScore(Integer userId,Integer articleId){
		Map sqlMap = new HashMap<String,Object>();
		sqlMap.put("userId", userId);
		sqlMap.put("articleId", articleId);
		sqlMap.put("score", "60");
		sqlMap.put("optionType", "6");
		sqlMap.put("remark", "审核驳回减去积分");
		String insertScoreSql = "insert into lj_user_score  set user_id=:userId,target_id=:articleId,score=:score,job_type=:optionType,message=:remark";
		systemService.executeSql(insertScoreSql,sqlMap);
	}

	/**
	 * 审核成功后 在作者表中发表文章数加1
	 * @param authorId
	 */
	public void addWorkSum(Integer authorId,Integer articleId) throws Exception{
		LjCmsAuthorEntity author = systemService.getEntity(LjCmsAuthorEntity.class, authorId);
		if (author != null && author.getWorksSum() != null) {
			Integer beforeSum = author.getWorksSum();
			String updateWorksSum = "update lj_cms_author set works_sum=works_sum+1 where id='"+author.getId()+"'";
			systemService.executeSql(updateWorksSum);
			Map sqlMap = new HashMap<String,Object>();
			sqlMap.put("authorId", authorId);
			sqlMap.put("articleId", articleId);
			sqlMap.put("articleNum", beforeSum);
			sqlMap.put("optionType", "1");
			sqlMap.put("remark", "审核通过增加");
			String insertWorkSum = "insert into lj_author_worksun  set author_id=:authorId,article_id=:articleId,article_num=:articleNum,option_type=:optionType,remark=:remark";
			systemService.executeSql(insertWorkSum,sqlMap);
		}
	}
	/**
	 * 审核驳回后 在作者表中发表文章数减1
	 * @param authorId
	 */
	public void subWorkSum(Integer authorId,Integer articleId) throws Exception{
		LjCmsAuthorEntity author = systemService.getEntity(LjCmsAuthorEntity.class, authorId);
		if (author != null && author.getWorksSum() != null) {
			Integer beforeSum = author.getWorksSum();
			String updateWorksSum = "update lj_cms_author set works_sum=if(works_sum=0,0,works_sum-1) where id='"+author.getId()+"'";
			systemService.executeSql(updateWorksSum);
			Map sqlMap = new HashMap<String,Object>();
			sqlMap.put("authorId", authorId);
			sqlMap.put("articleId", articleId);
			sqlMap.put("articleNum", beforeSum);
			sqlMap.put("optionType", "2");
			sqlMap.put("remark", "审核驳回减少");
			String insertWorkSum = "insert into lj_author_worksun  set author_id=:authorId,article_id=:articleId,article_num=:articleNum,option_type=:optionType,remark=:remark";
			systemService.executeSql(insertWorkSum,sqlMap);
		}
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

	@RequestMapping(params = "viewContent")
	public ModelAndView viewContent(HttpServletRequest request) {
		return new ModelAndView("com/java/ljcj/ljCmsViewContent");
	}

	/**
	 * 跳转排序页面
	 * @param
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "goSortList")
	public ModelAndView goSortList(LjCmsContentEntity ljCmsContent, HttpServletRequest request) {
		Integer contentAuditId = ljCmsContent.getId();
		String querySql = "select t.id,t.pushTop,t.contentTitle from LjCmsContentEntity t where t.pushTop between 1 and 10 order by t.pushTop desc";
		List<Map<String,Object>> queryList = ljCmsContentService.findByQueryString(querySql);
		request.setAttribute("queryList",queryList);
		request.setAttribute("contentAuditId",contentAuditId);
		return new ModelAndView("com/java/ljcj/ljCmsContentAudit-sortList");
	}

	/**
	 * 排序
	 * @param
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "doSortList")
	@ResponseBody
	public AjaxJson doSortList(LjCmsContentEntity ljCmsContent, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		String contentAuditId = request.getParameter("contentAuditId");
		String pushTop = request.getParameter("pushTop");
		String uniqueSql = "select t.id from LjCmsContentEntity t where t.pushTop ="+pushTop;
		List<LjCmsContentEntity> queryList = ljCmsContentService.findByQueryString(uniqueSql);
		System.out.println(contentAuditId+"-=-=-"+pushTop);
		try {
			if (queryList != null && queryList.size() > 0) {
				message = "排序覆盖成功!";
				LjCmsContentEntity oldCon = ljCmsContentService.get(LjCmsContentEntity.class,queryList.get(0));
				oldCon.setPushTop(0);
				ljCmsContentService.saveOrUpdate(oldCon);
				LjCmsContentEntity newCon = ljCmsContentService.get(LjCmsContentEntity.class,Integer.parseInt(contentAuditId));
				newCon.setPushTop(Integer.parseInt(pushTop));
				ljCmsContentService.saveOrUpdate(newCon);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} else {
				message = "排序设置成功!";
				LjCmsContentEntity newCon = ljCmsContentService.get(LjCmsContentEntity.class,Integer.parseInt(contentAuditId));
				newCon.setPushTop(Integer.parseInt(pushTop));
				ljCmsContentService.saveOrUpdate(newCon);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "排序失败!";
		}
		j.setMsg(message);
		return j;
	}

}
