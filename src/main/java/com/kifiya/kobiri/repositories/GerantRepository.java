package com.kifiya.kobiri.repositories;

import com.kifiya.kobiri.models.Gerant;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class GerantRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String CONNEXION = "Select nom,prenom,password from GERANT where login = :login";

    private static final String ADD_GERANT = "INSERT into GERANT(id,nom,prenom,telephone,password) " +
            "VALUES(:id,:nom,:prenom,:telephone,:password)";

    public GerantRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void ajouterGerant(Gerant gerant){
        var parameters = new MapSqlParameterSource();
        parameters.addValue("id",gerant.getId());
        parameters.addValue("nom",gerant.getNom());
        parameters.addValue("prenom",gerant.getPrenom());
        parameters.addValue("telephone",gerant.getTelephone());
        parameters.addValue("password",gerant.getPassword());
        namedParameterJdbcTemplate.update(ADD_GERANT,parameters);
    }


    public Gerant connexion(String login) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("login", login);
        return namedParameterJdbcTemplate.query(CONNEXION, parameters, resultSet -> {
            if (resultSet.next()) {
                var gerant = new Gerant();
                gerant.setNom(resultSet.getString("nom"));
                gerant.setPrenom(resultSet.getString("prenom"));
                gerant.setPassword(resultSet.getString("password"));
                return gerant;
            }
            return null;
        });
    }

    public Integer nombreDeGerants() {
        String sql = "SELECT COUNT(*) FROM GERANT";
        return namedParameterJdbcTemplate.queryForObject(sql, (SqlParameterSource) null, Integer.class);
    }
}
