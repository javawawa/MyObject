<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>用户管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script>

  </script>
 </head>
 <body >
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ljCmsActivityController.do?save">
			<input id="id" name="id" type="hidden" value="${LjBaseUserPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							手机:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="loginMobile" name="loginMobile"    value="${LjBaseUserPage.loginMobile}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							邮箱:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="loginEmail" name="loginEmail"    value="${LjBaseUserPage.loginEmail}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							类型:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="type" datatype="*" typeGroupCode="userType" hasLabel="false" defaultVal="${LjBaseUserPage.type}"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							昵称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="nickName" name="nickName"    value="${LjBaseUserPage.nickName}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							身份证号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="idCode" name="idCode"    value="${LjBaseUserPage.idCode}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							真实姓名:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="trueName" name="nickName"    value="${LjBaseUserPage.trueName}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							实名认证:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="isVerify" datatype="*" typeGroupCode="verifySta" hasLabel="false" defaultVal="${LjBaseUserPage.isVerify}"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							个人签名:
						</label>
					</td>
					<td class="value">
						<textarea id="sign" cols="80"
								  name="sign" type="text"
								  style="width: 300px;height: 100px" class="sign" >
								${LjBaseUserPage.sign}
						</textarea>
						<span class="Validform_checktip"></span>
					</td>
				</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							注册来源:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="registFrom" name="registFrom"    value="${LjBaseUserPage.registFrom}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							等级:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="grade" name="grade"    value="${LjBaseUserPage.grade}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							收藏文章数:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="favoriteSum" name="favoriteSum"    value="${LjBaseUserPage.favoriteSum}" />
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
						<t:dictSelect field="status" datatype="*" typeGroupCode="qjStatus" hasLabel="false" defaultVal="${LjBaseUserPage.status}"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							创建时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="createAt" name="createAt"     value="<fmt:formatDate value='${LjBaseUserPage.createAt}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>"  />
						<span class="Validform_checktip"></span>
					</td>
				</tr>


			</table>
		</t:formvalid>
 </body>
