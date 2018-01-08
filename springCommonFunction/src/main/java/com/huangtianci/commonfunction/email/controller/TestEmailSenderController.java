package com.huangtianci.commonfunction.email.controller;

import com.huangtianci.commonfunction.common.bean.EmailInfo;
import com.huangtianci.commonfunction.common.bean.Out;
import com.huangtianci.commonfunction.email.service.EmailSender;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Huang Tianci
 * @date 2017/11/28
 */
@RestController
@RequestMapping("/sendEmail")
public class TestEmailSenderController {

    @Autowired
    EmailSender emailSender;

    @ApiOperation(value = "测试发送邮件", notes = "测试发送邮件")
    @GetMapping
    public Out send() throws Exception {
        EmailInfo emailInfo = new EmailInfo();
        emailInfo.setFrom("tianci.huang@qq.com");
        emailInfo.setTo("tianci.huang@qq.com");
        emailInfo.setSubject("测试发送");
        emailInfo.setContent("这是一封测试邮件，请忽略");
        emailSender.sendSimpleMail(emailInfo);
        return Out.builder().success().build();
    }

}
