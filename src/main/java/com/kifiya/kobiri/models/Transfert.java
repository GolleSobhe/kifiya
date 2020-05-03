package com.kifiya.kobiri.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "telephone"})
@ToString(of = {"id", "nom", "prenom", "telephone"})
@Data
@Builder
@Table(name = "TRANSFERT")
public class Transfert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Date dateTransfert;
    @NotEmpty(message = "*Please provide your nom")
    String nom;
    @NotEmpty(message = "*Please provide your prenom")
    String prenom;
    @Pattern(regexp = "(\\+224|00224)[0-9]{9}")
    String telephone;
    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    Utilisateur responsable;
    @ManyToOne(cascade = CascadeType.PERSIST)
    Gerant gerant;
    String code;
    Long montantEuros;
    Double taux;
    Date dateValidation;
}

