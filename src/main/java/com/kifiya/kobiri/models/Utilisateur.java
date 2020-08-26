package com.kifiya.kobiri.models;


import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"email"})
@ToString(of = {"email", "password", "role", "active"})
@Data
@SuperBuilder
public class Utilisateur {

    @NonNull
    @Email(message = "*Please provide a valid Email")
    @NotBlank(message = "*veuillez fournir votre email")
    String email;

    @NotBlank(message = "*veuillez fournir votre password")
    String password;

    boolean active;

    String role;

}
