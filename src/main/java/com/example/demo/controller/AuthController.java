package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.auth.JwtUtil;
import com.example.demo.model.CustomUser;
import com.example.demo.model.request_body.AuthenticateRequestBody;
import com.example.demo.service.JwtUserDetailsService;

@RestController
@CrossOrigin
public class AuthController {

  // @Autowired
  // private DBInstance dbInstance;

  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUserDetailsService userDetailsService;

  @GetMapping("/api/public/auth")
  public ResponseEntity<?> authenticate(@RequestBody AuthenticateRequestBody authRequest) throws Exception{
    authenticate(authRequest.getUsername(), authRequest.getPassword());

    final CustomUser userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

    final String jwt = jwtUtil.generateToken(userDetails);

    return ResponseEntity.ok(jwt);
    // String sql = "SELECT * FROM \"USER\" WHERE \"username\" = ?";
    // String result;

    // List<Map<String, Object>> rows = dbInstance.getJdbcTemplate().queryForList(sql, authRequest.getUsername());
    // if (rows.size() <= 0) {
    //   result = "Cannot find user " + authRequest.getUsername();
    //   return ResponseEntity.ok(result);
    // }

    // // there should only be one here
    // Map<String, Object> unfilteredRow = rows.get(0);
    // Boolean passwordMatch = unfilteredRow.get("password").equals(authRequest.getPassword());
    // if (passwordMatch) {
    //   Map<String, Object> row = new HashMap<String, Object>();
    //   // filter out null values and password field
    //   for (Map.Entry<String, Object> entry : unfilteredRow.entrySet()) {
    //     if (entry.getValue() != null && !entry.getKey().equals("password"))
    //       row.put(entry.getKey(), entry.getValue());
    //   }
    //   System.out.println(row.toString());
    //   for (Map.Entry<String, Object> entry : row.entrySet()) {
    //     System.out.println(entry.getValue().getClass());
    //   }
    //   AuthenticateResponseBody response = new AuthenticateResponseBody();
    //   response.setJwt(jwtUtil.generateToken(row));
    //   return ResponseEntity.ok(response);
    // } else {
    //   return ResponseEntity.ok("Password does not match");
    // }

  }

  private void authenticate(String username, String password) throws Exception {
    try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
  }

}
