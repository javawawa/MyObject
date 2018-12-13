<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>作者管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ljCmsAuthorController.do?save">
			<input id="id" name="id" type="hidden" value="${ljCmsAuthorPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							名字(用户昵称):
						</label>
					</td>
					<td class="value">
						<input disabled="disabled" class="inputxt" id="Aname" name="Aname" maxlength="128"  value="${ljCmsAuthorPage.nickName}"  />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">名字</label>
					</td>
				</tr>
				<%--<tr>validType="lj_cms_author,name,id"--%>
					<%--<td align="right">--%>
						<%--<label class="Validform_label">--%>
							<%--avatar:--%>
						<%--</label>--%>
					<%--</td>--%>
					<%--<td class="value">--%>
						<%--<input class="inputxt" id="avatar" name="avatar" ignore="ignore"  value="${ljCmsAuthorPage.avatar}" />--%>
						<%--<span class="Validform_checktip"></span>--%>
					<%--</td>--%>
				<%--</tr>--%>
				<tr>
					<td align="right">
						<span style="color: red;">*</span>
						<label class="Validform_label">
							类型:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="type" typeGroupCode="autType" title="类型" datatype="*" defaultVal="${ljCmsAuthorPage.type}"></t:dictSelect>
						<%--<input class="inputxt" id="type" name="type" ignore="ignore"  value="${ljCmsAuthorPage.type}" />--%>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span style="color: red;">*</span>
						<label class="Validform_label">
							标签(最多3个):
						</label>
					</td>
					<td class="value" nowrap>
						<input id="label" name="label" type="hidden" value="${ljCmsAuthorPage.label}"/>
						<input name="name" id="name" class="inputxt" value="${ljCmsAuthorPage.label}" readonly="readonly" datatype="testTab" errormsg="最多选择三个标签！" nullmsg="请选择标签！" />
						<t:choose hiddenName="label" hiddenid="name" textname="name" url="ljCmsAuthorController.do?tags"  name="tagList" icon="icon-search" title="标签列表" isclear="true" isInit="true" left="50%" top="50%"></t:choose>
						<span class="Validform_checktip"></span>
					</td>

					<%--<td class="value">--%>
						<%--<input class="inputxt" id="label" name="label" datatype="*"  value="${ljCmsAuthorPage.label}" />--%>
						<%--<span class="Validform_checktip"></span>--%>
					<%--</td>--%>
				</tr>
				<%--<tr>--%>
					<%--<td align="right">--%>
						<%--<label class="Validform_label">--%>
							<%--文章数:--%>
						<%--</label>--%>
					<%--</td>--%>
					<%--<td class="value">--%>
						<%--<c:choose>--%>
							<%--<c:when test="${ljCmsAuthorPage.worksSum == null}">--%>
								<%--<input class="inputxt" id="worksSum" name="worksSum"   value="0" datatype="n" disabled="disabled" />--%>
							<%--</c:when>--%>
							<%--<c:otherwise>--%>
								<%--<input class="inputxt" id="worksSum" name="worksSum"   value="${ljCmsAuthorPage.worksSum}" datatype="n" disabled="disabled" />--%>
							<%--</c:otherwise>--%>
						<%--</c:choose>--%>
						<%--<span class="Validform_checktip"></span>--%>
					<%--</td>--%>
				<%--</tr>--%>
				<%--<tr>--%>
					<%--<td align="right">--%>
						<%--<label class="Validform_label">--%>
							<%--阅读数:--%>
						<%--</label>--%>
					<%--</td>--%>
					<%--<td class="value">--%>
						<%--<c:choose>--%>
							<%--<c:when test="${ljCmsAuthorPage.worksSum == null}">--%>
								<%--<input class="inputxt" id="clicksSum" name="clicksSum"   value="0" datatype="n" disabled="disabled" />--%>
							<%--</c:when>--%>
							<%--<c:otherwise>--%>
								<%--<input class="inputxt" id="clicksSum" name="clicksSum"  value="${ljCmsAuthorPage.clicksSum}" datatype="n" disabled="disabled" />--%>
							<%--</c:otherwise>--%>
						<%--</c:choose>--%>
						<%--<span class="Validform_checktip"></span>--%>
					<%--</td>--%>
				<%--</tr>--%>
				<%--<tr>--%>
					<%--<td align="right">--%>
						<%--<span style="color: red;">*</span>--%>
						<%--<label class="Validform_label">--%>
							<%--状态:--%>
						<%--</label>--%>
					<%--</td>--%>
					<%--<td class="value">--%>
						<%--<t:dictSelect field="status" typeGroupCode="catStatus" title="作者状态" datatype="*" defaultVal="${ljCmsAuthorPage.status}"></t:dictSelect>--%>
						<%--&lt;%&ndash;<input class="inputxt" id="status" name="status" ignore="ignore"  value="${ljCmsAuthorPage.status}" />&ndash;%&gt;--%>
						<%--<span class="Validform_checktip"></span>--%>
					<%--</td>--%>
				<%--</tr>--%>

			</table>
		</t:formvalid>
 <script>
     $(".registerform").Validform({
         tiptype:2,
         datatype : {
             testTab:function(gets,obj,curform,regxp){
				var code = $("#label").val();
				debugger;
				if(gets==""){
                    return "请至少选择一个标签";
				}
				var count = gets.split(",").length;
                 if(count<=3){
                     return true;
                 }else{
                     return false;
                 }
             }
         }
     });

 </script>
 </body>