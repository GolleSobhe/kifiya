package com.kifiya.kobiri.repositories;

import com.kifiya.kobiri.models.Boutique;
import com.kifiya.kobiri.models.Transfert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TransfertRepository {
    String INSERT = "insert into TRANSFERT (code,date,montant_euros,montantgnf,nom,prenom,status,taux,telephone,responsable_id)" +
            "values (:code,:date,:montant_euros,:montantgnf,:nom,:prenom,:status,:taux,:telephone,:responsable_id)";

    String SELECT = "select CODE, DATE, MONTANT_EUROS, MONTANTGNF, NOM, PRENOM, STATUS, TAUX, TELEPHONE from TRANSFERT";

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

    public List<Transfert> findAll() {
        return namedParameterJdbcTemplate.query(SELECT, (resultat, i) -> {
            Transfert transfert = new Transfert();
            transfert.setCode(resultat.getString("CODE"));
            transfert.setDate(resultat.getDate("DATE"));
            transfert.setMontantEuros(resultat.getLong("MONTANT_EUROS"));
            transfert.setMontantGNF(resultat.getLong("MONTANTGNF"));
            transfert.setNom(resultat.getString("NOM"));
            transfert.setPrenom(resultat.getString("PRENOM"));
            transfert.setStatus(resultat.getBoolean("STATUS"));
            transfert.setTaux(resultat.getDouble("TAUX"));
            transfert.setTelephone(resultat.getString("TELEPHONE"));

            return transfert;
        });
    }

    public List<Transfert> findTransfertByResponsable(Long id) {
        return  null;
    }

    public Integer determinerNombreDeTransfertsTotal() {
        String sql = "SELECT COUNT(*) FROM TRANSFERT";
        return namedParameterJdbcTemplate.queryForObject(sql, (SqlParameterSource) null, Integer.class);
    }

    public int determinerNombreDeTransfertsEnCours() {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("status", false);
        String sql = "SELECT COUNT(*) FROM TRANSFERT WHERE status = :status";
        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
    }

    public int determinerNombreDeTransfertsRendus() {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("status", true);
        String sql = "SELECT COUNT(*) FROM TRANSFERT WHERE status = :status";
        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
    }
}
