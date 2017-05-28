package edu.hm.schatter.auth.businesslayer;

import edu.hm.schatter.auth.models.User;
import edu.hm.schatter.auth.models.UserGroup;

import java.util.*;

public class UserDatabaseImpl implements UserDatabase {

    private static Set<User> users = new HashSet<>();

    static {
        final User admin = new User("admin", "123456", "admin@shareit.com");
        admin.setGroup(UserGroup.ADMIN);
        users.add(admin);
    }

    @Override
    public Optional<User> getUser(String email) {
        Optional<User> result = Optional.empty();
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                result = Optional.of(user);
                break;
            }
        }
        return result;
    }

    @Override
    public User[] getUsers() {
        return (User[]) users.toArray();
    }

    @Override
    public AuthServiceResult createUser() {
        return null;
    }

    @Override
    public AuthServiceResult updateUser(String email, User updatedUser) {
        AuthServiceResult result;
        Optional<User> user = getUser(email);

        if (user.isPresent()) {
            users.remove(user.get());

            if (updatedUser.isValid()) {
                users.add(updatedUser);
                result = AuthServiceResult.OK;
            } else {
                result = AuthServiceResult.INVALID_INFORMATION;
            }
        } else {
            result = AuthServiceResult.NOT_FOUND;
        }

        return result;
    }
}
