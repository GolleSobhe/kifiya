package com.kifiya.kobiri.repositories;

import com.kifiya.kobiri.models.VerificationToken;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class VerificationTokenRepository {

    private static final String INSERER_TOKEN = "Insert into VERIFICATION_TOKEN(token,idUser,expirationDate) " +
            "values(:token, :idUser, :expirationDate)";

    private static final String RECHERCHER_VERIFICATION = "Select idUser,expirationDate from VERIFICATION_TOKEN " +
            "where token = :token";

    private static final String SUPPRIMER_TOKEN = "Delete from VERIFICATION_TOKEN where token = :token";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public VerificationTokenRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void creerToken(VerificationToken verificationToken){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("token",verificationToken.getToken());
        params.addValue("idUser",verificationToken.getIdUser());
        params.addValue("expirationDate",verificationToken.getExpirationDate());
        namedParameterJdbcTemplate.update(INSERER_TOKEN,params);
    }

    public VerificationToken recupererToken(String token){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("token", token);
        return namedParameterJdbcTemplate.query(RECHERCHER_VERIFICATION,params,resultSet -> {
           if(!resultSet.next()){
               return null;
           }
           VerificationToken verificationToken = new VerificationToken();
           verificationToken.setToken(token);
           verificationToken.setIdUser(resultSet.getString("idUser"));
           verificationToken.setExpirationDate(resultSet.getTimestamp("expirationDate").toLocalDateTime());
           return verificationToken;
        });
    }

    public void supprimerToken(String token){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("token", token);
        namedParameterJdbcTemplate.update(SUPPRIMER_TOKEN,params);
    }
}
