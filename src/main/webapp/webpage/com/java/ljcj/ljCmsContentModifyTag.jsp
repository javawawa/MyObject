<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>内容管理</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>

</head>
<body >
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ljCmsContentAuditController.do?modify">
	<input id="id" name="id" type="hidden" value="${ljCmsContentModifyTag.id }">
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">
					所属频道:
				</label>
			</td>
			<td class="value">
				<t:dictSelect field="catId" dictTable="lj_cms_category" dictField="id" dictText="cat_name" title="所属频道"  defaultVal="${ljCmsContentModifyTag.catId}" datatype="*" ></t:dictSelect>
					<%--<input class="inputxt" id="catId" name="catId" ignore="ignore"  value="${ljCmsContentModifyTag.catId}" datatype="n" />--%>
				<span class="Validform_checktip"></span>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					发布时间:
				</label>
			</td>
			<td class="value">
				<c:choose>
					<c:when test="${ljCmsContentModifyTag.publishTime != null}">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px;height: 28px" id="publishTime" name="publishTime" ignore="checked"    value="<fmt:formatDate value='${ljCmsContentModifyTag.publishTime}' type="date" pattern="yyyy-MM-dd HH:mm:ss"/>" datatype="*" />
					</c:when>
					<c:otherwise>
						<input class="Wdate" onClick="WdatePicker({startDate:'%y-%M-%d %H:%M:%s',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"  style="width: 150px;height: 28px" id="publishTime" name="publishTime" ignore="checked"  datatype="*"  value="" />
					</c:otherwise>
				</c:choose>
				<span class="Validform_checktip"></span>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					内容类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect field="contentType"  typeGroupCode="contType" hasLabel="false" defaultVal="${ljCmsContentModifyTag.contentType}" readonly="readonly"></t:dictSelect>
					<%--<input class="inputxt" id="contentType" name="contentType" ignore="ignore"  value="${ljCmsContentModifyTag.contentType}" />--%>
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
				<t:dictSelect field="status"  typeGroupCode="ContStatus" hasLabel="false" defaultVal="${ljCmsContentModifyTag.status}" readonly="readonly"></t:dictSelect>
					<%--<input class="inputxt" id="status" name="status" ignore="ignore"  value="${ljCmsContentModifyTag.status}" />--%>
				<span class="Validform_checktip"></span>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					发布渠道:
				</label>
			</td>
			<td class="value">
				<t:dictSelect field="releaseChannel"   typeGroupCode="reChannel" hasLabel="false" defaultVal="${ljCmsContentModifyTag.releaseChannel}"></t:dictSelect>
					<%--<input class="inputxt" id="releaseChannel" name="releaseChannel"   value="${ljCmsContentModifyTag.releaseChannel}" datatype="n" />--%>
				<span class="Validform_checktip"></span>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					内容标题:
				</label>
			</td>
			<td class="value">
				<input class="inputxt" id="contentTitle" name="contentTitle" datatype="*"  style="width: 300px"  value="${ljCmsContentModifyTag.contentTitle}" />
				<span class="Validform_checktip"></span>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					列表标题:
				</label>
			</td>
			<td class="value">
				<input class="inputxt" id="listTile" name="listTile" datatype="*"  style="width: 300px"  value="${ljCmsContentModifyTag.listTile}" />
				<span class="Validform_checktip"></span>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					内容标签:
				</label>
			</td>
			<td class="value" nowrap>
				<input id="contentLabel" name="contentLabel" type="hidden" value="${ljCmsContentModifyTag.contentLabel}"/>
				<input name="name" id="name" class="inputxt" value="${ljCmsContentModifyTag.contentLabel}" readonly="readonly" datatype="testTab" errormsg="最多选择三个标签！" nullmsg="请选择标签！" />
				<t:choose hiddenName="contentLabel" hiddenid="name" textname="name" url="ljCmsAuthorController.do?tags"  name="tagList" icon="icon-search" title="标签列表" isclear="true" isInit="true" left="50%" top="50%"></t:choose>
				<span class="Validform_checktip"></span>
			</td>
		</tr>

		<tr>
			<td align="right">
				<label class="Validform_label">
					内容图片:
				</label>
			</td>
			<td class="value">
				<c:choose>
					<c:when test="${ljCmsContentModifyTag.picLink != null}">
						<img style="width:44px;height:44px;" border="1" src="http://static.unicoin.top${ljCmsContentModifyTag.picLink}">
					</c:when>
				</c:choose>
				<t:upload  name="uploadimg" dialog="false" multi="false" extend="pic" queueID="instructionfile"  auto="true" uploader="imageUploadToQiNiuController.do?uploadImage&uploadType=contentImg&width=400&height=240" onUploadSuccess="uploadSuccess"  id="instruction" formData="documentTitle"></t:upload>
				<input type="hidden" id="picLink" name="picLink"  value="${ljCmsContentModifyTag.picLink}" />
				<span id="mobilepx" style="color: red;">建议图片大小为400*240px</span>
			</td>
		</tr>
		<tr>
			<td align="right" id="uploadTd">
				<label class="Validform_label">
					上传文件:
				</label>
			</td>
			<td colspan="2" id="instructionfile" class="value">
			</td>
		</tr>








	</table>
</t:formvalid>
<script>
    $(function () {
        $("#contentTitle").blur(function () {
            var contentTitle = $("#contentTitle").val();
            $("#listTile").val(contentTitle)
        })
    });
    function uploadSuccess(d,file,response){
        debugger;
        tip(d.msg);
        var imgsrc = "/"+d.attributes.filename;
        $("#picLink").val(imgsrc);
    }
    $(".registerform").Validform({
        tiptype:2,
        datatype : {
            testTab:function(gets,obj,curform,regxp){
                var code = $("#label").val();
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