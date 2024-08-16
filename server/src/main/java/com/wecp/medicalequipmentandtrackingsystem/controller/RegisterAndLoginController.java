package com.wecp.medicalequipmentandtrackingsystem.controller;


import com.wecp.medicalequipmentandtrackingsystem.dto.LoginRequest;
import com.wecp.medicalequipmentandtrackingsystem.dto.LoginResponse;
import com.wecp.medicalequipmentandtrackingsystem.entitiy.User;
import com.wecp.medicalequipmentandtrackingsystem.jwt.JwtUtil;
import com.wecp.medicalequipmentandtrackingsystem.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RegisterAndLoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/api/user/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        // register user and return the registered user with status code 201 created
        // User cons=
        // if(cons!=null){
        // return new ResponseEntity<>(HttpStatus.CREATED);
        // }
        return new ResponseEntity<>(userService.registerUser(user),HttpStatus.CREATED);
    }

    @PostMapping("/api/user/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        // login user and return the login response with status code 200 ok
        // if authentication fails, return status code 401 unauthorized
            User user = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
            if (user != null) {
                    String token = jwtUtil.generateToken(loginRequest.getUsername());
                    LoginResponse response = new LoginResponse(token, user.getUsername(), user.getEmail(), user.getRole());
                    return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
                   
}
@GetMapping("/api/user/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

 

}
// @PostMapping("/api/user/register")
//     public ResponseEntity<User> registerUser(@RequestBody User user) {
//         User registeredUser = userService.registerUser(user);
       
//         return new ResponseEntity<>(registeredUser,HttpStatus.CREATED);
//     }
 
//     @PostMapping("/api/user/login")
//     public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) {
//         User user = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
//         if (user != null) {
//             LoginResponse response = new LoginResponse("token", user.getUsername(), user.getEmail(), user.getRole());
//             return ResponseEntity.ok(response);
//         } else {
//             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//         }
//     }



  // User user = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
                    // if (user != null) {
                    //     LoginResponse response = new LoginResponse("token", user.getUsername(), user.getEmail(), user.getRole());
                    //     return ResponseEntity.ok(response);
                    // } else {
                    //     return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                    // }



                   