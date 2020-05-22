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

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String SELECT ="Select B.prenom, B.nom, B.telephone, T.date_transfert, T.date_validation, T.montant_euros, T.taux " +
            "From TRANSFERT T, BENEFICIAIRE B  where T.BENEFICIAIRE_ID = B.ID And T.date_validation IS NULL";

    private static final String SELECT_HISTORIQUE="SELECT B.NOM , B.PRENOM , B.TELEPHONE, T.CODE, T.DATE_TRANSFERT, T.DATE_VALIDATION,T.MONTANT_EUROS*T.TAUX as MONTANT_GNF "+
            "from  TRANSFERT T,  GERANT_TRANSFERTS GT, BENEFICIAIRE B WHERE GT.GERANT_ID = :idGenant AND GT.TRANSFERTS_ID = T.ID AND T.BENEFICIAIRE_ID = B.ID";

    private static final String SELECT_STATISTIQUE="SELECT SUM(T.MONTANT_EUROS*T.TAUX) as montant, COUNT(*) as nombre from  TRANSFERT T,  GERANT_TRANSFERTS GT  WHERE GT.GERANT_ID = :idGenant AND T.ID = GT.TRANSFERTS_ID";

    private static final String CONNEXION = "Select nom,prenom,password from GERANT where login = :login";

    private static final String INSERT = "INSERT into GERANT(id,nom,prenom,telephone,password) " +
            "VALUES(:id,:nom,:prenom,:telephone,:password)";

    private static final String SELECT_CODE ="Select prenom, nom, telephone, date_transfert, date_validation, montant_euros, taux, code " +
            "From TRANSFERT Where code = :code";
    private static final String UPDATE = "UPDATE TRANSFERT SET date_validation = :date_validation, gerant_id = :gerant_id Where code = :code";

    public void ajouterGerant(Gerant gerant){
        parameters.addValue("id",gerant.getId());
        parameters.addValue("nom",gerant.getNom());
        parameters.addValue("prenom",gerant.getPrenom());
        parameters.addValue("telephone",gerant.getTelephone());
        parameters.addValue("password",gerant.getPassword());
        namedParameterJdbcTemplate.update(INSERT,parameters);
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
            transfert.setDateTransfert(resultSet.getDate("date_transfert"));
            transfert.setDateValidation(resultSet.getDate("date_validation"));
            transfert.setMontantEuros(resultSet.getLong("montant_euros"));
            transfert.setTaux(resultSet.getDouble("taux"));
            return transfert;
        });
    }

    public List<Transfert> rechercherTransfert(String code) {
        parameters.addValue("code", code);
        return namedParameterJdbcTemplate.query(SELECT_CODE, parameters, (resultSet, i) ->{
            Transfert transfert = new Transfert();
            //transfert.setPrenom(resultSet.getString("prenom"));
            //transfert.setNom(resultSet.getString("nom"));
            //transfert.setTelephone(resultSet.getString("telephone"));
            transfert.setDateTransfert(resultSet.getDate("date_transfert"));
            transfert.setDateValidation(resultSet.getDate("date_validation"));
            transfert.setMontantEuros(resultSet.getLong("montant_euros"));
            transfert.setTaux(resultSet.getDouble("taux"));
            transfert.setCode(resultSet.getString("code"));
            return transfert;
        });
    }

    public void validerTransfert(Transfert transfert) {
        //parameters.addValue("gerant_id", transfert.getGerant().getId());
        parameters.addValue("date_validation", transfert.getDateValidation());
        parameters.addValue("code", transfert.getCode());
        namedParameterJdbcTemplate.update(UPDATE, parameters);
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
