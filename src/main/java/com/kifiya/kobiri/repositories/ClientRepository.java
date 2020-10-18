package com.kifiya.kobiri.repositories;

import com.kifiya.kobiri.models.Client;
import com.kifiya.kobiri.models.Utilisateur;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;

@Repository
public class ClientRepository {

    private static final String AJOUTER_UTILISATEUR = "insert into CONNEXION(email,password,active,role) "+
            "values(:email,:password,:active,:role);";

    private static final String AJOUTER_CLIENT = "insert into CLIENT(email,nom,prenom,telephone,adresse,code_postale,ville,pays) " +
            "values(:email, :nom, :prenom, :telephone, :adresse, :code_postale, :ville, :pays);";


    private static final String VALIDER_CLIENT = "Update CONNEXION set active = :active,password = :password  where email = :email";

    private static final String CLIENT_EXISTE = "Select email from CLIENT where email = :email";

    private static final String RECHERCHER_CLIENT = "Select email,nom,prenom,telephone,adresse,code_postale,ville,pays " +
            "from CLIENT where email = :email";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ClientRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void ajouter(Client client){
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
        params.addValue("active",client.isActive());
        params.addValue("role",client.getRole());
        namedParameterJdbcTemplate.update(AJOUTER_UTILISATEUR,params);
        namedParameterJdbcTemplate.update(AJOUTER_CLIENT,params);
    }

    public void validerClient(String email, String password){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email",email);
        params.addValue("active", true);
        params.addValue("password", password);
        namedParameterJdbcTemplate.update(VALIDER_CLIENT,params);
    }


    public  Client findByEmail(String email){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email",email);
        return namedParameterJdbcTemplate.query(RECHERCHER_CLIENT,params,resultSet -> {
            if(!resultSet.next()){
                return null;
            }
            Client client = new Client();
            client.setEmail(resultSet.getString("email"));
            client.setNom(resultSet.getString("nom"));
            client.setPrenom(resultSet.getString("prenom"));
            client.setAdresse(resultSet.getString("adresse"));
            client.setVille(resultSet.getString("ville"));
            client.setPays(resultSet.getString("pays"));
            client.setCodePostale(resultSet.getString("code_postal"));
            client.setTelephone(resultSet.getString("telephone"));
            return client;
        });
    }

    public boolean clientExists(String email){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email",email);
        return namedParameterJdbcTemplate.query(CLIENT_EXISTE,params, ResultSet::next);
    }

}
