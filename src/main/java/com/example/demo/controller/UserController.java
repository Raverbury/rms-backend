package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.DBInstance;
import com.example.demo.model.request_body.RegisterRequestBody;

@RestController
@CrossOrigin
public class UserController {

  @Autowired
  private JdbcTemplate jdbcTemplate;
  @Autowired
  private DBInstance dbInstance;
  @Autowired
  private PasswordEncoder bcryptEncoder;

  @PostMapping("/api/public/user/register")
  public ResponseEntity<?> registerNewUser(@RequestBody RegisterRequestBody request)
  {
    String sql = "INSERT INTO \"USER\" (username, password, email, phone_number, role) values (?, ?, ?, ?, ?)";
    String result;
    try {
      jdbcTemplate.update(sql, request.getUsername(), bcryptEncoder.encode(request.getPassword()), request.getEmail(), request.getPhone_number(), "owner");
      result = "Registered new user successfully.";
    }
    catch (Exception e) {
      result = "Exception: " + e.getMessage();
    }
    return ResponseEntity.ok(result);
  }

  @GetMapping("/api/protected/user/test")
  public ResponseEntity<?> test()
  {
    return ResponseEntity.ok("monkaHmm");
  }

  @GetMapping("/api/public/user/all")
  public ResponseEntity<?> getAllUsers()
  {
    String sql = "SELECT * FROM \"USER\"";
    return ResponseEntity.ok(dbInstance.getJdbcTemplate().queryForList(sql));
  }
}
