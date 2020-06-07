package com.kifiya.kobiri.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"email"}, callSuper = true)
@ToString(of = {"id", "nom", "prenom", "email"})
@Data
@SuperBuilder
@Table(name = "GERANT")
public class Gerant extends Utilisateur{

    //@NonNull
    @Pattern(regexp = "(\\+224|00224)[0-9]{9}")
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

    @NonNull
    @Size(min = 8, max = 8)
    private String code;

    @ManyToMany(cascade = CascadeType.ALL)
    List<Transfert> transferts;
}
