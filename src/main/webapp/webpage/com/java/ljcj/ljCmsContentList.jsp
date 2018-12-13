<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="ljCmsContentList" fitColumns="false" title="内容管理" actionUrl="ljCmsContentController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="所属频道" align="center" field="catId" dictionary="lj_cms_category,id,cat_name"   width="120" query="true" queryMode="single"></t:dgCol>
   <t:dgCol title="内容标题" align="center" field="contentTitle"  query="true" queryMode="single" width="240"></t:dgCol>
   <%--<t:dgCol title="列表标题" field="listTile"   width="120"></t:dgCol>--%>
   <t:dgCol title="内容图片"  field="picLink" formatterjs="showImg" align="center"   width="120"></t:dgCol>
   <%--<t:dgCol title="列表标题样式" field="listStyle"   width="120"></t:dgCol>--%>
   <%--<t:dgCol title="内容摘要" field="contentAbstract"   width="120"></t:dgCol>--%>
   <t:dgCol title="标签" field="contentLabel" align="center"  width="140"></t:dgCol>
   <t:dgCol title="内容作者" field="author" dictionary="lj_base_user,author_id,nick_name" query="true" queryMode="single" align="center"  width="120"></t:dgCol>
   <%--<t:dgCol title="内容来源" field="contentSource"   width="120"></t:dgCol>--%>
   <%--<t:dgCol title="内容来源URL" field="contentSourceUrl"   width="120"></t:dgCol>--%>
   <t:dgCol title="内容类型" field="contentType" align="center"  dictionary="contType"  width="120" query="false" queryMode="single"></t:dgCol>
   <%--<t:dgCol title="富文本内容" field="contentHtml"   width="120"></t:dgCol>--%>
   <%--<t:dgCol title="内容模板" field="contentTemplate"   width="120"></t:dgCol>--%>
   <t:dgCol title="状态" field="status" align="center" dictionary="ContStatus"   width="120" query="true" queryMode="single"></t:dgCol>
   <%--<t:dgCol title="发布渠道" field="releaseChannel" align="center" dictionary="reChannel"   width="120" query="true" queryMode="single"></t:dgCol>--%>
   <%--<t:dgCol title="releaseDate" field="releaseDate" formatter="yyyy-MM-dd hh:mm:ss"  width="120"></t:dgCol>--%>
   <%--<t:dgCol title="频道推荐" field="sort"   width="120"></t:dgCol>--%>
   <%--<t:dgCol title="全部推荐" field="pushTop"   width="120"></t:dgCol>--%>
   <%--<t:dgCol title="点击数" field="stat"   width="120"></t:dgCol>--%>
   <%--<t:dgCol title="点赞数" field="thumbup"   width="120"></t:dgCol>--%>
   <t:dgCol title="创建时间" field="createAt"  align="center"  formatter="yyyy-MM-dd hh:mm:ss"  width="130"></t:dgCol>
   <t:dgCol title="操作" align="center" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="ljCmsContentController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add"  funname="addContent"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="ljCmsContentController.do?addorupdate" funname="updateContent"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="ljCmsContentController.do?viewDetails" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
<script>
    function showImg(value, row, index){
        if(value){
            value="http://phsvdmodd.bkt.clouddn.com/"+value;
            return "<img style='width:44px;height:44px;' border='1' src='"+value+"'/>";
        }
    }

    function addContent(){
        $.dialog({
            content: "url:ljCmsContentAuditController.do?addorupdate",
            lock : true,
            width:"650px",
            height:"400px",
            title:"新增内容",
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
    function updateContent(){
        var rowsData = $('#ljCmsContentList').datagrid('getSelections');
        if (!rowsData || rowsData.length==0) {
            tip("请至少选择一条数据进行操作！");
            return;
        }
        var id = rowsData[0].id;
        $.dialog({
            content: "url:ljCmsContentAuditController.do?addorupdate&id="+id,
            lock : true,
            width:"650px",
            height:"400px",
            title:"编辑内容",
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

</script>