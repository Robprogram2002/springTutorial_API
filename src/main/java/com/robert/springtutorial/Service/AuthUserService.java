package com.robert.springtutorial.Service;

import com.robert.springtutorial.Authentication.AuthUser;
import com.robert.springtutorial.Authentication.Role;

import java.util.List;

public interface AuthUserService {
    AuthUser saveAuthUser(AuthUser user);
    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);
    AuthUser getAuthUser(String username);
    List<AuthUser> getAuthUsers();
}
