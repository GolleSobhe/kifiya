package com.kifiya.kobiri.repositories;

import com.kifiya.kobiri.models.Gerant;
import com.kifiya.kobiri.models.Transfert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GerantRepository {

    MapSqlParameterSource parameters = new MapSqlParameterSource();

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String CONNEXION = "Select nom,prenom,password from GERANT where login = :login";

    private static final String INSERT = "INSERT into GERANT(id,nom,prenom,telephone,password) " +
            "VALUES(:id,:nom,:prenom,:telephone,:password)";
    private static final String SELECT ="Select prenom, nom, telephone, date_transfert, date_validation, montant_euros, taux " +
            "From TRANSFERT where date_validation IS NULL And gerant_id  IS NULL";

    private static final String SELECT_CODE ="Select prenom, nom, telephone, date_transfert, date_validation, montant_euros, taux, code " +
            "From TRANSFERT Where code = :code";
    private static final String UPDATE = "UPDATE TRANSFERT SET date_validation = :date_validation, gerant_id = :gerant_id Where code = :code";

    public void ajouterGerant(Gerant gerant){
        parameters.addValue("id",gerant.getId());
        parameters.addValue("nom",gerant.getNom());
        parameters.addValue("prenom",gerant.getPrenom());
        parameters.addValue("telephone",gerant.getTelephone());
        parameters.addValue("password",gerant.getPassword());
        namedParameterJdbcTemplate.update(INSERT,parameters);
    }


    public Gerant connexion(String login) {
        parameters.addValue("login", login);
        return namedParameterJdbcTemplate.query(CONNEXION, parameters, resultSet -> {
            if (resultSet.next()) {
                Gerant gerant = new Gerant();
                gerant.setNom(resultSet.getString("nom"));
                gerant.setPrenom(resultSet.getString("prenom"));
                gerant.setPassword(resultSet.getString("password"));
                return gerant;
            }
            return null;
        });
    }

    public List<Transfert> trouverTransfertParStatus() {
        return namedParameterJdbcTemplate.query(SELECT, (resultSet, i) ->{
            Transfert transfert = new Transfert();
            //transfert.setPrenom(resultSet.getString("prenom"));
            //transfert.setNom(resultSet.getString("nom"));
            //transfert.setTelephone(resultSet.getString("telephone"));
            transfert.setDateTransfert(resultSet.getDate("date_transfert"));
            transfert.setDateValidation(resultSet.getDate("date_validation"));
            transfert.setMontantEuros(resultSet.getLong("montant_euros"));
            transfert.setTaux(resultSet.getDouble("taux"));
            return transfert;
        });
    }

    public List<Transfert> rechercherTransfert(String code) {
        parameters.addValue("code", code);
        return namedParameterJdbcTemplate.query(SELECT_CODE, parameters, (resultSet, i) ->{
            Transfert transfert = new Transfert();
            //transfert.setPrenom(resultSet.getString("prenom"));
            //transfert.setNom(resultSet.getString("nom"));
            //transfert.setTelephone(resultSet.getString("telephone"));
            transfert.setDateTransfert(resultSet.getDate("date_transfert"));
            transfert.setDateValidation(resultSet.getDate("date_validation"));
            transfert.setMontantEuros(resultSet.getLong("montant_euros"));
            transfert.setTaux(resultSet.getDouble("taux"));
            transfert.setCode(resultSet.getString("code"));
            return transfert;
        });
    }

    public void validerTransfert(Transfert transfert) {
        //parameters.addValue("gerant_id", transfert.getGerant().getId());
        parameters.addValue("date_validation", transfert.getDateValidation());
        parameters.addValue("code", transfert.getCode());
        namedParameterJdbcTemplate.update(UPDATE, parameters);
    }
}
