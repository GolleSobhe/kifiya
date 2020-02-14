package com.kifiya.kobiri.services;


import com.kifiya.kobiri.models.user.Historic;
import com.kifiya.kobiri.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class InitialisationService {

    @Autowired
    UserService userService;

    public void init() {
        initUsers();
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
        User user1 = new User();
        user1.setConfirmationToken("");
        user1.setNom("Golle");
        user1.setPrenom("Sobhe");
        user1.setEmail("sobhe1@gmail.com");
        user1.setTelephone("0606060606");
        user1.setPays("No houdha");
        user1.setVille("No feti");
        user1.setCodePostale("06560");
        user1.setAdresse("Geoges pompidou");
        user1.setPassword("sobhe");
        user1.setEnabled(true);
        userService.save(user1);
        User user2 = new User();
        user2.setConfirmationToken("");
        user2.setNom("Golle");
        user2.setPrenom("Sobhe");
        user2.setEmail("sobhe2@gmail.com");
        user2.setTelephone("0606060606");
        user2.setPays("No houdha");
        user2.setVille("No feti");
        user2.setCodePostale("06560");
        user2.setAdresse("Geoges pompidou");
        user2.setPassword("sobhe");
        user2.setEnabled(true);
        userService.save(user2);
    }







}
