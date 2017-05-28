package edu.hm.schatter.auth.models;

/**
 * Users can have groups with certain rights.
 */
public enum UserGroup {
    ADMIN("admin"),
    NORMAL("normal");

    private String stringRepresentation;

    /**
     * Constructor to save string representation.
     * @param stringRepresentation String representation.
     */
    UserGroup(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    /**
     * Getter.
     * @return String representation.
     */
    public String getStringRepresentation() {
        return stringRepresentation;
    }
}
