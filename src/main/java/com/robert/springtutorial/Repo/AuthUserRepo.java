package com.robert.springtutorial.Repo;

import com.robert.springtutorial.Authentication.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRepo extends JpaRepository<AuthUser, Long> {
    AuthUser findAuthUserByUsername(String username);

}
