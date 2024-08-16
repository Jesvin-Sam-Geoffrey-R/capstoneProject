package com.wecp.medicalequipmentandtrackingsystem.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.wecp.medicalequipmentandtrackingsystem.dto.LoginRequest;
import com.wecp.medicalequipmentandtrackingsystem.dto.LoginResponse;
import com.wecp.medicalequipmentandtrackingsystem.entitiy.User;
import com.wecp.medicalequipmentandtrackingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService{

    @Autowired
   private UserRepository userRepository;

    public User registerUser(User user){
    String userName=user.getUsername();
    User ifexist=userRepository.findByUsername(userName);
    if(ifexist!=null){
        return null;
    }
    else{
        return userRepository.save(user);
    }
    }
    
    public User loginUser(String username, String password){

        User user = userRepository.findByUsername(username);
            if (user != null && user.getPassword().equals(password)) {
                return user;
            }
            return null;

    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         User user = getUserByUsername(username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // public UserDetails loadByUsername(String username) throws UsernameNotFoundException{
        
    // }   
}
