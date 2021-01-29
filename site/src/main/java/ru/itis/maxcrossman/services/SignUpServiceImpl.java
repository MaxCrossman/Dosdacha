package ru.itis.maxcrossman.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.maxcrossman.dto.SignUpForm;
import ru.itis.maxcrossman.models.User;
import ru.itis.maxcrossman.repositories.UsersRepository;

public class SignUpServiceImpl implements SignUpService {

    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;

    public SignUpServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void signUp(SignUpForm form) {
        User user = new User(
                form.getEmail(),
                passwordEncoder.encode(form.getPassword()),
                form.getFirstName(),
                form.getLastName());
        usersRepository.save(user);
    }
}
