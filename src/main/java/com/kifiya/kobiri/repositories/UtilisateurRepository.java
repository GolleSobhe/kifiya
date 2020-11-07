package com.kifiya.kobiri.repositories;

import com.kifiya.kobiri.models.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class UtilisateurRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static String UTILISATEUR_CONNEXION = "select email,password, role, active from CONNEXION where active='1' and email = ?";

    public Optional<Utilisateur> userByEmail(String email) {
        return jdbcTemplate.query(UTILISATEUR_CONNEXION, new Object[] {email}, new ResultSetExtractor<Optional<Utilisateur>>() {

            @Override
            public Optional<Utilisateur> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if(resultSet.next()){
                    Utilisateur utilisateur = new Utilisateur();
                    utilisateur.setEmail(resultSet.getString(1));
                    utilisateur.setPassword(resultSet.getString(2));
                    utilisateur.setRole(resultSet.getString(3));
                    utilisateur.setActive(resultSet.getBoolean(4));
                    return Optional.ofNullable(utilisateur);
                }
                return null;
            }
        });
    }
}
