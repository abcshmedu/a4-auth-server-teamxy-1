package edu.hm.schatter.auth.businesslayer;

import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class AuthServiceImplTest {

    @Test
    public void checkingInvalidTokenReturnsInvalidTokenResponse() {
        AuthServiceImpl sut = new AuthServiceImpl();

        String response = sut.checkToken("INVALID TOKEN");
        assertEquals(response, AuthService.INVALID_TOKEN_RESPONSE);
    }

    @Test
    public void checkingNullTokenReturnsInvalidTokenResponse() {
        AuthServiceImpl sut = new AuthServiceImpl();

        String response = sut.checkToken(null);
        assertEquals(response, AuthService.INVALID_TOKEN_RESPONSE);
    }

    @Test
    public void authenticatingNonExistentUserReturnsNull() {
        AuthServiceImpl sut = new AuthServiceImpl();

        String token = sut.authenticateUser("INVALID EMAIL", "INVALID PASSWORD");
        assertNull(token);
    }

    @Test
    public void authenticatingExistingUserWithWrongPasswordReturnsNull() {
        AuthServiceImpl sut = new AuthServiceImpl();

        String token = sut.authenticateUser("admin@shareit.com", "INVALID PASSWORD");
        assertNull(token);
    }

    @Test
    public void correctlyAuthenticatingReturnsToken() {
        AuthServiceImpl sut = new AuthServiceImpl();

        String token = sut.authenticateUser("admin@shareit.com", "123456");
        assertNotNull(token);
    }

    @Test
    public void correctlyAuthenticatingAndThenCheckingGeneratedTokenGivesValidResponse() {
        AuthServiceImpl sut = new AuthServiceImpl();

        String token = sut.authenticateUser("admin@shareit.com", "123456");

        String response = sut.checkToken(token);
        JSONObject obj = new JSONObject(response);

        boolean isTokenValid = obj.getBoolean("valid");
        assertTrue(isTokenValid);

        String name = obj.getString("user");
        assertEquals(name, "admin");
    }
}