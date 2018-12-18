package com.jeecg.ljcj.controller;

import com.jeecg.ljcj.entity.LjBaseUserEntity;
import com.jeecg.ljcj.entity.LjCmsSensitiveEntity;
import com.jeecg.ljcj.service.LjBaseUserServiceI;
import com.jeecg.ljcj.service.LjCmsAuthorServiceI;
import com.jeecg.ljcj.service.LjCmsSensitiveServiceI;
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
import java.io.*;
import java.nio.charset.StandardCharsets;
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
@RequestMapping("/ljCmsSensitiveController")
public class LjCmsSensitiveController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LjCmsSensitiveController.class);


	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private LjCmsSensitiveServiceI ljCmsSensitiveService;

	private static final ResourceBundle bundle = ResourceBundle.getBundle("sysConfig");


	/**
	 * 敏感词库表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/java/ljcj/ljCmsSensitiveList");
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
	public void datagrid(LjCmsSensitiveEntity ljCmsSensitive, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LjCmsSensitiveEntity.class, dataGrid);
		//查询条件组装器
		if (ljCmsSensitive.getSensitiveName() != null && !"".equals(ljCmsSensitive.getSensitiveName())) {
			ljCmsSensitive.setSensitiveName("*" + ljCmsSensitive.getSensitiveName() + "*");
		}
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, ljCmsSensitive, request.getParameterMap());
		try{
			//自定义追加查询条件
			cq.eq("deleted",0);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.ljCmsSensitiveService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除敏感词库
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(LjCmsSensitiveEntity ljCmsSensitive, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		ljCmsSensitive = systemService.getEntity(LjCmsSensitiveEntity.class, ljCmsSensitive.getId());
		message = "敏感词库管理删除成功";
		try{
			ljCmsSensitive.setDeleted(1);
			ljCmsSensitiveService.saveOrUpdate(ljCmsSensitive);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "敏感词库管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}






	/**
	 * 添加敏感词库
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(LjCmsSensitiveEntity ljCmsSensitive, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(ljCmsSensitive.getId())) {
			message = "敏感词库更新成功";
			LjCmsSensitiveEntity t = ljCmsSensitiveService.get(LjCmsSensitiveEntity.class, ljCmsSensitive.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(ljCmsSensitive, t);
				ljCmsSensitiveService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "敏感词库更新失败";
			}
		} else {
			message = "敏感词库添加成功";
            ljCmsSensitive.setDeleted(0);
			ljCmsSensitiveService.save(ljCmsSensitive);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 敏感词库新增编辑跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(LjCmsSensitiveEntity ljCmsSensitive, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(ljCmsSensitive.getId())) {
			ljCmsSensitive = ljCmsSensitiveService.getEntity(LjCmsSensitiveEntity.class, ljCmsSensitive.getId());
			req.setAttribute("LjCmsSensitivePage", ljCmsSensitive);
		}
		return new ModelAndView("com/java/ljcj/ljCmsSensitive");
	}

	/**
	 * 用于批量导入数据
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "batchSave")
	@ResponseBody
	public String batchSave(HttpServletRequest request) {
		try {
			File file = new File("d://CensorWords.txt");
			InputStream input = new FileInputStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
			int count = 0;
			for(String line = reader.readLine(); line != null; line = reader.readLine()){
				LjCmsSensitiveEntity sensitiveEntity = new LjCmsSensitiveEntity();
				sensitiveEntity.setSensitiveName(line);
				sensitiveEntity.setDeleted(0);
				ljCmsSensitiveService.save(sensitiveEntity);
				count++;
			}
			System.out.println("总共录入了"+count+"条数据!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "success";
	}



	







}
