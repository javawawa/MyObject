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
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ljCmsLiveController.do?remarkLive" >
					<input id="id" name="id" type="hidden" value="${ljCmsLivePage.id }"/>
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<input type="hidden" name="isComment" id="isComment" value="0">
					<td align="right">
						<label class="Validform_label">
							点评内容:
						</label>
					</td>
					<td class="value">
						<textarea id="commentContent" cols="80" name="commentContent" type="text" style="width: 250px;height: 100px" class="textarea" datatype="*1-2000">${ljCmsLivePage.commentContent}</textarea>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">点评内容</label>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
