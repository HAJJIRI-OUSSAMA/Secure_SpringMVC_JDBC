package org.example.springmvc;

import org.example.springmvc.entities.*;
import org.example.springmvc.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.provisioning.*;

import javax.sql.*;
import java.util.*;

@SpringBootApplication
public class SpringMvcApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(SpringMvcApplication.class, args);
    }
//    @Bean
    CommandLineRunner start(PatientRepository patientRepository) {
        return args -> {
            patientRepository.save(new Patient(null, "Mohamed", new Date(), false, 42));
            patientRepository.save(new Patient(null, "Imane", new Date(), true, 98));
            patientRepository.save(new Patient(null, "Yassine", new Date(), true, 342));
            patientRepository.save(new Patient(null, "Laila", new Date(), false, 123));
        };
    }
    @Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager){
        PasswordEncoder passwordEncoder = passwordEncoder();
        return args -> {
            UserDetails user1 = jdbcUserDetailsManager.loadUserByUsername("user");
            if(user1 == null) {
                jdbcUserDetailsManager.createUser(User.withUsername("user").
                        password(passwordEncoder.encode("123")).
                        roles("USER").build());
            }

            UserDetails admin = jdbcUserDetailsManager.loadUserByUsername("admin");
            if (admin == null) {
                jdbcUserDetailsManager.createUser(User.withUsername("admin").
                        password(passwordEncoder.encode("admin")).
                        roles("USER", "ADMIN").build());
            }

        };
    }
@Bean
    PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }


    @Override
    public void run(String... args) throws Exception {

    }
}
