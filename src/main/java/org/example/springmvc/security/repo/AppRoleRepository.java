package org.example.springmvc.security.repo;

import org.example.springmvc.security.entities.*;
import org.springframework.data.jpa.repository.*;

public interface AppRoleRepository extends JpaRepository<AppRole,String> {
}
