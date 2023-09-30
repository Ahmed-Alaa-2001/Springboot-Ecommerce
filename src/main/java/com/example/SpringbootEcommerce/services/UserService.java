package com.example.SpringbootEcommerce.services;

import com.example.SpringbootEcommerce.dto.UserDTO;
import com.example.SpringbootEcommerce.exceptions.UserExists;
import com.example.SpringbootEcommerce.exceptions.UserNotFound;
import com.example.SpringbootEcommerce.model.Cart;
import com.example.SpringbootEcommerce.model.User;
import com.example.SpringbootEcommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  EncryptionService encryptionService;
    @Autowired
    private JWTService jwtService;
    public User Register(UserDTO user) throws UserExists {
        if(userRepository.findByEmailIgnoreCase(user.getEmail()).isPresent()){
            throw new UserExists("email is already exist");
        }
        if(userRepository.findByEmail(user.getUserName()).isPresent()){
            throw new UserExists("username is already exist");
        }
        String password = user.getPassword();
       /* if(!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$") ||
           password.length()<3 || password.length()>255){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid password format or length");
        }*/
        Cart cart = new Cart();
        User userEntity=new User();
        userEntity.setUserName(user.getUserName());
        userEntity.setEmail(user.getEmail());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setCart(cart);
        cart.setUser(userEntity);
        userEntity.setPassword(encryptionService.encryptPassword(user.getPassword()));
        System.out.println(userEntity);
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
    public List<User>findAllUsers() throws UserNotFound{
        List<User>users=userRepository.findAll();
        System.out.println(users.get(0));
        if(users.isEmpty()){
            throw new UserNotFound("there is no user");
        }
        return users;
    }
    public User findUserByName(String name) throws UserNotFound{
        Optional<User>user=userRepository.findByUserNameIgnoreCase(name);
        if(user.isEmpty()){
            throw new UserNotFound("there is no user with given name");
        }
        return user.get();
    }
    public String deleteUserByName(String name) throws UserNotFound{
        Optional<User>user=userRepository.findByUserNameIgnoreCase(name);
        if(user.isEmpty()){
            throw new UserNotFound("there is no user with given name");
        }
        userRepository.delete(user.get());
        return "User deleted successfully";
    }
    public User EditUser(UserDTO userDTO,String name) throws UserNotFound , UserExists{
        System.out.println(userDTO.getUserName());
        Optional<User>opuser=userRepository.findByUserNameIgnoreCase(name);
        Optional<User>opusername=userRepository.findByUserNameIgnoreCase(userDTO.getUserName());
        Optional<User>opuseremail=userRepository.findByEmail(userDTO.getEmail());
        if(opuser.isEmpty()){
            throw new UserNotFound("there is no user with given username");
        }
        if(opusername.isPresent()){
            throw new UserExists("username is already exist");
        }
        if(opuseremail.isPresent()){
            throw new UserExists("email is already exist");
        }
        User existUser=opuser.get();
        existUser.setUserName(userDTO.getUserName());
        existUser.setFirstName(userDTO.getFirstName());
        existUser.setLastName(userDTO.getLastName());
        existUser.setEmail(userDTO.getEmail());
        existUser.setPassword(encryptionService.encryptPassword(userDTO.getPassword()));
        return userRepository.save(existUser);
    }
}
