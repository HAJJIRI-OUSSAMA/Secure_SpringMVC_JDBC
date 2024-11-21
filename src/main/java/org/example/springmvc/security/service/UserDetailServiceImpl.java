package org.example.springmvc.security.service;

import lombok.*;
import org.example.springmvc.security.entities.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private AccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = accountService.loadUserByUsername(username);

        if (appUser == null) throw new UsernameNotFoundException(String.format("User %s not Found",username));

        String[] roles = appUser.getRoles().stream().map(user->user.getRole()).toArray(String[]::new);
        UserDetails userDetails = User
                .withUsername(username)
                .password(appUser.getPassword())
                .roles(roles)
                .build();

        return userDetails;
    }
}
