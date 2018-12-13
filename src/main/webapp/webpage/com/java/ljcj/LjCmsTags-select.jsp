<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding: 1px;">
	<t:datagrid name="tagList" title="选择标签" actionUrl="ljCmsTagsController.do?datagrid" fitColumns="true" idField="id" queryMode="group"  checkbox="true"  onLoadSuccess="initCheck">
		<%--<t:dgCol title="common.code" field="id" hidden="true"></t:dgCol>--%>
		<%--<t:dgCol title="common.username" field="userName" query="true" width="100"></t:dgCol>--%>
		<t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
		<t:dgCol title="标签名"  field="name" align="center" queryMode="single" query="true"  width="80"></t:dgCol>
		<t:dgCol title="标签类型"  field="type" align="center" dictionary="tagType"  queryMode="single" query="true"  width="120"></t:dgCol>
	</t:datagrid>
	</div>
</div>
<script type="text/javascript">
	$(function(){
        $("input[value='确定']").click(
	        function (){
                alert(1232);
			}
		);
		// alert( $("input[value='确定']").val());
	})
  	function getUserId(){
  		var rowsData = $("#tagList").datagrid("getChecked");
  		var id = "";
  		if(rowsData.length==0){
  			tip("你没有选择标签!");
  			return "";
  		}
  		debugger;
  		if(rowsData.length <= 3){
            return rowsData[0].id;
        }else{
            tip("最多只能选择三个标签!");
		}
  	}
  	function getSelesctTab(){
		alert(123);
	}
    function initCheck(data){
        var ids = "${ids}";
        var idArr = ids.split(",");
        for(var i=0;i<idArr.length;i++){
            if(idArr[i]!=""){
                $("#tagList").datagrid("getChecked",idArr[i]);
            }
        }
    }

</script>
