package com.kifiya.kobiri.services;

import com.kifiya.kobiri.models.user.Role;
import com.kifiya.kobiri.models.user.User;
import com.kifiya.kobiri.repositories.RoleRepository;
import com.kifiya.kobiri.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private static String QUERY_USER = "select nom,prenom from user where email = ? and password = ?";

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
