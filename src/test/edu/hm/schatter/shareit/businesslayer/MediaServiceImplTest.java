package edu.hm.schatter.shareit.businesslayer;

import edu.hm.schatter.shareit.models.Book;
import edu.hm.schatter.shareit.models.Disc;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class MediaServiceImplTest {

    private final MediaService mediaService = new MediaServiceImpl();

    @After
    public void resetStorage() {
        MediaServiceImpl mediaServiceImpl = (MediaServiceImpl) mediaService;
        mediaServiceImpl.resetStorage();
    }

    @Test
    public void addingValidBookReturnsOK() {
        final Book book = new Book("title", "author", "isbn");
        final MediaServiceResult result = mediaService.addBook(book);
        assertEquals(result, MediaServiceResult.OK);
    }

    @Test
    public void addingBookWithInvalidTitleReturnsINVALID_INFORMATION() {
        final Book book = new Book("", "author", "isbn");
        final MediaServiceResult result = mediaService.addBook(book);
        assertEquals(result, MediaServiceResult.INVALID_INFORMATION);
    }

    @Test
    public void addingBookWithInvalidAuthorReturnsINVALID_INFORMATION() {
        final Book book = new Book("title", "", "isbn");
        final MediaServiceResult result = mediaService.addBook(book);
        assertEquals(result, MediaServiceResult.INVALID_INFORMATION);
    }

    @Test
    public void addingBookWithInvalidISBNReturnsINVALID_INFORMATION() {
        final Book book = new Book("title", "author", "");
        final MediaServiceResult result = mediaService.addBook(book);
        assertEquals(result, MediaServiceResult.INVALID_INFORMATION);
    }

    @Test
    public void addingTheSameBookTwiceReturnsALREADY_EXISTS() {
        final Book book = new Book("title", "author", "isbn");
        mediaService.addBook(book);
        final MediaServiceResult result = mediaService.addBook(book);
        assertEquals(result, MediaServiceResult.ALREADY_EXISTS);
    }

    @Test
    public void addingValidDiscReturnsOK() {
        final Disc disc = new Disc("title", "barcode", "director", 0);
        final MediaServiceResult result = mediaService.addDisc(disc);
        assertEquals(result, MediaServiceResult.OK);
    }

    @Test
    public void addingDiscWithInvalidTitleReturnsINVALID_INFORMATION() {
        final Disc disc = new Disc("", "barcode", "director", 0);
        final MediaServiceResult result = mediaService.addDisc(disc);
        assertEquals(result, MediaServiceResult.INVALID_INFORMATION);
    }

    @Test
    public void addingDiscWithInvalidDirectorReturnsINVALID_INFORMATION() {
        final Disc disc = new Disc("title", "barcode", "", 0);
        final MediaServiceResult result = mediaService.addDisc(disc);
        assertEquals(result, MediaServiceResult.INVALID_INFORMATION);
    }

    @Test
    public void addingDiscWithInvalidBarcodeReturnsINVALID_INFORMATION() {
        final Disc disc = new Disc("title", "", "director", 0);
        final MediaServiceResult result = mediaService.addDisc(disc);
        assertEquals(result, MediaServiceResult.INVALID_INFORMATION);
    }

    @Test
    public void addingTheSameDiscTwiceReturnsALREADY_EXISTS() {
        final Disc disc = new Disc("title", "barcode", "director", 0);
        mediaService.addDisc(disc);
        final MediaServiceResult result = mediaService.addDisc(disc);
        assertEquals(result, MediaServiceResult.ALREADY_EXISTS);
    }

    @Test
    public void getBooksNoItem() {
        final Book[] books = mediaService.getBooks();
        assertEquals(books.length, 0);
    }

    @Test
    public void getBooksSingleItem() {
        final Book book = new Book("title", "author", "isbn");
        mediaService.addBook(book);

        final Book[] books = mediaService.getBooks();
        assertEquals(books.length, 1);
        assertEquals(books[0], book);
    }

    @Test
    public void getBooks100Items() {
        for(int i = 0; i < 100; i++) {
            final Book book = new Book("title", "author", "isbn"+i);
            mediaService.addBook(book);
        }

        final Book[] books = mediaService.getBooks();
        assertEquals(books.length, 100);

        for(int i = 0; i < 100; i++) {
            final Book expectedBook = new Book("title", "author", "isbn"+i);
            assertEquals(books[i], expectedBook);
        }
    }

    @Test
    public void getDiscsNoItem() {
        final Disc[] discs = mediaService.getDiscs();
        assertEquals(discs.length, 0);
    }

    @Test
    public void getDiscsSingleItem() {
        final Disc disc = new Disc("title", "barcode", "director", 0);
        mediaService.addDisc(disc);

        final Disc[] discs = mediaService.getDiscs();
        assertEquals(discs.length, 1);
        assertEquals(discs[0], disc);
    }

    @Test
    public void getDiscs100Items() {
        for(int i = 0; i < 100; i++) {
            final Disc disc = new Disc("title", "barcode"+i, "director", 0);
            mediaService.addDisc(disc);
        }

        final Disc[] discs = mediaService.getDiscs();
        assertEquals(discs.length, 100);

        for(int i = 0; i < 100; i++) {
            final Disc expectedDisc = new Disc("title", "barcode"+i, "director", 0);
            assertEquals(discs[i], expectedDisc);
        }
    }

    @Test
    public void updatingBookReturnsOK() {
        final String isbn = "isbn";
        final Book book = new Book("title", "author", isbn);
        mediaService.addBook(book);

        final Book updatedBook = new Book("updated title", "updated author", isbn);
        final MediaServiceResult result = mediaService.updateBook(isbn, updatedBook);

        assertEquals(result, MediaServiceResult.OK);
    }

    @Test
    public void updatingBookWithoutTitleReturnsMISSING_INFORMATION() {
        final String isbn = "isbn";
        final Book book = new Book("title", "author", isbn);
        mediaService.addBook(book);

        final Book updatedBook = new Book("", "updated author", isbn);
        final MediaServiceResult result = mediaService.updateBook(isbn, updatedBook);

        assertEquals(result, MediaServiceResult.MISSING_INFORMATION);
    }

    @Test
    public void updatingBookWithoutAuthorReturnsMISSING_INFORMATION() {
        final String isbn = "isbn";
        final Book book = new Book("title", "author", isbn);
        mediaService.addBook(book);

        final Book updatedBook = new Book("updated title", "", isbn);
        final MediaServiceResult result = mediaService.updateBook(isbn, updatedBook);

        assertEquals(result, MediaServiceResult.MISSING_INFORMATION);
    }

    @Test
    public void updatingBookWithMismatchinISBNReturnsINVALID_INFORMATION() {
        final String isbn = "isbn";
        final Book book = new Book("title", "author", isbn);
        mediaService.addBook(book);

        final Book updatedBook = new Book("updated title", "updated author", isbn);
        final MediaServiceResult result = mediaService.updateBook("mismatching isbn", updatedBook);

        assertEquals(result, MediaServiceResult.INVALID_INFORMATION);
    }

    @Test
    public void updatingBookThatDoesNotExistReturnsNOT_FOUND() {
        final String isbn = "isbn";
        final Book updatedBook = new Book("", "updated author", isbn);
        final MediaServiceResult result = mediaService.updateBook(isbn, updatedBook);

        assertEquals(result, MediaServiceResult.NOT_FOUND);
    }

    @Test
    public void updatingBookWithSameBookReturnsALREADY_EXISTS() {
        final String isbn = "isbn";
        final Book book = new Book("title", "author", isbn);
        mediaService.addBook(book);
        final MediaServiceResult result = mediaService.updateBook(isbn, book);

        assertEquals(result, MediaServiceResult.ALREADY_EXISTS);
    }

    @Test
    public void updatingDiscReturnsOK() {
        final String barcode = "barcode";
        final Disc disc = new Disc("title", barcode, "director", 0);
        mediaService.addDisc(disc);

        final Disc updatedDisc = new Disc("updated title", barcode, "updated director", 18);
        final MediaServiceResult result = mediaService.updateDisc(barcode, updatedDisc);

        assertEquals(result, MediaServiceResult.OK);
    }

    @Test
    public void updatingDiscWithMismatchinBarcodeReturnsINVALID_INFORMATION() {
        final String barcode = "barcode";
        final Disc disc = new Disc("title", barcode, "director", 0);
        mediaService.addDisc(disc);

        final Disc updatedDisc = new Disc("updated title", barcode, "updated director", 18);
        final MediaServiceResult result = mediaService.updateDisc("MISMATCHING BARCODE", updatedDisc);

        assertEquals(result, MediaServiceResult.INVALID_INFORMATION);
    }

    @Test
    public void updatingDiscThatDoesNotExistReturnsNOT_FOUND() {
        final String barcode = "barcode";
        final Disc disc = new Disc("title", barcode, "director", 0);

        final Disc updatedDisc = new Disc("updated title", barcode, "updated director", 18);
        final MediaServiceResult result = mediaService.updateDisc(barcode, updatedDisc);

        assertEquals(result, MediaServiceResult.NOT_FOUND);
    }

    @Test
    public void updatingDiscWithoutTitleReturnsMISSING_INFORMATION() {
        final String barcode = "barcode";
        final Disc disc = new Disc("title", barcode, "director", 0);
        mediaService.addDisc(disc);

        final Disc updatedDisc = new Disc("", barcode, "updated director", 18);
        final MediaServiceResult result = mediaService.updateDisc(barcode, updatedDisc);

        assertEquals(result, MediaServiceResult.MISSING_INFORMATION);
    }

    @Test
    public void updatingDiscWithoutDirectorReturnsMISSING_INFORMATION() {
        final String barcode = "barcode";
        final Disc disc = new Disc("title", barcode, "director", 0);
        mediaService.addDisc(disc);

        final Disc updatedDisc = new Disc("updated title", barcode, "", 18);
        final MediaServiceResult result = mediaService.updateDisc(barcode, updatedDisc);

        assertEquals(result, MediaServiceResult.MISSING_INFORMATION);
    }

    @Test
    public void updatingDiscWithSameDiscReturnsALREADY_EXISTS() {
        final String barcode = "barcode";
        final Disc disc = new Disc("title", barcode, "director", 0);
        mediaService.addDisc(disc);

        final MediaServiceResult result = mediaService.updateDisc(barcode, disc);

        assertEquals(result, MediaServiceResult.ALREADY_EXISTS);
    }

    @Test
    public void getBookByISBNreturnsBook() {
        final String isbn = "isbn";
        final Book book = new Book("title", "author", isbn);
        mediaService.addBook(book);

        final Book returnedBook = mediaService.getBookByISBN(isbn);

        assertEquals(book, returnedBook);
    }

    @Test
    public void getBookByISBNreturnsNullIfBookDoesNotExist() {
        final Book returnedBook = mediaService.getBookByISBN("isbn");

        assertNull(returnedBook);
    }

    @Test
    public void getDiscByBarcodeReturnsDisc() {
        final String barcode = "barcode";
        final Disc disc = new Disc("title", barcode, "director", 0);
        mediaService.addDisc(disc);

        final Disc returnedDisc = mediaService.getDiscByBarcode(barcode);

        assertEquals(disc, returnedDisc);
    }

    @Test
    public void getDiscByBarcodeReturnsNullIfDiscDoesNotExist() {
        final Disc returnedDisc = mediaService.getDiscByBarcode("barcode");

        assertNull(returnedDisc);
    }
}