package com.jxx.test;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @author Strange
 * @ClassName FtpTest.java
 * @Description TODO
 * @createTime 2020年12月22日 14:34:00
 */
public class FtpTest {
    private static FTPClient ftpClient = new FTPClient();
    private static String encoding = "UTF-8";
    private String ftp_url = "192.168.1.143";
    private String ftp_user = "FTPWMS_TEST";
    private String ftp_password = "12345678";
    @Test
    public void test() throws Exception{

        OutputStream os = null;
        int reply;
        try {
            // 登录
            ftpClient.connect(ftp_url,21);
            ftpClient.login(ftp_user, ftp_password);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            // 检验是否连接成功
            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
            }
            ftpClient.setControlEncoding(encoding);
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.changeWorkingDirectory("/2020/12/09/");
            ftpClient.enterLocalPassiveMode();
            File localFile = new File("/Users/dhs/Downloads/11.jpeg");
            os = new FileOutputStream(localFile);
            String ftpFileName = "V111126_0503_1_20201209191240.jpeg";
            ftpClient.retrieveFile(new String(ftpFileName.getBytes("UTF-8")), os);
            os.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            os.close();
            ftpClient.logout();
            if (ftpClient.isConnected()) {
                ftpClient.disconnect();
            }
        }

    }


}
