package edu.hm.schatter.auth.businesslayer;

import edu.hm.schatter.auth.models.User;

public interface AuthService {
    public static final String INVALID_TOKEN_RESPONSE = "{\"valid\":false}";

    String authenticateUser(String email, String password);

    String checkToken(String token);
}
