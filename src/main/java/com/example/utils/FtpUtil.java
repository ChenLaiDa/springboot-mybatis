package com.example.utils;
import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2021/11/1 21:10
 */
@Slf4j
@Component
public class FtpUtil {
    /**
     * ftp服务器ip地址
     */
    private static String host;
    @Value("${ftp.host}")
    public void setHost(String val){
        FtpUtil.host = val;
    }
    /**
     * 端口
     */
    private static int port;
    @Value("${ftp.port}")
    public void setPort(int val){
        FtpUtil.port = val;
    }
    /**
     * 用户名
     */
    private static String userName;
    @Value("${ftp.userName}")
    public void setUserName(String val){
        FtpUtil.userName = val;
    }
    /**
     * 密码
     */
    private static String password;
    @Value("${ftp.password}")
    public void setPassword(String val){
        FtpUtil.password = val;
    }
    /**
     * 存放图片的根目录
     */
    private static String rootPath;
    @Value("${ftp.rootPath}")
    public void setRootPath(String val){
        FtpUtil.rootPath = val;
    }
    /**
     * 存放图片的路径
     */
    private static String imgUrl;
    @Value("${ftp.img.url}")
    public void setImgUrl(String val){
        FtpUtil.imgUrl = val;
    }
    /**
     * 获取连接
     */
    private static ChannelSftp getChannel() throws Exception{
        JSch jsch = new JSch();
        //->ssh root@host:port
        Session sshSession = jsch.getSession(userName,host);
        //密码
        sshSession.setPassword(password);
        Properties sshConfig = new Properties();
        sshConfig.put("StrictHostKeyChecking", "no");
        sshSession.setConfig(sshConfig);
        sshSession.connect();
        Channel channel = sshSession.openChannel("sftp");
        channel.connect();
        return (ChannelSftp) channel;
    }
    /**
     * ftp上传图片
     * @param inputStream 图片io流
     * @param imagesName 图片名称
     * @return urlStr 图片的存放路径
     */
    public static String putImages(InputStream inputStream, String imagesName){
        try {
            ChannelSftp sftp = getChannel();
            String path = rootPath + "/";
            createDir(path,sftp);
            //上传文件
            sftp.put(inputStream, path + imagesName);
            log.info("上传成功！");
            sftp.quit();
            sftp.exit();
            //处理返回的路径
            String resultFile;
            resultFile = imgUrl + imagesName;
            return resultFile;
        } catch (Exception e) {
            log.error("上传失败：" + e.getMessage());
        }
        return "";
    }
    /**
     * 创建目录
     */
    private static void createDir(String path,ChannelSftp sftp) throws SftpException {
        String[] folders = path.split("/");
        sftp.cd("/");
        for ( String folder : folders ) {
            if ( folder.length() > 0 ) {
                try {
                    sftp.cd( folder );
                }catch ( SftpException e ) {
                    sftp.mkdir( folder );
                    sftp.cd( folder );
                }
            }
        }
    }
    /**
     * 删除图片
     */
    public static void delImages(String imagesName){
        try {
            ChannelSftp sftp = getChannel();
            String path = rootPath + imagesName;
            sftp.rm(path);
            sftp.quit();
            sftp.exit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
