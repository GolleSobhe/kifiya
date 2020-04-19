package com.kifiya.kobiri.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    UUID id;

    @NotNull @NotEmpty
    private String nom;

    @NotNull @NotEmpty
    private String prenom;

    @NotNull @NotEmpty
    private String telephone;

    private String password;
}
