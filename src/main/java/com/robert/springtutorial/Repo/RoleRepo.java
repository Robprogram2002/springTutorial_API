package com.robert.springtutorial.Repo;

import com.robert.springtutorial.Authentication.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String name);

}
