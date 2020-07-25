package com.kifiya.kobiri.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendValidationTokenToClient(String appUrl, String validationToken, @Email(message = "*Please provide a valid Email") @NotEmpty(message = "*Please provide an email") String email) {

        SimpleMailMessage registrationEmail = new SimpleMailMessage();
        registrationEmail.setTo(email);
        registrationEmail.setSubject("Confirmer votre inscription");
        registrationEmail.setText("\n" +
                "Pour confirmer votre adresse e-mail, veuillez cliquer sur le lien ci-dessous:\n"
                + appUrl + "/utilisateur/confirmation?token=" + validationToken);
        registrationEmail.setFrom("noreply@fiyahollo.com");
        mailSender.send(registrationEmail);
    }

}
