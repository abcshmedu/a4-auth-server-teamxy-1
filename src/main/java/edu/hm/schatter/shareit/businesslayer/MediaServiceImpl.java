package edu.hm.schatter.shareit.businesslayer;

import edu.hm.schatter.shareit.models.Book;
import edu.hm.schatter.shareit.models.Disc;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation for the media service interface.
 */
public class MediaServiceImpl implements MediaService {

    private static final List<Book> BOOKS = new ArrayList<>();
    private static final List<Disc> DISCS = new ArrayList<>();

    @Override
    public MediaServiceResult addBook(Book book) {
        final MediaServiceResult result;

        if (book.getAuthor().equals("") || book.getTitle().equals("") || book.getIsbn().equals("")) {
            result = MediaServiceResult.INVALID_INFORMATION;

        } else if (doesISBNexist(book.getIsbn())) {
            result = MediaServiceResult.ALREADY_EXISTS;

        } else {
            BOOKS.add(book);
            result = MediaServiceResult.OK;
        }

        return result;
    }

    @Override
    public MediaServiceResult addDisc(Disc disc) {
        final MediaServiceResult result;

        if (!discHasValidInformation(disc)) {
            result = MediaServiceResult.INVALID_INFORMATION;

        } else if (doesBarcodeExist(disc.getBarcode())) {
            result = MediaServiceResult.ALREADY_EXISTS;

        } else {
            DISCS.add(disc);
            result = MediaServiceResult.OK;
        }

        return result;
    }

    /**
     * Checks whether a disc has valid information. Invalid FSK can occur in reflection-built instances.
     * @param disc The disc that is checked.
     * @return Whether the disc has valid information.
     */
    private boolean discHasValidInformation(Disc disc) {
        return !disc.getDirector().equals("") && !disc.getTitle().equals("")
                && disc.getFsk() >= Disc.MIN_FSK && disc.getFsk() <= Disc.MAX_FSK
                && !disc.getBarcode().equals("");
    }

    @Override
    public Book[] getBooks() {
        return BOOKS.toArray(new Book[BOOKS.size()]);
    }

    @Override
    public Disc[] getDiscs() {
        return DISCS.toArray(new Disc[DISCS.size()]);
    }

    @Override
    public MediaServiceResult updateBook(String isbn, Book book) {
        final MediaServiceResult result;
        final Book bookToBeUpdated = getBookByISBN(isbn);

        if (!book.getIsbn().equals(isbn)) {
            result = MediaServiceResult.INVALID_INFORMATION;

        } else if (bookToBeUpdated == null) {
            result = MediaServiceResult.NOT_FOUND;

        } else if (book.getAuthor().equals("") || book.getTitle().equals("")) {
            result = MediaServiceResult.MISSING_INFORMATION;

        } else if (bookToBeUpdated.getTitle().equals(book.getTitle())
                && bookToBeUpdated.getAuthor().equals(book.getAuthor())) {
            result = MediaServiceResult.ALREADY_EXISTS;

        } else {
            BOOKS.remove(bookToBeUpdated);
            BOOKS.add(new Book(book.getTitle(), book.getAuthor(), isbn));
            result = MediaServiceResult.OK;
        }

        return result;
    }

    @Override
    public MediaServiceResult updateDisc(String barcode, Disc disc) {
        final MediaServiceResult result;
        final Disc discToBeUpdated = getDiscByBarcode(barcode);

        if (!disc.getBarcode().equals(barcode) || !disc.hasValidFSK()) {
            result = MediaServiceResult.INVALID_INFORMATION;

        } else if (discToBeUpdated == null) {
            result = MediaServiceResult.NOT_FOUND;

        } else if (disc.getDirector().equals("") || disc.getTitle().equals("")) {
            result = MediaServiceResult.MISSING_INFORMATION;

        } else if (discToBeUpdated.getTitle().equals(disc.getTitle())
                && discToBeUpdated.getDirector().equals(disc.getDirector())
                && discToBeUpdated.getFsk() == disc.getFsk()) {
            result = MediaServiceResult.ALREADY_EXISTS;

        } else {
            DISCS.remove(discToBeUpdated);
            DISCS.add(new Disc(disc.getTitle(), barcode, disc.getDirector(), disc.getFsk()));
            result = MediaServiceResult.OK;
        }

        return result;
    }

    @Override
    public Book getBookByISBN(String isbn) {
        for (Book book : BOOKS) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public Disc getDiscByBarcode(String barcode) {
        for (Disc disc : DISCS) {
            if (disc.getBarcode().equals(barcode)) {
                return disc;
            }
        }
        return null;
    }

    /**
     * Checks whether an ISBN exists in the current book pool.
     * @param isbn The ISBN which is checked.
     * @return Whether the ISBN exists.
     */
    private boolean doesISBNexist(String isbn) {
        return getBookByISBN(isbn) != null;
    }

    /**
     * Checks whether a barcode exists in the current disc pool.
     * @param barcode The barcode which is checked.
     * @return Whether the barcode exists.
     */
    private boolean doesBarcodeExist(String barcode) {
        return getDiscByBarcode(barcode) != null;
    }

    /**
     * Resets the storage. Needed for testing.
     */
    void resetStorage() {
        BOOKS.clear();
        DISCS.clear();
    }
}
