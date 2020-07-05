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
@ToString(of = {"id", "nom", "prenom", "telephone", "email"})
@Data
@SuperBuilder
public class Utilisateur {

    Long id;

    @NonNull
    @NotBlank(message = "*veuillez fournir votre nom")
    String nom;

    @NonNull
    @NotBlank(message = "*veuillez fournir votre prenom")
    String prenom;

    @NonNull
    @Email(message = "*Please provide a valid Email")
    @NotBlank(message = "*veuillez fournir votre email")
    String email;

    String password;

    boolean active;

    String confirmationToken;

    List<Role> roles;

}
