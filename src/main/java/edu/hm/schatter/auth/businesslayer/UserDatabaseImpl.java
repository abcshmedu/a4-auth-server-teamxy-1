package edu.hm.schatter.auth.businesslayer;

import edu.hm.schatter.auth.models.User;
import edu.hm.schatter.auth.models.UserGroup;

import java.util.*;

/**
 * Implementation of UserDatabase.
 */
public class UserDatabaseImpl implements UserDatabase {

    private static Set<User> users = new HashSet<>();

    static {
        final User admin = new User("admin", "123456", "admin@shareit.com");
        admin.setGroup(UserGroup.ADMIN);
        users.add(admin);

        final User user = new User("user", "123456", "user@shareit.com");
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
}
