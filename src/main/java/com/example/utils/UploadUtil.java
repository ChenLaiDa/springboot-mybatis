package com.example.utils;

import com.example.common.exception.CommonException;
import com.example.common.exception.ExceptionEnum;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2021/11/1 21:09
 */
public class UploadUtil {

    /**
     * 上传文件到 nginx
     * @param uploadFile
     * @return
     */
    public static String uploadImg2Nginx(MultipartFile uploadFile){
        //1.给上传的图片取一个新的文件名
        //1.1 获取原始的文件的名字
        String originalFilename = uploadFile.getOriginalFilename();
        //1.2 生成新的文件的名字
        String newName = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        if(originalFilename != null){
            newName = newName + originalFilename;
        }
        //2.把图片上传到图片服务器
        //2-1.获取上传的io流
        InputStream inputStream = null;
        try {
            inputStream = uploadFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //2-2.调用FtpUtil工具类进行上传
        String imageUrl = FtpUtil.putImages(inputStream, newName);
        if("".equals(imageUrl)){
            throw new CommonException(ExceptionEnum.FILE_UPLOAD_FAILED);
        }
        return imageUrl;
    }
}
