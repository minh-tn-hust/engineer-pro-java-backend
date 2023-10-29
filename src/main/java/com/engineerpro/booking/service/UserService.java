package com.engineerpro.booking.service;

import com.engineerpro.booking.models.User;
import com.engineerpro.booking.repositories.user.IUserRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserService {
    @Autowired
    private IUserRepo userRepo;

    public User createUserWithName(String name) {
        User newUser = new User(name);
        userRepo.save(newUser);
        return newUser;
    };
}
