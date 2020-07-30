package com.kifiya.kobiri.services;

import com.kifiya.kobiri.models.Boutique;
import com.kifiya.kobiri.repositories.BoutiqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexService {

    @Autowired
    private BoutiqueRepository boutiqueRepository;

    public List<Boutique> listerBoutiques() {
        return boutiqueRepository.listerBoutiques();
    }
}
