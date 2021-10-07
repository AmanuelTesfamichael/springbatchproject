package edu.mum.springbatchproject.controller;

import edu.mum.springbatchproject.domain.Person;
import edu.mum.springbatchproject.service.Imp.AuthenticationService;
import edu.mum.springbatchproject.util.AuthRequest;
import edu.mum.springbatchproject.util.AuthResponse;
import edu.mum.springbatchproject.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationService authservice;


    @PostMapping("/authenticate/login")
    public ResponseEntity<AuthResponse> authenticate(
            @RequestBody AuthRequest authenticationRequest){

        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            authenticationManager.authenticate(authenticationToken);
        }
        catch (AuthenticationException authenticationException){
            throw authenticationException;
        }

        UserDetails userDetails = authservice.loadUserByUsername(authenticationRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(token,true));
    }


    @PostMapping("/authenticate/create")
    public ResponseEntity<AuthResponse> createUser(@RequestBody Person userSignUpDTO){
        boolean created = authservice.createUser(userSignUpDTO);

        if(created) {
            UserDetails userDetails = authservice.loadUserByUsername(userSignUpDTO.getUsername());
            String token = jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok(new AuthResponse(token,true));
        }

        return ResponseEntity.ok(new AuthResponse(null,false));
    }




}