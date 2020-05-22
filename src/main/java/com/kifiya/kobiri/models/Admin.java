package com.kifiya.kobiri.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "email"})
@ToString(of = {"id", "nom", "prenom", "email"})
@Data
@SuperBuilder
@Table(name = "ADMIN")
public class Admin extends Utilisateur{

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
