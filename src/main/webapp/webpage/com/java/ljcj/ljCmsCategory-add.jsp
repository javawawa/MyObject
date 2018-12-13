<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>lj_cms_live</title>
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
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ljCmsCategoryController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${ljCmscategoryPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">

				<tr>
					<td align="right">
						<span style="color: red;">*</span>
						<label class="Validform_label">
							频道名:
						</label>
					</td>
					<td class="value">
						 <input id="catName" name="catName" type="text" maxlength="20" style="width: 256px" class="inputxt" datatype="*" placeholder="最多输入十个汉字"  validType="lj_cms_category,cat_name,id" />
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
						<input id="catCode" name="catCode" type="text" maxlength="10" style="width: 256px" class="inputxt" datatype="*" placeholder="请输入三位数字,例110" validType="lj_cms_category,cat_code,id" />
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
						<input id="sort" name="sort" type="text" maxlength="10" style="width: 256px" class="inputxt" datatype="n1-10" placeholder="数字大的在前"  validType="lj_cms_category,sort,id" />
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
						<input id="catTitle" name="catTitle" type="text" maxlength="100" style="width: 256px" placeholder="最多输入50个字符" class="inputxt" datatype="*"  />
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
						<textarea id="catDesc" cols="80" name="catDesc" type="text" placeholder="请输入1到255位字符" ignore="ignore" style="width: 250px;height: 100px" class="textarea" datatype="*1-255"></textarea><span class="Validform_checktip"></span><label class="Validform_label" style="display: none;">频道简介</label>
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
						<t:dictSelect field="status" typeGroupCode="catStatus" title="状态" datatype="*" defaultVal="t" ></t:dictSelect>
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
						<t:dictSelect field="releaseChannel" typeGroupCode="reChannel" title="发布渠道" datatype="*" defaultVal="1"></t:dictSelect>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">发布渠道</label>
					</td>
				</tr>

			</table>
		</t:formvalid>
 </body>
