<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="ljCmsNoticeList" title="公告管理" actionUrl="ljCmsNoticeController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="标题" field="tittle" query="true" queryMode="single" align="center"  width="120"></t:dgCol>
   <t:dgCol title="内容" field="content" hidden="true"  width="120" align="center"></t:dgCol>
   <t:dgCol title="是否启用" field="status" query="true" queryMode="single" dictionary="qjStatus" align="center" width="120"></t:dgCol>
   <t:dgCol title="审核状态" field="auditStatus" query="true" queryMode="single" dictionary="notiStatus" align="center" width="120"></t:dgCol>
   <t:dgCol title="类型 " field="type" query="true" queryMode="single"  width="120" align="center" dictionary="noticeType"></t:dgCol>
   <%--<t:dgCol title="修改人" field="updateUser"   width="120"></t:dgCol>--%>
   <t:dgCol title="创建时间" field="createAt" align="center" formatter="yyyy-MM-dd hh:mm:ss"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100" align="center"></t:dgCol>
   <t:dgFunOpt exp="status#eq#1" title="禁用" funname="changeStatus(id,status)" urlclass="ace_button"  urlfont="fa-lock" />
   <t:dgFunOpt exp="status#eq#0" title="启用" funname="changeStatus(id,status)" urlclass="ace_button"  urlfont="fa-unlock" />
   <t:dgDelOpt title="删除" url="ljCmsNoticeController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="ljCmsNoticeController.do?addorupdate" funname="addNotice"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="ljCmsNoticeController.do?addorupdate" funname="updateNotice"></t:dgToolBar>
   <t:dgToolBar title="通过/驳回" icon="icon-redo" url="ljCmsNoticeController.do?goAudit" funname="auditNotice"></t:dgToolBar>
   <%--<t:dgToolBar title="查看" icon="icon-search" url="ljCmsNoticeController.do?addorupdate" funname="detail"></t:dgToolBar>--%>
   <t:dgToolBar title="预览" icon="icon-search" url="" funname="viewContent"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
<script>

    //修改状态
    function changeStatus(id,status) {
        $.ajax({
            url:"ljCmsNoticeController.do?changeStatus",
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

    function viewContent(){
        var rowsData = $('#ljCmsNoticeList').datagrid('getSelections');
        if (!rowsData || rowsData.length==0) {
            tip("请至少选择一条数据进行预览！");
            return;
        }
        var content = rowsData[0].content;
        sessionStorage.setItem('contentHtmlView', content);
        createdetailwindow('内容预览', 'ljCmsContentAuditController.do?viewContent' ,"1000px","800px");
    }

    //添加公告
    function addNotice(){
        $.dialog({
            content: "url:ljCmsNoticeController.do?addorupdate",
            lock : true,
            width:"650px",
            height:"400px",
            title:"新增公告",
            opacity : 0.3,
            cache:false,
            okVal: '提交审核',
            ok: function () {
                iframe = this.iframe.contentWindow;
                $("#auditStatus",iframe.document).val("a");
                saveObj();//已封装过的保存方法
                return false;
            },
            cancelVal: '关闭',
            cancel: true,
            button:[{
                name: '保存草稿',
                callback: function(){
                    iframe = this.iframe.contentWindow;//获取弹出层的iframe
                    $("#auditStatus",iframe.document).val("0");
                    saveObj();//自定义保存数据方法
                    return false;//阻止页面关闭（默认为true不关闭）
                }
            }]
        }).zindex();
    }
    //编辑公告
    function updateNotice(){
        var rowsData = $('#ljCmsNoticeList').datagrid('getSelections');
        if (!rowsData || rowsData.length==0) {
            tip("请至少选择一条数据进行操作！");
            return;
        }
        var id = rowsData[0].id;
        $.dialog({
            content: "url:ljCmsNoticeController.do?addorupdate&id="+id,
            lock : true,
            width:"650px",
            height:"400px",
            title:"编辑公告",
            opacity : 0.3,
            cache:false,
            okVal: '提交审核',
            ok: function () {
                iframe = this.iframe.contentWindow;
                $("#auditStatus",iframe.document).val("a");
                saveObj();//已封装过的保存方法
                return false;
            },
            cancelVal: '关闭',
            cancel: true,
            button:[{
                name: '保存草稿',
                callback: function(){
                    iframe = this.iframe.contentWindow;//获取弹出层的iframe
                    $("#auditStatus",iframe.document).val("0");
                    saveObj();//自定义保存数据方法
                    return false;//阻止页面关闭（默认为true不关闭）
                }
            }]
        }).zindex();
    }

    //审核公告
    function auditNotice(){
        var rowsData = $('#ljCmsNoticeList').datagrid('getSelections');
        if (!rowsData || rowsData.length==0) {
            tip("请至少选择一条数据进行操作！");
            return;
        }
        var auditStatus = rowsData[0].auditStatus;
        var id = rowsData[0].id;
        $.dialog({
            content: "url:ljCmsNoticeController.do?goAudit&id="+id,
            lock : true,
            width:"650px",
            height:"400px",
            title:"审核活动",
            opacity : 0.3,
            cache:false,
            okVal: '通过',
            ok: function () {
                if(auditStatus == 't'){
                    tip("该公告已经发布，无需通过审核!");
                    return;
                }
                iframe = this.iframe.contentWindow;
                $("#auditStatus",iframe.document).val("t");
                saveObj();//已封装过的保存方法
                return false;
            },
            cancelVal: '关闭',
            cancel: true,
            button:[{
                name: '驳回',
                callback: function(){
                    iframe = this.iframe.contentWindow;//获取弹出层的iframe
                    $("#auditStatus",iframe.document).val("1");
                    saveObj();//自定义保存数据方法
                    return false;//阻止页面关闭（默认为true不关闭）
                }
            }]
        }).zindex();
    }

</script>