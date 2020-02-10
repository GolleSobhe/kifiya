package com.kifiya.kobiri.services;

import com.kifiya.kobiri.models.user.Historic;
import com.kifiya.kobiri.repositories.HistoricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricService {

    @Autowired
    HistoricRepository historicRepository;

    public List<Historic> findAll() {
        return historicRepository.findAll();
    }

    public Historic findById(Long id) {
        return null;
    }
}
