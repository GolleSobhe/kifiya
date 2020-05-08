package com.kifiya.kobiri.services;

import com.kifiya.kobiri.models.Transfert;
import com.kifiya.kobiri.repositories.TransfertRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        transfert.setDateTransfert(new Date());
        transfert.setTaux((double) 10600);
        transfert.setCode(getHexa(8));
        transfertRepository.ajouter(transfert);
        return transfert;
    }

    public List<Transfert> findByUserId() {
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //Utilisateur utilisateur = (Utilisateur) auth.getPrincipal();
        return new ArrayList<Transfert>();
    }

    private String getHexa(int nombreCaractere){
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        while(sb.length() < nombreCaractere){
            sb.append(Integer.toHexString(random.nextInt()));
        }
        return sb.toString().substring(0, nombreCaractere);
    }

    //Supprimer apres
    public void init(Transfert transfert) {

        transfert.setDateTransfert(new Date());
        transfert.setTaux((double) 10600);
        transfert.setCode(getHexa(8));
        transfertRepository.ajouter(transfert);
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
