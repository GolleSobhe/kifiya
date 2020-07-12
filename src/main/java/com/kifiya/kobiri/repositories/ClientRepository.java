package com.kifiya.kobiri.repositories;

import com.kifiya.kobiri.models.Client;
import com.kifiya.kobiri.models.Utilisateur;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;

@Repository
public class ClientRepository {

    private static final String CREER = "insert into CLIENT(email,nom,prenom,password,telephone,adresse,code_postale," +
            "ville,pays,est_valide)" +
            "values(:email, :nom, :prenom,:password, :telephone, :adresse, :code_postale, :ville, :pays, 'NON')";

    private static final String VALIDER_CLIENT = "Update CLIENT set est_valide = 'OUI' where email = :email";

    private static final String CLIENT_EXISTE = "Select email from CLIENT where email = :email";

    private static final String RECHERCHER_CLIENT = "Select email,nom,prenom,telephone,adresse,code_postale,ville,pays " +
            "from CLIENT where email = :email";

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

    public void validerClient(String email){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email",email);
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
