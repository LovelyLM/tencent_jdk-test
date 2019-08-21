package com.leiming.tencent_jdktest.controller;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.leiming.tencent_jdktest.entity.User;
import com.leiming.tencent_jdktest.service.UserService;
import com.leiming.tencent_jdktest.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Random;

@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @RequestMapping("/")
    public ModelAndView index(HttpServletRequest request){
        return new ModelAndView("index.html");

    }
    @RequestMapping("/register")
    public String register(User user,HttpServletRequest request){
        user.setState(0);
        String usercode = user.getCode();
        String code = (String) request.getSession().getAttribute("code");
        System.out.println(code);
        user.setCode(code);

        if (usercode.equals(code)){
            user.setState(1);
            userService.save(user);
            return "ok";
        }else {
            return "no";
        }


    }
    @RequestMapping("/sms")
    public String sms(String telephone, HttpServletRequest request) throws HTTPException, IOException {
        //给手机发送短信验证码
        //AppID与key
        int appid = 1400246445;
        String appkey = "8bfad2f0f656a163c85c68171d9181fc";

        //短信的正文ID
        int templateId = 399290;
        //短信的签名
        String smsSign= "云顶之奕最新资讯";
        //手机号码
        String phoneNumber = telephone;
        //验证码
        Random r = new Random();
        String code = ""+r.nextInt(10)+r.nextInt(10)+r.nextInt(10)+r.nextInt(10);
        //将验证码先存入session中，以便验证
        request.getSession().setAttribute("code",code);
        String[] params = {code};
        SmsSingleSender sender = new SmsSingleSender(appid,appkey);
        SmsSingleSenderResult result = sender.sendWithParam("86", phoneNumber, templateId, params, smsSign, "", "");
        return "ok";
    }
    @RequestMapping("/login")
    public String login(User user){
        User user2 = userService.findByUsername(user.getUsername());
        System.out.println(user);
        System.out.println(user2);
        if (user2!=null){
            if (user.getPassword().equals(user2.getPassword())){
                return "ok";
            }else {
                return "not";
            }
        }else {
            return "no";
        }


    }

}
