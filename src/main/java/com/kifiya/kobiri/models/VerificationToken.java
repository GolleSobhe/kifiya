package com.kifiya.kobiri.models;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Data
public class VerificationToken {

    public VerificationToken(String idUser) {
        this.token = UUID.randomUUID().toString();
        this.idUser = idUser;
        this.expirationDate = LocalDateTime.now().plusDays(1);
    }

    private String token;

    private String idUser;

    private LocalDateTime expirationDate;

}
