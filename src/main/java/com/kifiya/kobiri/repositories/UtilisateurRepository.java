package com.kifiya.kobiri.repositories;

import com.kifiya.kobiri.models.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UtilisateurRepository {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static String UTILISATEUR_CONNEXION = "select email,password, role from CONNEXION where active='1' and email = ?";

    public Utilisateur connexion(String email, String password) {
        return jdbcTemplate.query(UTILISATEUR_CONNEXION, new Object[] {email}, new ResultSetExtractor<Utilisateur>() {

            @Override
            public Utilisateur extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if(resultSet.next()){
                    Utilisateur utilisateur = new Utilisateur();
                    utilisateur.setEmail(resultSet.getString(1));
                    utilisateur.setPassword(resultSet.getString(2));
                    utilisateur.setRole(resultSet.getString(3));
                    if(bCryptPasswordEncoder.matches(password, utilisateur.getPassword())){
                        return utilisateur;
                    }
                }
                return null;
            }
        });
    }
}
