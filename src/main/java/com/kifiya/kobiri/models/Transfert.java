package com.kifiya.kobiri.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

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

    Beneficiaire beneficiaire;

    Client client;

    String code;

    //@NotNull
    Long montant;

    @NotNull
    Long montantEuros;

    Double taux;

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

