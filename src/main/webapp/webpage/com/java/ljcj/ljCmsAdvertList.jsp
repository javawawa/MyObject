<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="ljCmsAdvertList" queryMode="group" fitColumns="false" title="广告配置"  actionUrl="ljCmsAdvertController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="广告位" field="position" queryMode="single" query="true" dictionary="advPosit" align="center"  width="120"></t:dgCol>
   <t:dgCol title="点击类型" field="targetType" queryMode="single" query="false" dictionary="advCtype" align="center"  width="120"></t:dgCol>
   <%--<t:dgCol title="点击目标" field="target"  align="center" width="120"></t:dgCol>--%>
   <t:dgCol title="标题" field="title"  align="center" width="120"></t:dgCol>
   <%--<t:dgCol title="内容" field="content" align="center"  width="120"></t:dgCol>--%>
   <t:dgCol title="PC广告图" field="pic" align="center" width="120" formatterjs="showImg" ></t:dgCol>
   <%--<t:dgCol title="移动广告图" field="picMobile" align="center"  width="120" formatterjs="showImg"></t:dgCol>--%>
   <%--<t:dgCol title="点击量" field="clickQantity" align="center"  width="120"></t:dgCol>--%>
   <t:dgCol title="审核状态" queryMode="single" query="true" dictionary="advStatus"  align="center" field="verifyStatus"   width="120"></t:dgCol>
   <t:dgCol title="状态" queryMode="single" query="false" field="status" dictionary="catStatus" align="center" width="120"></t:dgCol>
   <t:dgCol title="创建时间" field="createAt" formatter="yyyy-MM-dd hh:mm:ss" align="center" width="130"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="130" align="center"></t:dgCol>
   <%--<t:dgFunOpt exp="status#eq#t" title="禁用" funname="changeStatus(id,status)" urlclass="ace_button"  urlfont="fa-lock" />--%>
   <%--<t:dgFunOpt exp="status#eq#f" title="启用" funname="changeStatus(id,status)" urlclass="ace_button"  urlfont="fa-unlock" />--%>
   <t:dgDelOpt title="删除" url="ljCmsAdvertController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="ljCmsAdvertController.do?addorupdate" funname="addAdbert"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="ljCmsAdvertController.do?addorupdate" funname="updateAdvert"></t:dgToolBar>
   <t:dgToolBar title="通过/驳回" icon="icon-redo" url="ljCmsAdvertController.do?goAudit" funname="auditAdvert"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="ljCmsAdvertController.do?viewDetails" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
<script>
    function showImg(value, row, index){
        if(value!=""){
            value="http://static.unicoin.top"+value;
            return "<img style='width:44px;height:44px;' border='1' src='"+value+"'/>";
        }
    }
    //修改状态
    function changeStatus(id,status) {
        $.ajax({
            url:"ljCmsAdvertController.do?changeStatus",
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
    //添加广告
    function addAdbert(){
        $.dialog({
            content: "url:ljCmsAdvertController.do?addorupdate",
            lock : true,
            width:"1000px",
            height:"750px",
            title:"新增广告",
            opacity : 0.3,
            cache:false,
            okVal: '提交审核',
            ok: function () {
                iframe = this.iframe.contentWindow;
                $("#verifyStatus",iframe.document).val("1");
                saveObj();//已封装过的保存方法
                return false;
            },
            cancelVal: '关闭',
            cancel: true,
            button:[{
                name: '保存草稿',
                callback: function(){
                    iframe = this.iframe.contentWindow;//获取弹出层的iframe
                    $("#verifyStatus",iframe.document).val("0");
                    saveObj();//自定义保存数据方法
                    return false;//阻止页面关闭（默认为true不关闭）
                }
            }]
        }).zindex();
    }
    //编辑广告
    function updateAdvert(){
        var rowsData = $('#ljCmsAdvertList').datagrid('getSelections');
        if (!rowsData || rowsData.length==0) {
            tip("请至少选择一条数据进行操作！");
            return;
        }
        var id = rowsData[0].id;
        $.dialog({
            content: "url:ljCmsAdvertController.do?addorupdate&id="+id,
            lock : true,
            width:"1000px",
            height:"750px",
            title:"编辑广告",
            opacity : 0.3,
            cache:false,
            okVal: '提交审核',
            ok: function () {
                iframe = this.iframe.contentWindow;
                $("#verifyStatus",iframe.document).val("1");
                saveObj();//已封装过的保存方法
                return false;
            },
            cancelVal: '关闭',
            cancel: true,
            button:[{
                name: '保存草稿',
                callback: function(){
                    iframe = this.iframe.contentWindow;//获取弹出层的iframe
                    $("#verifyStatus",iframe.document).val("0");
                    saveObj();//自定义保存数据方法
                    return false;//阻止页面关闭（默认为true不关闭）
                }
            }]
        }).zindex();
    }

    //审核
    function auditAdvert(){
        var rowsData = $('#ljCmsAdvertList').datagrid('getSelections');
        if (!rowsData || rowsData.length==0) {
            tip("请至少选择一条数据进行操作！");
            return;
        }
        var id = rowsData[0].id;
        $.dialog({
            content: "url:ljCmsAdvertController.do?goAudit&id="+id,
            lock : true,
            width:"650px",
            height:"400px",
            title:"审核广告",
            opacity : 0.3,
            cache:false,
            okVal: '通过',
            ok: function () {
                iframe = this.iframe.contentWindow;
                var auditFlag = iframe.$("#auditFlag").val();
                var faildMsg = iframe.$("#faildMsg").val();
                if (auditFlag=='false'){
                    tip(faildMsg);
                    return;
                }
                $("#verifyStatus",iframe.document).val("2");
                saveObj();//已封装过的保存方法
                return false;
            },
            cancelVal: '关闭',
            cancel: true,
            button:[{
                name: '驳回',
                callback: function(){
                    iframe = this.iframe.contentWindow;//获取弹出层的iframe
                    $("#verifyStatus",iframe.document).val("3");
                    saveObj();//自定义保存数据方法
                    return false;//阻止页面关闭（默认为true不关闭）
                }
            }]
        }).zindex();
    }
</script>