package org.example.springmvc.security.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class AppUser {
    @Id
    private  String userId;
    @Column(unique = true)
    private String username;
    private String password;
    private String email;
    @ManyToMany(fetch=FetchType.EAGER)
    private List<AppRole> roles;
}
