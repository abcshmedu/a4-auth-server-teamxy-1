package edu.hm.schatter.shareit.models;

/**
 * A data structure representing a book.
 */
public class Book extends Medium {
    private final String author;
    private final String isbn;

    /**
     * Private standard constructor needed for reflection.
     */
    private Book() {
        super("");
        author = "";
        isbn = "";
    }

    /**
     * Standard constructor.
     * @param title The title of the book.
     * @param author The author of the book.
     * @param isbn The isbn of the book.
     */
    public Book(String title, String author, String isbn) {
        super(title);

        if (author == null) {
            throw new IllegalArgumentException("author must not be null");
        }

        if (isbn == null) {
            throw new IllegalArgumentException("isbn must not be null");
        }

        this.author = author;
        this.isbn = isbn;
    }

    /**
     * Getter.
     * @return Author of the book.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Getter.
     * @return ISBN of the book.
     */
    public String getIsbn() {
        return isbn;
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

        Book book = (Book) o;

        if (!author.equals(book.author)) {
            return false;
        }
        return isbn.equals(book.isbn);

    }

    @Override
    public int hashCode() {
        int result = author.hashCode();
        result = 31 * result + isbn.hashCode();
        return result;
    }
}
