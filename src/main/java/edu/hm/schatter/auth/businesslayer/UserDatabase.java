package edu.hm.schatter.auth.businesslayer;

import edu.hm.schatter.auth.models.User;

import java.util.List;
import java.util.Optional;

public interface UserDatabase {
    Optional<User> getUser(String email);

    User[] getUsers();

    AuthServiceResult createUser();

    AuthServiceResult updateUser(String email, User updatedUser);
}
