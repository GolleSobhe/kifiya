package com.kifiya.kobiri.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"telephone", "clientId"})
@ToString(of = {"nom", "prenom", "telephone"})
@Data
@Builder
public class Beneficiaire {

    Long id;
    @NotEmpty(message = "*veuillez fournir le nom")
    String nom;
    @NotEmpty(message = "*veuillez fournir le prenom")
    String prenom;
    @Pattern(regexp = "\\d{3}[- .]?\\d{2}[- .]?\\d{2}[- .]?\\d{2}$")
    String telephone;

    String clientId;
}
