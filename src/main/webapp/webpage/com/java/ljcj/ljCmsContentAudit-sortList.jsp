<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>lj_cms_live</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
			<tr><span style="color: red;font-size:14px;">系统中已存在的排序(数字大在前)</span></tr>
			<tr>
				<td align="center">
					<label class="Validform_label">
					序号
					</label>
				</td>
				<td class="value" align="center">标题</td>
			</tr>
			<c:forEach items="${queryList}" var ="list" varStatus="status">
				<tr>
					<td align="center">
						<label class="Validform_label">${list[1]}</label>
					</td>
					<td class="value" align="center">${list[2]}</td>
				</tr>
				<%--<td>${list[1]}</td>--%>
			</c:forEach>
		</table>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ljCmsContentAuditController.do?doSortList" >
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr><span style="color: red;font-size:14px;" >需要修改的排序(如果序号已存在，则覆盖之前的排序。0表示不排序。)</span></tr>
				<tr>
					<td align="center">
						<label class="Validform_label">
							排序
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="pushTop" name="pushTop" datatype="/^([0-9]|10)$/g" placeholder="请输入排序号，0-10" errormsg="只能输入1-10之间的数字!" />
						<span class="Validform_checktip"></span>
					</td>
					<input type="hidden" name="contentAuditId" id="contentAuditId" value="${contentAuditId}">
				</tr>
			</table>
		</t:formvalid>
 </body>
