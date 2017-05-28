package edu.hm.schatter.auth.models;

public class User {
    private String username;
    private String password;
    private String email;
    private boolean active;
    private UserGroup group;

    private User() {
        username = "";
        password = "";
        email = "";
        active = false;
        group = null;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        active = false;
        group = UserGroup.NORMAL;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public UserGroup getGroup() {
        return group;
    }

    public void setGroup(UserGroup group) {
        this.group = group;
    }

    public boolean isValid() {
        return isNotNullOrEmpty(username)
                && isNotNullOrEmpty(password)
                && isNotNullOrEmpty(email)
                && group != null;
    }

    private boolean isNotNullOrEmpty(String sut) {
        return sut != null && !sut.equals("");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }
}

