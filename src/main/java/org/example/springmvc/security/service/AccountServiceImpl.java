package org.example.springmvc.security.service;

import lombok.*;
import org.example.springmvc.security.entities.*;
import org.example.springmvc.security.repo.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@Transactional //gerer les transaction
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;


    @Override
    public AppUser addNewUser(String username, String password, String email, String confirmPasword) {
        AppUser appUser =appUserRepository.findByUsername(username);
        if (appUser != null) throw new RuntimeException("This user already exist");
        if (!password.equals(confirmPasword)) throw new RuntimeException("Password not match");
        appUser = AppUser.builder().
                userId(UUID.randomUUID().toString())
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();
        AppUser savedAppUser = appUserRepository.save(appUser);
        return null;
    }

    @Override
    public AppRole addNewRole(String role) {
        AppRole appRole = appRoleRepository.findById(role).orElse(null);
        if(appRole != null) throw new RuntimeException("this role already exist");
        appRole = AppRole.builder()
                .role(role)
                .build();
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        AppUser appUser=appUserRepository.findByUsername(username);
        AppRole appRole = appRoleRepository.findById(role).get();
//        if (appUser != null) throw new RuntimeException("User Not Found");
//        if (appRole != null) throw new RuntimeException("Role Not Found");
        appUser.getRoles().add(appRole);
//        appUserRepository.save(appUser); // parceque la methode est transactionel
    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        AppUser appUser=appUserRepository.findByUsername(username);
        AppRole appRole = appRoleRepository.findById(role).get();
        appUser.getRoles().remove(appRole);
    }



    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}
