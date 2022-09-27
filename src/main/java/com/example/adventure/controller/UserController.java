package com.example.adventure.controller;

import com.example.adventure.exception.ResourceNotFoundException;
import com.example.adventure.model.User;
import com.example.adventure.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

@RestController
public class UserController {

   /* private UserService uService;


    public UserController(UserService uService){
        this.uService = uService;
    }
    //Show all users in DB
    @GetMapping("/users")
    public ResponseEntity<Set<User>> getUsers(){
    return new ResponseEntity<>(uService.findAll(), HttpStatus.OK);
    }


    @PostMapping("/adduser")
    public ResponseEntity<Set<User>>addUser(User name) {
        uService.save(name);
        return new ResponseEntity<>(uService.findAll(), HttpStatus.OK);
    }

   @DeleteMapping("/deleteuser")
    public ResponseEntity<Set<User>> deleteUserById(User id){
        uService.delete(id);
        return new ResponseEntity<>(uService.findAll(), HttpStatus.OK);

    }

    @PutMapping("/updateuser")
    public ResponseEntity<Set<User>> updateUser(Long id, User name){
        User userUpdate = uService.findById(id).orElseThrow(() -> new ResourceNotFoundException("No one exists with this id: " + id));
        userUpdate.setName(name.getName());
        uService.save(userUpdate);
        return new ResponseEntity<>(uService.findAll(), HttpStatus.OK);
    }
*/




}

