package org.example.springmvc.security.repo;

import org.example.springmvc.security.entities.*;
import org.springframework.data.jpa.repository.*;

public interface AppUserRepository extends JpaRepository<AppUser,String> {
    AppUser findByUsername(String username);
}
