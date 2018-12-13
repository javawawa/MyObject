<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>活动管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script>
	 function uploadSuccess(d,file,response){
		 debugger;
		 tip(d.msg);
		 var imgsrc = "/"+d.attributes.filename;
		 $("#activityPic").val(imgsrc);
	 }
  </script>
 </head>
 <body >
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ljCmsActivityController.do?save">
			<input id="id" name="id" type="hidden" value="${ljCmsActivityPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							活动标题:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="activityTitle" name="activityTitle" validType="lj_cms_activity,activity_title,id" datatype="*1-100"  value="${ljCmsActivityPage.activityTitle}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							活动图片:
						</label>
					</td>
                    <td class="value">
						<c:choose>
							<c:when test="${ljCmsActivityPage.activityPic != null}">
								<img style="width:44px;height:44px;" border="1" src="http://static.unicoin.top${ljCmsActivityPage.activityPic}">
							</c:when>
						</c:choose>
						<t:upload  name="uploadimg" dialog="false" multi="false" extend="pic" queueID="instructionfile"  auto="true" uploader="imageUploadToQiNiuController.do?uploadImage&uploadType=activityImg&width=580&height=300" onUploadSuccess="uploadSuccess"  id="instruction" formData="documentTitle"></t:upload>
                        <input type="hidden" id="activityPic" name="activityPic"  value="${ljCmsActivityPage.activityPic}" datatype="*" nullmsg="请上传活动图片!" />
						<span id="mobilepx" style="color: red;">建议图片大小为580*300px</span>
                    </td>
					<%--<td class="value">--%>
						<%--<input class="inputxt" id="activityPic" name="activityPic"   value="${ljCmsActivityPage.activityPic}" />--%>
						<%--<span class="Validform_checktip"></span>--%>
					<%--</td>--%>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							主办单位:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="organizer" name="organizer" datatype="*1-128"  value="${ljCmsActivityPage.organizer}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							活动类型:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="activityType" datatype="*" typeGroupCode="actType" hasLabel="false" defaultVal="${ljCmsActivityPage.activityType}"></t:dictSelect>
						<%--<input class="inputxt" id="activityType" name="activityType" ignore="ignore"  value="${ljCmsActivityPage.activityType}" />--%>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							活动地址:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="activityAddress" name="activityAddress" datatype="*1-128"  value="${ljCmsActivityPage.activityAddress}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							活动内容:
						</label>
					</td>
					<td class="value">
                        <a href="#" class="easyui-linkbutton l-btn l-btn-plain" plain="true" icon="icon-edit" onclick="editWang()">富文本编辑</a>
						<input class="inputxt" id="activityHtml" name="activityHtml" type="hidden"  value='${ljCmsActivityPage.activityHtml}' />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							活动时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="activityTime" name="activityTime"     value="<fmt:formatDate value='${ljCmsActivityPage.activityTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" datatype="*" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							价格:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="price" name="price"   value="${ljCmsActivityPage.price}" datatype="n" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<%--<tr>--%>
					<%--<td align="right">--%>
						<%--<label class="Validform_label">--%>
							<%--作者:--%>
						<%--</label>--%>
					<%--</td>--%>
					<%--<td class="value">--%>
						<%--<t:dictSelect field="authorId" dictTable="lj_base_user"  dictField="lj_cms_author.id" dictText="nick_name" dictCondition="right JOIN lj_cms_author on lj_base_user.id = lj_cms_author.user_id where lj_base_user.is_verify =1 and lj_base_user.deleted = 0 and lj_cms_author.type =1" title="作者"  defaultVal="${ljCmsActivityPage.authorId}" ></t:dictSelect>--%>
						<%--&lt;%&ndash;<input class="inputxt" id="authorId" name="authorId" ignore="ignore"  value="${ljCmsActivityPage.authorId}" datatype="n" />&ndash;%&gt;--%>
						<%--<span class="Validform_checktip"></span>--%>
					<%--</td>--%>
				<%--</tr>--%>
				<%--<tr>--%>
					<%--<td align="right">--%>
						<%--<label class="Validform_label">--%>
							<%--状态:--%>
						<%--</label>--%>
					<%--</td>--%>
					<%--<td class="value">--%>
						<%--<t:dictSelect field="status" datatype="*" typeGroupCode="actStatus" hasLabel="false" defaultVal="${ljCmsActivityPage.status}"></t:dictSelect>--%>
						<%--<span class="Validform_checktip"></span>--%>
					<%--</td>--%>
				<%--</tr>--%>
				<input id="status" name="status" type="hidden" value="${ljCmsActivityPage.status}" />
				<tr>
					<td align="right">
						<label class="Validform_label">
							排序:
						</label>
					</td>
					<td class="value">
						<input class="inputxt"  id="sort" name="sort" validType="lj_cms_activity,sort,id"  value="${ljCmsActivityPage.sort}" datatype="n" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>

			</table>
		</t:formvalid>
 </body>
<script>
    function editWang(){
        var contentHtml = $("#activityHtml").val();
        // contentHtml = encodeURI(encodeURI(contentHtml));
        sessionStorage.setItem('contentHtml', contentHtml);
        $.dialog({
            content: "url:ljCmsContentController.do?testWang&contentHtml="+contentHtml,
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
                var newContentHtml = iframe.editor.txt.html();
                $("#activityHtml").val(newContentHtml);
            },
            cancelVal: '关闭',
            cancel: true
        });

    }
</script>