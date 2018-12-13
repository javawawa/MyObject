<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>公告管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ljCmsNoticeController.do?save">
			<input id="id" name="id" type="hidden" value="${ljCmsNoticePage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<span style="color: red;">*</span>
						<label class="Validform_label">
							标题:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="tittle" name="tittle" placeholder="请输入标题" validType="lj_cms_category,cat_name,id" datatype="*" value="${ljCmsNoticePage.tittle}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<%--<tr>--%>
					<%--<td align="right">--%>
						<%--<label class="Validform_label">--%>
							<%--公告内容:--%>
						<%--</label>--%>
					<%--</td>--%>
					<%--<td class="value">--%>
						<%--<textarea id="content" cols="80" name="content" type="text" style="width: 250px;height: 100px" class="textarea" datatype="*1-255">${ljCmsNoticePage.content}</textarea>--%>
						<%--&lt;%&ndash;<input class="inputxt" id="content" name="content" ignore="ignore"  value="${ljCmsNoticePage.content}" />&ndash;%&gt;--%>
						<%--<span class="Validform_checktip"></span>--%>
					<%--</td>--%>
				<%--</tr>--%>
				<tr>
					<td align="right">
						<label class="Validform_label">
							公告内容:
						</label>
					</td>
					<td class="value">
						<a href="#" class="easyui-linkbutton l-btn l-btn-plain" plain="true" icon="icon-edit" onclick="editWang()">富文本编辑</a>
						<input class="inputxt" id="content" name="content" type="hidden"  value='${ljCmsNoticePage.content}' />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span style="color: red;">*</span>
						<label class="Validform_label">
							状态 :
						</label>
					</td>
					<td class="value">
						<c:choose>
							<c:when test="${ljCmsNoticePage.status == null}">
								<t:dictSelect field="status" datatype="*" typeGroupCode="qjStatus" hasLabel="false" defaultVal="1"></t:dictSelect>
							</c:when>
							<c:otherwise>
								<t:dictSelect field="status" datatype="*" typeGroupCode="qjStatus" hasLabel="false" defaultVal="${ljCmsNoticePage.status}"></t:dictSelect>
							</c:otherwise>
						</c:choose>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<input type="hidden" id="auditStatus" name="auditStatus" value="${ljCmsNoticePage.auditStatus}" />
				<tr>
					<td align="right">
						<span style="color: red;">*</span>
						<label class="Validform_label">
							类型 :
						</label>
					</td>
					<td class="value">
						<c:choose>
							<c:when test="${ljCmsNoticePage.type == null}">
								<t:dictSelect field="type" datatype="*" typeGroupCode="noticeType" hasLabel="false" defaultVal="0"></t:dictSelect>
							</c:when>
							<c:otherwise>
								<t:dictSelect field="type" datatype="*" typeGroupCode="noticeType" hasLabel="false" defaultVal="${ljCmsNoticePage.type}"></t:dictSelect>
							</c:otherwise>
						</c:choose>
						<span class="Validform_checktip"></span>
					</td>
				</tr>

			</table>
		</t:formvalid>
 </body>
 <script>
     function editWang(){
         var noticeHtml = $("#content").val();
         sessionStorage.setItem('noticeHtml', noticeHtml);
         $.dialog({
             content: "url:ljCmsNoticeController.do?goNoticeEditor",
             lock : true,
             zIndex: getzIndex(),
             width:"650px",
             height:"400px",
             title:"富文本编辑",
             opacity : 0.3,
             cache:false,
             okVal: '确定',
             ok: function(){
                 debugger;
                 var iframe = this.iframe.contentWindow;
                 var noticeHtml = iframe.editor.txt.html();
                 $("#content").val(noticeHtml);
             },
             cancelVal: '关闭',
             cancel: true
         });

     }
 </script>
