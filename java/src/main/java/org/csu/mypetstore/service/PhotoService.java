package org.csu.mypetstore.service;

import com.qiniu.cdn.CdnManager;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;


import com.qiniu.util.StringMap;

import org.springframework.stereotype.Service;

import java.io.File;

//上传图片到七牛云
public class PhotoService {

    //密钥
    static String accessKey = "-QO-oZhwFam-uTKvzb3Ge7Lstga7NBsJT-fGvWrr";
    static String secretKey = "9oWv-J2tJRtx7vtkB0VmkcXFaFHKW0-3U7-kvyum";
    static Auth auth = Auth.create(accessKey, secretKey);
    //存储空间名
    static String bucketName = "tomsdog";
    final static public String domain="http://qbyy9dziv.bkt.clouddn.com/";

    public static void refresh(String url) throws QiniuException {
        String[] urls = {url};
        CdnManager c = new CdnManager(auth);
         c.refreshUrls(urls);
    }

    //上传
    public static void uploadImage(String fileLoad, String key)  {

        try {
            File file = new File(fileLoad);
            // 创建上传对象，Zone*代表地区
            Configuration configuration = new Configuration(Region.autoRegion());
            UploadManager uploadManager = new UploadManager(configuration);

            //允许上传覆盖
            uploadManager.put(file, key, auth.uploadToken(bucketName, key,3600, new StringMap().put("insertOnly", 0)));

        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            e.printStackTrace();
            System.out.println("error " + r.toString());
            try {
                // 响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                System.out.println("error " + e1.error());
            }
        }
    }
}

