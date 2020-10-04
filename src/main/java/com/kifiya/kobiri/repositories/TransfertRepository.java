package com.kifiya.kobiri.repositories;

import com.kifiya.kobiri.models.Transfert;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class TransfertRepository {

    private final String AJOUTER = "insert into TRANSFERT(code,date_transfert,montant_euros,taux, frais, beneficiaire_id," +
            "boutique_id,client_id) " +
            "values (:code, :date_transfert, :montant_euros, :taux, :frais, :beneficiaire_id, :boutique_id, :client_id)";

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
        params.addValue("taux", transfert.getTaux());
        params.addValue("frais", transfert.getFrais());
        params.addValue("beneficiaire_id", transfert.getBeneficiaire().getTelephone());
        params.addValue("boutique_id", transfert.getBoutique().getNom());
        params.addValue("client_id", transfert.getClient().getEmail());
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
            transfert.setTaux(resultSet.getDouble("taux"));
            transfert.setCode(resultSet.getString("code"));
            return transfert;
        });
    }

    public int determinerNombreDeTransfertsTotal() {
        String sql = "SELECT COUNT(*) FROM TRANSFERT";
        return namedParameterJdbcTemplate.queryForObject(sql, (SqlParameterSource) null, Integer.class);
    }

    public int determinerNombreDeTransfertsEnCours() {
        String sql = "SELECT COUNT(*) FROM TRANSFERT where DATE_VALIDATION is null";
        return namedParameterJdbcTemplate.queryForObject(sql, (SqlParameterSource) null, Integer.class);
    }

    public int determinerNombreDeTransfertsRendus() {
        String sql = "SELECT COUNT(*) FROM TRANSFERT where DATE_VALIDATION is not null";
        return namedParameterJdbcTemplate.queryForObject(sql, (SqlParameterSource) null, Integer.class);
    }
}
