package com.kifiya.kobiri.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
public class Transfert {

    LocalDateTime dateTransfert = LocalDateTime.now();

    LocalDateTime dateValidation;

    @NotNull(message = "*Veuillez selectionner un bénéficiaire")
    Beneficiaire beneficiaire;

    @NotNull(message = "*Veuillez selectionner un client")
    Client client;

    @NotNull(message = "*Veuillez selectionner un point de retrait")
    Boutique boutique;

    @NotNull(message = "*Code de recuperation")
    String code;

    @NotNull
    @DecimalMin("10.00")
    Long montantEuros;

    @NotNull
    Double taux;

    @NotNull
    Double frais;

    EtatTransfert statut;
}

