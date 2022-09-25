package com.example.demo.model;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUser implements UserDetails {
  private UserDetails userDetails;
  private Map<String, Object> claims;
  public CustomUser(UserDetails userDetails, Map<String, Object> claims) {
    this.userDetails = userDetails;
    this.claims = claims;
  }
  public UserDetails getUserDetails() {
    return userDetails;
  }
  public void setUserDetails(UserDetails userDetails) {
    this.userDetails = userDetails;
  }
  public Map<String, Object> getClaims() {
    return claims;
  }
  public void setClaims(Map<String, Object> claims) {
    this.claims = claims;
  }
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return userDetails.getAuthorities();
  }
  @Override
  public String getPassword() {
    return userDetails.getPassword();
  }
  @Override
  public String getUsername() {
    return userDetails.getUsername();
  }
  @Override
  public boolean isAccountNonExpired() {
    return userDetails.isAccountNonExpired();
  }
  @Override
  public boolean isAccountNonLocked() {
    return userDetails.isAccountNonLocked();
  }
  @Override
  public boolean isCredentialsNonExpired() {
    return userDetails.isCredentialsNonExpired();
  }
  @Override
  public boolean isEnabled() {
    return userDetails.isEnabled();
  }
}
