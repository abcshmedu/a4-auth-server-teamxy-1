package edu.hm.schatter.auth.models;

/**
 * A user of our system.
 */
public class User {
    private String username;
    private String password;
    private String email;
    private boolean active;
    private UserGroup group;

    /**
     * Private default constructor.
     */
    private User() {
        username = "";
        password = "";
        email = "";
        active = false;
        group = null;
    }

    /**
     * Normal constructor.
     * @param username Name
     * @param password Password
     * @param email Email
     */
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        active = false;
        group = UserGroup.NORMAL;
    }

    /**
     * Getter.
     * @return username
     */
    public String getUsername() {
        return username;
    }
    /**
     * Setter.
     * @param username new name
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * Getter.
     * @return password
     */
    public String getPassword() {
        return password;
    }
    /**
     * Setter.
     * @param password new password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Getter.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter.
     * @param email new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter.
     * @return active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Setter.
     * @param active new status
     */
    public void setActive(boolean active) {
        this.active = active;
    }
    /**
     * Getter.
     * @return Usergroup
     */
    public UserGroup getGroup() {
        return group;
    }

    /**
     * Setter.
     * @param group new group
     */
    public void setGroup(UserGroup group) {
        this.group = group;
    }

    /**
     * Checks whether the user has valid information.
     * @return whether the user has valid information
     */
    public boolean isValid() {
        return isNotNullOrEmpty(username)
                && isNotNullOrEmpty(password)
                && isNotNullOrEmpty(email)
                && group != null;
    }

    /**
     * Checks whether a string is not null or empty.
     * @param siq string in question
     * @return whether the string is not null or empty
     */
    private boolean isNotNullOrEmpty(String siq) {
        return siq != null && !siq.equals("");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }
}

