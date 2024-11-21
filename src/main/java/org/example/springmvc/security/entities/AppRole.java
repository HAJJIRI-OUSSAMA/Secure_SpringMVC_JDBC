package org.example.springmvc.security.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class AppRole {
    @Id
    private String role;



}
