package com.kifiya.kobiri.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
