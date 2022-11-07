package com.example.contenttest.controller;

import com.example.contenttest.exception.BaseForbiddenException;
import com.example.contenttest.exception.ResourceNotFoundException;
import com.example.contenttest.model.*;
import com.example.contenttest.services.TokenService;
import com.example.contenttest.services.UserService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class UsersController {
    private UserService userService;

    private final TokenService tokenService;

    @PostMapping(value = "/addUser" )
    public Users addUser(@Valid @RequestBody Users users) throws ResourceNotFoundException {
        return userService.saveUser(users);
    }

    @GetMapping(value = "/getUser")
    public List<Users> getUser(){
        return userService.getAllUser();
    }

    @GetMapping(value = "/getUserById/{id}")
    public Users getUserById(@PathVariable(name = "id") String id){
        return userService.getUserById(id);
    }

    @PutMapping(value = "/updateUser")
    public Users updateUser(@PathVariable(name = "id") String id , @RequestBody Users content) throws ResourceNotFoundException {
        return userService.updateUser(id , content);
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public String delete(@PathVariable(name = "id") String id) throws ResourceNotFoundException {
        return userService.deleteUser(id);
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest){
        Users user = userService.login(loginRequest);
        String token = tokenService.tokenize(user);
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping(value = "/register")
    @ResponseBody
    public ObjectNode register(@Valid @RequestBody Users users){
        return userService.register(users);
    }

    @GetMapping("/profile")
    public ResponseEntity<Users> profile() throws BaseForbiddenException {
        Users user = tokenService.getUserByToken();
        return ResponseEntity.ok(user);
    }

    @PutMapping("/updateProfile")
    public ResponseEntity<Users> update(@RequestBody UserUpdateRequest request) throws BaseForbiddenException {
        Users user = tokenService.getUserByToken();
        Users res = userService.update(user,request.getEmail(),request.getNickname());
        return ResponseEntity.ok(res);
    }
}
