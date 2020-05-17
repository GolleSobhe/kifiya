package com.kifiya.kobiri.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
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
@Table(name = "CLIENT")
public class Client extends Utilisateur{
    Double promo;
    @ManyToMany(cascade = CascadeType.ALL)
    Set<Beneficiaire> beneficiaires;
    @OneToMany(mappedBy = "client") @JsonIgnore
    List<Transfert> transferts = new ArrayList<>();
}
