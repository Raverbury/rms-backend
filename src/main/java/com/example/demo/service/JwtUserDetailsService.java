package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.CustomUser;
import com.example.demo.model.DBInstance;

@Service
public class JwtUserDetailsService implements UserDetailsService {

  @Autowired
  DBInstance dbInstance;

  @Override
  public CustomUser loadUserByUsername(String username) throws UsernameNotFoundException {
    String sql = "SELECT * FROM \"USER\" WHERE \"username\" = ?";

    List<Map<String, Object>> rows = dbInstance.getJdbcTemplate().queryForList(sql, username);
    if (rows.size() <= 0) {
      throw new UsernameNotFoundException("User not found with username: " + username);
    }

    // there should only be one here
    Map<String, Object> unfilteredRow = rows.get(0);
    Map<String, Object> row = new HashMap<String, Object>();
    // filter out null values and username/password field
    String password = unfilteredRow.get("password").toString();
    for (Map.Entry<String, Object> entry : unfilteredRow.entrySet()) {
      if (entry.getValue() != null && !entry.getKey().equals("password") && !entry.getKey().equals("username"))
        row.put(entry.getKey(), entry.getValue());
    }
    return new CustomUser(new User(username, password, new ArrayList<>()), row);
  }
}
