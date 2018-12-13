<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
     <meta charset="UTF-8">
     <meta http-equiv="x-ua-compatible" content="ie=edge">
     <meta name="robots" content="all">
     <meta name="keywords" content="">
     <meta name="description" content="">
  <title>广告配置</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
     <script src="plug-in/cutImg/iscroll-zoom.js"></script>
     <script src="plug-in/cutImg/hammer.js"></script>
     <script src="plug-in/cutImg/lrz.all.bundle.js"></script>
     <script src="plug-in/cutImg/jquery.photoClip.js"></script>
     <script>
         $(function(){
             var position = $("#position").val();
             var cutHeightMb, cutWidthMb,cutHeightPc,cutHeightPc;
             if (position == 1) {
                 $("#pcpx").text("建议图片大小为1920*480px");
                 $("#mobilepx").text("建议图片大小为750*280px");
                 cutWidthMb = 750;
                 cutHeightMb = 280;
                 // $("#viewMb").width(cutWidthMb);
                 // $("#viewMb").height(cutHeightMb);
                 initCutImgMb();
                 cutWidthPc= 1920;
                 cutHeightPc = 480;
                 // $("#viewPc").width(cutWidthPc);
                 // $("#viewPc").height(cutHeightPc);
                 // initCutImgPc();
                 $("#filePc").css('display', 'none');
                 $("#filePcNo").css('display', '');
             } else if (position == 2) {
                 $("#pcpx").text("建议图片大小为800*480px");
                 $("#mobilepx").text("移动首页小图可以不上传!");
                 $("#mbupdiv").css('display', 'none');
                 cutWidthPc = 800;
                 cutHeightPc = 480;
                 // $("#viewPc").width(cutWidthPc);
                 // $("#viewPc").height(cutHeightPc);
                 initCutImgPc();
                 $("#filePcNo").css('display', 'none');
             } else if (position == 3) {
                 $("#pcpx").text("建议图片大小为790*120px");
                 $("#mobilepx").text("建议图片大小为690*144px");
                 cutWidthMb = 690;
                 cutHeightMb = 144;
                 // $("#viewMb").width(cutWidthMb);
                 // $("#viewMb").height(cutHeightMb);
                 cutWidthPc = 690;
                 cutHeightPc = 144;
                 // $("#viewPc").width(cutWidthPc);
                 // $("#viewPc").height(cutHeightPc);
                 initCutImgPc();
                 initCutImgMb();
             }else{
                 $("#pcpx").text("");
                 $("#mobilepx").text("");
			 }

             var targetType = $("#targetType").val();
             if (targetType == "0") {
                 //文章
                 $("#chooseLabel").css('display', '');
                 $("#textspan").text("选择文章");
                 $("#target").attr('readonly', 'readonly');
             }else if(targetType == "4"){
				 //活动
                 $("#chooseLabel").css('display', '');
                 $("#textspan").text("选择活动");
                 $("#target").attr('readonly', 'readonly');
			 }

             $("#position").change(function () {
                 var position = $("#position").val();
                 if (position == 1) {
                     $("#pcpx").text("建议图片大小为1920*480px");
                     $("#mobilepx").text("建议图片大小为750*280px");
                     $("#mbupdiv").css('display', '');
                     cutWidthMb = 750;
                     cutHeightMb = 280;
                     // $("#viewMb").width(cutWidthMb);
                     // $("#viewMb").height(cutHeightMb);
                     initCutImgMb();
                     cutWidthPc = 1920;
                     cutHeightPc = 480;
                     // $("#viewPc").width(cutWidthPc);
                     // $("#viewPc").height(cutHeightPc);
                     // initCutImgPc();
                     $("#filePcNo").css('display', '');
                     $("#filePc").css('display', 'none');
                 } else if (position == 2) {
                     $("#pcpx").text("建议图片大小为800*480px");
                     $("#mobilepx").text("移动首页小图可以不上传!");
                     $("#mbupdiv").css('display', 'none');
                     cutWidthPc = 800;
                     cutHeightPc = 480;
                     // $("#viewPc").width(cutWidthPc);
                     // $("#viewPc").height(cutHeightPc);
                     initCutImgPc();
                     $("#filePcNo").css('display', 'none');
                     $("#filePc").css('display', '');
                 } else if (position == 3) {
                     $("#pcpx").text("建议图片大小为790*120px");
                     $("#mobilepx").text("建议图片大小为690*144px");
                     $("#mbupdiv").css('display', '');
                     cutWidthMb = 690;
                     cutHeightMb = 144;
                     // $("#viewMb").width(cutWidthMb);
                     // $("#viewMb").height(cutHeightMb);
                     initCutImgMb();
                     cutWidthPc = 790;
                     cutHeightPc = 120;
                     // $("#viewPc").width(cutWidthPc);
                     // $("#viewPc").height(cutHeightPc);
                     initCutImgPc();
                     $("#filePcNo").css('display', 'none');
                     $("#filePc").css('display', '');
                 }else{
                     $("#mbupdiv").css('display', '');
                     $("#filePcNo").css('display', 'none');
                     $("#filePc").css('display', '');
                 }
             });
             $("#targetType").change(function () {
                 var targetType = $("#targetType").val();
             	 if(targetType=="0") {
                     $("#chooseLabel").css('display', '');
                     $("#textspan").text("选择文章");
                     $("#target").val("");
                     $("#target").attr('readonly', 'readonly');
                 }else if(targetType=="4"){
                     $("#chooseLabel").css('display', '');
                     $("#textspan").text("选择活动");
                     $("#target").val("");
                     $("#target").attr('readonly', 'readonly');
				 }else if(targetType=="3"){
                     $("#chooseLabel").css('display', 'none');
                     $("#target").val("");
                     $("#target").attr('readonly', 'readonly');
                 }else{
                     $("#target").val("");
                     $("#chooseLabel").css('display', 'none');
                     $("#target").removeAttr('readonly');
				 }
             });


             function initCutImgMb(){
                 var clipArea = new bjj.PhotoClip("#clipAreaMb", {
                     size: [cutWidthMb,cutHeightMb],// 截取框的宽和高组成的数组。默认值为[260,260]
                     outputSize: [cutWidthMb,cutHeightMb], // 输出图像的宽和高组成的数组。默认值为[0,0]，表示输出图像原始大小
                     //outputType: "jpg", // 指定输出图片的类型，可选 "jpg" 和 "png" 两种种类型，默认为 "jpg"
                     file: "#fileMb", // 上传图片的<input type="file">控件的选择器或者DOM对象
                     view: "#viewMb", // 显示截取后图像的容器的选择器或者DOM对象
                     ok: "#clipBtnMb", // 确认截图按钮的选择器或者DOM对象
                     loadStart: function() {
                         // 开始加载的回调函数。this指向 fileReader 对象，并将正在加载的 file 对象作为参数传入
                         $('.cover-wrapmb').fadeIn();
                     },
                     loadComplete: function() {
                         // 加载完成的回调函数。this指向图片对象，并将图片地址作为参数传入
                     },
                     //loadError: function(event) {}, // 加载失败的回调函数。this指向 fileReader 对象，并将错误事件的 event 对象作为参数传入
                     clipFinish: function(dataURL) {
                         // 裁剪完成的回调函数。this指向图片对象，会将裁剪出的图像数据DataURL作为参数传入
                         $('.cover-wrapmb').fadeOut();
                         $('#viewMb').css('background-size','100% 100%');
                         console.log('dataURL', dataURL);
                         $.ajax({
                             url : "ljCmsAdvertController.do?uploadImageMb",
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
                                     $("#picMobile").val(filename);
                                 }
                             }
                         });
                     }
                 });

             }
             function initCutImgPc(){
                 var clipArea = new bjj.PhotoClip("#clipAreaPc", {
                     size: [cutWidthPc,cutHeightPc],// 截取框的宽和高组成的数组。默认值为[260,260]
                     outputSize: [cutWidthPc,cutHeightPc], // 输出图像的宽和高组成的数组。默认值为[0,0]，表示输出图像原始大小
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
                             url : "ljCmsAdvertController.do?uploadImagePc",
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
                                     $("#pic").val(filename);
                                 }
                             }
                         });
                     }
                 });

             }


		 })

         /**
          * 选择点击类型后弹出窗口
          */
         function chooseTarget() {
             var targetType = $("#targetType").val();
             var url = "";
             if(targetType=="0") {
                 //文章
                 url = "url:ljCmsContentController.do?articleSelect";
             }else if(targetType=="4"){
                 //活动
                 url = "url:ljCmsActivityController.do?activitySelect";
             }
             $.dialog({
                 content: url,
                 lock : true,
                 zIndex: getzIndex(),
                 width:"650px",
                 height:"400px",
                 title:"选择内容",
                 opacity : 0.3,
                 cache:false,
                 okVal: '确定',
                 ok: function(){
                     iframe = this.iframe.contentWindow;
                     var rows = iframe.getSelectRows();
                     var actId = rows[0].id;
                     var targetUrl = "";
                     if(targetType=="0") {
                         //文章
                         targetUrl = "/view/article.html?id=";
                     }else if(targetType=="4"){
                         //活动
                         targetUrl = "/view/activityDetails.html?id=";
                     }
                     targetUrl = targetUrl + actId;
                     $("#target").val(targetUrl);
                     $.i18n.prop('dialog.close');
                 },
                 cancelVal: '关闭',
                 cancel: true
             });
         }

         function clearTarget() {
             $("#target").val("");
         }

	 </script>
 </head>
 <body >
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="ljCmsAdvertController.do?save">
			<input id="id" name="id" type="hidden" value="${ljCmsAdvertPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<span style="color: red;">*</span>
						<label class="Validform_label">
							广告位:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="position" id="position" datatype="*" typeGroupCode="advPosit" hasLabel="false" defaultVal="${ljCmsAdvertPage.position}"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<input type="hidden" name="verifyStatus" id="verifyStatus" value="0" >
				<tr>
					<td align="right">
						<span style="color: red;">*</span>
						<label class="Validform_label">
							点击类型:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="targetType" datatype="*" id="targetType" typeGroupCode="advCtype" hasLabel="false" defaultVal="${ljCmsAdvertPage.targetType}"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>

				<tr>
					<td align="right">
						<span style="color: red;">*</span>
						<label class="Validform_label">
							点击目标:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="target" name="target" style="width: 250px" value="${ljCmsAdvertPage.target}" datatype="valTarget" nullmsg="点击目标不能为空!" />
						<label id="chooseLabel" style="display: none" >
							<a href="#" class="easyui-linkbutton l-btn l-btn-plain" plain="true" icon="icon-search" onclick="chooseTarget()">
								<span id="textspan" >选择活动</span>
							</a>
							<a href="#" class="easyui-linkbutton l-btn l-btn-plain" plain="true" icon="icon-redo" onclick="clearTarget()">
								<span >清空</span>
							</a>
						</label>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span style="color: red;">*</span>
						<label class="Validform_label">
							标题:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="title" name="title"  style="width: 250px" value="${ljCmsAdvertPage.title}" datatype="*" validType="lj_cms_advert,title,id" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<span style="color: red;">*</span>
						<label class="Validform_label">
							排序:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="sort" name="sort" style="width: 250px" value="${ljCmsAdvertPage.sort}" datatype="/^[0-3]$/" errormsg="只能输入0-3的数字" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							内容:
						</label>
					</td>
					<td class="value">
						<textarea id="description" cols="80" name="content" type="text" style="width: 250px;height: 100px" class="textarea" >${ljCmsAdvertPage.content}</textarea>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							PC广告图:
						</label>
					</td>
					<td class="value">
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
                                    <a id="clipBtnPc" style="margin: 0 auto;display: block;width:120px;height: 36px;border-radius: 4px;background-color:#ff8a00;color: #FFFFFF;font-size: 14px;text-align: center;line-height: 36px;outline: none;" >保存pc广告图</a>
                                </div>
                            </div>
                            <div id="viewPc" style="width: 214px; height: 160.5px; background-color: rgb(102, 102, 102); background-repeat: no-repeat; background-position: center center; background-size: contain;background-image: url('http://static.unicoin.top${ljCmsAdvertPage.pic}')" ></div>
                            <div class="" style="margin-top: 10px;width:140px;height:32px;border-radius: 4px;background-color:#ff8a00;color: #FFFFFF;font-size: 14px;text-align:center;line-height:32px;outline:none;margin-left:37px;position:relative;">
                                点击上传pc广告图
                                <input type="file" id="filePc" style="cursor:pointer;opacity:0;filter:alpha(opacity=0);width:100%;height:100%;position:absolute;top:0;left:0;" accept="image/*" />
                                <input type="file" id="filePcNo" style="display: none;cursor:pointer;opacity:0;filter:alpha(opacity=0);width:100%;height:100%;position:absolute;top:0;left:0;" accept="image/*" />
								<span id="pcpx" style="color: red;position: absolute; right: -160px;"></span>
                            </div>
                        </div>
						<input type="hidden" id="pic" name="pic"  value="${ljCmsAdvertPage.pic}" datatype="*" nullmsg="请上传PC广告图" />
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							移动广告图:
						</label>
					</td>
					<td class="value">
                        <div class="ycupload-mainbox" id="mbupdiv" >
                            <div class="cover-wrapmb" style="position: fixed; left: 0px; top: 0px; width: 100%; height: 100%; background: rgba(0, 0, 0, 0.4); z-index: 10000000; text-align: center; display: none;">
                                <div class="" style="width:900px;height:400px;margin:100px auto;background-color:#FFFFFF;overflow: hidden;border-radius:4px;">
                                    <div id="clipAreaMb" style="margin: 10px; height: 320px; user-select: none; overflow: hidden; position: relative;">
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
                                    <a id="clipBtnMb" style="margin: 0 auto;display: block;width:120px;height: 36px;border-radius: 4px;background-color:#ff8a00;color: #FFFFFF;font-size: 14px;text-align: center;line-height: 36px;outline: none;" >保存移动广告图</a>
                                </div>
                            </div>
                            <div id="viewMb" style="width: 214px; height: 160.5px; background-color: rgb(102, 102, 102); background-repeat: no-repeat; background-position: center center; background-size: contain;background-image: url('http://static.unicoin.top${ljCmsAdvertPage.picMobile}')" ></div>
                            <div class="" style="margin-top: 10px;width:140px;height:32px;border-radius: 4px;background-color:#ff8a00;color: #FFFFFF;font-size: 14px;text-align:center;line-height:32px;outline:none;margin-left:37px;position:relative;">
                                点击上传移动广告图
                                <input type="file" id="fileMb" style="cursor:pointer;opacity:0;filter:alpha(opacity=0);width:100%;height:100%;position:absolute;top:0;left:0;" accept="image/*" />
								<span id="mobilepx" style="color: red;position: absolute; right: -160px;"></span>
                            </div>
                        </div>
						<input type="hidden" id="picMobile" name="picMobile"  value="${ljCmsAdvertPage.picMobile}" datatype="valmbupload" />
					</td>
				</tr>

			</table>
		</t:formvalid>
 </body>
 <script>
     $(".registerform").Validform({
         tiptype:2,
         datatype : {
             valTarget:function(gets,obj,curform,regxp){
                 var targetType = $("#targetType").val();
                 var target = $("#target").val();
                 debugger;
                 if (targetType != "3" && target == "") {
                     return "点击目标不能为空";
                 }

             },
			 valmbupload:function(gets,obj,curform,regxp){
                 var position = $("#position").val();
                 var picMobile = $("#picMobile").val();
                 if(position != "2" && picMobile == ""){
                     return "请上传移动广告图!";
				 }
			 }

         }
     });

     /**
	  * pc图片上传 position 1
      */
     $("#filePcNo").change('click',function(e){
         var ele = e.target;
         var xhr = new XMLHttpRequest();
         var formData = new FormData();
         var position = $("#position").val();
         if(position==""){
             tip("请先选择广告类型!");
             return false;
         }
         for(var i=0, f; f=ele.files[i]; i++){
             // 上传图片前获取图片宽高
             var _URL = window.URL || window.webkitURL;
             img = new Image();
             img.src = _URL.createObjectURL(f);
             var width = 0;
             var height = 0;
             setTimeout(function(){
                 height = img.height;
                 width = img.width;
                 console.log(width)
                 console.log(height)
             },100)
             formData.append('file', f);
         }
         xhr.open('POST', 'imageUploadToQiNiuController.do?uploadImage&uploadType=activityImg&width=1920&height=480', true);
         xhr.send(formData);
         xhr.onreadystatechange = function (e) {
             if(xhr.readyState == 4) {
                 if (xhr.status == 200) {
                     var data = JSON.parse(e.target.response);
                     if(data.success){
                         tip(data.msg);
                         var imgsrc = "/"+data.attributes.filename;
                         $("#pic").val(imgsrc);
                         var imgurl = "url('http://static.unicoin.top"+imgsrc+"')";
                         $("#viewPc").css("background-image",imgurl);
					 }else{
                         tip(data.msg);
					 }
                 }
             }
         }
     })

     /**
	  * 移动端广告图片上传
      */
     // $("#mbUpdateBtn").change('click',function(e){
     //     var ele = e.target;
     //     var xhr = new XMLHttpRequest();
     //     var formData = new FormData();
     //     var position = $("#position").val();
     //     if(position==""){
     //         tip("请先选择广告类型!");
     //         return false;
     //     }
     //     for(var i=0, f; f=ele.files[i]; i++){
     //         // 上传图片前获取图片宽高
     //         var _URL = window.URL || window.webkitURL;
     //         img = new Image();
     //         img.src = _URL.createObjectURL(f);
     //         var mbwidth = 0;
     //         var mbheight = 0;
     //         setTimeout(function(){
     //             mbheight = img.height;
     //             mbwidth = img.width;
     //         },100)
     //         formData.append('file', f);
     //     }
     //     xhr.open('POST', 'ljCmsAdvertController.do?uploadImageMb&position='+position, true);
     //     xhr.send(formData);
     //     xhr.onreadystatechange = function (e) {
     //         if(xhr.readyState == 4) {
     //             if (xhr.status == 200) {
     //                 var data = JSON.parse(e.target.response);
     //                 if(data.success){
     //                     tip(data.msg);
     //                     var imgsrc = "/"+data.attributes.filename;
     //                     $("#picMobile").val(imgsrc);
     //                 }else{
     //                     tip(data.msg);
     //                 }
     //             }
     //         }
     //     }
     // })
 </script>
