package ru.itis.usersservice.util;

/**
 * created: 13-07-2021 - 16:05
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
public interface MailUtil {

    void sendHtmlMail(String to, String from, String subject, String text);

}


