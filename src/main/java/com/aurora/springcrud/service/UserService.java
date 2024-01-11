package com.aurora.springcrud.service;

import com.aurora.springcrud.entity.User;
import com.aurora.springcrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    public void addUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {

        User user = userRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return user;
    }

    public ResponseEntity<User> updateUser(Integer id, User user) {
        Optional<User> existingUser = userRepository.findById(id);

        if(existingUser.isPresent()){

            User newUser = existingUser.get();
            if(user.getId() != null){
                newUser.setId(user.getId());
            }
            if(user.getAge() != null){
                newUser.setAge(user.getAge());
            }
            if(user.getName() != null){
                newUser.setName(user.getName());
            }
            if(user.getAddress()!= null){
                newUser.setName(user.getName());
            }
            userRepository.save(newUser);
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
