package com.kifiya.kobiri.repositories;

import com.kifiya.kobiri.models.Transfert;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TransfertRepository {

    private final String AJOUTER = "insert into TRANSFERT(code,date_transfert,montant,montant_euros,prenom_beneficiaire," +
            "nom_beneficiare,telephone_beneficiaire,client_id) " +
            "values (:code, :date_transfert, :montant, :montant_euros, :prenom, :nom, :telephone, :client_id)";

    private static final String RENDRE = "update TRANSFERT set date_validation = :date_validation, " +
            "gerant_id = :gerant_id where code = :code";

    private static final String TRANSFERT_PAR_CODE ="select prenom, nom, telephone, date_transfert, date_validation, " +
            "montant_euros, taux, code from TRANSFERT where code = :code";


    private String TRANFERT_ENCOURS_CLIENT = "select code,date_transfert,montant,montant_euros,prenom_beneficiaire," +
            "nom_beneficiare,telephone_beneficiaire where client_id = :client_id and date_validation is null";

    private String TRANFERT_RENDUS_CLIENT = "select code,date_transfert,montant,montant_euros,prenom_beneficiaire," +
            "nom_beneficiare,telephone_beneficiaire, date_validation " +
            "where client_id = :client_id and date_validation is not null";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TransfertRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void creer(Transfert transfert) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("code", transfert.getCode());
        params.addValue("date_transfert", LocalDateTime.now());
        params.addValue("montant_euros", transfert.getMontantEuros());
        params.addValue("prenom", transfert.getPrenomBeneficiaire());
        params.addValue("nom", transfert.getNomBeneficiaire());
        params.addValue("telephone", transfert.getTelephoneBeneficiaire());
        params.addValue("client_id", transfert.getClientId());
        namedParameterJdbcTemplate.update(AJOUTER, params);
    }

    public void rendreTransfert(String codeTransfert,String gerantId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("gerant_id", gerantId);
        params.addValue("date_validation", LocalDateTime.now());
        params.addValue("code",codeTransfert);
        namedParameterJdbcTemplate.update(RENDRE, params);
    }

    public Transfert rechercherTransfert(String code) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("code", code);
        return namedParameterJdbcTemplate.query(TRANSFERT_PAR_CODE, params, resultSet ->{
            Transfert transfert = new Transfert();
            //transfert.setPrenom(resultSet.getString("prenom"));
            //transfert.setNom(resultSet.getString("nom"));
            //transfert.setTelephone(resultSet.getString("telephone"));
            transfert.setDateTransfert(resultSet.getTimestamp("date_transfert").toLocalDateTime());
            transfert.setDateValidation(resultSet.getTimestamp("date_validation").toLocalDateTime());
            transfert.setMontantEuros(resultSet.getLong("montant_euros"));
            transfert.setTaux(resultSet.getLong("taux"));
            transfert.setCode(resultSet.getString("code"));
            return transfert;
        });
    }

    public int determinerNombreDeTransfertsTotal() {
        return 0;
    }

    public int determinerNombreDeTransfertsEnCours() {
        return 0;
    }

    public int determinerNombreDeTransfertsRendus() {
        return 0;
    }
}
