package com.kifiya.kobiri.repositories;

import com.kifiya.kobiri.models.Beneficiaire;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class BeneficiaireRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final String CREER_BENEFICIAIRE = "Insert into BENEFICIAIRE(nom,prenom,telephone,client_id) " +
            "values(:nom, :prenom, :telephone, :client_id)";

    private final String RECHERCHER_BENEFICIARES = "Select nom,prenom,telephone from BENEFICIAIRE " +
            "where client_id = :client_id";

    private final String BENEFICIAIRE_EXISTE = "Select id from BENEFICIAIRE where telephone = :telephone "+
            "and client_id = :client_id";

    public BeneficiaireRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Long ajouterBeneficiaire(Beneficiaire beneficiaire){
        MapSqlParameterSource params = new MapSqlParameterSource();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        params.addValue("nom",beneficiaire.getNom());
        params.addValue("prenom",beneficiaire.getPrenom());
        params.addValue("telephone",beneficiaire.getTelephone());
        params.addValue("client_id",beneficiaire.getClientId());
        namedParameterJdbcTemplate.update(CREER_BENEFICIAIRE,params,  keyHolder, new String[]{"ID"});
        return keyHolder.getKey().longValue();
    }

    public List<Beneficiaire>  listerBeneficiaires(String clientId){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("client_id",clientId);
        return namedParameterJdbcTemplate.query(RECHERCHER_BENEFICIARES,params,(resultSet, i) -> {
            Beneficiaire beneficiaire = new Beneficiaire();
            beneficiaire.setNom(resultSet.getString("nom"));
            beneficiaire.setPrenom(resultSet.getString("prenom"));
            beneficiaire.setTelephone(resultSet.getString("telephone"));
            return beneficiaire;
        });
    }

    public boolean beneficiaireExists(String telephone, String client_id){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("telephone",telephone);
        params.addValue("client_id",client_id);
        return namedParameterJdbcTemplate.query(BENEFICIAIRE_EXISTE,params, ResultSet::next);
    }

}