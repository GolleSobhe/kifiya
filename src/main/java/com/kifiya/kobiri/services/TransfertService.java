package com.kifiya.kobiri.services;

import com.kifiya.kobiri.models.Transfert;
import com.kifiya.kobiri.models.Utilisateur;
import com.kifiya.kobiri.repositories.TransfertRepository;
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

    @Autowired
    TransfertRepository transfertRepository;


    public Transfert save(Transfert transfert) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur utilisateurAuth = (Utilisateur) auth.getPrincipal();
        transfert.setResponsable(utilisateurAuth);
        transfert.setDate(new Date());
        transfert.setStatus(Boolean.TRUE);
        transfert.setTaux((double) 10600);
        transfert.setCode(getHexa());
        transfert.setMontantGNF((long) (transfert.getMontantEuros()*transfert.getTaux()));
        transfertRepository.save(transfert);
        return transfert;
    }

    public List<Transfert> findByUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur utilisateur = (Utilisateur) auth.getPrincipal();
        return new ArrayList<Transfert>();
    }

    private String getHexa(){
        Random random = new Random();
        int val = random.nextInt();
        String Hex = new String();
        Hex = Integer.toHexString(val);
        return Hex;
    }

    public List<Transfert> findAll() {
        return this.transfertRepository.findAll();
    }

    public int determinerNombreDeTransfertsTotal() {
        return this.transfertRepository.determinerNombreDeTransfertsTotal();
    }

    public int determinerNombreDeTransfertsEnCours() {
        return this.transfertRepository.determinerNombreDeTransfertsEnCours();
    }

    public int determinerNombreDeTransfertsRendus() {
        return this.transfertRepository.determinerNombreDeTransfertsRendus();
    }
}
