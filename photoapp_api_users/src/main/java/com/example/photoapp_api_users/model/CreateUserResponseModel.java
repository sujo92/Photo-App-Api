package com.example.photoapp_api_users.model;

import lombok.Data;

@Data
public class CreateUserResponseModel {
    private String firstName;
    private String lastName;
    private String email;
    private String userId;
}
