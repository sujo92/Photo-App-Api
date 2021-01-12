package com.example.photoapp_api_users.ui.controller;

import com.example.photoapp_api_users.Data.UserEntity;
import com.example.photoapp_api_users.model.CreateUserResponseModel;
import com.example.photoapp_api_users.model.UserDto;
import com.example.photoapp_api_users.service.UsersService;
import com.example.photoapp_api_users.ui.model.CreateUserRequestModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private Environment env;

    @Autowired
    private UsersService usersService;

    @GetMapping("/status/check")
    public String status(){

        return "working on port: "+env.getProperty("local.server.port");
    }

    @PostMapping(
            consumes= {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel userDetails){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
        UserDto  createdUser = usersService.createUser(userDto);
        CreateUserResponseModel returnValue =modelMapper.map(createdUser,CreateUserResponseModel.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }
}
