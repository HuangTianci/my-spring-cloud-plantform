package com.huangtianci.commonfunction.email.service;

import com.huangtianci.commonfunction.common.bean.EmailInfo;
import org.apache.commons.lang3.StringUtils;
//import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

//import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Huang Tianci
 * @date 2017/11/28
 * 邮件发送
 */
@Service
public class EmailSender {

    //注释掉  用的时候需要配置一下授权码
    //@Autowired
    private JavaMailSender mailSender;

   /* @Autowired
    private VelocityEngine velocityEngine;*/

    /**
     * 发送简单文本邮件
     * @param emailInfo  邮件信息
     * @throws Exception
     */
    public void sendSimpleMail(EmailInfo emailInfo) throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailInfo.getFrom());
        message.setTo(emailInfo.getTo());
        message.setSubject(emailInfo.getSubject());
        message.setText(emailInfo.getContent());
        //mailSender.send(message);
    }

    /**
     * 发送模板邮件--可发送附件
     *
     * @param emailInfo
     * @throws Exception
     */
    public void sendTemplateMail(EmailInfo emailInfo) throws Exception {
        /*MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(emailInfo.getFrom());
        helper.setTo(emailInfo.getTo());
        helper.setSubject(emailInfo.getSubject());
        Map<String, Object> model = new HashMap<>();
        model.put("subject", emailInfo.getSubject());
        model.put("content", emailInfo.getContent());
        String text = VelocityEngineUtils.mergeTemplateIntoString(
                velocityEngine, "templates/email-template.vm", "UTF-8", model);
        helper.setText(text, true);

        if (StringUtils.isNotBlank(emailInfo.getFileLocation())) {
            FileSystemResource file1 = new FileSystemResource(new File("C:\\Users\\huangtianci\\Desktop\\对码\\问题列表.txt"));
            //FileSystemResource file2 = new FileSystemResource(new File("C:\\Users\\Administrator\\Desktop\\Think in Java.pdf"));
            helper.addAttachment("问题列表.txt", file1);
            //helper.addAttachment("附件-2.pdf", file2);
            //或者
            InputStream inputStream = service.downloadFile(emailInfoDO.getFileLocation());
            helper.addAttachment(emailInfoDO.getFileName() == null ? "file.txt" : emailInfoDO.getFileName(),
                    new ByteArrayResource(IOUtils.toByteArray(inputStream)));
        }

        mailSender.send(mimeMessage);*/
    }
}
