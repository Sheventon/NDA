package ru.itis.usersservice.services;

import ru.itis.usersservice.exceptions.ConfirmMaliException;

/**
 * created: 14-07-2021 - 13:10
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */
public interface ConfirmMailService {

    void confirmMail(String code) throws ConfirmMaliException;

}
