package com.isa.service;

import com.isa.dto.UserRegistrationDto;
import com.isa.entity.Role;
import com.isa.entity.User;
import com.isa.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User();
        user.setFirstName(registrationDto.getFirstName());
        user.setLastName(registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(registrationDto.getPassword());
        Role role = new Role();
        role.setName("ROLE_USER");
        user.setRoles(Arrays.asList(role));
        return userRepository.save(user);
    }
}
