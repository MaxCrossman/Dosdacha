package ru.itis.maxcrossman.repositories;

import ru.itis.maxcrossman.models.User;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<User>{
    Optional<User> findByEmail(String email);
}
