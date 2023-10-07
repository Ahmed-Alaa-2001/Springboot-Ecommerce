package com.example.SpringbootEcommerce.controller;

import com.example.SpringbootEcommerce.dto.AddressDTO;
import com.example.SpringbootEcommerce.dto.LoginDTO;
import com.example.SpringbootEcommerce.dto.UserDTO;
import com.example.SpringbootEcommerce.exceptions.AddressNotFound;
import com.example.SpringbootEcommerce.exceptions.UserExists;
import com.example.SpringbootEcommerce.exceptions.UserNotFound;
import com.example.SpringbootEcommerce.model.Address;
import com.example.SpringbootEcommerce.model.User;
import com.example.SpringbootEcommerce.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    UserService userService;
    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody UserDTO userDTO){
        try{
            User user=userService.Register(userDTO);
//            return ResponseEntity.ok().build();
            return new ResponseEntity<User>(user,HttpStatus.CREATED);
        }catch (UserExists err){
            return new ResponseEntity<String>(err.getMessage(),HttpStatus.CONFLICT);
//            return ResponseEntity.status(HttpStatus.CONFLICT).build();
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
    @GetMapping("/userinfo")
    public UserDTO getUserinfo(@AuthenticationPrincipal User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(user.getUserName());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setEmail(user.getEmail());
        userDTO.setLastName(user.getLastName());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }
    @GetMapping("/getusers")
    public ResponseEntity getAllUsers() throws UserNotFound {
        try{
            List<User> user=userService.findAllUsers();
            return new ResponseEntity<List<User>>(user,HttpStatus.FOUND);
        }catch(UserNotFound err){
            return new ResponseEntity<String>(err.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getuser/{name}")
    public ResponseEntity getUser(@PathVariable String name) throws UserNotFound {
        try{
            User user=userService.findUserByName(name);
            return new ResponseEntity<User>(user,HttpStatus.FOUND);
        }catch(UserNotFound err){
            return new ResponseEntity<String>(err.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{name}")
    public ResponseEntity deleteUser(@PathVariable String name) throws UserNotFound {
        try{
            return new ResponseEntity<String>(userService.deleteUserByName(name),HttpStatus.OK);
        }catch(UserNotFound err){
            return new ResponseEntity<String>(err.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/Edit/{name}")
    public ResponseEntity deleteUser(@RequestBody UserDTO userDTO,@PathVariable String name){
        try{
            return new ResponseEntity<User>(userService.EditUser(userDTO,name),HttpStatus.OK);
        }catch(UserNotFound err){
            return new ResponseEntity<String>(err.getMessage(),HttpStatus.NOT_FOUND);
        }catch (UserExists err){
            return new ResponseEntity<String>(err.getMessage(),HttpStatus.CONFLICT);
        }
    }
    @PostMapping("/creditcart/add")
    public ResponseEntity addAndUpdateAddress(@RequestBody String creditCart,@AuthenticationPrincipal User user){
        return new ResponseEntity<User>(userService.addAndUpdateAddress(creditCart,user),HttpStatus.OK);
    }
    @PostMapping("/address/add")
    public ResponseEntity addAndUpdateAddress(@RequestBody AddressDTO addressDTO,@AuthenticationPrincipal User user){
        return new ResponseEntity<Address>(userService.addAndUpdateAddress(addressDTO,user),HttpStatus.CREATED);
    }
    @DeleteMapping("/address/delete")
    public ResponseEntity deleteAddress(@AuthenticationPrincipal User user) throws AddressNotFound {
        try {
            return new ResponseEntity<Address>(userService.deleteAddress(user), HttpStatus.OK);
        }
        catch (AddressNotFound err){
            return new ResponseEntity<String>(err.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
