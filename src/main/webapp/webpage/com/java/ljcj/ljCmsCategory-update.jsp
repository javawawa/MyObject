<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>lj_cms_tags</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  $(function(){
      $("#catName").change(function(){
          var catname = $("#catName").val();
          $("#catTitle").val(catname)
      })
  })
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ljCmsCategoryController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${ljCmsCategoryPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								<span style="color: red;">*</span>
								频道名:
							</label>
						</td>
						<td class="value">
						    <input id="catName" name="catName" type="text" maxlength="20" style="width: 150px" class="inputxt"  datatype="*"    value='${ljCmsCategoryPage.catName}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">频道名</label>
						</td>
					</tr>

					<tr>
						<td align="right">
							<span style="color: red;">*</span>
							<label class="Validform_label">
								频道编号:
							</label>
						</td>
						<td class="value">
							<input id="catCode" name="catCode" type="text" maxlength="20" style="width: 150px" class="inputxt"  datatype="*"    value='${ljCmsCategoryPage.catCode}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">频道编号</label>
						</td>
					</tr>

					<tr>
						<td align="right">
							<span style="color: red;">*</span>
							<label class="Validform_label">
								排序:
							</label>
						</td>
						<td class="value">
							<input id="sort" name="sort" type="text" maxlength="10"  class="inputxt" datatype="n1-10" placeholder="数字大的在前" value='${ljCmsCategoryPage.sort}' validType="lj_cms_category,sort,id" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">排序</label>
						</td>
					</tr>

					<tr>
						<td align="right">
							<span style="color: red;">*</span>
							<label class="Validform_label">
								频道标题:
							</label>
						</td>
						<td class="value">
							<input id="catTitle" name="catTitle" type="text" maxlength="20" style="width: 150px" class="inputxt"  datatype="*"    value='${ljCmsCategoryPage.catTitle}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">频道标题</label>
						</td>
					</tr>

					<tr>
						<td align="right">
							<label class="Validform_label">
								频道简介:
							</label>
						</td>
						<td class="value">
							<textarea id="catDesc" cols="80" name="catDesc" type="text" style="width: 250px;height: 100px" class="textarea" datatype="*1-255">${ljCmsCategoryPage.catDesc}</textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">频道简介</label>
						</td>
					</tr>

					<tr>
						<td align="right">
							<span style="color: red;">*</span>
							<label class="Validform_label">
								状态:
							</label>
						</td>
						<td class="value">
							<t:dictSelect field="status" typeGroupCode="catStatus" title="状态" datatype="*" defaultVal="${ljCmsCategoryPage.status}" ></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">状态</label>
						</td>
					</tr>

					<tr>
						<td align="right">
							<span style="color: red;">*</span>
							<label class="Validform_label">
								发布渠道:
							</label>
						</td>
						<td class="value">
							<t:dictSelect field="releaseChannel" typeGroupCode="reChannel" title="发布渠道" datatype="*" defaultVal="${ljCmsCategoryPage.releaseChannel}"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">发布渠道</label>
						</td>
					</tr>

			</table>
		</t:formvalid>
 </body>
