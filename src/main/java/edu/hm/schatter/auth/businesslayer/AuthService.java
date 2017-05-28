package edu.hm.schatter.auth.businesslayer;

/**
 * Authentication service that handles tokens.
 */
public interface AuthService {
    String INVALID_TOKEN_RESPONSE = "{\"valid\":false}";

    /**
     * Authenticates a user via their email and password.
     * @param email Users email
     * @param password Users password
     * @return Generated token or null
     */
    String authenticateUser(String email, String password);

    /**
     * Checks whether a token is valid.
     * @param token Token to be checked.
     * @return Response as JSON.
     */
    String checkToken(String token);
}
