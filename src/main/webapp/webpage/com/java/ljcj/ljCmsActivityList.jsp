<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="ljCmsActivityList" title="活动管理" actionUrl="ljCmsActivityController.do?datagrid" idField="id" fit="true" fitColumns="false" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="活动标题" field="activityTitle" queryMode="single" query="true" align="center" width="140" sortable="false"></t:dgCol>
   <t:dgCol title="活动图片" field="activityPic" align="center" formatterjs="showImg"  width="120" sortable="false"></t:dgCol>
   <t:dgCol title="主办单位" field="organizer" align="center"  width="140" sortable="false"></t:dgCol>
   <t:dgCol title="活动类型" dictionary="actType" queryMode="single" query="true" field="activityType" align="center"  width="120" sortable="false"></t:dgCol>
   <t:dgCol title="活动地址" field="activityAddress" align="center"  width="120" sortable="false"></t:dgCol>
   <%--<t:dgCol title="活动内容" field="activityHtml" align="center"  width="120"></t:dgCol>--%>
   <t:dgCol title="价格" field="price" align="center"  width="120" sortable="false"></t:dgCol>
   <%--<t:dgCol title="作者" field="authorId" align="center" dictionary="lj_base_user,author_id,nick_name" width="120"></t:dgCol>--%>
   <t:dgCol title="状态" dictionary="actStatus" queryMode="single" query="true" field="status" align="center"  width="120" sortable="false"></t:dgCol>
   <t:dgCol title="排序" field="sort" align="center"  width="80" sortable="false"></t:dgCol>
   <t:dgCol title="浏览数" field="stat" align="center"  width="120" sortable="false"></t:dgCol>
   <t:dgCol title="活动时间" field="activityTime" align="center" formatter="yyyy-MM-dd hh:mm:ss"  width="140" sortable="false"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100" align="center"></t:dgCol>
   <t:dgDelOpt title="删除" url="ljCmsActivityController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="" funname="addActivity"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="" funname="updateActivity"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="ljCmsActivityController.do?viewDetails" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="通过/驳回" icon="icon-redo" url="ljCmsActivityController.do?goAudit" funname="auditActivity"></t:dgToolBar>
   <t:dgToolBar title="排序" icon="icon-put" url="ljCmsActivityController.do?sortList" funname="sortList"></t:dgToolBar>
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
    function sortList(){
        var rows = $("#ljCmsActivityList").datagrid('getSelections');
        var id = rows[0].id;
        if (rows.length == 1) {
            var url ='ljCmsActivityController.do?goSortList&id='+id;
            createwindow('活动排序', url ,"650px","400px");
        }else{
            tip("只能选择一条数据进行排序!");
        }
    }
    //添加活动
    function addActivity(){
        $.dialog({
            content: "url:ljCmsActivityController.do?addorupdate",
            lock : true,
            width:"1000px",
            height:"800px",
            title:"新增活动",
            opacity : 0.3,
            cache:false,
            okVal: '提交审核',
            ok: function () {
                iframe = this.iframe.contentWindow;
                $("#status",iframe.document).val("a");
                saveObj();//已封装过的保存方法
                return false;
            },
            cancelVal: '关闭',
            cancel: true,
            button:[{
                name: '保存草稿',
                callback: function(){
                    iframe = this.iframe.contentWindow;//获取弹出层的iframe
                    $("#status",iframe.document).val("0");
                    saveObj();//自定义保存数据方法
                    return false;//阻止页面关闭（默认为true不关闭）
                }
            }]
        }).zindex();
    }
    //编辑活动
    function updateActivity(){
        var rowsData = $('#ljCmsActivityList').datagrid('getSelections');
        if (!rowsData || rowsData.length==0) {
            tip("请至少选择一条数据进行操作！");
            return;
        }
        var id = rowsData[0].id;
        $.dialog({
            content: "url:ljCmsActivityController.do?addorupdate&id="+id,
            lock : true,
            width:"650px",
            height:"400px",
            title:"编辑活动",
            opacity : 0.3,
            cache:false,
            okVal: '提交审核',
            ok: function () {
                iframe = this.iframe.contentWindow;
                $("#status",iframe.document).val("a");
                saveObj();//已封装过的保存方法
                return false;
            },
            cancelVal: '关闭',
            cancel: true,
            button:[{
                name: '保存草稿',
                callback: function(){
                    iframe = this.iframe.contentWindow;//获取弹出层的iframe
                    $("#status",iframe.document).val("0");
                    saveObj();//自定义保存数据方法
                    return false;//阻止页面关闭（默认为true不关闭）
                }
            }]
        }).zindex();
    }

    //审核活动
    function auditActivity(){
        var rowsData = $('#ljCmsActivityList').datagrid('getSelections');
        if (!rowsData || rowsData.length==0) {
            tip("请至少选择一条数据进行操作！");
            return;
        }
        var status = rowsData[0].status;
        var activityTitle = rowsData[0].activityTitle;
        var activityPic = rowsData[0].activityPic;
        var id = rowsData[0].id;
        $.dialog({
            content: "url:ljCmsActivityController.do?goAudit&id="+id,
            lock : true,
            width:"650px",
            height:"400px",
            title:"审核活动",
            opacity : 0.3,
            cache:false,
            okVal: '通过',
            ok: function () {
                if(status == 't'){
                    tip("该数据已经发布，无需通过审核!");
                    return;
                }
                if (activityTitle==''){
                    tip("活动标题为空不能通过审核！");
                    return;
                }
                if (activityPic==''){
                    tip("活动图片为空不能通过审核！");
                    return;
                }
                iframe = this.iframe.contentWindow;
                $("#status",iframe.document).val("t");
                saveObj();//已封装过的保存方法
                return false;
            },
            cancelVal: '关闭',
            cancel: true,
            button:[{
                name: '驳回',
                callback: function(){
                    iframe = this.iframe.contentWindow;//获取弹出层的iframe
                    $("#status",iframe.document).val("1");
                    saveObj();//自定义保存数据方法
                    return false;//阻止页面关闭（默认为true不关闭）
                }
            }]
        }).zindex();
    }
</script>