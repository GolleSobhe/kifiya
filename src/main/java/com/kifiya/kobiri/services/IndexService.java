package com.kifiya.kobiri.services;

import com.kifiya.kobiri.models.Boutique;
import com.kifiya.kobiri.repositories.BoutiqueRepository;
import com.kifiya.kobiri.repositories.IndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class IndexService {

    @Autowired
    IndexRepository indexRepository;

    public Map<String, Object> obtenirPrametre() {
        return indexRepository.obtenirPrametre();
    }
}
