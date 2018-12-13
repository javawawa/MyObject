<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="ljCmsCategoryList" fitColumns="true"  title="频道管理" actionUrl="ljCmsCategoryController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="频道名" field="catName" align="center" query="true" queryMode="single"   width="100"></t:dgCol>
   <t:dgCol title="频道编号" field="catCode" query="true" queryMode="single" align="center"  width="80"></t:dgCol>
   <t:dgCol title="频道标题" field="catTitle" align="center"  width="140"></t:dgCol>
   <%--<t:dgCol title="频道简介" field="catDesc" align="center"  width="240"></t:dgCol>--%>
   <%--<t:dgCol title="catPic" field="catPic"   width="120"></t:dgCol>--%>
   <%--<t:dgCol title="父频道ID" field="parentId"   width="120"></t:dgCol>--%>
   <t:dgCol title="状态" field="status" dictionary="catStatus" query="true" queryMode="single" align="center"  width="80"></t:dgCol>
   <t:dgCol title="发布渠道" field="releaseChannel" dictionary="reChannel" align="center" query="true" queryMode="single"   width="100"></t:dgCol>
   <t:dgCol title="排序" align="center" field="sort"   width="120"></t:dgCol>
   <t:dgFunOpt exp="status#eq#t" title="停用" funname="changeStatus(id,status)" urlclass="ace_button"  urlfont="fa-lock" />
   <t:dgFunOpt exp="status#eq#f" title="启用" funname="changeStatus(id,status)" urlclass="ace_button"  urlfont="fa-unlock" />
      <%--<t:dgCol title="path" field="path"   width="120"></t:dgCol>--%>
   <%--<t:dgCol title="depth" field="depth"   width="120"></t:dgCol>--%>
   <%--<t:dgCol title="级别" field="level"   width="120"></t:dgCol>--%>
   <%--<t:dgCol title="是否叶子结点" field="isLeaf"   width="120"></t:dgCol>--%>
   <%--<t:dgCol title="是否已被删除" field="deleted"   width="120"></t:dgCol>--%>
   <t:dgCol title="创建时间" field="createAt" formatter="yyyy-MM-dd hh:mm:ss" align="center" width="130"></t:dgCol>
   <%--<t:dgCol title="更新时间" field="updateAt" formatter="yyyy-MM-dd hh:mm:ss"  width="120"></t:dgCol>--%>
   <t:dgCol title="操作" field="opt" width="140" align="center"></t:dgCol>
   <t:dgDelOpt title="删除" url="ljCmsCategoryController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="ljCmsCategoryController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="ljCmsCategoryController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="ljCmsCategoryController.do?goUpdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
<script type="text/javascript">
    //修改状态
    function changeStatus(id,status) {
        $.ajax({
            url:"ljCmsCategoryController.do?changeStatus",
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