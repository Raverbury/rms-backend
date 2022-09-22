package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.*;

@RestController
@CrossOrigin
public class UserController {
  @Autowired
  private UserRepository userRepository;

  @GetMapping("/api/public/user/test")
  public ResponseEntity<?> test() {
    return ResponseEntity.ok(new String("Test public"));
  }

  @GetMapping("/api/protected/user/all")
  public ResponseEntity<Iterable<User>> getAllUsers() {
    return ResponseEntity.ok(userRepository.findAll());
  }

  @PostMapping("/api/public/user/add")
  public ResponseEntity<?> registerUser(@RequestBody UserInfoForm request) {
    boolean success;
    String response;
    try {
      User newUser = new User();
      newUser.setUserId(request.username);
      newUser.setPassword(request.password);
      userRepository.save(newUser);
      success = true;
      response = "User added successfully";
    } catch (Exception e) {
      success = false;
      response = e.toString();
    }
    if (success) {
      return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.badRequest().build();
    }
  }
}
