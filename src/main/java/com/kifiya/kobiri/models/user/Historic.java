package com.kifiya.kobiri.models.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Historic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Boolean status;
    Date date;
    Long montant;
    String nom;
    String prenom;
    @Pattern(regexp = "(\\+224)[0-9]{9}")
    String telephone;
    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    User responsable;

}

