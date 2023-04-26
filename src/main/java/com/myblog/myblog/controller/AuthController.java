package com.myblog.myblog.controller;

import com.myblog.myblog.entity.Role;
import com.myblog.myblog.entity.User;
import com.myblog.myblog.payload.JWTAuthResponse;
import com.myblog.myblog.payload.LoginDto;
import com.myblog.myblog.payload.SignUpDto;
import com.myblog.myblog.repository.RoleRepository;
import com.myblog.myblog.repository.UserRepository;
import com.myblog.myblog.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

   @Autowired
    private AuthenticationManager authenticationManager;



   //http://localhost:8080/api/auth/signin
  // @PostMapping("/signin")
   // public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
     //  Authentication authentication = authenticationManager.authenticate(
         //      new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword())
      // );
      // SecurityContextHolder.getContext().setAuthentication(authentication);
      // return new ResponseEntity<>("User signed-in successfully!", HttpStatus.OK);
  // }

    //http://localhost:8080/api/auth/signup
 //  @PostMapping("/signup")
   // public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){

       //add check for username exists in a DB
      // if(userRepository.existsByUsername(signUpDto.getUsername())){
         //  return new ResponseEntity<>("Username is already taken!",HttpStatus.BAD_REQUEST);
      // }

       //add check for email exists in DB
     //  if (userRepository.existsByEmail(signUpDto.getEmail())){
       //    return new ResponseEntity<>("Email is already taken!",HttpStatus.BAD_REQUEST);
      // }

       //create user object
      // User user = new User();
      // user.setName(signUpDto.getName());
      // user.setUsername(signUpDto.getUsername());
      // user.setEmail(signUpDto.getEmail());
      // user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

      // Role roles = roleRepository.findByName("ROLE_ADMIN").get();
      // user.setRoles(Collections.singleton(roles));

      // userRepository.save(user);

      // return new ResponseEntity<>("User registered successfully",HttpStatus.OK);
 //  }



    //http://localhost:8080/api/auth/signin
     @PostMapping("/signin")
     public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto){
      Authentication authentication = authenticationManager.authenticate(
         new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword())
     );
     SecurityContextHolder.getContext().setAuthentication(authentication);

     //get token from tokenProvider
         String token = tokenProvider.generateToken(authentication);

         return ResponseEntity.ok(new JWTAuthResponse(token));
     }

    //http://localhost:8080/api/auth/signup
      @PostMapping("/signup")
     public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){

    //add check for username exists in a DB
     if(userRepository.existsByUsername(signUpDto.getUsername())){
      return new ResponseEntity<>("Username is already taken!",HttpStatus.BAD_REQUEST);
     }

    //add check for email exists in DB
      if (userRepository.existsByEmail(signUpDto.getEmail())){
        return new ResponseEntity<>("Email is already taken!",HttpStatus.BAD_REQUEST);
     }

          //create user object
           User user = new User();
           user.setName(signUpDto.getName());
           user.setUsername(signUpDto.getUsername());
           user.setEmail(signUpDto.getEmail());
           user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

           Role roles = roleRepository.findByName("ROLE_ADMIN").get();
           user.setRoles(Collections.singleton(roles));

           userRepository.save(user);

           return new ResponseEntity<>("User registered successfully",HttpStatus.OK);
            }

}