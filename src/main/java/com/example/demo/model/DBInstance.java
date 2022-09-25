package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Singleton class, wrapping a JdbcTemplate instance and autoruns some extra
 * utility methods.
 */
@Component
public class DBInstance {

  public static final String USER_TABLE_DDL = "CREATE TABLE IF NOT EXISTS \"USER\" ( id SERIAL PRIMARY KEY NOT NULL, username TEXT NOT NULL UNIQUE, password TEXT NOT NULL, email TEXT, phone_number TEXT, role TEXT, work_for_store INT);";

  /**
   * Retrieves an '@Autowired' JdbcTemplate instance in this DBInstance instance.
   * 
   * @return a JdbcTemplate instance
   */
  public JdbcTemplate getJdbcTemplate() {
    tryCreateTables();
    return jdbcTemplate;
  }

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private void tryCreateTables() {
    tryCreateUserTable();
  }

  private void tryCreateUserTable() {
    jdbcTemplate.execute(USER_TABLE_DDL);
  }
}
