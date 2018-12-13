<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
   <t:datagrid name="ljCmsAuthorList"  checkbox="false" pagination="true" fitColumns="true" title="作者管理" sortName="id" actionUrl="ljCmsAuthorController.do?mainDatagrid" idField="id" fit="true" queryMode="group">
       <t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
       <t:dgCol title="uid"  field="uid"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
       <t:dgCol title="真实姓名"  field="trueName" align="center"  queryMode="group"  width="100"></t:dgCol>
       <t:dgCol title="昵称"  field="nickName"  align="center" queryMode="single" query="true"  width="80"></t:dgCol>
       <t:dgCol title="手机" align="center" field="loginMobile"  queryMode="single"  width="100"></t:dgCol>
       <t:dgCol title="类型"  field="type" align="center" dictionary="autType"  queryMode="single" query="false"  width="120"></t:dgCol>
       <t:dgCol title="标签"  field="label" align="center"  queryMode="group"  width="160"></t:dgCol>
       <t:dgCol title="文章数"  field="worksSum" align="center"  queryMode="single"   width="120"></t:dgCol>
       <t:dgCol title="阅读数"  field="clicksSum" align="center"  queryMode="single"   width="120"></t:dgCol>
       <t:dgCol title="状态"  field="status" align="center" dictionary="catStatus"  queryMode="single" query="false"  width="120"></t:dgCol>
       <t:dgCol title="实名认证"  field="isVerify" dictionary="verifySta"  queryMode="single" query="true" align="center" width="70"></t:dgCol>
       <t:dgCol title="创建时间"  field="createAt" align="center"  formatter="yyyy-MM-dd hh:mm:ss"  queryMode="group"  width="120"></t:dgCol>
       <t:dgCol title="操作" field="opt" width="80" align="center"></t:dgCol>
       <t:dgFunOpt exp="status#eq#t" title="停用" funname="changeStatus(id,status)" urlclass="ace_button"  urlfont="fa-lock" />
       <t:dgFunOpt exp="status#eq#f" title="启用" funname="changeStatus(id,status)" urlclass="ace_button"  urlfont="fa-unlock" />
       <%--<t:dgDelOpt title="删除" url="ljCmsAuthorController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>--%>
     <%--<t:dgToolBar title="录入" icon="icon-add" url="ljCmsAuthorController.do?addorupdate" funname="add"></t:dgToolBar>--%>
       <t:dgToolBar title="编辑" icon="icon-edit" url="ljCmsAuthorController.do?addorupdate" funname="update"></t:dgToolBar>
       <t:dgToolBar title="查看" icon="icon-search" url="ljCmsAuthorController.do?addorupdate" funname="detail"></t:dgToolBar>
       <t:dgToolBar title="通过"  icon="icon-redo" url="ljCmsAuthorController.do?goCertification" funname="certification"></t:dgToolBar>
       <t:dgToolBar title="驳回"  icon="icon-undo" url="ljCmsAuthorController.do?reject" funname="reject"></t:dgToolBar>
   </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
     function detail(title,url, id,width,height) {
         var rowsData = $('#'+id).datagrid('getSelections');
         if (!rowsData || rowsData.length == 0) {
             tip($.i18n.prop('read.selectItem'));
             return;
         }
         if (rowsData.length > 1) {
             tip($.i18n.prop('read.selectOneItem'));
             return;
         }
         var id = rowsData[0].id;
         if(id==""){
             tip("请先通过实名认证!");
             return;
         }
         url += '&load=detail&id='+rowsData[0].id;
         createdetailwindow(title,url,width,height);
     }

     function update(title,url, id,width,height){
         var rowsData = $('#ljCmsAuthorList').datagrid('getSelections');
         if (!rowsData || rowsData.length==0) {
             tip("请至少选择一条数据进行操作！");
             return;
         }
         var id = rowsData[0].id;
         if(id==""){
             tip("请先通过实名认证!");
             return;
         }
         $.dialog({
             content: "url:ljCmsAuthorController.do?addorupdate&id="+id,
             lock : true,
             width:"650px",
             height:"400px",
             title:"修改作者",
             opacity : 0.3,
             cache:false,
             okVal: '提交',
             ok: function () {
                 iframe = this.iframe.contentWindow;
                 // $("#auditType",iframe.document).val("t");
                 saveObj();//已封装过的保存方法
                 return false;
             },
             cancelVal: '关闭',
             cancel: true,

         }).zindex();
     }


     //修改状态
     function changeStatus(id,status) {
         $.ajax({
             url:"ljCmsAuthorController.do?changeStatus",
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
     //实名认证 页面不展示否的数据 1、审核通过 修改用户状态 添加作者信息
     function certification(title,url,gname) {
         gridname=gname;
         var rows = $("#"+gname).datagrid('getSelections');
         if(rows.length == 1){
             var id = rows[0].uid;
             var isVerify = rows[0].isVerify;
             if (isVerify == '2') {
                 $.dialog({
                     content: "url:ljCmsAuthorController.do?goCertification&id="+id,
                     lock : true,
                     width:"650px",
                     height:"400px",
                     title:"认证通过",
                     opacity : 0.3,
                     cache:false,
                     okVal: '提交',
                     ok: function () {
                         iframe = this.iframe.contentWindow;
                         $("#isVerify",iframe.document).val("1");
                         saveObj();//已封装过的保存方法
                         return false;
                     },
                     cancelVal: '关闭',
                     cancel: true,

                 }).zindex();

             } else {
                 tip('只能通过待审核的数据!');
             }
         } else {
             tip("请选择一条数据进行实名认证！");
         }
     }

     //  2、驳回 修改审核状态(只能驳回待审核的)
     function reject(title,url,gname){
         var rowsData = $("#"+gname).datagrid('getSelections');
         if (!rowsData || rowsData.length==0) {
             tip("请至少选择一条数据进行驳回！");
             return;
         }
         var isVerify = rowsData[0].isVerify;
         if(isVerify!="2"){
             tip("只能驳回待审核的数据!");
             return;
         }
         var id = rowsData[0].uid;
         $.dialog({
             content: "url:ljCmsAuthorController.do?goReject&id="+id,
             lock : true,
             width:"650px",
             height:"400px",
             title:"认证驳回",
             opacity : 0.3,
             cache:false,
             okVal: '提交',
             ok: function () {
                 iframe = this.iframe.contentWindow;
                 $("#isVerify",iframe.document).val("3");
                 saveObj();//已封装过的保存方法
                 return false;
             },
             cancelVal: '关闭',
             cancel: true,

         }).zindex();
     }
 </script>