package com.tx.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class MyMailUtil {
    @Autowired
    private JavaMailSenderImpl mailSender;
    
    private static Logger logger = LoggerFactory.getLogger(MyMailUtil.class);
    
    public boolean sendSimpleMail(String mail){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time=df.format(new Date());// new Date()为获取当前系统时间
        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("2197654067@qq.com");
            simpleMailMessage.setTo(mail);
            simpleMailMessage.setSubject("sprinboot mail test");
            simpleMailMessage.setText("123123123132131321");
            mailSender.send(simpleMailMessage);//发送
            logger.info(String.format("%s 成功发送至%s",time,mail));
            return true;
        }catch(Exception e){
//            System.out.println(e.getMessage());
            logger.info(String.format("%s 发送失败 %s",time,e.getMessage()));
            return false;
        }
    }

}
