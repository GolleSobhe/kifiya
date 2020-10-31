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
public class Client extends Utilisateur{

    @NonNull
    @NotBlank(message = "*veuillez fournir votre nom")
    String nom;

    @NonNull
    @NotBlank(message = "*veuillez fournir votre prenom")
    String prenom;

    @NonNull
    @NotBlank(message = "*veuillez fournir votre telephone")
    @Pattern(regexp = "\\d{2}[- .]?\\d{2}[- .]?\\d{2}[- .]?\\d{2}[- .]?\\d{2}$")
    String telephone;

    String adresse;

    String pays;

    String ville;

    String codePostale;

    List<Beneficiaire> beneficiaires;

    public String toString(){
        return prenom + " " + nom;
    }
}
