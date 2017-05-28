package edu.hm.schatter.auth.businesslayer;

import edu.hm.schatter.auth.models.User;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class UserDatabaseImplTest {
    @Test
    public void getAdminTest() {
        UserDatabaseImpl sut = new UserDatabaseImpl();
        Optional<User> userOptional = sut.getUser("admin@shareit.com");

        assertTrue(userOptional.isPresent());
        assertEquals("admin", userOptional.get().getUsername());
    }

    @Test
    public void getNormalUserTest() {
        UserDatabaseImpl sut = new UserDatabaseImpl();
        Optional<User> userOptional = sut.getUser("user@shareit.com");

        assertTrue(userOptional.isPresent());
        assertEquals("user", userOptional.get().getUsername());
    }

    @Test
    public void getNonExistingUserTest() {
        UserDatabaseImpl sut = new UserDatabaseImpl();
        Optional<User> userOptional = sut.getUser("INVALID EMAIL");

        assertFalse(userOptional.isPresent());
    }

    @Test
    public void getNullExistingUserTest() {
        UserDatabaseImpl sut = new UserDatabaseImpl();
        Optional<User> userOptional = sut.getUser(null);

        assertFalse(userOptional.isPresent());
    }
}