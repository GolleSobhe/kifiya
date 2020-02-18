package com.kifiya.kobiri.services;

import com.kifiya.kobiri.models.user.Historic;
import com.kifiya.kobiri.models.user.User;
import com.kifiya.kobiri.repositories.HistoricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HistoricService {

    String INSERT_HISTORY = "insert into HISTORIC(DATE,MONTANT,NOM,PRENOM,STATUS,TELEPHONE,RESPONSABLE_ID)" +
            "values (?,?,?,?,?,?,?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Historic save(Historic historic) {
        jdbcTemplate.update(INSERT_HISTORY, historic.getDate(), historic.getMontant(), historic.getNom(), historic.getPrenom(),
                historic.getStatus(), historic.getTelephone(), historic.getResponsable().getId());
        return historic;
    }

    public List<Historic> findByUserId(Long id) {
        return new ArrayList<Historic>();
    }
}
