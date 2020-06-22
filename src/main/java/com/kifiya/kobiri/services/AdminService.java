package com.kifiya.kobiri.services;

import com.kifiya.kobiri.models.Boutique;
import com.kifiya.kobiri.repositories.BoutiqueRepository;
import com.kifiya.kobiri.repositories.TransfertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminService {

    private final BoutiqueRepository boutiqueRepository;

    private final TransfertRepository transfertRepository;

    public AdminService(BoutiqueRepository boutiqueRepository, TransfertRepository transfertRepository) {
        this.boutiqueRepository = boutiqueRepository;
        this.transfertRepository = transfertRepository;
    }

    public Boutique ajouterBoutique(Boutique boutique) {
        boutiqueRepository.ajouter(boutique);
        return boutique;
    }

    public List<Boutique> listerBoutiques() {
        return boutiqueRepository.findAll();
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

    public int nombreDeGerants() {
        return 0;
    }
}
