package com.kifiya.kobiri.services;

import com.kifiya.kobiri.models.user.Role;
import com.kifiya.kobiri.models.user.User;
import com.kifiya.kobiri.repositories.RoleRepository;
import com.kifiya.kobiri.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    private JdbcTemplate jdbcTemplate;


    private static String QUERY_USER = "select id, nom,prenom,password from user where enabled='true' and email = ?";

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
        if(user.getPassword() != null){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }

    public User findByConfirmationToken(String confirmationToken) {
        return userRepository.findByConfirmationToken(confirmationToken);
    }

    public User connection(String email, String password) {

        return jdbcTemplate.query(QUERY_USER, new Object[] {email}, new ResultSetExtractor<User>() {
            @Override
            public User extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if(resultSet.next()){
                    User user = new User();
                    user.setId(resultSet.getLong(1));
                    user.setNom(resultSet.getString(2));
                    user.setPrenom(resultSet.getString(3));
                    user.setPassword(resultSet.getString(4));
                    if(bCryptPasswordEncoder.matches(password, user.getPassword())){
                        return user;
                    }
                }
                return null;
            }
        });
    }

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        return null;
    }

}
