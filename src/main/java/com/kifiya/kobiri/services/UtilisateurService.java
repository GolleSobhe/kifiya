package com.kifiya.kobiri.services;

import com.kifiya.kobiri.models.Client;
import com.kifiya.kobiri.models.Gerant;
import com.kifiya.kobiri.models.Role;
import com.kifiya.kobiri.models.Utilisateur;
import com.kifiya.kobiri.repositories.RoleRepository;
import com.kifiya.kobiri.repositories.UtilisateurRepository;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
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
    private static Logger logger = Logger.getLogger(UtilisateurService.class);
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    private static final String SELECT = "select id, nom,prenom,password from UTILISATEUR where enabled='true' and email = ?";

    public Utilisateur findUtilisateurByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    public Utilisateur ajouter(Utilisateur utilisateur) {
        /**
        if(utilisateur.getPassword() != null){
            utilisateur.setPassword(bCryptPasswordEncoder.encode(utilisateur.getPassword()));
        }
         */
        Role userRole = roleRepository.findByDroit("ADMIN");
        if (utilisateur instanceof Client){
            userRole = roleRepository.findByDroit("CLIENT");
        }
        if (utilisateur instanceof Gerant){
            userRole = roleRepository.findByDroit("GERANT");
        }
        utilisateur.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur findByConfirmationToken(String confirmationToken) {
        return utilisateurRepository.findByConfirmationToken(confirmationToken);
    }

    public Utilisateur connection(String email) {

        return jdbcTemplate.query(SELECT, new Object[] {email}, new ResultSetExtractor<Utilisateur>() {
            @Override
            public Utilisateur extractData(ResultSet resultSet) {
                try {
                    if(resultSet.next()){
                        Utilisateur utilisateur = new Utilisateur();
                        utilisateur.setId(resultSet.getLong(1));
                        utilisateur.setNom(resultSet.getString(2));
                        utilisateur.setPrenom(resultSet.getString(3));
                        utilisateur.setPassword(resultSet.getString(4));
                        /**
                        if(bCryptPasswordEncoder.matches(password, utilisateur.getPassword())){
                            return utilisateur;
                        }
                         */
                    }
                } catch (SQLException e) {
                    logger.error(e.getSQLState());
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
