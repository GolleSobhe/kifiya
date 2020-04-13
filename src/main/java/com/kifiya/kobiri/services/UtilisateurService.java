package com.kifiya.kobiri.services;

import com.kifiya.kobiri.models.Role;
import com.kifiya.kobiri.models.Utilisateur;
import com.kifiya.kobiri.repositories.RoleRepository;
import com.kifiya.kobiri.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@Service
@Transactional
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    private JdbcTemplate jdbcTemplate;


    private static String SELECT = "select id, nom,prenom,password from UTILISATEUR where enabled='true' and email = ?";

    public Utilisateur findUtilisateurByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    public Utilisateur save(Utilisateur utilisateur) {
        if(utilisateur.getPassword() != null){
            utilisateur.setPassword(bCryptPasswordEncoder.encode(utilisateur.getPassword()));
        }
        Role userRole = roleRepository.findByRole("ADMIN");
        utilisateur.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur findByConfirmationToken(String confirmationToken) {
        return utilisateurRepository.findByConfirmationToken(confirmationToken);
    }

    public Utilisateur connection(String email, String password) {

        return jdbcTemplate.query(SELECT, new Object[] {email}, new ResultSetExtractor<Utilisateur>() {
            @Override
            public Utilisateur extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if(resultSet.next()){
                    Utilisateur utilisateur = new Utilisateur();
                    utilisateur.setId(resultSet.getLong(1));
                    utilisateur.setNom(resultSet.getString(2));
                    utilisateur.setPrenom(resultSet.getString(3));
                    utilisateur.setPassword(resultSet.getString(4));
                    if(bCryptPasswordEncoder.matches(password, utilisateur.getPassword())){
                        return utilisateur;
                    }
                }
                return null;
            }
        });
    }

    public Utilisateur findById(Long id) {
        Optional<Utilisateur> user = utilisateurRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        return null;
    }

}
