package com.kifiya.kobiri.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "email"})
@ToString(of = {"id", "nom", "prenom", "email"})
@Data
@Table(name = "ADMIN")
public class Admin extends Utilisateur{
}
