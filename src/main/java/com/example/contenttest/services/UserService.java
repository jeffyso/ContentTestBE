package com.example.contenttest.services;

import com.example.contenttest.exception.ResourceNotFoundException;
import com.example.contenttest.model.Content;
import com.example.contenttest.model.LoginRequest;
import com.example.contenttest.model.Users;
import com.example.contenttest.repository.UserRepository;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final ObjectMapper jsonMapper = new ObjectMapper();

    public Users saveUser(Users users){
        return userRepository.save(users);
    }

    public List<Users> getAllUser(){
        return userRepository.findAll();
    }

    public Users getUserById(long id){
        return userRepository.findById(id).orElse(null);
    }

    String message = "message" ;

    public Users updateUser(Users users){
        Users findUser = userRepository.findById(users.getId()).orElse(null);
        findUser.setUsername(findUser.getUsername());
        findUser.setPassword(findUser.getPassword());
        findUser.setNickname(findUser.getNickname());
        findUser.setEmail(findUser.getEmail());
        return userRepository.save(findUser);
    }

    public String deleteUser(long id) throws ResourceNotFoundException {
        userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        userRepository.deleteById(id);
        return "Delete Success";
    }

    public ObjectNode login(LoginRequest loginRequest) {
        Users user = userRepository.findByUsernameAndPassword(loginRequest.getUsername(),loginRequest.getPassword());
        ObjectNode response = jsonMapper.createObjectNode();
        if(user == null){
            response.put(message ,"Login Failed");
        } else {
            response.put(message ,"Logged in");
        }
        return response;
    }

    public ObjectNode register(Users users) {
        Users user = new Users();
        ObjectNode response = jsonMapper.createObjectNode();
        user.setUsername(users.getUsername());
        user.setPassword(users.getPassword());
        user.setNickname(users.getNickname());
        user.setEmail(users.getEmail());

        userRepository.save(user);

        response.put(message,"Success");

        return response;
    }
}
