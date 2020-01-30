package com.kifiya.kobiri.services;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    @Async
    public void sendEmail(SimpleMailMessage email) {
        mailSender.send(email);
    }
}
