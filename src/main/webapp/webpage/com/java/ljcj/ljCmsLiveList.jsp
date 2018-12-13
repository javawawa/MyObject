<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script type="text/javascript" src="plug-in/share/js/html2canvas.min.js"></script>
<link rel="stylesheet" type="text/css" href="plug-in/share/css/share.css"/>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="ljCmsLiveList" checkbox="false" pagination="true" fitColumns="true" title="快讯管理" sortName="liveTime" sortOrder="desc" actionUrl="ljCmsLiveController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <%--<t:dgCol title="源数据id" align="center" field="sourceId"  queryMode="group"  width="70"></t:dgCol>--%>
   <%--<t:dgCol title="标签"  field="label"  queryMode="group"  width="120"></t:dgCol>--%>
   <t:dgCol title="标题"  field="title" align="center"  queryMode="single" query="true"  width="160"></t:dgCol>
   <t:dgCol title="内容"  field="content" align="center" hidden="true" queryMode="group"  width="240"></t:dgCol>
   <t:dgCol title="来源"  field="source" dictionary="liveSource" align="center" queryMode="single" query="true"  width="70"></t:dgCol>
   <%--<t:dgCol title="富文本内容"  field="contentHtml"  queryMode="group"  width="120"></t:dgCol>--%>
   <%--<t:dgCol title="是否标红"  field="isImportant" dictionary="sfNum"  queryMode="single" query="false" align="center" width="70"></t:dgCol>--%>
   <%--<t:dgCol title="是否点评"  field="isComment" dictionary="sfNum" queryMode="single" query="false" align="center"  width="70"></t:dgCol>--%>
   <%--<t:dgCol title="点评内容"  field="commentContent"  queryMode="group"  width="240" align="center"></t:dgCol>--%>
   <t:dgCol title="状态"  field="status" dictionary="liveStatus"  queryMode="single" query="true" align="center"  width="70"></t:dgCol>
   <%--<t:dgCol title="点击数"  field="stat"  queryMode="group"  width="120"></t:dgCol>--%>
   <t:dgCol title="快讯时间"  field="liveTime"  formatter="yyyy-MM-dd hh:mm:ss"  queryMode="group" align="center" width="130"></t:dgCol>
   <%--<t:dgCol title="创建人id"  field="createBy"  queryMode="group"  width="120"></t:dgCol>--%>
   <%--<t:dgCol title="更新人id"  field="updateBy"  queryMode="group"  width="120"></t:dgCol>--%>
   <%--<t:dgCol title="createAt"  field="createAt"  formatter="yyyy-MM-dd"  queryMode="group"  width="120"></t:dgCol>--%>
   <%--<t:dgCol title="updateAt"  field="updateAt"  formatter="yyyy-MM-dd"  queryMode="group"  width="120"></t:dgCol>--%>
   <t:dgCol title="操作" field="opt" width="110" align="center"></t:dgCol>
   <t:dgToolBar title="录入" icon="icon-add" url="ljCmsLiveController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="ljCmsLiveController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="ljCmsLiveController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="重要"  icon="icon-print" url="ljCmsLiveController.do?redLive" funname="commonChange"></t:dgToolBar>
   <t:dgFunOpt title="点评"  urlclass="ace_button"  urlfont="fa-bullhorn"  funname="remarkLive(id)"></t:dgFunOpt>
   <t:dgToolBar title="发布"  icon="icon-redo" url="ljCmsLiveController.do?publishLive" funname="publishLive"></t:dgToolBar>
   <t:dgToolBar title="下架"  icon="icon-undo" url="ljCmsLiveController.do?soldoutLive" funname="commonChange"></t:dgToolBar>
   <t:dgDelOpt title="删除" url="ljCmsLiveController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <%--<t:dgToolBar title="标签设置"  icon="icon-put" url="ljCmsLiveController.do?livetagSet" funname="livetagSet"></t:dgToolBar>--%>
  </t:datagrid>
  </div>
  <div id="img">

  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){

 });

 function remarkLive(id){
     var rows = $("#ljCmsLiveList").datagrid('getSelections');
     if (rows.length == 1) {
         var url ='ljCmsLiveController.do?goRemarkLive&id='+id;
         createwindow('点评快讯', url ,"760px","700px");
     }else{
         tip("只能选择一条数据进行操作!");
     }

 }

 //发布
 function publishLive(title,url,gname) {
     var rows = $("#"+gname).datagrid('getSelections');
     if (rows.length == 1) {
             var id = rows[0].id;
             <%--var time = rows[0].liveTime;--%>
             <%--time = time.substr(0, 19);--%>
             <%--var title = rows[0].title;--%>
             <%--var content = rows[0].content;--%>
             <%--var shareurl="";--%>
             <%--$("#img").css('display','block');--%>
             <%--$("#img").html(" <img src='plug-in/share/img/shareHeader.png' alt=''> " +--%>
                 <%--"<div class='share_content'> <div class='share_header'> <img src='plug-in/share/img/timeIcon.png' alt=''>" +--%>
                 <%--" <span>"+time+"</span> </div> <span>"+title+"</span> <article>"+content+"</article> </div> <div class='share_footer'>" +--%>
                 <%--" <div> <img src='plug-in/share/img/shareCode.png' alt=''>  </div> </div> ")--%>
             <%--setTimeout(function(){--%>
                 <%--html2canvas(document.getElementById("img")).then(canvas => {--%>
                     <%--console.log('canvas',canvas.toDataURL())--%>
                 <%--$("#img").html(`<img src="${canvas.toDataURL()}">`);--%>
                 <%--shareurl=canvas.toDataURL();--%>
                 <%--$("#img").html("");--%>
                 $.ajax({
                     url : "ljCmsLiveController.do?publishLive",
                     type : 'post',
                     data: {
                         id: id
                     },
                     cache : false,
                     success : function(data) {
                         var d = $.parseJSON(data);
                         if (d.success) {
                             var msg = d.msg;
                             tip(msg);
                             reloadTable();
                             $("#"+gname).datagrid('unselectAll');
                         }
                     }
                 });
             // });
             // },100);
     } else {
         tip("只能选择一条数据进行操作！");
     }
 }


 //通用js 修改状态 标红 下架
 function commonChange(title,url,gname) {
     gridname=gname;
     var rows = $("#"+gname).datagrid('getSelections');
     var id = rows[0].id;
     if (rows.length == 1) {
         $.ajax({
             url : url,
             type : 'post',
             data : {
                 id : id
             },
             cache : false,
             success : function(data) {
                 var d = $.parseJSON(data);
                 if (d.success) {
                     var msg = d.msg;
                     tip(msg);
                     reloadTable();
                     $("#"+gname).datagrid('unselectAll');
                 }
             }
         });
     } else {
         tip("只能选择一条数据进行操作！");
     }
     // gridname=gname;
     // var ids = [];
     // var rows = $("#"+gname).datagrid('getSelections');
     // if (rows.length > 0) {
     //      for ( var i = 0; i < rows.length; i++) {
     //          ids.push(rows[i].id);
     //      }
     //      $.ajax({
     //          url : url,
     //          type : 'post',
     //          data : {
     //              ids : ids.join(',')
     //          },
     //          cache : false,
     //          success : function(data) {
     //              var d = $.parseJSON(data);
     //              if (d.success) {
     //                  var msg = d.msg;
     //                  tip(msg);
     //                  reloadTable();
     //                  $("#"+gname).datagrid('unselectAll');
     //                  ids='';
     //              }
     //          }
     //      });
     // } else {
     //     tip("请至少选择一条数据进行操作！");
     // }
 }





 </script>