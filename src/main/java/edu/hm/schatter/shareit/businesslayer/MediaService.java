package edu.hm.schatter.shareit.businesslayer;

import edu.hm.schatter.shareit.models.Book;
import edu.hm.schatter.shareit.models.Disc;

/**
 * Interface for a media service for the share it application.
 */
public interface MediaService {
    /**
     * Adds a book to the system.
     * @param book Book to be added.
     * @return Information on how the procedure went.
     */
    MediaServiceResult addBook(Book book);

    /**
     * Adds a disc to the system.
     * @param disc Disc to be added.
     * @return Information on how the procedure went.
     */
    MediaServiceResult addDisc(Disc disc);

    /**
     * Getter for all books currently in the system.
     * @return Array of all books.
     */
    Book[] getBooks();

    /**
     * Getter for all discs currently in the system.
     * @return Array of all discs.
     */
    Disc[] getDiscs();

    /**
     * Updates the information of a book.
     * @param isbn The isbn which identifies the book to be updated.
     * @param book The book containing the updated information.
     * @return Information on how the procedure went.
     */
    MediaServiceResult updateBook(String isbn, Book book);

    /**
     * Updates the information of a disc.
     * @param barcode The barcode which identifies the disc to be updated.
     * @param disc The disc containing the updated information.
     * @return Information on how the procedure went.
     */
    MediaServiceResult updateDisc(String barcode, Disc disc);

    /**
     * Getter for a specific book.
     * @param isbn The isbn which identifies the book.
     * @return The book.
     */
    Book getBookByISBN(String isbn);

    /**
     * Getter for a specific disc.
     * @param barcode The barcode which identifies the disc.
     * @return The disc.
     */
    Disc getDiscByBarcode(String barcode);
}
