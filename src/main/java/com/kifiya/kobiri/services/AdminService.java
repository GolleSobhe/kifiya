package com.kifiya.kobiri.services;

import com.kifiya.kobiri.models.Boutique;
import com.kifiya.kobiri.models.EtatTransfert;
import com.kifiya.kobiri.models.Gerant;
import com.kifiya.kobiri.models.Transfert;
import com.kifiya.kobiri.repositories.BoutiqueRepository;
import com.kifiya.kobiri.repositories.GerantRepository;
import com.kifiya.kobiri.repositories.TransfertRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AdminService {

    private final BoutiqueRepository boutiqueRepository;

    private final TransfertRepository transfertRepository;

    private final GerantRepository gerantRepository;

    public AdminService(BoutiqueRepository boutiqueRepository, TransfertRepository transfertRepository, GerantRepository gerantRepository) {
        this.boutiqueRepository = boutiqueRepository;
        this.transfertRepository = transfertRepository;
        this.gerantRepository = gerantRepository;
    }

    public void ajouterGerant(Gerant gerant){
        //gerant.setPassword(bCryptPasswordEncoder.encode(DEFAULT_PASSWORD));
        gerant.setEmail("login");
        gerantRepository.creer(gerant);
    }

    public Boutique ajouterBoutique(Boutique boutique) {
        boutiqueRepository.ajouter(boutique);
        return boutique;
    }

    public List<Transfert> listerTransfertsEncours(){
        return transfertRepository.listerTransfertsParStatut(EtatTransfert.ENCOURS);
    }

    public void desactiverTransfert(String codeTransfert){
        transfertRepository.desactiverTransfert(codeTransfert);
    }

    public List<Boutique> listerBoutiques() {
        return boutiqueRepository.listerBoutiques();
    }

    public int nombreDeBoutiques() {
        return boutiqueRepository.nombreDeBoutiques();
    }

    public int determinerNombreDeTransfertsEnCours() {
        return this.transfertRepository.determinerNombreDeTransfertsEnCours();
    }

    public int determinerNombreDeTransfertsRendus() {
        return this.transfertRepository.determinerNombreDeTransfertsRendus();
    }

    public int determinerNombreDeTransfertsDesactives(){
        return this.transfertRepository.determinerNombreDeTransfertsDesactives();
    }

    public int nombreDeGerants() {
        return this.gerantRepository.nombreGerants();
    }
}
