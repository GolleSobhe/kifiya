package com.kifiya.kobiri.services;

import com.kifiya.kobiri.models.user.Historic;
import com.kifiya.kobiri.models.user.User;
import com.kifiya.kobiri.repositories.HistoricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HistoricService {

    @Autowired
    HistoricRepository historicRepository;

    public Historic save(Historic historic) {

        historic.setDate(new Date());
        return historicRepository.save(historic);
    }

    public List<Historic> findByUserId(Long id) {
        return new ArrayList<Historic>();
    }
}
