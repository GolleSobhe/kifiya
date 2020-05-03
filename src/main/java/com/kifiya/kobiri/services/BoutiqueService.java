package com.kifiya.kobiri.services;

import com.kifiya.kobiri.models.Boutique;
import com.kifiya.kobiri.repositories.BoutiqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BoutiqueService {

    @Autowired
    private BoutiqueRepository boutiqueRepository;

    public List<Boutique> findAll() {
        return boutiqueRepository.findAll();
    }

    public Boutique ajouter(Boutique boutique) {
        boutique.setDate(new Date());
        boutiqueRepository.ajouter(boutique);
        return boutique;
    }

    public int nombreDeBoutiques() {
        return boutiqueRepository.nombreDeBoutiques();
    }
}
