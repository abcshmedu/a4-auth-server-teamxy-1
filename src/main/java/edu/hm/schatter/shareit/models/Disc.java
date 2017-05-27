package edu.hm.schatter.shareit.models;

/**
 * A data structure representing a disc.
 */
public class Disc extends Medium {
    public static final int MIN_FSK = 0;
    public static final int MAX_FSK = 18;

    private final String barcode;
    private final String director;
    private final int fsk;

    /**
     * Private standard constructor needed for reflection.
     */
    private Disc() {
        super("");
        barcode = "";
        director = "";
        fsk = 0;
    }

    /**
     * Standard constructor.
     * @param title The title of the disc.
     * @param barcode The barcode of the disc.
     * @param director The director of the disc.
     * @param fsk The fsk of the disc. Must be in between MIN_FSK and MAX_FSK.
     */
    public Disc(String title, String barcode, String director, int fsk) {
        super(title);

        if (barcode == null) {
            throw new IllegalArgumentException("barcode must not be null");
        }

        if (director == null) {
            throw new IllegalArgumentException("director must not be null");
        }

        if (!isValidFSK(fsk)) {
            throw new IllegalArgumentException("fsk must be between 0 and 18");
        }

        this.barcode = barcode;
        this.director = director;
        this.fsk = fsk;
    }

    /**
     * Getter.
     * @return The barcode of the disc.
     */
    public String getBarcode() {
        return barcode;
    }
    /**
     * Getter.
     * @return The director of the disc.
     */
    public String getDirector() {
        return director;
    }
    /**
     * Getter.
     * @return The fsk of the disc.
     */
    public int getFsk() {
        return fsk;
    }

    /**
     * Checks whether the disc has a valid fsk. (Invalid fsks can occur in reflection-built instances)
     * @return Whether the fsk of the disc is valid.
     */
    public boolean hasValidFSK() {
        return isValidFSK(fsk);
    }

    /**
     * Checks whether a fsk is valid.
     * @param fsk The fsk to be checked.
     * @return Whether fsk is valid.
     */
    private boolean isValidFSK(int fsk) {
        return fsk <= MAX_FSK && fsk >= MIN_FSK;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }

        if (this == o) {
            return true;
        }
        if (getClass() != o.getClass()) {
            return false;
        }

        Disc disc = (Disc) o;

        if (fsk != disc.fsk) {
            return false;
        }
        if (!barcode.equals(disc.barcode)) {
            return false;
        }
        return director.equals(disc.director);

    }

    @Override
    public int hashCode() {
        int result = barcode.hashCode();
        result = 31 * result + director.hashCode();
        result = 31 * result + fsk;
        return result;
    }
}
