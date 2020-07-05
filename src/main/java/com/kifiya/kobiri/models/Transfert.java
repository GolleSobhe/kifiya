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

    @NotNull(message = "*Veuillez selectionner un bénéficiaire")
    Client client;

    @NotNull(message = "*Code de recuperation")
    String code;

    @NotNull
    @DecimalMin("10.00")
    Long montantEuros;

    Long taux;

    public String getClientId() {
        return client.getEmail();
    }

    public String getPrenomBeneficiaire(){
        return beneficiaire.getPrenom();
    }

    public String getNomBeneficiaire(){
        return beneficiaire.getNom();
    }

    public String getTelephoneBeneficiaire(){
        return beneficiaire.getTelephone();
    }
}

