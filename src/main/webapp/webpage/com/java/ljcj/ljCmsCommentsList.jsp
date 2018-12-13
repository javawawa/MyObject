<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="ljCmsContentList"  title="评论管理" actionUrl="ljCmsCommentsController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="用户昵称" align="center" field="userNickname"  query="true" queryMode="single" width="40"></t:dgCol>
   <%--<t:dgCol title="用户头像"  field="userHeadPic" formatterjs="showImg" align="center"   width="120"></t:dgCol>--%>
   <t:dgCol title="新闻标题" field="postsTittle" query="true" queryMode="single"  align="center"  width="140"></t:dgCol>
   <t:dgCol title="评论内容" field="content" query="true" queryMode="single"   align="center"  width="150"></t:dgCol>
   <t:dgCol title="创建时间" field="createTime"  align="center"  formatter="yyyy-MM-dd hh:mm:ss"  width="80"></t:dgCol>
   <t:dgCol title="操作" align="center" field="opt" width="80"></t:dgCol>
   <t:dgDelOpt title="删除" url="ljCmsCommentsController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
  </t:datagrid>
  </div>
 </div>
<script>
    function showImg(value, row, index){
        if(value){
            value="http://static.unicoin.top"+value;
            return "<img style='width:44px;height:44px;' border='1' src='"+value+"'/>";
        }
    }
</script>