package com.kifiya.kobiri.models.user;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotEmpty(message = "*Please provide your nom")
    private String nom;
    @NotEmpty(message = "*Please provide your prenom")
    private String prenom;
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;
    @Pattern(regexp = "(\\+33|0)[0-9]{9}")
    private String telephone;
    @NotEmpty(message = "*Please provide an pays")
    private String pays;
    @NotEmpty(message = "*Please provide an ville")
    private String ville;
    private String codePostale;
    @NotEmpty(message = "*Please provide an adresse")
    private String adresse;
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;
    @ManyToMany(cascade = CascadeType.ALL)
    Set<Role> roles;
}
