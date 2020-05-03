package com.kifiya.kobiri.repositories;

import com.kifiya.kobiri.models.Transfert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TransfertRepository {
    String INSERT = "insert into TRANSFERT (code, date_transfert, montant_euros, nom, prenom, taux, telephone, responsable_id)" +
            "values (:code,:date_transfert, :montant_euros, :nom, :prenom, :taux, :telephone, :responsable_id)";
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void ajouter(Transfert transfert) {
        namedParameterJdbcTemplate.update(INSERT, new MapSqlParameterSource().addValue("code", transfert.getCode())
                .addValue("date_transfert", transfert.getDateTransfert())
                .addValue("date_validation", transfert.getDateValidation())
                .addValue("montant_euros", transfert.getMontantEuros())
                .addValue("nom", transfert.getNom())
                .addValue("prenom", transfert.getPrenom())
                .addValue("taux", transfert.getTaux())
                .addValue("telephone", transfert.getTelephone())
                .addValue("responsable_id", transfert.getResponsable().getId()));

    }
}
