package com.kifiya.kobiri.repositories;

import com.kifiya.kobiri.models.Transfert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TransfertRepository {
    String INSERT = "insert into TRANSFERT (code,date,montant_euros,montantgnf,nom,prenom,status,taux,telephone,responsable_id)" +
            "values (:code,:date,:montant_euros,:montantgnf,:nom,:prenom,:status,:taux,:telephone,:responsable_id)";
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void save(Transfert transfert) {
        namedParameterJdbcTemplate.update(INSERT, new MapSqlParameterSource().addValue("code", transfert.getCode())
                .addValue("date", transfert.getDate())
                .addValue("montant_euros", transfert.getMontantEuros())
                .addValue("montantgnf", transfert.getMontantGNF())
                .addValue("nom", transfert.getNom())
                .addValue("prenom", transfert.getPrenom())
                .addValue("status", transfert.getStatus())
                .addValue("taux", transfert.getTaux())
                .addValue("telephone", transfert.getTelephone())
                .addValue("responsable_id", transfert.getResponsable().getId()));

    }
}
