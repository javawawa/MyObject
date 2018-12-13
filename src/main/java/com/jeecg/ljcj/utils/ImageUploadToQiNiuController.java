package com.jeecg.ljcj.utils;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

@Controller
@RequestMapping("/imageUploadToQiNiuController")
public class ImageUploadToQiNiuController extends BaseController {

    private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("sysConfig");

    /**
     * 上传图片
     * @param request
     * @return
     */
    @RequestMapping(params = "uploadImage")
    @ResponseBody
    public AjaxJson uploadImage(MultipartHttpServletRequest request,String uploadType,String width,String height) {
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setSuccess(true);
        ajaxJson.setMsg("图片上传成功!");
        Map<String, Object> ajaxMap = new HashMap<String, Object>();
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile multipartFile=multipartRequest.getFile("file");// 获取上传文件对象
            CommonsMultipartFile cf = (CommonsMultipartFile)multipartFile;
            DiskFileItem fi = (DiskFileItem) cf.getFileItem();
            File file = fi.getStoreLocation();
            //拼接七牛云 文件名称 分类
            String fname = multipartFile.getOriginalFilename();
            //生成文件名
            String filename = "Img"+DateUtils.getDataString(DateUtils.yyyymmddhhmmss) + StringUtil.random(4);
            //后缀
            String ext = fname.substring(fname.lastIndexOf(".") + 1);
            //前缀 读取配置文件
            String prefix = "image/contentImg/";
            if(bundle.getString(uploadType)!=null && !"".equals(bundle.getString(uploadType))){
                prefix = bundle.getString(uploadType);
            }
            filename=prefix+filename+"."+ext;
            BufferedImage sourceImg = ImageIO.read(new FileInputStream(file));
			double fwidth = sourceImg.getWidth();
			double fheight = sourceImg.getHeight();
            if (Double.doubleToLongBits(fwidth) > Double.doubleToLongBits(Double.parseDouble(width)) || Double.doubleToLongBits(fheight) > Double.doubleToLongBits(Double.parseDouble(height))) {
                ajaxJson.setMsg("图片上传失败！尺寸大小超过上传限制!");
            }else{
                //调用上传方法
                int recode = QiNiuUtil.uploadFile(file,filename);
                if(recode != 200 ){
                    ajaxJson.setMsg("图片上传失败！错误码为:"+recode);
                }
                ajaxJson.setObj(filename);
                ajaxMap.put("filename", filename);
                ajaxJson.setAttributes(ajaxMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg(e.getMessage());
        }
        return ajaxJson;
    }

    @RequestMapping(params = "uploadImageByEditor")
    @ResponseBody
    public ImgInfo uploadImageByEditor(@RequestParam("imgFile") MultipartFile file, MultipartHttpServletRequest request) {
        ImgInfo imgInfo = new ImgInfo();
        try {
            String fname = file.getOriginalFilename();
            String filename = "con"+DateUtils.getDataString(DateUtils.yyyymmddhhmmss) + StringUtil.random(4);
            String ext = fname.substring(fname.lastIndexOf(".") + 1);
            String prefix = "image/contentImg/";
            if(bundle.getString("contentUploadUrl")!=null && !"".equals(bundle.getString("contentUploadUrl"))){
                prefix = bundle.getString("contentUploadUrl");
            }
            filename=prefix+filename+"."+ext;
            CommonsMultipartFile cf = (CommonsMultipartFile)file;
            //这个myfile是MultipartFile的
            DiskFileItem fi = (DiskFileItem) cf.getFileItem();
            File files = fi.getStoreLocation();
            int recode = QiNiuUtil.uploadFile(files,filename);
            String value = "http://phsvdmodd.bkt.clouddn.com/" + filename;
            String[] values = { value };
            int errcode = 0;
            imgInfo.setUrl(values);
            if(recode != 200 ){
                errcode = recode;
            }
            imgInfo.setError(errcode);
        } catch (Exception e) {
            imgInfo.setError(440);
            e.printStackTrace();
        }
        return imgInfo;
    }
}
