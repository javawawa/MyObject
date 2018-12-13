<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>广告配置</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
	 <script>
         function uploadSuccessPC(d,file,response){
             debugger;
             tip("PC"+d.msg);
             var imgsrc = "/"+d.attributes.filename;
             $("#pic").val(imgsrc);
         }
         function uploadSuccessMB(d,file,response){
             debugger;
             tip("移动"+d.msg);
             var imgsrc = "/"+d.attributes.filename;
             $("#picMobile").val(imgsrc);
         }
	 </script>
 </head>
 <body >
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ljCmsAdvertController.do?save">
			<input id="id" name="id" type="hidden" value="${ljCmsAdvertPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							广告位:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="position" datatype="*" typeGroupCode="advPosit" hasLabel="false" defaultVal="${ljCmsAdvertPage.position}"></t:dictSelect>
						<%--<input class="inputxt" id="position" name="position" ignore="ignore"  value="${ljCmsAdvertPage.position}" />--%>
						<span class="Validform_checktip"></span>
					</td>
				</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							审核状态:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="verifyStatus" datatype="*" typeGroupCode="advStatus" hasLabel="false" defaultVal="${ljCmsAdvertPage.verifyStatus}"></t:dictSelect>
						<%--<input class="inputxt" id="verifyStatus" name="verifyStatus" ignore="ignore"  value="${ljCmsAdvertPage.verifyStatus}" datatype="n" />--%>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							状态:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="status" datatype="*" typeGroupCode="catStatus" hasLabel="false" defaultVal="${ljCmsAdvertPage.status}"></t:dictSelect>
						<%--<input class="inputxt" id="status" name="status" ignore="ignore"  value="${ljCmsAdvertPage.status}" />--%>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							点击类型:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="targetType" datatype="*" typeGroupCode="advCtype" hasLabel="false" defaultVal="${ljCmsAdvertPage.targetType}"></t:dictSelect>
						<%--<input class="inputxt" id="targetType" name="targetType" ignore="ignore"  value="${ljCmsAdvertPage.targetType}" />--%>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							点击目标:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="target" name="target" style="width: 250px" value="${ljCmsAdvertPage.target}" datatype="*" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							标题:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="title" name="title"  style="width: 250px" value="${ljCmsAdvertPage.title}" datatype="*" validType="lj_cms_advert,title,id" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							内容:
						</label>
					</td>
					<td class="value">
						<textarea id="description" cols="80"
								  name="content" type="text"
								  style="width: 250px;height: 100px" class="textarea"
								  datatype="*1-255">
								${ljCmsAdvertPage.content}
						</textarea>
						<%--<input class="inputxt" id="content" name="content" ignore="ignore"  value="${ljCmsAdvertPage.content}" />--%>
						<span class="Validform_checktip"></span>
					</td>
				</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							点击量:
						</label>
					</td>
					<td class="value">
						<c:choose>
							<c:when test="${ljCmsAdvertPage.clickQantity == null}">
								<input class="inputxt" id="clickQantity" name="clickQantity"   value="0" datatype="n" disabled="disabled" />
							</c:when>
							<c:otherwise>
								<input class="inputxt" id="clickQantity" name="clickQantity"   value="${ljCmsAdvertPage.clickQantity}" disabled="disabled" />
							</c:otherwise>
						</c:choose>
						<span class="Validform_checktip"></span>
					</td>
				</tr>


			</table>
		</t:formvalid>
 </body>