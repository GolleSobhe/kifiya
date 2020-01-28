package com.kifiya.kobiri.services;

import com.kifiya.kobiri.models.user.User;
import com.kifiya.kobiri.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user){
        this.userRepository.save(user);
        return user;
    }

}
