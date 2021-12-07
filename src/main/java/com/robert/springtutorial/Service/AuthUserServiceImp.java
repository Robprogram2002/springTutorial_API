package com.robert.springtutorial.Service;

import com.robert.springtutorial.Authentication.AuthUser;
import com.robert.springtutorial.Authentication.Role;
import com.robert.springtutorial.Repo.AuthUserRepo;
import com.robert.springtutorial.Repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
// dependency injection
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AuthUserServiceImp implements AuthUserService, UserDetailsService {
    private final AuthUserRepo authUserRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser user = authUserRepo.findAuthUserByUsername(username);
        if (user == null) {
            String errorMessage = "User not found in the database";
            log.error(errorMessage);
            throw new UsernameNotFoundException(errorMessage);
        }

        log.info("user: {} found in DB", username);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        var springAuthUser =  new User(user.getUsername(), user.getPassword(), authorities);
        return springAuthUser;
    }

    @Override
    public AuthUser saveAuthUser(AuthUser user) {
        log.info("saving new auth user to DB: {}", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return authUserRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("saving new role to DB : {}", role.getName() );
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to auth user: {}", roleName, username);
        AuthUser user = authUserRepo.findAuthUserByUsername(username);
        Role role = roleRepo.findByName(roleName);

        user.getRoles().add(role);
    }

    @Override
    public AuthUser getAuthUser(String username) {
        log.info("fetching the auth user : {}", username);
        return authUserRepo.findAuthUserByUsername(username);
    }

    @Override
    public List<AuthUser> getAuthUsers() {
        log.info("fetching all the auth users");
        return authUserRepo.findAll();
    }
}
