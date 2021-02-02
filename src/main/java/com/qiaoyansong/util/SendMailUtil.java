package com.qiaoyansong.util;


import com.qiaoyansong.entity.StatusCode;
import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/2 17:57
 * description：发送邮件工具类
 */
public class SendMailUtil {
    private static SendMailUtil instance;

    public static SendMailUtil getInstance() {
        if (instance == null) {
            synchronized (SendMailUtil.class) {
                if (instance == null) {
                    instance = new SendMailUtil();
                    return instance;
                }
            }
        }
        return instance;
    }

    /**
     * @param to 邮件接收方
     * @param title 邮件标题
     * @param content 邮件内容
     */
    public StatusCode sendMail(String to, String title, String content) {
        //创建一个配置文件并保存
        Properties properties = new Properties();
        properties.setProperty("mail.host", "smtp.qq.com");
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        //QQ存在一个特性设置SSL加密
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return StatusCode.SEND_EMAIL_FAILED;
        }
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);
        //创建一个session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("1012077930@qq.com", "gevvwekdarzvbehj");
            }
        });
        //开启debug模式
        session.setDebug(true);
        //获取连接对象
        Transport transport = null;
        try {
            transport = session.getTransport();
            //连接服务器
            transport.connect("smtp.qq.com", "1012077930@qq.com", "gevvwekdarzvbehj");
            //创建邮件对象
            MimeMessage mimeMessage = new MimeMessage(session);
            //邮件发送人
            mimeMessage.setFrom(new InternetAddress("1012077930@qq.com"));
            //邮件接收人
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //邮件标题
            mimeMessage.setSubject(title);
            //邮件内容
            mimeMessage.setContent(content, "text/html;charset=UTF-8");
            //发送邮件
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            return StatusCode.SEND_EMAIL_FAILED;
        } catch (AddressException e) {
            e.printStackTrace();
            return StatusCode.SEND_EMAIL_FAILED;
        } catch (MessagingException e) {
            e.printStackTrace();
            return StatusCode.SEND_EMAIL_FAILED;
        } finally {
            if (transport != null) {
                try {
                    transport.close();
                } catch (MessagingException e) {
                    e.printStackTrace();
                    return StatusCode.SEND_EMAIL_FAILED;
                }
            }
        }
        return StatusCode.COMPLETE;
    }
}
