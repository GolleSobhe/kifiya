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
    
    String INSERT = "insert into BOUTIQUE (date, email, nom, telephone, ville)" +
            "values (:date, :email, :nom, :telephone, :ville)";
    String SELECT = "select VILLE, NOM, EMAIL, TELEPHONE, DATE from BOUTIQUE";

    public List<Boutique> findAll() {
        return namedParameterJdbcTemplate.query(SELECT, (resultSet, i) -> {
            Boutique boutique = new Boutique();
            boutique.setDate(resultSet.getDate("DATE"));
            boutique.setEmail(resultSet.getString("EMAIL"));
            boutique.setNom(resultSet.getString("NOM"));
            boutique.setTelephone(resultSet.getString("TELEPHONE"));
            boutique.setVille(resultSet.getString("VILLE"));
            return boutique;
        });
    }

    public void save(Boutique boutique) {
        namedParameterJdbcTemplate.update(INSERT, new MapSqlParameterSource().addValue("date",boutique.getDate())
                .addValue("ville",boutique.getVille())
                .addValue("nom",boutique.getNom())
                .addValue("email",boutique.getEmail())
                .addValue("telephone",boutique.getTelephone()));
    }

    public Integer nombreDeBoutiques() {
        String sql = "SELECT COUNT(*) FROM BOUTIQUE";
        return namedParameterJdbcTemplate.queryForObject(sql, (SqlParameterSource) null, Integer.class);
    }
}
