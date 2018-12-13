<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>lj_cms_tags</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ljCmsTagsController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${ljCmsTagsPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<span style="color: red;">*</span>
						<label class="Validform_label">
							标签名:
						</label>
					</td>
					<td class="value">
						<input id="name" name="name" type="text" maxlength="20" style="width: 150px" class="inputxt"  datatype="*" placeholder="最多输入10个字符"  validType="lj_cms_tags,name,id"  />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">标签名</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							标签描述:
						</label>
					</td>
					<td class="value">
						<textarea id="description" cols="80" name="description" type="text" placeholder="请输入1到255位字符" ignore="checked" style="width: 250px;height: 100px" class="textarea" ></textarea>
						<%--<input id="description" name="description" type="text" maxlength="255" style="width: 150px" class="inputxt" datatype="*"  ignore="ignore" />--%>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">标签描述</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span style="color: red;">*</span>
						<label class="Validform_label">
							标签类型:
						</label>
					</td>
					<td class="value">
						<%--<input id="type" name="type" type="text" maxlength="2" style="width: 150px" class="inputxt"  datatype="*"  ignore="checked" />--%>
						<t:dictSelect field="type" typeGroupCode="tagType" title="标签类型" datatype="*" defaultVal="1"></t:dictSelect>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">标签类型</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span style="color: red;">*</span>
						<label class="Validform_label">
							控制状态:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="status" typeGroupCode="tagStatus" title="控制状态" datatype="*" defaultVal="t"></t:dictSelect>
						 <%--<input id="status" name="status" type="text" maxlength="1" style="width: 150px" class="inputxt"  ignore="ignore" />--%>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">控制状态</label>
					</td>
				</tr>
				<%--<tr>--%>
					<%--<td align="right">--%>
						<%--<label class="Validform_label">--%>
							<%--createAt:--%>
						<%--</label>--%>
					<%--</td>--%>
					<%--<td class="value">--%>
							   <%--<input id="createAt" name="createAt" type="text" style="width: 150px" class="Wdate" onClick="WdatePicker()"  datatype="*"  ignore="checked" />    --%>
							<%--<span class="Validform_checktip"></span>--%>
							<%--<label class="Validform_label" style="display: none;">createAt</label>--%>
						<%--</td>--%>
				<%--</tr>--%>
				<%--<tr>--%>
					<%--<td align="right">--%>
						<%--<label class="Validform_label">--%>
							<%--createBy:--%>
						<%--</label>--%>
					<%--</td>--%>
					<%--<td class="value">--%>
					     	 <%--<input id="createBy" name="createBy" type="text" maxlength="10" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore" />--%>
							<%--<span class="Validform_checktip"></span>--%>
							<%--<label class="Validform_label" style="display: none;">createBy</label>--%>
						<%--</td>--%>
				<%--</tr>--%>
				<%--<tr>--%>
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
							<%--updateBy:--%>
						<%--</label>--%>
					<%--</td>--%>
					<%--<td class="value">--%>
					     	 <%--<input id="updateBy" name="updateBy" type="text" maxlength="10" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore" />--%>
							<%--<span class="Validform_checktip"></span>--%>
							<%--<label class="Validform_label" style="display: none;">updateBy</label>--%>
						<%--</td>--%>
				<%--</tr>--%>
				<%--<tr>--%>
					<%--<td align="right">--%>
						<%--<label class="Validform_label">--%>
							<%--deleted:--%>
						<%--</label>--%>
					<%--</td>--%>
					<%--<td class="value">--%>
					     	 <%--<input id="deleted" name="deleted" type="text" maxlength="3" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore" />--%>
							<%--<span class="Validform_checktip"></span>--%>
							<%--<label class="Validform_label" style="display: none;">deleted</label>--%>
						<%--</td>--%>
				<%--</tr>--%>
				<%--<tr>--%>
				<%--<td align="right">--%>
				<%--<label class="Validform_label">--%>
				<%--使用次数:--%>
				<%--</label>--%>
				<%--</td>--%>
				<%--<td class="value">--%>
				<%--<input id="stat" name="stat" type="text" maxlength="10" style="width: 150px" class="inputxt"  datatype="n"  ignore="checked" />--%>
				<%--<span class="Validform_checktip"></span>--%>
				<%--<label class="Validform_label" style="display: none;">使用次数</label>--%>
				<%--</td>--%>
				<%--</tr>--%>
				
			</table>
		</t:formvalid>
 </body>
