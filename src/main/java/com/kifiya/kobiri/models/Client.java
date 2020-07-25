package com.kifiya.kobiri.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Data
@SuperBuilder

public class Client{

    @NonNull
    @NotBlank(message = "*veuillez fournir votre nom")
    String nom;

    @NonNull
    @NotBlank(message = "*veuillez fournir votre prenom")
    String prenom;

    @NonNull
    @Email(message = "*veuillez fournir un email valide")
    @NotBlank(message = "*veuillez fournir votre email")
    String email;

    String password;

    //@NonNull
    @Pattern(regexp = "(\\+33|0033|0)[0-9]{9}")
    String telephone;

    @NonNull
    @NotEmpty(message = "*veuillez fournir votre adresse")
    String adresse;

    @NonNull
    @NotEmpty(message = "*veuillez fournir votre pays")
    String pays;

    @NonNull
    @NotEmpty(message = "*veuillez fournir votre ville")
    String ville;

    String codePostale;

    List<Beneficiaire> beneficiaires;
}
