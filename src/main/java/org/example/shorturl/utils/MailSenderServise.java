package org.example.shorturl.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MailSenderServise {
    private final JavaMailSender javaMailSender;
    private final Configuration configuration;
    private final static String EMAIL = "shorturl@info.com";




    @Async
    public void sendActivationMail(Map<String, String> model) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(EMAIL);
            mimeMessageHelper.setTo(model.get("to"));
            mimeMessageHelper.setSubject("Activation Email for SHORT URL ");

            ClassPathResource classPathResource = new ClassPathResource("static/img/logo.png");
            mimeMessageHelper.addInline("logo_id", classPathResource);

            Template template = configuration.getTemplate("activation.ftlh");
            String url = "http://localhost:8080/api/auth/activate/" + model.get("code");
            Map<String, String> objectModel = Map.of("url", url);
            String htmlMailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, objectModel);
            mimeMessageHelper.setText(htmlMailContent, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
    }


    @Async
    public   void sendWeeklyReport(Map<String, Object> model)    {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(EMAIL);
            mimeMessageHelper.setTo(model.get("to").toString() );
            mimeMessageHelper.setSubject("Weekly Report  ");

            Template template = configuration.getTemplate("report.ftlh");

            String url = "http://localhost:8080/api/auth/activate/" + model.get("code");
            String htmlMailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model );
            mimeMessageHelper.setText(htmlMailContent, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
}

