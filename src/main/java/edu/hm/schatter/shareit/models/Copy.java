package edu.hm.schatter.shareit.models;

/**
 * A copy is a data structure which holds the information of which person is owning a medium at the moment.
 */
public class Copy {
    private final Medium medium;
    private final String owner;

    /**
     * Standard constructor.
     * @param medium The medium.
     * @param owner The owner of the medium.
     */
    public Copy(Medium medium, String owner) {
        if (medium == null) {
            throw new IllegalArgumentException("medium must not be null");
        }

        if (owner == null) {
            throw new IllegalArgumentException("owner must not be null");
        }

        this.medium = medium;
        this.owner = owner;
    }

    /**
     * Getter.
     * @return The medium.
     */
    public Medium getMedium() {
        return medium;
    }

    /**
     * Getter.
     * @return Username of the owner.
     */
    public String getUsername() {
        return owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Copy copy = (Copy) o;

        if (!medium.equals(copy.medium)) {
            return false;
        }
        return owner.equals(copy.owner);

    }

    @Override
    public int hashCode() {
        int result = medium.hashCode();
        result = 31 * result + owner.hashCode();
        return result;
    }
}
