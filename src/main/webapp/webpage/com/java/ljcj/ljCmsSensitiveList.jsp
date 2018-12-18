<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="ljCmsSensitiveList" title="敏感词库管理" actionUrl="ljCmsSensitiveController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="敏感词名称" field="sensitiveName" query="true" queryMode="single" align="center"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="140" align="center"></t:dgCol>
   <t:dgDelOpt title="删除" url="ljCmsSensitiveController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="ljCmsSensitiveController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="ljCmsSensitiveController.do?addorupdate" funname="update"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
<script>

</script>