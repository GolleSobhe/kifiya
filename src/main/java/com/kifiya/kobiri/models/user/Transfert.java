package com.kifiya.kobiri.models.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
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
    Boolean status;
    Date date;
    @NotEmpty(message = "*Please provide your nom")
    String nom;
    @NotEmpty(message = "*Please provide your prenom")
    String prenom;
    @Pattern(regexp = "(\\+224|00224)[0-9]{9}")
    String telephone;
    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    User responsable;
    String code;
    Long montantEuros;
    Long montantGNF;
    Double taux;
}

