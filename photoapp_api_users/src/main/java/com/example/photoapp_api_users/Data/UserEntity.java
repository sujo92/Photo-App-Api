package com.example.photoapp_api_users.Data;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Data
@Table(name="users")
public class UserEntity implements Serializable {

    private static final long serialVersionUUID = -233454665475L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable=false, length=50)
    private String firstName;

    @Column(nullable=false, length=50)
    private String lastName;

    @Column(nullable=false, length=120, unique = true)
    private String email;

    @Column(nullable=false, unique = true)
    private String userId;

    @Column(nullable=false, unique = true)
    private String encryptedPassword;

}
