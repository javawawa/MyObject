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
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ljCmsLiveController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${ljCmsLivePage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<%--<tr>--%>
					<%--<td align="right">--%>
						<%--<label class="Validform_label">--%>
							<%--标签:--%>
						<%--</label>--%>
					<%--</td>--%>
					<%--<td class="value">--%>
						 <%--<input id="label" name="label" type="text" maxlength="128" style="width: 150px" class="inputxt"  ignore="ignore" />--%>
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
						 <input id="title" name="title" type="text" maxlength="200" style="width: 256px" class="inputxt" datatype="*"  ignore="ignore" validType="lj_cms_live,title,id" />
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
						<textarea id="content" cols="80" datatype="*" name="content" type="text" placeholder="请输入1到2000位字符" style="width: 250px;height: 100px" class="textarea" datatype="*1-2000"></textarea>
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
						<t:dictSelect field="source" typeGroupCode="liveSource" title="快讯来源" readonly="readonly" defaultVal="1" ></t:dictSelect>
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
						<input id="label" name="label" type="hidden" value=""/>
						<input name="name" id="name" class="inputxt" value="" readonly="readonly" datatype="testTab" errormsg="最多选择三个标签！"  />
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
						<%--<t:dictSelect field="isImportant" typeGroupCode="sfNum" title="是否重要" datatype="*" defaultVal="0"></t:dictSelect>--%>
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
						<%--<t:dictSelect field="status" typeGroupCode="liveStatus" title="状态" datatype="*" defaultVal="a"></t:dictSelect>--%>
							<%--&lt;%&ndash;<input id="status" name="status" type="text" maxlength="1" style="width: 150px" class="inputxt"  ignore="ignore" />&ndash;%&gt;--%>
						<%--<span class="Validform_checktip"></span>--%>
						<%--<label class="Validform_label" style="display: none;">status</label>--%>
					<%--</td>--%>
				<%--</tr>--%>

				<tr>
					<%--<td align="right">--%>
						<%--<label class="Validform_label">--%>
							<%--是否点评:--%>
						<%--</label>--%>
					<%--</td>--%>
					<%--<td class="value">--%>
						<%--<t:dictSelect field="isComment" typeGroupCode="sfNum" title="是否点评" datatype="*"></t:dictSelect>--%>
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
						<textarea id="commentContent" cols="80" name="commentContent" type="text" placeholder="请输入1到2000位字符" style="width: 250px;height: 100px" class="textarea" ></textarea>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">点评内容</label>
					</td>
				</tr>


					<%--<td align="right">--%>
						<%--<label class="Validform_label">--%>
							<%--点击数:--%>
						<%--</label>--%>
					<%--</td>--%>
					<%--<td class="value">--%>
					     	 <%--<input id="stat" name="stat" type="text" maxlength="10" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore" />--%>
							<%--<span class="Validform_checktip"></span>--%>
							<%--<label class="Validform_label" style="display: none;">点击数</label>--%>
						<%--</td>--%>

				<%--<tr>--%>
					<%--<td align="right">--%>
						<%--<label class="Validform_label">--%>
							<%--liveTime:--%>
						<%--</label>--%>
					<%--</td>--%>
					<%--<td class="value">--%>
							   <%--<input id="liveTime" name="liveTime" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker()"  ignore="ignore" />    --%>
							<%--<span class="Validform_checktip"></span>--%>
							<%--<label class="Validform_label" style="display: none;">liveTime</label>--%>
						<%--</td>--%>
					<%--<td align="right">--%>
						<%--<label class="Validform_label">--%>
							<%--创建人id:--%>
						<%--</label>--%>
					<%--</td>--%>
					<%--<td class="value">--%>
					     	 <%--<input id="createBy" name="createBy" type="text" maxlength="10" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore" />--%>
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
					     	 <%--<input id="updateBy" name="updateBy" type="text" maxlength="10" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore" />--%>
							<%--<span class="Validform_checktip"></span>--%>
							<%--<label class="Validform_label" style="display: none;">更新人id</label>--%>
						<%--</td>--%>
					<%--<td align="right">--%>
						<%--<label class="Validform_label">--%>
							<%--是否删除:--%>
						<%--</label>--%>
					<%--</td>--%>
					<%--<td class="value">--%>
					     	 <%--<input id="deleted" name="deleted" type="text" maxlength="3" style="width: 150px" class="inputxt"  datatype="n"  ignore="checked" />--%>
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
							   <%--<input id="createAt" name="createAt" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker()"  datatype="*"  ignore="checked" />--%>
							<%--<span class="Validform_checktip"></span>--%>
							<%--<label class="Validform_label" style="display: none;">createAt</label>--%>
						<%--</td>--%>
					<%--<td align="right">--%>
						<%--<label class="Validform_label">--%>
							<%--updateAt:--%>
						<%--</label>--%>
					<%--</td>--%>
					<%--<td class="value">--%>
							   <%--<input id="updateAt" name="updateAt" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker()"  datatype="*"  ignore="checked" />    --%>
							<%--<span class="Validform_checktip"></span>--%>
							<%--<label class="Validform_label" style="display: none;">updateAt</label>--%>
						<%--</td>--%>
					<%--</tr>--%>
				
				
				<%--<tr>--%>
					<%--<td align="right">--%>
						<%--<label class="Validform_label">--%>
							<%--contentHtml:--%>
						<%--</label>--%>
					<%--</td>--%>
					<%--<td class="value"  colspan="3" >--%>
						  	 <%--<textarea style="width:600px;" class="inputxt" rows="6" id="contentHtml" name="contentHtml"  ignore="ignore" ></textarea>--%>
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
