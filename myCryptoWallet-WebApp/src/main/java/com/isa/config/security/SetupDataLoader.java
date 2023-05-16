package com.isa.config.security;

import com.isa.entity.User;
import com.isa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Optional<User> admin = repository.findUserByRolesIs("ADMIN");
        if (admin.isEmpty()) {
            User adminUser = new User();
            adminUser.setEmail("admin");
            adminUser.setPassword(passwordEncoder.encode("admin"));
            adminUser.setRoles("ADMIN, USER");
            repository.save(adminUser);
        }
    }
}
