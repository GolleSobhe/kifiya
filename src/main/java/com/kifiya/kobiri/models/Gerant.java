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
@Getter
@Setter
@Data
@Table(name = "GERANT")
public class Gerant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotNull
    @NotEmpty
    private String nom;
    @NotNull
    @NotEmpty
    private String prenom;
    @NotNull
    @NotEmpty
    private String telephone;
    private String password;
    @Size(min = 8, max = 8)
    private String code;
    @OneToMany(mappedBy = "gerant") @JsonIgnore
    List<Transfert> transferts = new ArrayList<>();

}
