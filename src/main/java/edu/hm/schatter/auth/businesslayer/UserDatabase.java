package edu.hm.schatter.auth.businesslayer;

import edu.hm.schatter.auth.models.User;
import java.util.Optional;

/**
 * Database containing all users.
 */
public interface UserDatabase {
    /**
     * Get a certain user via their email.
     * @param email The users email.
     * @return The user.
     */
    Optional<User> getUser(String email);
}
