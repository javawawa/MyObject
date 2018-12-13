<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 1px;">
	<t:datagrid name="actList" title="选择活动" actionUrl="ljCmsActivityController.do?selectDatagrid" fitColumns="true" idField="id" queryMode="group"  checkbox="false"  onLoadSuccess="initCheck">
		<t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
		<t:dgCol title="活动标题"  field="activityTitle" align="center" queryMode="single" query="true"  width="120"></t:dgCol>
		<t:dgCol title="活动类型" dictionary="actType" queryMode="single" query="true" field="activityType" align="center"  width="100" sortable="false"></t:dgCol>
		<t:dgCol title="活动时间" field="activityTime" align="center" formatter="yyyy-MM-dd hh:mm:ss"  width="140" sortable="false"></t:dgCol>
	</t:datagrid>
	</div>
</div>
    <script type="text/javascript">
    $(function(){

        // alert( $("input[value='确定']").val());
    })

    function initCheck(data){
        <%--var ids = "${ids}";--%>
        <%--var idArr = ids.split(",");--%>
        <%--for(var i=0;i<idArr.length;i++){--%>
            <%--if(idArr[i]!=""){--%>
                <%--$("#tagList").datagrid("getChecked",idArr[i]);--%>
            <%--}--%>
        <%--}--%>
    }

</script>
