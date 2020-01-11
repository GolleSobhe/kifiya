package com.kifiya.kobiri.repositories;

import com.kifiya.kobiri.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository {

    private static String INSERT_USER = "insert into users(nom,prenom,email,pays,ville,adresse,telephone,password)" +
            "values (?,?,?,?,?,?,?,?)";

    private static String QUERY_USER = "select nom,prenom from users where email = ? and password = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User register(User user){
        jdbcTemplate.update(INSERT_USER,user.getNom(),user.getPrenom(),user.getEmail(),user.getPays(),user.getVille(),
                user.getAdresse(),user.getTelephone(),user.getPassword());
        return user;
    }

    public User connection(String email,String password){
        return jdbcTemplate.query(QUERY_USER, new Object[] {email,password}, new ResultSetExtractor<User>() {
            @Override
            public User extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if(resultSet.next()){
                    User user = new User();
                    user.setNom(resultSet.getString(1));
                    user.setPrenom(resultSet.getString(2));
                    return user;
                }
                return null;
            }
        });

    }
}
