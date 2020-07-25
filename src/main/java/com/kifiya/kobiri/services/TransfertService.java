package com.kifiya.kobiri.services;

import com.kifiya.kobiri.models.Client;
import com.kifiya.kobiri.models.Transfert;
import com.kifiya.kobiri.repositories.TransfertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class TransfertService {
    private Random random = new Random();
    private StringBuilder sb = new StringBuilder();
    @Autowired
    TransfertRepository transfertRepository;

    public Transfert ajouter(Transfert transfert) {
        /**
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur utilisateurAuth = (Utilisateur) auth.getPrincipal();
        Client client = new Client();
        client.setId(utilisateurAuth.getId());
        client.setPrenom(utilisateurAuth.getPrenom());
        client.setNom(utilisateurAuth.getNom());
        client.setEmail(utilisateurAuth.getEmail());
        client.setPassword(utilisateurAuth.getPassword());
        transfert.setResponsable(client);
        */
        transfert.setDateTransfert(LocalDateTime.now());
        transfert.setTaux((long) 10600);
        transfert.setCode(getHexa(8));
        Client c = new Client();
        c.setEmail("email");
        transfert.setClient(c);
        transfertRepository.creer(transfert);
        return transfert;
    }

    public List<Transfert> findByUserId() {
        return new ArrayList<>();
    }

    private String getHexa(int nombreCaractere){
        while(sb.length() < nombreCaractere){
            sb.append(Integer.toHexString(random.nextInt()));
        }
        return sb.toString().substring(0, nombreCaractere);
    }

    //Supprimer apres
    public void init(Transfert transfert) {

        transfert.setDateTransfert(LocalDateTime.now());
        transfert.setTaux((long) 10600);
        transfert.setCode(getHexa(8));
        transfertRepository.creer(transfert);
    }

}
