package com.jeecg.ljcj.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class QiNiuUtil {
    /** AK、SK **/
    private static final String ACCESS_KEY = "P5CWjSqgXZp7FjnlYm8gqg3pG74wrhfx-E5Ciasy";
    private static final String SECRET_KEY = "yRZmPl8Mrw1AjDMzb0dskSbpNzVKkrEUCVysMdjr";
    /**默认上传空间*/
    private static final String BUCKET_NAME = "ljcj";
    /**空间默认域名*/
    private static final String BUCKET_HOST_NAME = "http://phsvdmodd.bkt.clouddn.com";


    /**
     * 通过文件路径上传到七牛
     * @param filePath
     * @param key
     * @return
     * @throws IOException
     */
    public static String uploadFileByPath(String filePath, String key) throws IOException {
        try {
            String msg = "文件上传成功！";
            Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
            String token = auth.uploadToken(BUCKET_NAME);
            Configuration cfg = new Configuration(Zone.huabei());//华北
            UploadManager uploadManager = new UploadManager(cfg);
            Response response = uploadManager.put(filePath, key, token);
            int recode = response.statusCode;
            if(recode != 200 ){
                msg = "文件上传失败！错误码为:"+recode;
            }
            return msg;
        }catch (QiniuException ex) {
            Response r = ex.response;
            return "文件上传失败！错误码为:"+r.statusCode;
        }

    }


    /**
     * 通过file对象上传到七牛
     * @param file
     * @param key
     * @return
     * @throws IOException
     */
    public static int uploadFile(File file, String key) throws IOException {
        try {
            Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
            String token = auth.uploadToken(BUCKET_NAME);
            Configuration cfg = new Configuration(Zone.huabei());//华北
            UploadManager uploadManager = new UploadManager(cfg);
            Response response = uploadManager.put(file, key, token);
            int recode = response.statusCode;
            return recode;
        }catch (QiniuException ex) {
            Response r = ex.response;
            return r.statusCode;
        }

    }

    /**
     * 通过数据流上传
     * @param uploadBytes
     * @param key
     * @return
     */
    public static int uploadFileByByte(byte[] uploadBytes, String key){
        try {
            ByteArrayInputStream byteInputStream=new ByteArrayInputStream(uploadBytes);
            Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
            String token = auth.uploadToken(BUCKET_NAME);
            Configuration cfg = new Configuration(Zone.huabei());//华北
            UploadManager uploadManager = new UploadManager(cfg);
            Response response = uploadManager.put(byteInputStream, key, token, null, null);
            int recode = response.statusCode;
            return recode;
        }catch (QiniuException ex) {
            Response r = ex.response;
            return r.statusCode;
        }
    }


}
