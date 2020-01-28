package com.kifiya.kobiri.models.user;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "email"})
@ToString(of = {"id", "nom", "prenom", "email"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String pays;
    private String ville;
    private String adresse;
    private String codePostale;
    private String password;

}
