<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="ljCmsAuditContentList" fitColumns="false" title="内容审批" actionUrl="ljCmsContentAuditController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="所属频道" align="center" field="catId" dictionary="lj_cms_category,id,cat_name" sortable="false"  width="120" query="true" queryMode="single"></t:dgCol>
   <t:dgCol title="内容标题" align="center" field="contentTitle"  query="true" queryMode="single" sortable="false" width="240"></t:dgCol>
   <%--<t:dgCol title="列表标题" field="listTile"   width="120"></t:dgCol>--%>
   <t:dgCol title="内容图片"  field="picLink" formatterjs="showImg" align="center" sortable="false"  width="120"></t:dgCol>
   <%--<t:dgCol title="列表标题样式" field="listStyle"   width="120"></t:dgCol>--%>
   <%--<t:dgCol title="内容摘要" field="contentAbstract"   width="120"></t:dgCol>--%>
   <t:dgCol title="标签" field="contentLabel" align="center"  width="140" sortable="false"></t:dgCol>
   <t:dgCol title="内容作者" field="authorName" query="false" sortable="false" queryMode="single" align="center"  width="120"></t:dgCol>
   <%--<t:dgCol title="内容来源" field="contentSource"   width="120"></t:dgCol>--%>
   <%--<t:dgCol title="内容来源URL" field="contentSourceUrl"   width="120"></t:dgCol>--%>
   <t:dgCol title="内容类型" field="contentType" align="center"  dictionary="contType" sortable="false"  width="120" query="false" queryMode="single"></t:dgCol>
   <t:dgCol title="富文本内容" field="contentHtml" hidden="true"  width="120"></t:dgCol>
   <%--<t:dgCol title="内容模板" field="contentTemplate"   width="120"></t:dgCol>--%>
   <t:dgCol title="状态" field="status" align="center" dictionary="ContStatus"   width="120" query="true" queryMode="single" sortable="false"></t:dgCol>
   <%--<t:dgCol title="发布渠道" field="releaseChannel" align="center" dictionary="reChannel"   width="120" query="true" queryMode="single"></t:dgCol>--%>
   <%--<t:dgCol title="releaseDate" field="releaseDate" formatter="yyyy-MM-dd hh:mm:ss"  width="120"></t:dgCol>--%>
   <%--<t:dgCol title="频道推荐" field="sort"   width="120"></t:dgCol>--%>
   <t:dgCol title="全部推荐" field="pushTop" align="center" sortable="false" width="90" ></t:dgCol>
   <%--<t:dgCol title="点击数" field="stat"   width="120"></t:dgCol>--%>
   <%--<t:dgCol title="点赞数" field="thumbup"   width="120"></t:dgCol>--%>
   <t:dgCol title="创建时间" field="createAt"  align="center"  formatter="yyyy-MM-dd hh:mm:ss" sortable="false" width="130"></t:dgCol>
   <t:dgToolBar title="修改" icon="icon-add" url="ljCmsContentAuditController.do?modifyTag" funname="modifyTag"></t:dgToolBar>
   <t:dgToolBar title="通过/驳回" icon="icon-edit" url="ljCmsContentAuditController.do?goAudit" funname="auditContent"></t:dgToolBar>
   <t:dgToolBar title="排序" icon="icon-put" url="ljCmsContentAuditController.do?sortList" funname="sortList"></t:dgToolBar>
   <t:dgToolBar title="预览" icon="icon-search" url="" funname="viewContent"></t:dgToolBar>
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
    //排序
    function sortList(){
        var rows = $("#ljCmsAuditContentList").datagrid('getSelections');
        var id = rows[0].id;
        var status = rows[0].status;
        if(status!="t"){
            tip("只可对审核通过的数据进行排序!");
            return;
        }
        if (rows.length == 1) {
            var url ='ljCmsContentAuditController.do?goSortList&id='+id;
            createwindow('内容排序', url ,"650px","400px");
        }else{
            tip("只能选择一条数据进行排序!");
        }
    }
    //审核
    function auditContent(){
        var rowsData = $('#ljCmsAuditContentList').datagrid('getSelections');
        if (!rowsData || rowsData.length==0) {
            tip("请至少选择一条数据进行操作！");
            return;
        }
        var id = rowsData[0].id;
        var catId = rowsData[0].catId;
        var contentTitle = rowsData[0].contentTitle;
        var picLink = rowsData[0].picLink;
        var contentLabel = rowsData[0].contentLabel;
        var status = rowsData[0].status;
        $.dialog({
            content: "url:ljCmsContentAuditController.do?goAudit&id="+id,
            lock : true,
            width:"650px",
            height:"400px",
            title:"审核内容",
            opacity : 0.3,
            cache:false,
            okVal: '通过',
            ok: function () {
                if (catId==''){
                    tip("频道为空不能通过审核！");
                    return;
                }
                if (contentTitle==''){
                    tip("标题为空不能通过审核！");
                    return;
                }
                if (picLink==''){
                    tip("内容图片为空不能通过审核！");
                    return;
                }
                if (contentLabel==''){
                    tip("标签为空不能通过审核!");
                    return;
                }
                if (status=='t'){
                    tip("该数据已经发布无需通过审核!");
                    return;
                }
                iframe = this.iframe.contentWindow;
                $("#auditType",iframe.document).val("t");
                saveObj();//已封装过的保存方法
                return false;
            },
            cancelVal: '关闭',
            cancel: true,
            button:[{
                name: '驳回',
                callback: function(){
                    iframe = this.iframe.contentWindow;//获取弹出层的iframe
                    $("#auditType",iframe.document).val("n");
                    saveObj();//自定义保存数据方法
                    return false;//阻止页面关闭（默认为true不关闭）
                }
            }]
        }).zindex();
    }

    //修改标签
    function modifyTag(title,url, id,width,height){
        var rowsData = $('#ljCmsAuditContentList').datagrid('getSelections');
        if (!rowsData || rowsData.length==0) {
            tip("请至少选择一条数据进行操作！");
            return;
        }
        var id = rowsData[0].id;
        var status = rowsData[0].status;
        if(status=="t"){
            tip("审核通过后不允许修改！");
            return;
        }
        var url = "ljCmsContentAuditController.do?modifyTag&id+" + id;
        $.dialog({
            content: "url:ljCmsContentAuditController.do?modifyTag&id="+id,
            lock : true,
            width:"650px",
            height:"400px",
            title:"修改内容",
            opacity : 0.3,
            cache:false,
            okVal: '提交',
            ok: function () {
                iframe = this.iframe.contentWindow;
                // $("#auditType",iframe.document).val("t");
                saveObj();//已封装过的保存方法
                return false;
            },
            cancelVal: '关闭',
            cancel: true,

        }).zindex();
    }

    function viewContent(){
        var rowsData = $('#ljCmsAuditContentList').datagrid('getSelections');
        if (!rowsData || rowsData.length==0) {
            tip("请至少选择一条数据进行预览！");
            return;
        }
        var contentHtml = rowsData[0].contentHtml;
        sessionStorage.setItem('contentHtmlView', contentHtml);
        createdetailwindow('内容预览', 'ljCmsContentAuditController.do?viewContent' ,"1200px","800px");
    }

</script>