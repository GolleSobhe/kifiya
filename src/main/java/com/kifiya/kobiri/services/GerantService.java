package com.kifiya.kobiri.services;

import com.kifiya.kobiri.models.Gerant;
import com.kifiya.kobiri.repositories.GerantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GerantService {

    private static final String DEFAULT_PASSWORD = "kobiri";

    @Autowired
    private final GerantRepository gerantRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public GerantService(GerantRepository gerantRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.gerantRepository = gerantRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Gerant ajouterGerant(Gerant gerant){
        var id = UUID.randomUUID();
        gerant.setId(id);
        gerant.setPassword(bCryptPasswordEncoder.encode(DEFAULT_PASSWORD));
        gerantRepository.ajouterGerant(gerant);
        return gerant;
    }

    public int nombreDeGerants() {
        return gerantRepository.nombreDeGerants();
    }

}
