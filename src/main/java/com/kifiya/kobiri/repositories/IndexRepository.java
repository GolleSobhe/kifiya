package com.kifiya.kobiri.repositories;

import com.kifiya.kobiri.models.Boutique;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Repository
public class IndexRepository {
    private final JdbcTemplate jdbcTemplate;
    String SELECT_MAP = "SELECT taux, frais, nom, ville,description  FROM PARAMETRE, BOUTIQUE ;";

    public IndexRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<String, Object> obtenirPrametre() {
        Map<String,Object> results = new HashMap<>();
        Set<Boutique> boutiques = new HashSet<>();
        jdbcTemplate.query(SELECT_MAP, (ResultSet resultSet) -> {
            while (resultSet.next()) {
                boutiques.add(Boutique.builder().nom(resultSet.getString("nom"))
                        .ville(resultSet.getString("ville"))
                        .description(resultSet.getString("description")).build());
                results.put("taux", resultSet.getDouble("taux"));
                results.put("frais", resultSet.getDouble("frais"));
            }
        });
        results.put("boutiques", boutiques);
        return results;
    }
}
