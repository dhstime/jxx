package com.jxx.common.utils;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.SocketException;

/**
 * @author Strange
 * @ClassName WMSFTPUtil.java
 * @Description TODO
 * @createTime 2021年01月08日 11:21:00
 */
public class WMSFTPUtil {

    private Logger logger = LoggerFactory.getLogger(WMSFTPUtil.class);

    private String ftpHost = "192.168.1.152";

    private int ftpPort = 21;

    private String ftpUserName = "FTPWMS_USER_PROD";

    private String  ftpPassword = "FTPWMS_USER_PROD";

    public FTPClient getFTPClient() {

        FTPClient ftp = null;
        try {
            ftp = new FTPClient();
            // 连接FPT服务器,设置IP及端口
            ftp.connect(ftpHost, ftpPort);
            // 设置用户名和密码
            ftp.login(ftpUserName, ftpPassword);
            // 设置连接超时时间,5000毫秒
            ftp.setConnectTimeout(50000);
            // 设置中文编码集，防止中文乱码
            ftp.setControlEncoding("UTF-8");
            if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                logger.info("未连接到FTP，用户名或密码错误");
                ftp.disconnect();
            } else {
                logger.info("FTP连接成功");
            }

        } catch (SocketException e) {
            e.printStackTrace();
            logger.info("FTP的IP地址可能错误，请正确配置");
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("FTP的端口错误,请正确配置");
        }
        return ftp;
    }

    /**
     * 关闭FTP方法
     * @param ftp
     * @return
     */
    public boolean closeFTP(FTPClient ftp){

        try {
            ftp.logout();
        } catch (Exception e) {
            logger.error("FTP关闭失败");
        }finally{
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                    logger.error("FTP关闭失败");
                }
            }
        }

        return false;

    }

    /**
     * 下载FTP下指定文件
     * @param ftp FTPClient对象
     * @param filePath FTP文件路径
     * @param fileName 文件名
     * @param downPath 下载保存的目录
     * @return
     */
    public boolean downLoadFTP(FTPClient ftp, String filePath, String fileName,
                               String downPath) {
        // 默认失败
        boolean flag = false;

        try {
            // 跳转到文件目录
            ftp.changeWorkingDirectory(filePath);
            // 获取目录下文件集合
            ftp.enterLocalPassiveMode();
            FTPFile[] files = ftp.listFiles();
            for (FTPFile file : files) {
                // 取得指定文件并下载
                if (file.getName().equals(fileName)) {
                    File downFile = new File(downPath + File.separator
                            + file.getName());

                    OutputStream out = new FileOutputStream(downFile);
                    // 绑定输出流下载文件,需要设置编码集，不然可能出现文件为空的情况
                    flag = ftp.retrieveFile(new String(file.getName().getBytes("UTF-8"),"ISO-8859-1"), out);
//                    InputStream inputStream = ftp.retrieveFileStream(new String(file.getName().getBytes("UTF-8"), "ISO-8859-1"));

                    // 下载成功删除文件,看项目需求
                    // ftp.deleteFile(new String(fileName.getBytes("UTF-8"),"ISO-8859-1"));
                    out.flush();
                    out.close();
                    if(flag){
                        logger.info("下载成功");
                    }else{
                        logger.error("下载失败");
                    }
                }
            }

        } catch (Exception e) {
            logger.error("下载失败");
        }

        return flag;
    }

    /**
     * @description: 获取指定文件的IO流
     * @return: InputStream
     * @author: Strange
     * @date: 2021/1/8
     **/
    public InputStream downLoadFile(FTPClient ftp, String filePath, String fileName){
        InputStream inputStream = null;
        try {
            // 跳转到文件目录
            ftp.changeWorkingDirectory(filePath);
            // 获取目录下文件集合
            ftp.enterLocalPassiveMode();
            FTPFile[] files = ftp.listFiles();
            for (FTPFile file : files) {
                // 取得指定文件并下载
                if (file.getName().equals(fileName)) {

                    // 绑定输出流下载文件,需要设置编码集，不然可能出现文件为空的情况
                    inputStream = ftp.retrieveFileStream(new String(file.getName().getBytes("UTF-8"), "ISO-8859-1"));
                    return inputStream;
                }
            }

        } catch (Exception e) {
            logger.error("下载失败");
        }
        return inputStream;
    }


    public static void main(String[] args) {
        String paths = "/2021/01/V251838_2020061214_1_20210107145625.JPG;/2021/01/V251838_2020061214_2_20210107145625.JPG";
//        String paths = "2009080000000008.JPG";
        WMSFTPUtil wmsftpUtil = new WMSFTPUtil();
        FTPClient ftpClient = wmsftpUtil.getFTPClient();
//        wmsftpUtil.downLoadFTP(ftpClient,"/2021/01","V274780_201216_1_20210104165409.JPG","/Users/dhs/Downloads");
        String[] split = paths.split(";");
        for (String path : split) {
//            String[] split1 = path.split("/");
            int i = path.lastIndexOf("/", path.length());
            String filePath = path.substring(0, i+1);
            String fileName = path.substring(i+1, path.length());
            wmsftpUtil.downLoadFTP(ftpClient,filePath,fileName,"/Users/dhs/Downloads");

        }
    }
}
