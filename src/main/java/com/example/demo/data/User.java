package com.example.demo.data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
  @Id
  private String userId;

  private String password;

  private String bio;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }
}
