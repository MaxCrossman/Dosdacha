package ru.itis.maxcrossman.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.maxcrossman.dto.UserDto;
import ru.itis.maxcrossman.dto.SignInForm;
import ru.itis.maxcrossman.models.User;
import ru.itis.maxcrossman.repositories.UsersRepository;

import java.util.Optional;

public class SignInServiceImpl implements SignInService {

    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;

    public SignInServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<UserDto> signIn(SignInForm signInForm) {
        Optional<User> userOptional = usersRepository.findByEmail(signInForm.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(signInForm.getPassword(), user.getHashPassword())) {
                return Optional.of(UserDto.from(user));
            } else return Optional.empty();
        }
        return Optional.empty();
    }
}
