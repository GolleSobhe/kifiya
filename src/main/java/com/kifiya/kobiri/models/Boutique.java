package com.kifiya.kobiri.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
public class Boutique {

    @NotEmpty(message = "*veuillez fournir votre point de vente")
    String nom;
    @NotEmpty(message = "*veuillez fournir une ville pour le point de vente")
    String ville;
    //@NotEmpty
    String description;

}
