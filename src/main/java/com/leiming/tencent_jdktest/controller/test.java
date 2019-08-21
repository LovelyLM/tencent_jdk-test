package com.leiming.tencent_jdktest.controller;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class test {
    @RequestMapping("/test")
    public String test() throws HTTPException, IOException {

        //给手机发送短信验证码
        //AppID与key
        int appid = 1400246445;
        String appkey = "8bfad2f0f656a163c85c68171d9181fc";

        //短信的正文ID
        int templateId = 399290;
        //短信的签名
        String smsSign= "云顶之奕最新资讯";
        //手机号码
        String phoneNumber = "18883653609";
        //验证码
        String[] params = {"1234"};

        SmsSingleSender sender = new SmsSingleSender(appid,appkey);
        SmsSingleSenderResult result = sender.sendWithParam("86", phoneNumber, templateId, params, smsSign, "", "");
        System.out.println(result);


        return "发送短信验证码成功!";
    }
}
