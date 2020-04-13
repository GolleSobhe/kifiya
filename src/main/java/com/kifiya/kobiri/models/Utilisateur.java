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
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotEmpty(message = "*Please provide your nom")
    String nom;
    @NotEmpty(message = "*Please provide your prenom")
    String prenom;
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    String email;
    @Pattern(regexp = "(\\+33|0)[0-9]{9}")
    String telephone;
    @NotEmpty(message = "*Please provide an pays")
    String pays;
    @NotEmpty(message = "*Please provide an ville")
    String ville;
    String codePostale;
    @NotEmpty(message = "*Please provide an adresse")
    String adresse;
    String password;
    boolean enabled;
    String confirmationToken;
    @ManyToMany(cascade = CascadeType.ALL)
    Set<Role> roles;
    @OneToMany(mappedBy = "responsable") @JsonIgnore
    List<Transfert> transferts = new ArrayList<>();
}
