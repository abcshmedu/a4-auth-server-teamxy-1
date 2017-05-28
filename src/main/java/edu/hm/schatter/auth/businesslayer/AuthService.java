package edu.hm.schatter.auth.businesslayer;

public interface AuthService {
    String INVALID_TOKEN_RESPONSE = "{\"valid\":false}";

    String authenticateUser(String email, String password);

    String checkToken(String token);
}
