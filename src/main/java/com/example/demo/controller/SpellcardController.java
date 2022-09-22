package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class SpellcardController {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @GetMapping("/api/public/spellcard/test")
  public ResponseEntity<?> getTestSpellcard()
  {
    String sql = "SELECT * FROM 'user'";
    Object t = jdbcTemplate.queryForList(sql);
    return ResponseEntity.ok(t.toString());
  }

}
