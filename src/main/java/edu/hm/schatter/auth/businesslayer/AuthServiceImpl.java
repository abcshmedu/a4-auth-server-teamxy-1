package edu.hm.schatter.auth.businesslayer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hm.schatter.auth.models.User;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.UUID;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class AuthServiceImpl implements AuthService {
    private static final long TOKEN_EXPIRATION_SPAN_IN_MINUTES = 5;
    private static final String INVALID_TOKEN_RESPONSE = "{\"valid\":false}";

    private static Map<String, TokenInfo> tokenDatabase = new HashMap<>();
    private UserDatabase userDatabase = new UserDatabaseImpl();

    @Override
    public String authenticateUser(String email, String password) {
        final String result;

        Optional<User> optionalUser = userDatabase.getUser(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (user.getPassword().equals(password)) {
                final String token = getUnusedToken();
                final TokenInfo tokenInfo = new TokenInfo(user);
                tokenDatabase.put(token, tokenInfo);
                result = token;

            } else {
                result = null;
            }
        } else {
            result = null;
        }

        return result;
    }

    @Override
    public String checkToken(String token) {
        final TokenInfo tokenInfo = tokenDatabase.get(token);
        String result;

        if (tokenInfo == null) {
            result = AuthService.INVALID_TOKEN_RESPONSE;
        } else {
            try {
                result = tokenInfo.getJSON();
            } catch (JsonProcessingException e) {
                result = AuthService.INVALID_TOKEN_RESPONSE;
                e.printStackTrace();
            }
        }

        return result;
    }

    private String getUnusedToken() {
        String token = generateToken();
        while (tokenDatabase.keySet().contains(token)) {
            token = generateToken();
        }
        return token;
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }

    private final class TokenInfo {

        boolean valid;
        final String user;
        final String email;
        final String userGroup;
        final long expirationDate;

        TokenInfo() {
            valid = false;
            user = "";
            email = "";
            userGroup = null;
            expirationDate = 0;
        }

        public void setValid(boolean valid) {
            this.valid = valid;
        }

        public boolean getValid() {
            return valid;
        }

        public String getUser() {
            return user;
        }

        public String getEmail() {
            return email;
        }

        public String getUserGroup() {
            return userGroup;
        }

        public long getExpirationDate() {
            return expirationDate;
        }

        TokenInfo(User user) {
            this.user = user.getUsername();
            email = user.getEmail();
            userGroup = user.getGroup().toString();
            valid = true;
            expirationDate = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())
                    + TimeUnit.MINUTES.toSeconds(TOKEN_EXPIRATION_SPAN_IN_MINUTES);
        }

        boolean isValid() {
            final long currentTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
            return currentTime < expirationDate;
        }

        /**
         * Converts TokenInfo to json.
         * @return json.
         * @throws JsonProcessingException object could not be converted to json.
         */
        private String getJSON() throws JsonProcessingException {
            valid = isValid();
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);
        }
    }
}

