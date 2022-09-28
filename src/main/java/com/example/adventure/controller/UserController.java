package com.example.adventure.controller;

import com.example.adventure.exception.ResourceNotFoundException;
import com.example.adventure.model.User;
import com.example.adventure.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private UserService uService;

    public UserController(UserService uService){
        this.uService = uService;
    }
    //Show all users in DB
    @GetMapping
    public ResponseEntity<Set<User>> getUsers(){
    return new ResponseEntity<>(uService.findAll(), HttpStatus.OK);
    }

    //Add user and save in DB
    @PostMapping
    public ResponseEntity<Set<User>>addUser(@RequestBody User name){
        uService.save(name);
    return new ResponseEntity<>(uService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Set<User>> deleteUserById(@PathVariable User id){
        uService.delete(id);
        return new ResponseEntity<>(uService.findAll(), HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Set<User>> updateUser(@PathVariable Long id, User name){
        User userUpdate = uService.findById(id).orElseThrow(() -> new ResourceNotFoundException("No one exists with this id: " + id));
        userUpdate.setName(name.getName());
        uService.save(userUpdate);
        return new ResponseEntity<>(uService.findAll(), HttpStatus.OK);
    }




}
