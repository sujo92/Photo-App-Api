package com.example.photoapp_api_users.service;

import com.example.photoapp_api_users.model.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


public interface UsersService extends UserDetailsService {

    public UserDto createUser(UserDto userDetails);
    public UserDto getUserDetailsByEmail(String email);
}
