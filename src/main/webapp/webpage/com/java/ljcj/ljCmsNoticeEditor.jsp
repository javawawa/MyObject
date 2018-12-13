<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>wangEditor demo</title>
</head>
<body>
<div id="editor">

</div>

<!-- 注意， 只需要引用 JS，无需引用任何 CSS ！！！-->
<script type="text/javascript" src="plug-in/wangEditor/wangEditor.min.js"></script>
<script type="plug-in/jquery/jquery-1.8.3.js"></script>
<script type="text/javascript" src="https://unpkg.com/qiniu-js/dist/qiniu.min.js"></script>
<script type="text/javascript">
    var E = window.wangEditor;
    var editor = new E('#editor');
    // editor.customConfig.uploadImgShowBase64 = true;
    editor.customConfig.uploadImgServer = 'ljCmsContentController.do?uploadImageByEditor';
    editor.customConfig.withCredentials = true;
    editor.customConfig.uploadFileName = 'imgFile';
    editor.customConfig.uploadImgHooks = {

        customInsert : function(insertImg, result, editor) {
            // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
            // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果
            debugger;
            // 举例：假如上传图片成功后，服务器端返回的是 {url:'....'} 这种格式，即可这样插入图片：
            var url = result.url
            insertImg(url)

            // result 必须是一个 JSON 格式字符串！！！否则报错
        }
    }
    editor.create();
    // var contentHtml = document.getElementById('contentHtmls').value;
    var noticeHtml = sessionStorage.getItem('noticeHtml');
    editor.txt.html(noticeHtml);


</script>
</body>
</html>
