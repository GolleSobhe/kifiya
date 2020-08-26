package com.kifiya.kobiri.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Data
@SuperBuilder
public class Admin extends Utilisateur{

    @NonNull
    @NotBlank(message = "*veuillez fournir votre nom")
    String nom;

    @NonNull
    @NotBlank(message = "*veuillez fournir votre prenom")
    String prenom;

    @NonNull
    @Pattern(regexp = "(\\+33|0033|0|\\+224|00224)[0-9]{9}")
    @NotBlank(message = "*veuillez fournir votre telephone")
    String telephone;

    @NonNull
    @NotBlank(message = "*veuillez fournir votre pays")
    String pays;

    @NonNull
    @NotBlank(message = "*veuillez fournir votre ville")
    String ville;

    @NonNull
    @NotBlank(message = "*veuillez fournir votre adresse")
    String adresse;

}
