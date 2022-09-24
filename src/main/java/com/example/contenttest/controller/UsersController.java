package com.example.contenttest.controller;

import com.example.contenttest.exception.ResourceNotFoundException;
import com.example.contenttest.model.Content;
import com.example.contenttest.model.LoginRequest;
import com.example.contenttest.model.Users;
import com.example.contenttest.services.UserService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/addUser")
    public Users addUser(@Valid @RequestBody Users users) {
        return userService.saveUser(users);
    }

    @GetMapping(value = "/getUser")
    public List<Users> getUser(){
        return userService.getAllUser();
    }

    @GetMapping(value = "/getUserById/{id}")
    public Users getUserById(@PathVariable(name = "id") long id){
        return userService.getUserById(id);
    }

    @PutMapping(value = "/updateUser")
    public Users updateUser(@RequestBody Users users){
        return userService.updateUser(users);
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public String delete(@PathVariable(name = "id") long id) throws ResourceNotFoundException {
        return userService.deleteUser(id);
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public ObjectNode login(@RequestBody LoginRequest loginRequest){
        return userService.login(loginRequest);
    }

    @PostMapping(value = "/register")
    @ResponseBody
    public ObjectNode register(@RequestBody Users users){
        return userService.register(users);
    }

}
