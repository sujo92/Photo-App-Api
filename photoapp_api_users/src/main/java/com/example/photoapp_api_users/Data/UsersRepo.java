package com.example.photoapp_api_users.Data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.CrudRepositoryExtensionsKt;

public interface UsersRepo extends CrudRepository<UserEntity, Long> {

    UserEntity findByEmail(String username);
}
