package com.kifiya.kobiri.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "email"})
@ToString(of = {"id", "nom", "prenom", "email"})
@Data
@SuperBuilder
@Table(name = "CLIENT")
public class Client extends Utilisateur{

    //@NonNull
    @Pattern(regexp = "(\\+33|0033|0)[0-9]{9}")
    String telephone;

    @NonNull
    @NotEmpty(message = "*veuillez fournir votre pays")
    String pays;

    @NonNull
    @NotEmpty(message = "*veuillez fournir votre ville")
    String ville;

    @NonNull
    @NotEmpty(message = "*veuillez fournir votre adresse")
    String adresse;

    String codePostale;

    Double promo;

    @ManyToMany(cascade = CascadeType.ALL)
    Set<Beneficiaire> beneficiaires;

    @OneToMany(mappedBy = "client") @JsonIgnore
    List<Transfert> transferts = new ArrayList<>();
}
