package com.kifiya.kobiri.services;

import com.kifiya.kobiri.models.User;
import com.kifiya.kobiri.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(User user){
        this.userRepository.register(user);
        return user;
    }

}
