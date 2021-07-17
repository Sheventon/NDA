package ru.itis.usersservice.controllers;

import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.usersservice.exceptions.ConfirmMaliException;
import ru.itis.usersservice.services.ConfirmMailService;

import javax.annotation.security.PermitAll;

/**
 * created: 14-07-2021 - 13:07
 * project: NDA
 *
 * @author dinar
 * @version v0.1
 */

@RestController
@CrossOrigin(originPatterns = "*")
@Api(value = "Confirm mail controller")
public class ConfirmMailController {

    private final ConfirmMailService confirmMailService;

    public ConfirmMailController(ConfirmMailService confirmMailService) {
        this.confirmMailService = confirmMailService;
    }


    @PutMapping("/confirm-mail/{code}")
    @PermitAll
    public ResponseEntity<?> confirmMail(@PathVariable String code) {
        try {
            confirmMailService.confirmMail(code);
        } catch (ConfirmMaliException ex) {
            return ResponseEntity.ok(ex.getLocalizedMessage());
        }
        return ResponseEntity.ok("Email was confirmed");
    }

}
