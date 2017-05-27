package edu.hm.schatter.shareit.models;

/**
 * Data strucutre representing a medium.
 */
public abstract class Medium {
    private final String title;

    /**
     * Standard constructor.
     * @param title The title of the medium.
     */
    public Medium(String title) {
        if (title == null) {
            throw new IllegalArgumentException("Title must not be null");
        }
        this.title = title;
    }

    /**
     * Getter.
     * @return The title of the medium.
     */
    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Medium medium = (Medium) o;

        return title.equals(medium.title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
