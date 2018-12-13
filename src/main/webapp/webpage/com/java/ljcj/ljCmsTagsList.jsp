<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
   <t:datagrid name="ljCmsTagsList" checkbox="false" pagination="true" fitColumns="true" title="标签管理" sortName="id" actionUrl="ljCmsTagsController.do?datagrid" idField="id" fit="true" queryMode="group">
     <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
     <t:dgCol title="标签名"  field="name" align="center" queryMode="single" query="true"  width="80"></t:dgCol>
     <t:dgCol title="标签描述"  field="description" align="center"  queryMode="group"  width="160"></t:dgCol>
     <t:dgCol title="标签类型"  field="type" align="center" dictionary="tagType"  queryMode="single" query="true"  width="120"></t:dgCol>
     <t:dgCol title="使用次数"  field="stat" align="center"  queryMode="single"   width="120"></t:dgCol>
     <t:dgCol title="状态"  field="status" align="center" dictionary="tagStatus"  queryMode="group"  width="120"></t:dgCol>
     <t:dgCol title="创建时间"  field="createAt" align="center"  formatter="yyyy-MM-dd hh:mm:ss"  queryMode="group"  width="120"></t:dgCol>
     <t:dgCol title="创建人"  field="createBy" align="center" dictionary="t_s_base_user,id,realname" queryMode="group"  width="120"></t:dgCol>
     <%--<t:dgCol title="修改时间"  field="updateAt"  formatter="yyyy-MM-dd"  queryMode="group"  width="120"></t:dgCol>--%>
     <%--<t:dgCol title="修改人"  field="updateBy" dictionary="t_s_base_user,id,realname"  queryMode="group"  width="120"></t:dgCol>--%>
     <t:dgCol title="操作" field="opt" width="100" align="center"></t:dgCol>
     <t:dgFunOpt exp="status#eq#t" title="禁用" funname="changeStatus(id,status)" urlclass="ace_button"  urlfont="fa-lock" />
     <t:dgFunOpt exp="status#eq#f" title="启用" funname="changeStatus(id,status)" urlclass="ace_button"  urlfont="fa-unlock" />
     <t:dgDelOpt title="删除" url="ljCmsTagsController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
     <t:dgToolBar title="录入" icon="icon-add" url="ljCmsTagsController.do?goAdd" funname="add"></t:dgToolBar>
     <t:dgToolBar title="编辑" icon="icon-edit" url="ljCmsTagsController.do?goUpdate" funname="update"></t:dgToolBar>
     <t:dgToolBar title="查看" icon="icon-search" url="ljCmsTagsController.do?goUpdate" funname="detail"></t:dgToolBar>
   </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){

 });

 //修改状态
 function changeStatus(id,status) {
     $.ajax({
         url:"ljCmsTagsController.do?changeStatus",
         type:"post",
         data:{id:id,status:status},
         dataType:"json",
         success:function(data){
             tip(data.msg);
             if(data.success){
                 reloadTable();
             }
         }
     })
 }
 </script>