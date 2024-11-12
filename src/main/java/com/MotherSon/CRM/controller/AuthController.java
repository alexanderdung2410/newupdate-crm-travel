package com.MotherSon.CRM.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MotherSon.CRM.models.Role;
import com.MotherSon.CRM.models.User;
import com.MotherSon.CRM.payload.request.LoginRequest;
import com.MotherSon.CRM.payload.request.SignupRequest;
import com.MotherSon.CRM.payload.response.JwtResponse;
import com.MotherSon.CRM.payload.response.MessageResponse;
import com.MotherSon.CRM.payload.response.UserResponse;
import com.MotherSon.CRM.repository.RoleRepository;
import com.MotherSon.CRM.repository.UserRepository;
import com.MotherSon.CRM.security.jwt.JwtUtils;
import com.MotherSon.CRM.security.services.UserDetailsImpl;
import com.MotherSon.CRM.security.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//import java.util.stream.Collectors;
//
////import javax.servlet.http.HttpServletRequest;
////import javax.validation.Valid;
//
////import org.apache.el.stream.Optional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.MotherSon.CRM.models.ERole;
//import com.MotherSon.CRM.models.Role;
//import com.MotherSon.CRM.models.User;
////import com.MotherSon.CRM.entities.*;
//import com.MotherSon.CRM.payload.request.LoginRequest;
//import com.MotherSon.CRM.payload.request.SignupRequest;
//import com.MotherSon.CRM.payload.response.JwtResponse;
//import com.MotherSon.CRM.payload.response.MessageResponse;
//import com.MotherSon.CRM.payload.response.UserResponse;
//import com.MotherSon.CRM.repository.RoleRepository;
//import com.MotherSon.CRM.repository.UserRepository;
//import com.MotherSon.CRM.security.jwt.JwtUtils;
////import com.MotherSon.CRM.security.jwt.JwtUtils;
//import com.MotherSon.CRM.security.services.UserDetailsImpl;
//import com.MotherSon.CRM.security.services.UserService;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("Motherson/crm/v1")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	private UserService userservice;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		User user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with username: " + loginRequest.getUsername()));

		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);

			userservice.resetFailedAttempts(user.getUsername());

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
					.collect(Collectors.toList());

			return ResponseEntity.ok(new JwtResponse(jwt,

					userDetails.getUsername(), userDetails.getEmail(), roles));

		} catch (BadCredentialsException e) {
			userservice.increaseFailedAttempts(user);
			throw e;
		}
		
	   
	}

	@PostMapping("/signup")
		public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
			if (userRepository.existsByUsername(signUpRequest.getUsername())) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!!!!"));
			}
	 
			if (userRepository.existsByEmail(signUpRequest.getEmail())) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
			}
	 
			// Create new user's account
			User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),encoder.encode(signUpRequest.getPassword()));
	 
			Set<String> strRoles = signUpRequest.getRole();
	 
			Set<Role> roles = new HashSet<>();
			System.out.println("=====" + strRoles);
			if (strRoles == null) {
				
				return  ResponseEntity.badRequest().body(new MessageResponse(" Role is not found in payload."));
				
				
			} else {
				
				for (String role : strRoles) {
				    Role userRole = roleRepository.findByName(role);
	 
				    if (userRole == null) {
				        return  ResponseEntity.badRequest().body(new MessageResponse("Role not found:- "+role));
				    }
	 
				    roles.add(userRole);
				}
			}
			user.setRoles(roles);
			userRepository.save(user);
	 
			return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
		}
	
	@GetMapping("/getAllUser")
    public ResponseEntity<?> getAllUserInfo() {
		
        List<User> listUser = userRepository.findAll();
 
        // Get JWT Token from the Authorization header
//        String token = jwtUtils.getJwtFromRequest(request);
//        
//        // Validate token and extract username
//        if (token != null && jwtUtils.validateJwtToken(token)) {
//            String username = jwtUtils.getUserNameFromJwtToken(token);
//
//            // Fetch user by username
//            
//            User user = userRepository.findByUsername(username)
//                    .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
//
//            // Map user details to DTO (or return directly, depending on your design)
//            UserResponse userResponse = new UserResponse(
//                user.getId(),
//                user.getUsername(),
//                user.getEmail(),
//                user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList())
//            );
//
            return ResponseEntity.ok(listUser);
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("Invalid token", 401, HttpStatus.UNAUTHORIZED));
//        }
    }
	
	@GetMapping("/getAllUser/{id}")
    public ResponseEntity<?> getUserInfo(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        return ResponseEntity.ok(user);
        }
	
	@GetMapping("/getbytoken")
    public ResponseEntity<?> getUserInfo(HttpServletRequest request) {
 
        // Get JWT Token from the Authorization header
        String token = JwtUtils.getJwtFromRequest(request);
        
        // Validate token and extract username
        if (token != null && jwtUtils.validateJwtToken(token)) {
            String username = jwtUtils.getUserNameFromJwtToken(token);
 
            // Fetch user by username
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
 
            // Map user details to DTO (or return directly, depending on your design)
            UserResponse userResponse = new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList())
            );
 
            return ResponseEntity.ok(userResponse);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("Invalid token"));
        }
    }
	
	@GetMapping("/ipAddress")
    public String getIpAddress() {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            return ip.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "Unable to fetch IP Address";
        }
    }

}
