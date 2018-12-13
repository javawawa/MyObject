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
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ljCmsLiveController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${ljCmsLivePage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<%--<tr>--%>
						<%--<td align="right">--%>
							<%--<label class="Validform_label">--%>
								<%--源数据id:--%>
							<%--</label>--%>
						<%--</td>--%>
						<%--<td class="value">--%>
						    <%--<input id="sourceId" name="sourceId" type="text" maxlength="10" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore"  value='${ljCmsLivePage.sourceId}'/>--%>
							<%--<span class="Validform_checktip"></span>--%>
							<%--<label class="Validform_label" style="display: none;">源数据id</label>--%>
						<%--</td>--%>
						<%--<td align="right">--%>
							<%--<label class="Validform_label">--%>
								<%--label:--%>
							<%--</label>--%>
						<%--</td>--%>
						<%--<td class="value">--%>
						    <%--<input id="label" name="label" type="text" maxlength="128" style="width: 150px" class="inputxt"  ignore="ignore"  value='${ljCmsLivePage.label}'/>--%>
							<%--<span class="Validform_checktip"></span>--%>
							<%--<label class="Validform_label" style="display: none;">label</label>--%>
						<%--</td>--%>
					<%--</tr>--%>
					<tr>
						<td align="right">
							<span style="color: red;">*</span>
							<label class="Validform_label">
								标题:
							</label>
						</td>
						<td class="value">
						    <input id="title" name="title" type="text" maxlength="200" style="width: 256px" class="inputxt"  ignore="ignore"  value='${ljCmsLivePage.title}' />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">标题</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<span style="color: red;">*</span>
							<label class="Validform_label">
								内容:
							</label>
						</td>
						<td class="value">
							<textarea id="content" cols="80" name="content" type="text" style="width: 250px;height: 100px" class="textarea" datatype="*1-2000">${ljCmsLivePage.content}</textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">内容</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<span style="color: red;">*</span>
							<label class="Validform_label">
								来源:
							</label>
						</td>
						<td class="value">
						    <%--<input id="source" name="source" type="text" maxlength="1" style="width: 150px" class="inputxt"  ignore="ignore"  value='${ljCmsLivePage.source}'/>--%>
							<t:dictSelect field="source" datatype="*" typeGroupCode="liveSource" hasLabel="false" defaultVal="${ljCmsLivePage.source}"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">来源</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								内容标签:
							</label>
						</td>
						<td class="value" nowrap>
							<input id="label" name="label" type="hidden" value="${ljCmsLivePage.label}"/>
							<input name="name" id="name" class="inputxt" value="${ljCmsLivePage.label}" readonly="readonly" datatype="testTab" errormsg="最多选择三个标签！"  />
							<t:choose hiddenName="label" hiddenid="name" textname="name" url="ljCmsAuthorController.do?tags"  name="tagList" icon="icon-search" title="标签列表" isclear="true" isInit="true" left="50%" top="50%"></t:choose>
							<span class="Validform_checktip"></span>
						</td>
					</tr>
					<%--<tr>--%>
						<%--<td align="right">--%>
							<%--<span style="color: red;">*</span>--%>
							<%--<label class="Validform_label">--%>
								<%--是否重要:--%>
							<%--</label>--%>
						<%--</td>--%>
						<%--<td class="value">--%>
							<%--<t:dictSelect field="isImportant" datatype="*" typeGroupCode="sfNum" hasLabel="false" defaultVal="${ljCmsLivePage.isImportant}"></t:dictSelect>--%>
						    <%--&lt;%&ndash;<input id="isImportant" name="isImportant" type="text" maxlength="1" style="width: 150px" class="inputxt"  ignore="ignore"  value='${ljCmsLivePage.isImportant}'/>&ndash;%&gt;--%>
							<%--<span class="Validform_checktip"></span>--%>
							<%--<label class="Validform_label" style="display: none;">是否重要</label>--%>
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
								<%--<t:dictSelect field="status" datatype="*" typeGroupCode="liveStatus" hasLabel="false" defaultVal="${ljCmsLivePage.status}"></t:dictSelect>--%>
								<%--<span class="Validform_checktip"></span>--%>
								<%--<label class="Validform_label" style="display: none;">状态</label>--%>
							<%--</td>--%>
								<%--&lt;%&ndash;<td align="right">&ndash;%&gt;--%>
								<%--&lt;%&ndash;<label class="Validform_label">&ndash;%&gt;--%>
								<%--&lt;%&ndash;点击数:&ndash;%&gt;--%>
								<%--&lt;%&ndash;</label>&ndash;%&gt;--%>
								<%--&lt;%&ndash;</td>&ndash;%&gt;--%>
								<%--&lt;%&ndash;<td class="value">&ndash;%&gt;--%>
								<%--&lt;%&ndash;<input id="stat" name="stat" type="text" maxlength="10" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore"  value='${ljCmsLivePage.stat}'/>&ndash;%&gt;--%>
								<%--&lt;%&ndash;<span class="Validform_checktip"></span>&ndash;%&gt;--%>
								<%--&lt;%&ndash;<label class="Validform_label" style="display: none;">点击数</label>&ndash;%&gt;--%>
								<%--&lt;%&ndash;</td>&ndash;%&gt;--%>
						<%--</tr>--%>
					<tr>
						<%--<td align="right">--%>
							<%--<label class="Validform_label">--%>
								<%--是否点评:--%>
							<%--</label>--%>
						<%--</td>--%>
						<%--<td class="value">--%>
						    <%--<input id="isComment" name="isComment" type="text" maxlength="1" style="width: 150px" class="inputxt"  ignore="ignore"  value='${ljCmsLivePage.isComment}'/>--%>
							<%--<span class="Validform_checktip"></span>--%>
							<%--<label class="Validform_label" style="display: none;">isComment</label>--%>
						<%--</td>--%>
						<input type="hidden" name="isComment" id="isComment" value="0">
						<td align="right">
							<label class="Validform_label">
								点评内容:
							</label>
						</td>
						<td class="value">
							<textarea id="commentContent" cols="80" name="commentContent" type="text" style="width: 250px;height: 100px" class="textarea" >${ljCmsLivePage.commentContent}</textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">点评内容</label>
						</td>
					</tr>

					<%--<tr>--%>
						<%--<td align="right">--%>
							<%--<label class="Validform_label">--%>
								<%--liveTime:--%>
							<%--</label>--%>
						<%--</td>--%>
						<%--<td class="value">--%>
									  <%--<input id="liveTime" name="liveTime" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()"  ignore="ignore" value='<fmt:formatDate value='${ljCmsLivePage.liveTime}' type="date" pattern="yyyy-MM-dd"/>'/>--%>
							<%--<span class="Validform_checktip"></span>--%>
							<%--<label class="Validform_label" style="display: none;">liveTime</label>--%>
						<%--</td>--%>
						<%--<td align="right">--%>
							<%--<label class="Validform_label">--%>
								<%--创建人id:--%>
							<%--</label>--%>
						<%--</td>--%>
						<%--<td class="value">--%>
						    <%--<input id="createBy" name="createBy" type="text" maxlength="10" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore"  value='${ljCmsLivePage.createBy}'/>--%>
							<%--<span class="Validform_checktip"></span>--%>
							<%--<label class="Validform_label" style="display: none;">创建人id</label>--%>
						<%--</td>--%>
					<%--</tr>--%>
					<%--<tr>--%>
						<%--<td align="right">--%>
							<%--<label class="Validform_label">--%>
								<%--更新人id:--%>
							<%--</label>--%>
						<%--</td>--%>
						<%--<td class="value">--%>
						    <%--<input id="updateBy" name="updateBy" type="text" maxlength="10" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore"  value='${ljCmsLivePage.updateBy}'/>--%>
							<%--<span class="Validform_checktip"></span>--%>
							<%--<label class="Validform_label" style="display: none;">更新人id</label>--%>
						<%--</td>--%>
						<%--<td align="right">--%>
							<%--<label class="Validform_label">--%>
								<%--是否删除:--%>
							<%--</label>--%>
						<%--</td>--%>
						<%--<td class="value">--%>
						    <%--<input id="deleted" name="deleted" type="text" maxlength="3" style="width: 150px" class="inputxt"  datatype="n"  ignore="checked"  value='${ljCmsLivePage.deleted}'/>--%>
							<%--<span class="Validform_checktip"></span>--%>
							<%--<label class="Validform_label" style="display: none;">是否删除</label>--%>
						<%--</td>--%>
					<%--</tr>--%>
					<%--<tr>--%>
						<%--<td align="right">--%>
							<%--<label class="Validform_label">--%>
								<%--createAt:--%>
							<%--</label>--%>
						<%--</td>--%>
						<%--<td class="value">--%>
									  <%--<input id="createAt" name="createAt" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()"  datatype="*"  ignore="checked" value='<fmt:formatDate value='${ljCmsLivePage.createAt}' type="date" pattern="yyyy-MM-dd"/>'/>--%>
							<%--<span class="Validform_checktip"></span>--%>
							<%--<label class="Validform_label" style="display: none;">createAt</label>--%>
						<%--</td>--%>
						<%--<td align="right">--%>
							<%--<label class="Validform_label">--%>
								<%--updateAt:--%>
							<%--</label>--%>
						<%--</td>--%>
						<%--<td class="value">--%>
									  <%--<input id="updateAt" name="updateAt" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()"  datatype="*"  ignore="checked" value='<fmt:formatDate value='${ljCmsLivePage.updateAt}' type="date" pattern="yyyy-MM-dd"/>'/>--%>
							<%--<span class="Validform_checktip"></span>--%>
							<%--<label class="Validform_label" style="display: none;">updateAt</label>--%>
						<%--</td>--%>
					<%--</tr>--%>
				<%----%>
					<%--<tr>--%>
						<%--<td align="right">--%>
							<%--<label class="Validform_label">--%>
								<%--contentHtml:--%>
							<%--</label>--%>
						<%--</td>--%>
						<%--<td class="value"  colspan="3" >--%>
						  	 	<%--<textarea id="contentHtml" style="width:600px;" class="inputxt" rows="6" name="contentHtml"  ignore="ignore" >${ljCmsLivePage.contentHtml}</textarea>--%>
							<%--<span class="Validform_checktip"></span>--%>
							<%--<label class="Validform_label" style="display: none;">contentHtml</label>--%>
						<%--</td>--%>
					<%--</tr>--%>
			</table>
		</t:formvalid>
 </body>
 <script>
     $(".registerform").Validform({
         tiptype:2,
         datatype : {
             testTab:function(gets,obj,curform,regxp){
                 var code = $("#label").val();
                 // if(gets==""){
                 //     return "请至少选择一个标签";
                 // }
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
