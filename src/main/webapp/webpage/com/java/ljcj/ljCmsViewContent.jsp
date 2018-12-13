<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<body>
<div id="editor">

</div>


<script type="text/javascript">
    var contentHtml = sessionStorage.getItem('contentHtmlView');
    $("#editor").html(contentHtml);
</script>
