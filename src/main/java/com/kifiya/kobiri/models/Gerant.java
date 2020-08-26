package com.kifiya.kobiri.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Data
@SuperBuilder
public class Gerant extends Utilisateur{

    @NonNull
    @NotBlank(message = "*veuillez fournir votre nom")
    String nom;

    @NonNull
    @NotBlank(message = "*veuillez fournir votre prenom")
    String prenom;

    @Pattern(regexp = "(\\+224|00224)[0-9]{9}")
    String telephone;

    //@NonNull
    //@NotEmpty(message = "*veuillez fournir votre ville")
    String ville;

    //@NonNull
    //@NotEmpty(message = "*veuillez fournir votre adresse")
    String adresse;

    @NonNull
    @Size(min = 8, max = 8)
    private String code;

}
