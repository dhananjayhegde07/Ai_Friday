package com.ai.friday.service;

import com.ai.friday.entities.UserEntity;
import com.ai.friday.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepo.findByEmail(username);
        if (user.isEmpty())
            throw new UsernameNotFoundException(username);
        return new User(username,
                user.get().getPassword(),
                user.get().getPermissions().stream()
                        .map(SimpleGrantedAuthority::new)
                        .toList()
                );
    }
}
