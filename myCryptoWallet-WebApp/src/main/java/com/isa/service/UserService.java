package com.isa.service;

import com.isa.dto.UserRegistrationDto;
import com.isa.entity.User;

public interface UserService {
    User save(UserRegistrationDto registrationDto);
}
