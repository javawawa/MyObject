<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="ljCmsAuditContentList" fitColumns="true" title="点击点赞统计" actionUrl="ljCmsContentController.do?reportDatagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" align="center" hidden="false" width="40"></t:dgCol>
   <t:dgCol title="内容标题" align="center" field="contentTitle"  query="true" queryMode="single" sortable="false" width="150"></t:dgCol>
   <t:dgCol title="点击数" field="stat"  align="center" formatterjs="formatStat" width="60"></t:dgCol>
   <t:dgCol title="点赞数" field="thumbup" align="center"  width="60"></t:dgCol>
  </t:datagrid>
  </div>
 </div>
<script>
    function formatStat(value, row, index){
        if(value!=""){
            var result=0;
            debugger;
            if(value>567){
                result = value / 567;
            }
            return result;
        }
    }


</script>