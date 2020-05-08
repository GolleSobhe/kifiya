package com.kifiya.kobiri.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"non", "prenom", "telephone"})
@ToString(of = {"nom", "prenom"})
@Data
@Builder
@Table(name = "BENEFICIAIRE")
public class Beneficiaire {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NotEmpty(message = "*veuillez fournir le nom")
    String nom;
    @NotEmpty(message = "*veuillez fournir le prenom")
    String prenom;
    @Pattern(regexp = "(\\+224|00224)[0-9]{9}")
    String telephone;
    @ManyToOne(cascade = CascadeType.PERSIST)
    Client client;
}
