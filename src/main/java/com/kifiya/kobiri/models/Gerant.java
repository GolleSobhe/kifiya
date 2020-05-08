package com.kifiya.kobiri.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"email"})
@ToString(of = {"id", "nom", "prenom", "email"})
@Data
@Table(name = "GERANT")
public class Gerant extends Utilisateur{
    @Size(min = 8, max = 8)
    private String code;
    @ManyToMany(cascade = CascadeType.ALL)
    List<Transfert> transferts = new ArrayList<>();
}
