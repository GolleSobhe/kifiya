package com.kifiya.kobiri.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
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
    @OneToMany(mappedBy = "client") @JsonIgnore
    List<Beneficiaire> beneficiaires = new ArrayList<Beneficiaire>();
    @OneToMany(mappedBy = "client") @JsonIgnore
    List<Transfert> transferts = new ArrayList<Transfert>();
}
