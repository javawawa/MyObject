<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>活动管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script src="plug-in/cutImg/iscroll-zoom.js"></script>
  <script src="plug-in/cutImg/hammer.js"></script>
  <script src="plug-in/cutImg/lrz.all.bundle.js"></script>
  <script src="plug-in/cutImg/jquery.photoClip.js"></script>

  <script>
	$(function(){
			var clipArea = new bjj.PhotoClip("#clipAreaPc", {
				size: [580,300],// 截取框的宽和高组成的数组。默认值为[260,260]
				outputSize: [580,300], // 输出图像的宽和高组成的数组。默认值为[0,0]，表示输出图像原始大小
				//outputType: "jpg", // 指定输出图片的类型，可选 "jpg" 和 "png" 两种种类型，默认为 "jpg"
				file: "#filePc", // 上传图片的<input type="file">控件的选择器或者DOM对象
				view: "#viewPc", // 显示截取后图像的容器的选择器或者DOM对象
				ok: "#clipBtnPc", // 确认截图按钮的选择器或者DOM对象
				loadStart: function() {
					// 开始加载的回调函数。this指向 fileReader 对象，并将正在加载的 file 对象作为参数传入
					$('.cover-wrappc').fadeIn();
				},
				loadComplete: function() {
					// 加载完成的回调函数。this指向图片对象，并将图片地址作为参数传入
				},
				//loadError: function(event) {}, // 加载失败的回调函数。this指向 fileReader 对象，并将错误事件的 event 对象作为参数传入
				clipFinish: function(dataURL) {
					// 裁剪完成的回调函数。this指向图片对象，会将裁剪出的图像数据DataURL作为参数传入
					$('.cover-wrappc').fadeOut();
					$('#viewPc').css('background-size','100% 100%');
					$.ajax({
						url : "ljCmsActivityController.do?uploadImage",
						type : 'post',
						data: {
							"shareurl": dataURL
						},
						cache : false,
						success : function(data) {
							var d = $.parseJSON(data);
							if (d.success) {
								var msg = d.msg;
								tip(msg);
								var filename = d.obj;
								$("#activityPic").val(filename);
							}
						}
					});
				}
			});
	})
  </script>
 </head>
 <body >
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ljCmsActivityController.do?save">
			<input id="id" name="id" type="hidden" value="${ljCmsActivityPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							活动标题:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="activityTitle" name="activityTitle" validType="lj_cms_activity,activity_title,id" datatype="*1-100"  value="${ljCmsActivityPage.activityTitle}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							活动图片:
						</label>
					</td>
                    <td class="value">
						<%--<c:choose>--%>
							<%--<c:when test="${ljCmsActivityPage.activityPic != null}">--%>
								<%--<img style="width:44px;height:44px;" border="1" src="http://static.unicoin.top${ljCmsActivityPage.activityPic}">--%>
							<%--</c:when>--%>
						<%--</c:choose>--%>
						<%--<t:upload  name="uploadimg" dialog="false" multi="false" extend="pic" queueID="instructionfile"  auto="true" uploader="imageUploadToQiNiuController.do?uploadImage&uploadType=activityImg&width=580&height=300" onUploadSuccess="uploadSuccess"  id="instruction" formData="documentTitle"></t:upload>--%>
							<div class="ycupload-mainbox" id="pcupdiv" >
								<div class="cover-wrappc" style="position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; background: rgba(0, 0, 0, 0.4); z-index: 10000000; text-align: center; display: none;">
									<div class="" style="width:900px;height:600px;margin:100px auto;background-color:#FFFFFF;overflow: hidden;border-radius:4px;">
										<div id="clipAreaPc" style="margin: 10px; height: 490px; user-select: none; overflow: hidden; position: relative;">
											<div class="photo-clip-view" style="position: absolute; left: 50%; top: 50%; width: 428px; height: 321px; margin-left: -214px; margin-top: -160.5px;">
												<div class="photo-clip-moveLayer" style="transform-origin: 0px 0px 0px; transition-timing-function: cubic-bezier(0.1, 0.57, 0.1, 1); transition-duration: 0ms; transform: translate(0px, 0px) scale(1) translateZ(0px);">
													<div class="photo-clip-rotateLayer"></div>
												</div>
											</div>
											<div class="photo-clip-mask" style="position: absolute; left: 0px; top: 0px; width: 100%; height: 100%; pointer-events: none;">
												<div class="photo-clip-mask-left" style="position: absolute; left: 0px; right: 50%; top: 50%; bottom: 50%; width: auto; height: 321px; margin-right: 214px; margin-top: -160.5px; margin-bottom: -160.5px; background-color: rgba(0, 0, 0, 0.5);"></div>
												<div class="photo-clip-mask-right" style="position: absolute; left: 50%; right: 0px; top: 50%; bottom: 50%; margin-left: 214px; margin-top: -160.5px; margin-bottom: -160.5px; background-color: rgba(0, 0, 0, 0.5);"></div>
												<div class="photo-clip-mask-top" style="position: absolute; left: 0px; right: 0px; top: 0px; bottom: 50%; margin-bottom: 160.5px; background-color: rgba(0, 0, 0, 0.5);"></div>
												<div class="photo-clip-mask-bottom" style="position: absolute; left: 0px; right: 0px; top: 50%; bottom: 0px; margin-top: 160.5px; background-color: rgba(0, 0, 0, 0.5);"></div>
												<div class="photo-clip-area" style="border: 1px dashed rgb(221, 221, 221); position: absolute; left: 50%; top: 50%; width: 428px; height: 321px; margin-left: -215px; margin-top: -161.5px;"></div>
											</div>
										</div>
										<a id="clipBtnPc" style="margin: 0 auto;display: block;width:120px;height: 36px;border-radius: 4px;background-color:#ff8a00;color: #FFFFFF;font-size: 14px;text-align: center;line-height: 36px;outline: none;" >保存活动图片</a>
									</div>
								</div>
								<div id="viewPc" style="width: 214px; height: 160.5px; background-color: rgb(102, 102, 102); background-repeat: no-repeat; background-position: center center; background-size: contain;background-image: url('http://static.unicoin.top${ljCmsAdvertPage.pic}')" ></div>
								<div class="" style="margin-top: 10px;width:140px;height:32px;border-radius: 4px;background-color:#ff8a00;color: #FFFFFF;font-size: 14px;text-align:center;line-height:32px;outline:none;margin-left:37px;position:relative;">
									点击上传活动图片
									<input type="file" id="filePc" style="cursor:pointer;opacity:0;filter:alpha(opacity=0);width:100%;height:100%;position:absolute;top:0;left:0;" accept="image/*" />
									<span id="pcpx" style="color: red;position: absolute; right: -160px;"></span>
								</div>
							</div>
                        <input type="hidden" id="activityPic" name="activityPic"  value="${ljCmsActivityPage.activityPic}" datatype="*" nullmsg="请上传活动图片!" />
						<span id="mobilepx" style="color: red;">建议图片大小为580*300px</span>
                    </td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							主办单位:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="organizer" name="organizer" datatype="*1-128"  value="${ljCmsActivityPage.organizer}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							活动类型:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="activityType" datatype="*" typeGroupCode="actType" hasLabel="false" defaultVal="${ljCmsActivityPage.activityType}"></t:dictSelect>
						<%--<input class="inputxt" id="activityType" name="activityType" ignore="ignore"  value="${ljCmsActivityPage.activityType}" />--%>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							活动地址:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="activityAddress" name="activityAddress" datatype="*1-128"  value="${ljCmsActivityPage.activityAddress}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							活动内容:
						</label>
					</td>
					<td class="value">
                        <a href="#" class="easyui-linkbutton l-btn l-btn-plain" plain="true" icon="icon-edit" onclick="editWang()">富文本编辑</a>
						<input class="inputxt" id="activityHtml" name="activityHtml" type="hidden"  value='${ljCmsActivityPage.activityHtml}' />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							活动时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 157px;height: 26px" id="activityTime" name="activityTime"     value="<fmt:formatDate value='${ljCmsActivityPage.activityTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" datatype="*" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							价格:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="price" name="price"   value="${ljCmsActivityPage.price}" datatype="n" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<%--<tr>--%>
					<%--<td align="right">--%>
						<%--<label class="Validform_label">--%>
							<%--作者:--%>
						<%--</label>--%>
					<%--</td>--%>
					<%--<td class="value">--%>
						<%--<t:dictSelect field="authorId" dictTable="lj_base_user"  dictField="lj_cms_author.id" dictText="nick_name" dictCondition="right JOIN lj_cms_author on lj_base_user.id = lj_cms_author.user_id where lj_base_user.is_verify =1 and lj_base_user.deleted = 0 and lj_cms_author.type =1" title="作者"  defaultVal="${ljCmsActivityPage.authorId}" ></t:dictSelect>--%>
						<%--&lt;%&ndash;<input class="inputxt" id="authorId" name="authorId" ignore="ignore"  value="${ljCmsActivityPage.authorId}" datatype="n" />&ndash;%&gt;--%>
						<%--<span class="Validform_checktip"></span>--%>
					<%--</td>--%>
				<%--</tr>--%>
				<%--<tr>--%>
					<%--<td align="right">--%>
						<%--<label class="Validform_label">--%>
							<%--状态:--%>
						<%--</label>--%>
					<%--</td>--%>
					<%--<td class="value">--%>
						<%--<t:dictSelect field="status" datatype="*" typeGroupCode="actStatus" hasLabel="false" defaultVal="${ljCmsActivityPage.status}"></t:dictSelect>--%>
						<%--<span class="Validform_checktip"></span>--%>
					<%--</td>--%>
				<%--</tr>--%>
				<input id="status" name="status" type="hidden" value="${ljCmsActivityPage.status}" />
				<tr>
					<td align="right">
						<label class="Validform_label">
							排序:
						</label>
					</td>
					<td class="value">
						<input class="inputxt"  id="sort" name="sort" validType="lj_cms_activity,sort,id"  value="${ljCmsActivityPage.sort}" datatype="n" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>

			</table>
		</t:formvalid>
 </body>
<script>
    function editWang(){
        var contentHtml = $("#activityHtml").val();
        // contentHtml = encodeURI(encodeURI(contentHtml));
        sessionStorage.setItem('contentHtml', contentHtml);
        $.dialog({
            content: "url:ljCmsContentController.do?testWang&contentHtml="+contentHtml,
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
                $("#activityHtml").val(newContentHtml);
            },
            cancelVal: '关闭',
            cancel: true
        });

    }
</script>