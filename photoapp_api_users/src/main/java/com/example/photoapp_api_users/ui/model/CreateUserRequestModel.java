package com.example.photoapp_api_users.ui.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CreateUserRequestModel {

    @NotNull(message="First name cannot be null")
    @Size(min=2, message = "First name must not be less tha two characters")
    private String firstName;

    @NotNull(message="First name cannot be null")
    @Size(min=2, message = "Last name must not be less tha two characters")
    private String lastName;

    @NotNull(message = "Password cannot be null")
    @Size(min=8, max=16, message="password must be greater than 8 chars and less than 16 chars")
    private String password;

    @NotNull
    @Email
    private String email;
}
