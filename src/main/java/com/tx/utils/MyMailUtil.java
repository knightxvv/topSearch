package com.tx.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.tx.user.dao.User;

@Service
public class MyMailUtil {
    @Autowired
    private JavaMailSenderImpl mailSender;
    @Autowired
    private getIPAndPort getIPAndPort;
    
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
            logger.error(String.format("%s 发送失败: %s",time,e.getMessage()));
            return false;
        }
    }
    
    public void sendActiveMail(User user){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time=df.format(new Date());// new Date()为获取当前系统时间
        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("2197654067@qq.com");
            simpleMailMessage.setTo(user.getemail());
            System.out.println(user.getemail());
            simpleMailMessage.setSubject("来自topSearch的激活邮件");
            String text="";
            text="点击"+getIPAndPort.getUrl()+"/topSearch/user/activeAccount?id="+user.activecode+"激活你的账号";
            simpleMailMessage.setText(text);
            mailSender.send(simpleMailMessage);//发送
            logger.info(String.format("%s 成功发送至%s",time,user.getemail()));
        }catch(Exception e){
//            System.out.println(e.getMessage());
            e.printStackTrace();
            logger.error(String.format("%s 发送失败: %s",time,e.getMessage()));
        }
    }

}
