package edu.hm.schatter.auth.businesslayer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hm.schatter.auth.models.User;

import java.util.Optional;
import java.util.UUID;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Implementation of auth service.
 */
public class AuthServiceImpl implements AuthService {
    private static final long TOKEN_EXPIRATION_SPAN_IN_MINUTES = 5;

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

    /**
     * Generates a new and unused token.
     * @return new and unused token
     */
    private String getUnusedToken() {
        String token = generateToken();
        while (tokenDatabase.keySet().contains(token)) {
            token = generateToken();
        }
        return token;
    }

    /**
     * Generates a token.
     * @return Token
     */
    private String generateToken() {
        return UUID.randomUUID().toString();
    }

    /**
     * Stores info about a token.
     */
    private final class TokenInfo {

        private boolean valid;
        private final String user;
        private final String email;
        private final String userGroup;
        private final long expirationDate;

        /**
         * Standard constructor
         */
        TokenInfo() {
            valid = false;
            user = "";
            email = "";
            userGroup = null;
            expirationDate = 0;
        }

        /**
         * Setter.
         * @param valid new status
         */
        public void setValid(boolean valid) {
            this.valid = valid;
        }

        /**
         * Getter. Needed for jackson.
         * @return valid
         */
        public boolean getValid() {
            return valid;
        }

        /**
         * Getter. Needed for jackson
         * @return user
         */
        public String getUser() {
            return user;
        }

        /**
         * Getter. Needed for jackson
         * @return email
         */
        public String getEmail() {
            return email;
        }

        /**
         * Getter. Needed for jackson
         * @return user group
         */
        public String getUserGroup() {
            return userGroup;
        }

        /**
         * Getter. Needed for jackson
         * @return expiration date
         */
        public long getExpirationDate() {
            return expirationDate;
        }

        /**
         * Normal constructor
         * @param user User containing information
         */
        TokenInfo(User user) {
            this.user = user.getUsername();
            email = user.getEmail();
            userGroup = user.getGroup().toString();
            valid = true;
            expirationDate = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())
                    + TimeUnit.MINUTES.toSeconds(TOKEN_EXPIRATION_SPAN_IN_MINUTES);
        }

        /**
         * Gives info whether the token is expired or still valid.
         * @return whether the token is expired or still valid.
         */
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

