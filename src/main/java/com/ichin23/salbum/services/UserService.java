package com.ichin23.salbum.services;

import com.ichin23.salbum.domain.user.User;
import com.ichin23.salbum.repositories.UserRepository;
import com.ichin23.salbum.services.exceptions.UserExistsException;
import com.ichin23.salbum.services.exceptions.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findUserById(String id){
        return userRepository.findById(id);
    }

    public User registerUser(User user){
        return userRepository.save(user);
    }

    public boolean validateUser(String email, String username){
        var userEmail = this.findUserByEmail(email);
        if(userEmail!=null) throw new UserExistsException("Email já existente no banco");

        var userUsername = this.findUserByUsername(username);
        if(userUsername!=null) throw new UserExistsException("Username já existente no banco");

        return true;
    }

    public User findUserByUsername(String email){
        return userRepository.findByUsername(email);
    }

    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }


}
