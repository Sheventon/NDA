package ru.itis.usersservice.util;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

/**
 * created: 13-07-2021 - 16:07
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
@Component
public class MailUtilImpl implements MailUtil {

    private final JavaMailSender mailSender;

    private final ExecutorService executorService;

    public MailUtilImpl(JavaMailSender mailSender, ExecutorService executorService) {
        this.mailSender = mailSender;
        this.executorService = executorService;
    }

    @Override
    public void sendHtmlMail(String to, String from, String subject, String text) {
        Runnable mailSending = () -> mailSender.send(mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,
                    true,
                    "UTF-8");
            messageHelper.setTo(to);
            messageHelper.setFrom(from);
            messageHelper.setSubject(subject);
            messageHelper.setText(text, true);
        });

        executorService.submit(mailSending);
    }
}