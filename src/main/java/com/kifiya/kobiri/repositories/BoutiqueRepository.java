package com.kifiya.kobiri.repositories;

import com.kifiya.kobiri.models.Boutique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoutiqueRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    private static final String INSERT = "insert into BOUTIQUE (nom, ville, description) " +
            "values (:nom, :ville, :description)";

    private static final String SELECT = "select nom,ville,description  from BOUTIQUE";

    public List<Boutique> findAll() {
        return namedParameterJdbcTemplate.query(SELECT, (resultSet, i) -> {
            Boutique boutique = new Boutique();
            boutique.setNom(resultSet.getString("nom"));
            boutique.setVille(resultSet.getString("ville"));
            boutique.setDescription(resultSet.getString("description"));
            return boutique;
        });
    }

    public void ajouter(Boutique boutique) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ville",boutique.getVille());
        params.addValue("nom",boutique.getNom());
        params.addValue("description",boutique.getDescription());
        namedParameterJdbcTemplate.update(INSERT, params);
    }

    public Integer nombreDeBoutiques() {
        String sql = "SELECT COUNT(*) FROM BOUTIQUE";
        return namedParameterJdbcTemplate.queryForObject(sql, (SqlParameterSource) null, Integer.class);
    }
}
