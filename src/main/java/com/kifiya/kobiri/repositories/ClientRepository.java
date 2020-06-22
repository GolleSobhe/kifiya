package com.kifiya.kobiri.repositories;

import com.kifiya.kobiri.models.Client;
import com.kifiya.kobiri.models.Utilisateur;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepository {

    private static final String CREER = "insert into CLIENT(email,nom,prenom,password,telephone,adresse,code_postale," +
            "ville,pays,est_valide)" +
            "values(:email, :nom, :prenom,:password, :telephone, :adresse, :code_postale, :ville, :pays, 'NON')";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ClientRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void save(Client client){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email",client.getEmail());
        params.addValue("nom",client.getNom());
        params.addValue("prenom",client.getPrenom());
        params.addValue("password",client.getPassword());
        params.addValue("telephone",client.getTelephone());
        params.addValue("adresse",client.getAdresse());
        params.addValue("code_postale",client.getCodePostale());
        params.addValue("ville",client.getVille());
        params.addValue("pays",client.getPays());
        namedParameterJdbcTemplate.update(CREER,params);
    }

    public  Client findByEmail(String email){
        return null;
    }

    public Utilisateur findByConfirmationToken(String confirmationToken){
        return null;
    }

}
