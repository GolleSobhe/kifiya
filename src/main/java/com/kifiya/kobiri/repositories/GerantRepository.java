package com.kifiya.kobiri.repositories;

import com.kifiya.kobiri.models.Gerant;
import com.kifiya.kobiri.models.Transfert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GerantRepository {

    MapSqlParameterSource parameters = new MapSqlParameterSource();

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String CREER = "insert into GERANT(login,nom,prenom,password,adresse,code,telephone,ville) " +
            "values (:login, :nom, :prenom, :password, :adresse, :code, :telephone, :ville)";

    private static final String SELECT ="Select B.prenom, B.nom, B.telephone, T.date_transfert, T.date_validation, T.montant_euros, T.taux " +
            "From TRANSFERT T, BENEFICIAIRE B  where T.BENEFICIAIRE_ID = B.ID And T.date_validation IS NULL";

    private static final String SELECT_HISTORIQUE="SELECT B.NOM , B.PRENOM , B.TELEPHONE, T.CODE, T.DATE_TRANSFERT, T.DATE_VALIDATION,T.MONTANT_EUROS*T.TAUX as MONTANT_GNF "+
            "from  TRANSFERT T,  GERANT_TRANSFERTS GT, BENEFICIAIRE B WHERE GT.GERANT_ID = :idGenant AND GT.TRANSFERTS_ID = T.ID AND T.BENEFICIAIRE_ID = B.ID";

    private static final String SELECT_STATISTIQUE="SELECT SUM(T.MONTANT_EUROS*T.TAUX) as montant, COUNT(*) as nombre from  TRANSFERT T,  GERANT_TRANSFERTS GT  WHERE GT.GERANT_ID = :idGenant AND T.ID = GT.TRANSFERTS_ID";

    private static final String CONNEXION = "Select nom,prenom,password from GERANT where login = :login";

    public GerantRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void creer(Gerant gerant){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("login",gerant.getLogin());
        params.addValue("nom",gerant.getNom());
        params.addValue("prenom",gerant.getPrenom());
        params.addValue("telephone",gerant.getTelephone());
        params.addValue("password",gerant.getPassword());
        params.addValue("code",gerant.getCode());
        params.addValue("adresse",gerant.getAdresse());
        params.addValue("ville",gerant.getVille());
        namedParameterJdbcTemplate.update(CREER,params);
    }

    public Gerant connexion(String login) {
        parameters.addValue("login", login);
        return namedParameterJdbcTemplate.query(CONNEXION, parameters, resultSet -> {
            if (resultSet.next()) {
                Gerant gerant = new Gerant();
                gerant.setNom(resultSet.getString("nom"));
                gerant.setPrenom(resultSet.getString("prenom"));
                gerant.setPassword(resultSet.getString("password"));
                return gerant;
            }
            return null;
        });
    }

    public List<Transfert> trouverTransfertParStatus() {
        return namedParameterJdbcTemplate.query(SELECT, (resultSet, i) ->{
            Transfert transfert = new Transfert();
            //transfert.setPrenom(resultSet.getString("prenom"));
            //transfert.setNom(resultSet.getString("nom"));
            //transfert.setTelephone(resultSet.getString("telephone"));
            transfert.setDateTransfert(resultSet.getTimestamp("date_transfert").toLocalDateTime());
            transfert.setDateValidation(resultSet.getTimestamp("date_validation").toLocalDateTime());
            transfert.setMontantEuros(resultSet.getLong("montant_euros"));
            transfert.setTaux(resultSet.getLong("taux"));
            return transfert;
        });
    }

    public List<Transfert> rechercherTransfert(Long id) {
        parameters.addValue("idGenant", id);
        return namedParameterJdbcTemplate.query(SELECT_HISTORIQUE, parameters, (resultSet,i) -> {
            Transfert transfert = new Transfert();
            return transfert;
        });
    }

    public Map<String, String> obtenirStatistique(Long id) {
        parameters.addValue("idGenant", id);
        return namedParameterJdbcTemplate.query(SELECT_STATISTIQUE, parameters, new ResultSetExtractor<Map>() {
            @Override
            public Map extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                HashMap<String,String> mapRet= new HashMap<String,String>(){{
                    put("montant", "0");
                    put("nombre", "0");
                }};
                if (resultSet.next()){
                    mapRet.put("montant", resultSet.getString("montant"));
                    mapRet.put("nombre", resultSet.getString("nombre"));
                }
                return mapRet;
            }
        });
    }
}
