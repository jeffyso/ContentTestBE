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
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final ObjectMapper jsonMapper = new ObjectMapper();

    public Users saveUser(Users users) throws ResourceNotFoundException {

        try{
            return userRepository.save(users);
        }catch (Exception e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    public List<Users> getAllUser(){
        return userRepository.findAll();
    }

    public Users getUserById(String id){
        return userRepository.findById(id).orElse(null);
    }

    String message = "message" ;

    public Users updateUser(String id , Users users) throws ResourceNotFoundException {
        Users findUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        findUser.setUsername(findUser.getUsername());
        findUser.setPassword(findUser.getPassword());
        findUser.setNickname(findUser.getNickname());
        findUser.setEmail(findUser.getEmail());
        return userRepository.save(findUser);
    }

    public String deleteUser(String id) throws ResourceNotFoundException {
        userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        userRepository.deleteById(id);
        return "Delete Success";
    }

    public Users login(LoginRequest loginRequest) {
        Users user = userRepository.findByUsernameAndPassword(loginRequest.getUsername(),loginRequest.getPassword());
        return user;
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

    public Users update(Users user, String email, String nickname) {
        user.setEmail(email);
        user.setNickname(nickname);
        return userRepository.save(user);
    }
}
