package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.auth.JwtRequest;
import com.example.demo.auth.JwtResponse;
import com.example.demo.auth.JwtTokenUtil;
import com.example.demo.data.User;
import com.example.demo.data.UserRepository;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @PostMapping("/api/public/auth")
  public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

    authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

    final String token = jwtTokenUtil.generateToken(authenticationRequest.getUsername());

    return ResponseEntity.ok(new JwtResponse(token));
  }

  public void authenticate(String username, String password) throws Exception {
    try {
      User result = userRepository.findById(username).get();
      if (result == null) {
        throw new Exception("Cannot find such user");
      }
      if (!result.getPassword().equals(password)) {
        throw new Exception("Invalid credentials");
      }
    } catch (Exception e) {
      throw new Exception(e.toString(), e);
    }
  }
}