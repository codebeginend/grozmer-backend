package com.beginend.grozmerbackend.web.controller;

import com.beginend.grozmerbackend.model.RolesEnum;
import com.beginend.grozmerbackend.model.User;
import com.beginend.grozmerbackend.security.JwtTokenProvider;
import com.beginend.grozmerbackend.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthCtrl {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserDetailsServiceImpl usersService;

    @RequestMapping(value = "signin", method = RequestMethod.POST)
    public ResponseEntity signin(@RequestBody User user) {
        try {
            UserDetails resUser = this.usersService.loadUserByUsername(user.getLogin());
            if(resUser != null && user.getPassword().equals(resUser.getPassword())){
                //authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(), users.getPassword()));
                String token = jwtTokenProvider.createToken(user.getLogin(), RolesEnum.ROLE_USER);
                Map<Object, Object> model = new HashMap<>();
                model.put("username", user.getLogin());
                model.put("token", token);
                model.put("roles", resUser.getAuthorities().stream().findFirst().get().getAuthority());
                return new ResponseEntity(model, HttpStatus.OK);
            }else{
                return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
            }
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        } catch (NullPointerException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}
