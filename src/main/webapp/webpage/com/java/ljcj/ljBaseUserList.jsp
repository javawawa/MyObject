<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="ljBaseUserList" checkbox="false" pagination="true" fitColumns="true" title="用户管理" sortName="createAt" sortOrder="desc" actionUrl="ljBaseUserController.do?datagrid" idField="id" fit="true" queryMode="group">
    <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="昵称"  field="nickName" align="center" queryMode="single" query="true"  width="90"></t:dgCol>
    <t:dgCol title="头像"  field="headPic" align="center" formatterjs="showImg"  queryMode="group"  width="80"></t:dgCol>
    <t:dgCol title="手机" align="center" field="loginMobile"  queryMode="single"  width="100"></t:dgCol>
    <t:dgCol title="邮箱"  field="loginEmail" align="center"  queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="类型"  field="type" align="center" dictionary="userType" queryMode="single" query="true"  width="80"></t:dgCol>
    <t:dgCol title="身份证号"  field="idCode"  align="center" queryMode="group"  width="140"></t:dgCol>
    <t:dgCol title="真实姓名"  field="trueName" align="center"  queryMode="group"  width="100"></t:dgCol>
    <t:dgCol title="实名认证"  field="isVerify" dictionary="verifySta"  queryMode="single" query="true" align="center" width="70"></t:dgCol>
    <t:dgCol title="个人签名" align="center" field="sign"  queryMode="group"  width="160"></t:dgCol>
    <t:dgCol title="注册来源"  field="registFrom"  align="center"  width="80"></t:dgCol>
    <t:dgCol title="等级"  field="grade"  queryMode="group"  width="80" align="center"></t:dgCol>
    <t:dgCol title="收藏文章数"  field="favoriteSum"  queryMode="group"  width="80" align="center"></t:dgCol>
    <t:dgCol title="状态"  field="status" dictionary="lhjhStatus"  queryMode="single" query="true" align="center"  width="80"></t:dgCol>
    <t:dgCol title="创建时间"  field="createAt"  formatter="yyyy-MM-dd hh:mm:ss"  queryMode="group" align="center" width="130"></t:dgCol>
    <t:dgCol title="操作" field="opt" width="120" align="center"></t:dgCol>
    <t:dgFunOpt exp="status#eq#0" title="拉黑" funname="changeStatus(id,status)" urlclass="ace_button"  urlfont="fa-lock" />
    <t:dgFunOpt exp="status#eq#1" title="激活" funname="changeStatus(id,status)" urlclass="ace_button"  urlfont="fa-unlock" />
    <t:dgDelOpt title="删除" url="ljBaseUserController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
    <t:dgToolBar title="查看" icon="icon-search" url="ljBaseUserController.do?viewDetails" funname="detail"></t:dgToolBar>

  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">

 function showImg(value, row, index){
     if(value!=""){
         value="http://static.unicoin.top"+value;
         return "<img style='width:44px;height:44px;' border='1' src='"+value+"'/>";
     }
 }

 //修改状态
 function changeStatus(id,status) {
     $.ajax({
         url:"ljBaseUserController.do?changeStatus",
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