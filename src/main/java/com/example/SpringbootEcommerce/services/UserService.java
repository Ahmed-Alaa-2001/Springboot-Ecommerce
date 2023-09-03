package com.example.SpringbootEcommerce.services;

import com.example.SpringbootEcommerce.dto.RegisterDTO;
import com.example.SpringbootEcommerce.exceptions.UserExists;
import com.example.SpringbootEcommerce.model.User;
import com.example.SpringbootEcommerce.repository.UserRepository;
import com.example.SpringbootEcommerce.security.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  EncryptionService encryptionService;
    @Autowired
    private JWTService jwtService;
    public User Register(RegisterDTO user) throws UserExists {
        if(userRepository.findByUserNameIgnoreCase(user.getUserName()).isPresent()||
           userRepository.findByEmailIgnoreCase(user.getEmail()).isPresent()){
            throw new UserExists();
        }
        String password = user.getPassword();
       /* if(!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$") ||
           password.length()<3 || password.length()>255){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid password format or length");
        }*/
        User userEntity=new User();
        userEntity.setUserName(user.getUserName());
        userEntity.setEmail(user.getEmail());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setPassword(encryptionService.encryptPassword(user.getPassword()));
        return userRepository.save(userEntity);
    }
    public String login(String username,String password){
        Optional<User> userExist = userRepository.findByUserNameIgnoreCase(username);
        if(userExist.isPresent()){
            User user = userExist.get();
            if(encryptionService.verifyPassword(password,user.getPassword())){
               return  jwtService.generateJWT(user);
            }
        }
        return null;
    }
}
