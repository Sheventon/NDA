package ru.itis.usersservice.util;

/**
 * created: 13-07-2021 - 16:27
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
public interface MailMessageGenerator {

    String getMailForConfirm(String confirmCode, String path);

}
