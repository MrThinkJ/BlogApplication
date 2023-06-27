package com.mrthinkj.blogapplication.service.impl;

import com.mrthinkj.blogapplication.entity.Role;
import com.mrthinkj.blogapplication.entity.User;
import com.mrthinkj.blogapplication.exception.BlogAPIException;
import com.mrthinkj.blogapplication.exception.ResourceNotFoundException;
import com.mrthinkj.blogapplication.payload.LoginDTO;
import com.mrthinkj.blogapplication.payload.RegisterDTO;
import com.mrthinkj.blogapplication.repository.RoleRepository;
import com.mrthinkj.blogapplication.repository.UserRepository;
import com.mrthinkj.blogapplication.security.JwtTokenProvider;
import com.mrthinkj.blogapplication.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    AuthenticationManager authenticationManager;
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return token;
    }

    @Override
    public String register(RegisterDTO registerDTO) {
        // Check for username exists in database
        if (userRepository.existsByUsername(registerDTO.getUsername()))
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "This username is already used");
        if (userRepository.existsByEmail(registerDTO.getEmail()))
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "This email is already used");

        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setEmail(registerDTO.getEmail());

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);

        userRepository.save(user);

        return "User register successfully";
    }
}
