package com.kifiya.kobiri.services;

import com.kifiya.kobiri.models.user.Transfert;
import com.kifiya.kobiri.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class TransfertService {

    String INSERT_TRANSFERT = "insert into TRANSFERT (CODE,DATE,MONTANT_EUROS,MONTANTGNF,NOM,PRENOM,STATUS,TAUX,TELEPHONE,RESPONSABLE_ID)" +
            "values (?,?,?,?,?,?,?,?,?,?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Transfert save(Transfert transfert) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User userAuth = (User) auth.getPrincipal();
        transfert.setResponsable(userAuth);
        transfert.setDate(new Date());
        transfert.setStatus(Boolean.TRUE);
        transfert.setTaux((double) 10600);
        transfert.setCode(getHexa());
        transfert.setMontantGNF((long) (transfert.getMontantEuros()*transfert.getTaux()));
        jdbcTemplate.update(INSERT_TRANSFERT,transfert.getCode(), transfert.getDate(), transfert.getMontantEuros(),
                transfert.getMontantGNF(), transfert.getNom(), transfert.getPrenom(),
                transfert.getStatus(), transfert.getTaux(), transfert.getTelephone(), transfert.getResponsable().getId());
        return transfert;
    }

    public List<Transfert> findByUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        return new ArrayList<Transfert>();
    }

    private String getHexa(){
        Random random = new Random();
        int val = random.nextInt();
        String Hex = new String();
        Hex = Integer.toHexString(val);
        return Hex;
    }
}
