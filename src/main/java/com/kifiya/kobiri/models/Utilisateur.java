package com.kifiya.kobiri.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "email"})
@ToString(of = {"id", "nom", "prenom", "email"})
@Data
@Builder
@Table(name = "UTILISATEUR")
@Inheritance(strategy=InheritanceType.JOINED)
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotEmpty(message = "*veuillez fournir votre nom")
    String nom;
    @NotEmpty(message = "*veuillez fournir votre prenom")
    String prenom;
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*veuillez fournir votre email")
    String email;
    @Pattern(regexp = "(\\+33|0)[0-9]{9}")
    String telephone;
    @NotEmpty(message = "*veuillez fournir votre pays")
    String pays;
    @NotEmpty(message = "*veuillez fournir votre ville")
    String ville;
    String codePostale;
    @NotEmpty(message = "*veuillez fournir votre adresse")
    String adresse;
    String password;
    boolean enabled;
    String confirmationToken;
    @ManyToMany(cascade = CascadeType.ALL)
    Set<Role> roles;
}
