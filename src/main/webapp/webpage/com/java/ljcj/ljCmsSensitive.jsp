<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>敏感词库</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ljCmsSensitiveController.do?save">
			<input id="id" name="id" type="hidden" value="${LjCmsSensitivePage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<span style="color: red;">*</span>
						<label class="Validform_label">
							敏感词:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="sensitiveName" name="sensitiveName" placeholder="请输入敏感词" validType="lj_cms_sensitive,sensitive_name,id" datatype="*" value="${LjCmsSensitivePage.sensitiveName}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
 <script>

 </script>
