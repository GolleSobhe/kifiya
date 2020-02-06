package com.kifiya.kobiri.services;


import com.kifiya.kobiri.models.user.Historic;
import com.kifiya.kobiri.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class InitialisationService {

    @Autowired
    UserService userService;
    @Autowired
    HistoricService historicService;

    public void init() {
        initUsers();
        //initHistoric();
    }

    private void initUsers() {
        User user = new User();
        user.setConfirmationToken("");
        user.setNom("Golle");
        user.setPrenom("Sobhe");
        user.setEmail("sobhe@gmail.com");
        user.setTelephone("0606060606");
        user.setPays("No houdha");
        user.setVille("No feti");
        user.setCodePostale("06560");
        user.setAdresse("Geoges pompidou");
        user.setPassword("sobhe");
        user.setEnabled(true);
        userService.save(user);
    }

    private void initHistoric() {
        Historic historic = new Historic();
        historic.setStatus(true);
        historic.setDate(new Date());
        //historic.setMontant((Long) 500);
        historic.setTelephone("+224625060606");
        historic.setNom("fiyahollo");
        historic.setPrenom("Spbhe");
    }






}
