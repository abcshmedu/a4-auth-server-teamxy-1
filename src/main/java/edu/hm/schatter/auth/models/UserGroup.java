package edu.hm.schatter.auth.models;

public enum UserGroup {
    ADMIN("admin"),
    NORMAL("normal");

    private String stringRepresentation;

    UserGroup(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    public String getStringRepresentation() {
        return stringRepresentation;
    }
}
