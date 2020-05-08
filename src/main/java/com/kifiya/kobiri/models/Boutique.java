package com.kifiya.kobiri.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "telephone"})
@ToString(of = {"id", "ville", "nom", "telephone"})
@Data
@Builder
@Table(name = "BOUTIQUE")
public class Boutique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotEmpty(message = "*veuillez fournir votre ville")
    String ville;
    @NotEmpty(message = "*veuillez fournir votre point de vente")
    String nom;
    @Email(message = "*veuillez fournir un email valide")
    @NotEmpty(message = "*veuillez fournir votre email")
    String email;
    @Pattern(regexp = "(\\+224|00224)[0-9]{9}")
    String telephone;
    Date date;
}
