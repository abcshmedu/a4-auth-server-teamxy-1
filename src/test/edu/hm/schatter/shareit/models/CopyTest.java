package edu.hm.schatter.shareit.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class CopyTest {

    @Test(expected = IllegalArgumentException.class)
    public void constructorRejectsNullMedium() {
        final Copy sut = new Copy(null, "owner");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorRejectsNullOwner() {
        final Medium medium = new Book("title", "author", "isbn");
        final Copy sut = new Copy(medium, null);
    }

    @Test
    public void copyIsEqualToItself() {
        final Medium medium = new Book("title", "author", "isbn");
        final Copy sut = new Copy(medium, "owner");
        assertEquals(sut, sut);
    }

    @Test
    public void copyIsEqualToEqualCopy() {
        final Medium medium = new Book("title", "author", "isbn");
        final Copy sutA = new Copy(medium, "owner");
        final Copy sutB = new Copy(medium, "owner");
        assertEquals(sutA, sutB);
    }

    @Test
    public void copysWithDifferentMediumsAreUnequal() {
        final Medium mediumA = new Book("title", "author", "isbn");
        final Copy sutA = new Copy(mediumA, "owner");

        final Medium mediumB = new Disc("title", "barcode", "director", 0);
        final Copy sutB = new Copy(mediumB, "owner");
        assertNotEquals(sutA, sutB);
    }

    @Test
    public void copysWithDifferentOwnersAreUnequal() {
        final Medium medium = new Book("title", "author", "isbn");
        final Copy sutA = new Copy(medium, "owner");
        final Copy sutB = new Copy(medium, "UNEQUAL");
        assertNotEquals(sutA, sutB);
    }

    @Test
    public void copyIsUnequalToNull() {
        final Medium medium = new Book("title", "author", "isbn");
        final Copy sut = new Copy(medium, "owner");
        assertNotEquals(sut, null);
    }

    @Test
    public void copyIsUnequalToObject() {
        final Medium medium = new Book("title", "author", "isbn");
        final Copy sut = new Copy(medium, "owner");
        assertNotEquals(sut, new Object());
    }
}