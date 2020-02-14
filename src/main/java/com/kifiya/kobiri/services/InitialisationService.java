package com.kifiya.kobiri.services;


import com.kifiya.kobiri.models.user.Historic;
import com.kifiya.kobiri.models.user.Role;
import com.kifiya.kobiri.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class InitialisationService {

    @Autowired
    UserService userService;
    @Autowired
    HistoricService historicService;
    User user;

    public void init() {
        initUsers();
    }

    private void initUsers() {
        user = new User();
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
    }








}
