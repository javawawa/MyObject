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
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ljCmsActivityController.do?doAudit" >
					<input id="id" name="id" type="hidden" value="${ljCmsActivityPage.id }"/>
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							活动标题:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="contentTitle" name="contentTitle" readonly="readonly" style="width: 250px"  value="${ljCmsActivityPage.activityTitle}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<input type="hidden" name="status" id="status" >
					<td align="right">
						<label class="Validform_label">
							审核理由:
						</label>
					</td>
					<td class="value">
						<textarea id="auditMessage" cols="80" name="auditMessage" type="text" style="width: 250px;height: 100px" class="textarea" datatype="*1-2000"></textarea>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">审核理由</label>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
