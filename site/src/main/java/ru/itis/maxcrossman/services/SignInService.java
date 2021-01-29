package ru.itis.maxcrossman.services;

import ru.itis.maxcrossman.dto.UserDto;
import ru.itis.maxcrossman.dto.SignInForm;

import java.util.Optional;

public interface SignInService {
    Optional<UserDto> signIn(SignInForm userForm);
}
