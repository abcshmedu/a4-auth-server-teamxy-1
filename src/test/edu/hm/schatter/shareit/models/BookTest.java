package edu.hm.schatter.shareit.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {

    @Test(expected = IllegalArgumentException.class)
    public void constructorRejectsNullTitle() {
        final Book sut = new Book(null, "author", "isbn");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorRejectsNullAuthor() {
        final Book sut = new Book("title", null, "isbn");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorRejectsNullISBN() {
        final Book sut = new Book("title", "author", null);
    }

    @Test
    public void bookIsEqualToItself() {
        final Book sut = new Book("title", "author", "isbn");
        assertEquals(sut, sut);
    }

    @Test
    public void bookIsEqualToEqualBook() {
        final Book sutA = new Book("title", "author", "isbn");
        final Book sutB = new Book("title", "author", "isbn");

        assertEquals(sutA, sutB);
    }

    @Test
    public void booksWithDifferentTitelsAreUnequal() {
        final Book sutA = new Book("UNEQUAL", "author", "isbn");
        final Book sutB = new Book("title", "author", "isbn");

        assertNotEquals(sutA, sutB);
    }

    @Test
    public void booksWithDifferentAuthorsAreUnequal() {
        final Book sutA = new Book("title", "UNEQUAL", "isbn");
        final Book sutB = new Book("title", "author", "isbn");

        assertNotEquals(sutA, sutB);
    }

    @Test
    public void booksWithDifferentISBNSAreUnequal() {
        final Book sutA = new Book("title", "author", "UNEQUAL");
        final Book sutB = new Book("title", "author", "isbn");

        assertNotEquals(sutA, sutB);
    }

    @Test
    public void bookIsUnequalToNull() {
        final Book sut = new Book("title", "author", "isbn");
        assertNotEquals(sut, null);
    }

    @Test
    public void bookIsUnequalToObject() {
        final Book sut = new Book("title", "author", "isbn");
        assertNotEquals(sut, new Object());
    }
}