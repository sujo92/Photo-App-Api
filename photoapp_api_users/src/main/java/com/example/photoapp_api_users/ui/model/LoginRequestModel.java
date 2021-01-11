package com.example.photoapp_api_users.ui.model;

import lombok.Data;

@Data
public class LoginRequestModel {
    private String email;
    private String password;
}
