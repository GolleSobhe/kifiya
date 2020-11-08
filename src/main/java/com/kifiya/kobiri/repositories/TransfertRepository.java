package com.kifiya.kobiri.repositories;

import com.kifiya.kobiri.models.Boutique;
import com.kifiya.kobiri.models.Beneficiaire;
import com.kifiya.kobiri.models.Client;
import com.kifiya.kobiri.models.EtatTransfert;
import com.kifiya.kobiri.models.Transfert;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransfertRepository {

    private final String AJOUTER = "insert into TRANSFERT(code,date_transfert,montant_euros,taux, frais, beneficiaire_id," +
            "boutique_id,client_id,etat_transfert) " +
            "values (:code, :date_transfert, :montant_euros, :taux, :frais, :beneficiaire_id, :boutique_id, :client_id," +
            ":etat_transfert)";

    private static final String RENDRE = "update TRANSFERT set date_validation = :date_validation, " +
            "gerant_id = :gerant_id,etat_transfert = :etat_transfert where code = :code";

    private static final String DESACTIVER = "update TRANSFERT set etat_transfert = :etat_transfert" +
            " where code = :code";

    private static final String TRANSFERT_PAR_CODE ="select prenom, nom, telephone, date_transfert, date_validation, " +
            "montant_euros, taux, code from TRANSFERT where code = :code";

    private static final String COUNTER_PAR_ETAT = "Select count(*) from TRANSFERT where etat_transfert = :etat_transfert";

    private String TRANFERTS_CLIENT = "select code,date_transfert,montant_euros,etat_transfert,nom,prenom" +
            " from TRANSFERT t, BENEFICIAIRE b " +
            " where t.client_id = :client_id and b.client_id = t.client_id";

    private String TRANSFERTS_PAR_STATUT = "select t.code,t.date_transfert,t.montant_euros,c.nom nomclient," +
            "c.prenom prenomclient, b.nom nombeneficiaire,b.prenom prenombeneficiaire " +
            "from TRANSFERT t, CLIENT c, BENEFICIAIRE b where t.etat_transfert = :etat_transfert and " +
            "t.client_id = c.email and t.beneficiaire_id = b.id and c.email = b.client_id";

    private static final String SELECT_BOUTIQUE ="select nom, ville from BOUTIQUE where nom = :nom";


    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TransfertRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Transfert ajouterTransfert(Transfert transfert) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("code", transfert.getCode());
        params.addValue("date_transfert", LocalDateTime.now());
        params.addValue("montant_euros", transfert.getMontantEuros());
        params.addValue("taux", transfert.getTaux());
        params.addValue("frais", transfert.getFrais());
        params.addValue("beneficiaire_id", transfert.getBeneficiaire().getTelephone());
        params.addValue("boutique_id", transfert.getBoutique().getNom());
        params.addValue("client_id", transfert.getClient().getEmail());
        params.addValue("etat_transfert", EtatTransfert.ENCOURS.name());
        namedParameterJdbcTemplate.update(AJOUTER, params);
        Boutique boutique = namedParameterJdbcTemplate
                .queryForObject(SELECT_BOUTIQUE,
                        new MapSqlParameterSource("nom", transfert.getBoutique().getNom()),
                        BeanPropertyRowMapper.newInstance(Boutique.class));
        transfert.setBoutique(boutique);
        return transfert;
    }

    public void rendreTransfert(String codeTransfert,String gerantId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("gerant_id", gerantId);
        params.addValue("date_validation", LocalDateTime.now());
        params.addValue("code",codeTransfert);
        params.addValue("etat_transfert", EtatTransfert.RENDU.name());
        namedParameterJdbcTemplate.update(RENDRE, params);
    }

    public void desactiverTransfert(String codeTransfert){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("code",codeTransfert);
        params.addValue("etat_transfert", EtatTransfert.DESACTIVE.name());
        namedParameterJdbcTemplate.update(DESACTIVER, params);
    }

    public Transfert rechercherTransfert(String code) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("code", code);
        return namedParameterJdbcTemplate.query(TRANSFERT_PAR_CODE, params, resultSet ->{
            Transfert transfert = new Transfert();
            transfert.setDateTransfert(resultSet.getTimestamp("date_transfert").toLocalDateTime());
            transfert.setDateValidation(resultSet.getTimestamp("date_validation").toLocalDateTime());
            transfert.setMontantEuros(resultSet.getLong("montant_euros"));
            transfert.setTaux(resultSet.getDouble("taux"));
            transfert.setCode(resultSet.getString("code"));
            return transfert;
        });
    }

     public List<Transfert> transfertsClient(String client_id){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("client_id",client_id);
        return namedParameterJdbcTemplate.query(TRANFERTS_CLIENT,params, resultSet -> {
            List<Transfert> results = new ArrayList<>();
            Transfert t;
            Beneficiaire  beneficiaire;
            while (resultSet.next()){
                t = new Transfert();
                t.setDateTransfert(resultSet.getTimestamp("date_transfert").toLocalDateTime());
                t.setMontantEuros(resultSet.getLong("montant_euros"));
                t.setStatut(EtatTransfert.valueOf(resultSet.getString("etat_transfert")));
                beneficiaire = new Beneficiaire();
                beneficiaire.setNom(resultSet.getString("nom"));
                beneficiaire.setPrenom(resultSet.getString("prenom"));
                t.setBeneficiaire(beneficiaire);
                results.add(t);
            }
            return results;
        });
    }

    public List<Transfert> listerTransfertsParStatut(EtatTransfert statut){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("etat_transfert",statut.name());
        return namedParameterJdbcTemplate.query(TRANSFERTS_PAR_STATUT,params, resultSet -> {
            List<Transfert> results = new ArrayList<>();
            Transfert t;
            Client client;
            Beneficiaire beneficiaire;
            while (resultSet.next()){
                t = new Transfert();
                t.setCode(resultSet.getString("code"));
                t.setDateTransfert(resultSet.getTimestamp("date_transfert").toLocalDateTime());
                t.setMontantEuros(resultSet.getLong("montant_euros"));
                client = new Client();
                client.setNom(resultSet.getString("nomclient"));
                client.setPrenom(resultSet.getString("prenomclient"));
                t.setClient(client);
                beneficiaire = new Beneficiaire();
                beneficiaire.setPrenom(resultSet.getString("prenombeneficiaire"));
                beneficiaire.setNom(resultSet.getString("nombeneficiaire"));
                t.setBeneficiaire(beneficiaire);
                results.add(t);
            }
            return results;
        });
    }

    public int determinerNombreDeTransfertsTotal() {
        String sql = "SELECT COUNT(*) FROM TRANSFERT";
        return namedParameterJdbcTemplate.queryForObject(sql, (SqlParameterSource) null, Integer.class);
    }

    public int determinerNombreDeTransfertsEnCours() {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("etat_transfert", EtatTransfert.ENCOURS.name());
        return namedParameterJdbcTemplate.queryForObject(COUNTER_PAR_ETAT, params, Integer.class);
    }

    public int determinerNombreDeTransfertsRendus() {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("etat_transfert", EtatTransfert.RENDU.name());
        return namedParameterJdbcTemplate.queryForObject(COUNTER_PAR_ETAT, params, Integer.class);
    }

    public int determinerNombreDeTransfertsDesactives() {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("etat_transfert", EtatTransfert.DESACTIVE.name());
        return namedParameterJdbcTemplate.queryForObject(COUNTER_PAR_ETAT, params, Integer.class);
    }


}
