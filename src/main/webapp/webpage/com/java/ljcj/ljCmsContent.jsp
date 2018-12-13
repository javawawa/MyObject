<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>内容管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 <script type="text/javascript">
     function uploadSuccess(d,file,response){
         debugger;
         tip(d.msg);
         var imgsrc = "/"+d.attributes.filename;
         $("#picLink").val(imgsrc);
     }
     $(function(){
         $("#contentTitle").change(function(){
             var contentTitle = $("#contentTitle").val();
             $("#listTile").val(contentTitle)
         })
     })
 </script>
 </head>
 <body >
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ljCmsContentController.do?save">
			<input id="id" name="id" type="hidden" value="${ljCmsContentPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							所属频道:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="catId" dictTable="lj_cms_category" dictField="id" dictText="cat_name" title="所属频道" datatype="*" defaultVal="${ljCmsContentPage.catId}"></t:dictSelect>
						<%--<input class="inputxt" id="catId" name="catId" ignore="ignore"  value="${ljCmsContentPage.catId}" datatype="n" />--%>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							内容类型:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="contentType" datatype="*" typeGroupCode="contType" hasLabel="false" defaultVal="${ljCmsContentPage.contentType}"></t:dictSelect>
							<%--<input class="inputxt" id="contentType" name="contentType" ignore="ignore"  value="${ljCmsContentPage.contentType}" />--%>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<%--<tr>--%>
					<%--<td align="right">--%>
						<%--<label class="Validform_label">--%>
							<%--状态:--%>
						<%--</label>--%>
					<%--</td>--%>
					<%--<td class="value">--%>
						<%--<t:dictSelect field="status" datatype="*" typeGroupCode="ContStatus" hasLabel="false" defaultVal="${ljCmsContentPage.status}"></t:dictSelect>--%>
							<%--&lt;%&ndash;<input class="inputxt" id="status" name="status" ignore="ignore"  value="${ljCmsContentPage.status}" />&ndash;%&gt;--%>
						<%--<span class="Validform_checktip"></span>--%>
					<%--</td>--%>
				<%--</tr>--%>
				<input type="hidden" name="status" id="status" value="0" >
				<tr>
					<td align="right">
						<label class="Validform_label">
							发布渠道:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="releaseChannel" datatype="*" typeGroupCode="reChannel" hasLabel="false" defaultVal="${ljCmsContentPage.releaseChannel}"></t:dictSelect>
							<%--<input class="inputxt" id="releaseChannel" name="releaseChannel"   value="${ljCmsContentPage.releaseChannel}" datatype="n" />--%>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							内容标题:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="contentTitle" name="contentTitle" ignore="ignore" style="width: 300px"  value="${ljCmsContentPage.contentTitle}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							列表标题:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="listTile" name="listTile" ignore="ignore" style="width: 300px"  value="${ljCmsContentPage.listTile}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							内容图片:
						</label>
					</td>
					<%--<td>--%>
						<%--<t:webUploader name="imgUp" url="ljCmsContentController.do?uploadImage" displayTxt="false" auto="false" buttonText="选择图片"--%>
									   <%--buttonStyle="btn-blue btn-M" type="image" fileNumLimit="1"></t:webUploader>--%>
					<%--</td>--%>
					<td class="value">
						<t:upload  name="uploadimg" dialog="false" multi="false" extend="pic" queueID="instructionfile"  auto="true" uploader="ljCmsContentController.do?uploadImage" onUploadSuccess="uploadSuccess"  id="instruction" formData="documentTitle"></t:upload>
                        <input type="hidden" id="picLink" name="picLink"  value="${ljCmsContentPage.picLink}" />
					</td>
                </tr>
				<tr>
                    <td align="right" id="uploadTd">
                        <label class="Validform_label">
                            上传文件:
                        </label>
                    </td>
                    <td colspan="2" id="instructionfile" class="value">
                    </td>
				</tr>
				<%--<td class="value">--%>
						<%--<input class="inputxt" id="picLink" name="picLink" ignore="ignore"  value="${ljCmsContentPage.picLink}" />--%>
						<%--<span class="Validform_checktip"></span>--%>
					<%--</td>--%>

				<%--<tr>--%>
					<%--<td align="right">--%>
						<%--<label class="Validform_label">--%>
							<%--列表标题样式:--%>
						<%--</label>--%>
					<%--</td>--%>
					<%--<td class="value">--%>
						<%--<input class="inputxt" id="listStyle" name="listStyle" ignore="ignore" style="width: 150px"  value="${ljCmsContentPage.listStyle}" />--%>
						<%--<span class="Validform_checktip"></span>--%>
					<%--</td>--%>
				<%--</tr>--%>
				<tr>
					<td align="right">
						<label class="Validform_label">
							内容摘要:
						</label>
					</td>
					<td class="value">
						<textarea id="contentAbstract" cols="80" name="contentAbstract" type="text" placeholder="请输入1到255位字符" style="width: 300px;height: 100px" class="textarea" datatype="*1-255">${ljCmsContentPage.contentAbstract}</textarea>
						<%--<input class="inputxt" id="contentAbstract" name="contentAbstract" ignore="ignore"  value="${ljCmsContentPage.contentAbstract}" />--%>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							内容标签:
						</label>
					</td>
					<td class="value" nowrap>
						<input id="contentLabel" name="contentLabel" type="hidden" value="${ljCmsContentPage.contentLabel}"/>
						<input name="name" id="name" class="inputxt" value="${ljCmsContentPage.contentLabel}" readonly="readonly" datatype="testTab" errormsg="最多选择三个标签！" nullmsg="请选择标签！" />
						<t:choose hiddenName="contentLabel" hiddenid="name" textname="name" url="ljCmsAuthorController.do?tags"  name="tagList" icon="icon-search" title="标签列表" isclear="true" isInit="true" left="50%" top="50%"></t:choose>
						<span class="Validform_checktip"></span>
					</td>
					<%--<td class="value">--%>
						<%--<input class="inputxt" id="contentLabel" name="contentLabel" ignore="ignore"  value="${ljCmsContentPage.contentLabel}" />--%>
						<%--<span class="Validform_checktip"></span>--%>
					<%--</td>--%>
				</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							内容来源:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="contentSource" name="contentSource" ignore="ignore" style="width: 300px"  value="${ljCmsContentPage.contentSource}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							内容来源URL:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="contentSourceUrl" name="contentSourceUrl" style="width: 300px" ignore="ignore"  value="${ljCmsContentPage.contentSourceUrl}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>

				<tr>
					<td align="right">
						<label class="Validform_label">
							富文本内容:
						</label>
					</td>
					<td class="value">
						<a href="#" class="easyui-linkbutton l-btn l-btn-plain" plain="true" icon="icon-edit" onclick="editWang()">富文本编辑</a>
						<input class="inputxt" id="contentHtml" name="contentHtml" type="hidden"  value='${ljCmsContentPage.contentHtml}' />
						<span class="Validform_checktip"></span>
					</td>
				</tr>

				<%--<tr>--%>
					<%--<td align="right">--%>
						<%--<label class="Validform_label">--%>
							<%--contentTemplate:--%>
						<%--</label>--%>
					<%--</td>--%>
					<%--<td class="value">--%>
						<%--<input class="inputxt" id="contentTemplate" name="contentTemplate" ignore="ignore"  value="${ljCmsContentPage.contentTemplate}" />--%>
						<%--<span class="Validform_checktip"></span>--%>
					<%--</td>--%>
				<%--</tr>--%>


				<%--<tr>--%>
					<%--<td align="right">--%>
						<%--<label class="Validform_label">--%>
							<%--releaseDate:--%>
						<%--</label>--%>
					<%--</td>--%>
					<%--<td class="value">--%>
						<%--<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="releaseDate" name="releaseDate" ignore="ignore"    value="<fmt:formatDate value='${ljCmsContentPage.releaseDate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" />--%>
						<%--<span class="Validform_checktip"></span>--%>
					<%--</td>--%>
				<%--</tr>--%>
				<tr>
					<td align="right">
						<label class="Validform_label">
							内容作者:
						</label>
					</td>
					<td class="value">
						<%--<c:choose>--%>
							<%--<c:when test="${ljCmsContentPage.author == null}">--%>
								<t:dictSelect field="author" dictTable="lj_base_user" datatype="*" dictCondition="right JOIN lj_cms_author on lj_base_user.id = lj_cms_author.user_id where lj_base_user.is_verify =1 and lj_base_user.deleted = 0 and lj_cms_author.type =1"  dictField="lj_cms_author.id" dictText="nick_name" title="内容作者"  defaultVal="${ljCmsContentPage.author}" ></t:dictSelect>
							<%--</c:when>--%>
							<%--<c:otherwise>--%>
								<%--<t:dictSelect field="author" dictTable="lj_base_user" dictField="author_id" dictText="nick_name" title="内容作者"  defaultVal="${ljCmsContentPage.author}" readonly="readonly"></t:dictSelect>--%>
							<%--</c:otherwise>--%>
						<%--</c:choose>--%>
						<%--<input class="inputxt" id="author" name="author" dictTable="lj_cms_author" dictField="id" dictText="name"  value="${ljCmsContentPage.author}"  />--%>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							频道推荐:
						</label>
					</td>
					<td class="value">
						<c:choose>
							<c:when test="${ljCmsContentPage.sort == null}">
								<input class="inputxt" id="sort" name="sort"   value="0" datatype="n" disabled="disabled" />
							</c:when>
							<c:otherwise>
								<input class="inputxt" id="sort" name="sort"   value="${ljCmsContentPage.sort}" disabled="disabled" />
							</c:otherwise>
						</c:choose>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							全部推荐:
						</label>
					</td>
					<td class="value">
						<c:choose>
							<c:when test="${ljCmsContentPage.pushTop == null}">
								<input class="inputxt" id="pushTop" name="pushTop"   value="0" datatype="n" disabled="disabled" />
							</c:when>
							<c:otherwise>
								<input class="inputxt" id="pushTop" name="pushTop"   value="${ljCmsContentPage.pushTop}" disabled="disabled" />
							</c:otherwise>
						</c:choose>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							点击数:
						</label>
					</td>
					<td class="value">
						<c:choose>
							<c:when test="${ljCmsContentPage.stat == null}">
								<input class="inputxt" id="stat" name="stat"   value="0" datatype="n" disabled="disabled" />
							</c:when>
							<c:otherwise>
								<input class="inputxt" id="stat" name="stat"   value="${ljCmsContentPage.stat}" disabled="disabled" />
							</c:otherwise>
						</c:choose>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							点赞数:
						</label>
					</td>
					<td class="value">
						<c:choose>
							<c:when test="${ljCmsContentPage.thumbup == null}">
								<input class="inputxt" id="thumbup" name="thumbup"   value="0" datatype="n" disabled="disabled" />
							</c:when>
							<c:otherwise>
								<input class="inputxt" id="thumbup" name="thumbup"   value="${ljCmsContentPage.thumbup}" disabled="disabled" />
							</c:otherwise>
						</c:choose>
						<span class="Validform_checktip"></span>
					</td>
				</tr>

			</table>
		</t:formvalid>
<script>
    $(".registerform").Validform({
        tiptype:2,
        datatype : {
            testTab:function(gets,obj,curform,regxp){
                var code = $("#label").val();
                if(gets==""){
                    return "请至少选择一个标签";
                }
                var count = gets.split(",").length;
                if(count<=3){
                    return true;
                }else{
                    return false;
                }
            }
        }
    });
    function editWang(){
        var contentHtml = $("#contentHtml").val();
        // alert(contentHtml);
        // contentHtml = encodeURI(encodeURI(contentHtml));
        sessionStorage.setItem('contentHtml', contentHtml);
        $.dialog({
            content: "url:ljCmsContentController.do?testWang",
            lock : true,
            zIndex: getzIndex(),
            width:"650px",
            height:"400px",
            title:"富文本编辑",
            opacity : 0.3,
            cache:false,
            okVal: '确定',
            ok: function(){
                debugger;
                var iframe = this.iframe.contentWindow;
                var newContentHtml = iframe.editor.txt.html();
                $("#contentHtml").val(newContentHtml);
            },
            cancelVal: '关闭',
            cancel: true
        });

	}
</script>
 </body>