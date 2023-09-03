package com.example.SpringbootEcommerce.controller;

import com.example.SpringbootEcommerce.dto.LoginDTO;
import com.example.SpringbootEcommerce.dto.RegisterDTO;
import com.example.SpringbootEcommerce.exceptions.UserExists;
import com.example.SpringbootEcommerce.model.User;
import com.example.SpringbootEcommerce.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    UserService userService;
    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody RegisterDTO user){
        try{
            userService.Register(user);
            return ResponseEntity.ok().build();
        }catch (UserExists e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        //return "User registered successfully";
        //System.out.println(user.getPassword());
    }
    @PostMapping("/login")
    public ResponseEntity LoginUser(@RequestBody LoginDTO loginDto){
        String jwt=userService.login(loginDto.getUserName(),loginDto.getPassword());
        if(jwt==null)return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok(jwt);
    }
}
