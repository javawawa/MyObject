<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 1px;">
	<t:datagrid name="actList" title="选择新闻" actionUrl="ljCmsContentController.do?selectDatagrid" fitColumns="true" idField="id" queryMode="group"  checkbox="false" >
		<t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
		<t:dgCol title="文章标题"  field="contentTitle" align="center" queryMode="single" query="true"  width="120"></t:dgCol>
		<t:dgCol title="内容类型" dictionary="contType" queryMode="single" query="true" field="contentType" align="center"  width="100" sortable="false"></t:dgCol>
		<t:dgCol title="创建时间" field="createAt" align="center" formatter="yyyy-MM-dd hh:mm:ss"  width="140" sortable="false"></t:dgCol>
	</t:datagrid>
	</div>
</div>
    <script type="text/javascript">

</script>
