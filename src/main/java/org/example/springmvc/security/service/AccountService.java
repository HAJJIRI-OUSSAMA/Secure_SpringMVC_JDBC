package org.example.springmvc.security.service;

import org.example.springmvc.security.entities.*;

public interface AccountService {
    AppUser addNewUser(String username,String password,String email,String confirmPasword);
    AppRole addNewRole(String role);
    void addRoleToUser(String username,String role);
    void removeRoleFromUser(String username,String role);


    AppUser loadUserByUsername(String username);
}
