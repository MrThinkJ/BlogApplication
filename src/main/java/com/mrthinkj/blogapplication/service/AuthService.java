package com.mrthinkj.blogapplication.service;

import com.mrthinkj.blogapplication.payload.LoginDTO;
import com.mrthinkj.blogapplication.payload.RegisterDTO;

public interface AuthService {
    String login(LoginDTO loginDTO);
    String register(RegisterDTO registerDTO);
}
