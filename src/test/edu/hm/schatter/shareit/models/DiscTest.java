package edu.hm.schatter.shareit.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class DiscTest {
    @Test(expected = IllegalArgumentException.class)
    public void constructorRejectsNullTitle() {
        final Disc sut = new Disc(null, "barcode", "director", 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorRejectsNullBarcode() {
        final Disc sut = new Disc("title", null, "director", 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorRejectsNullDirector() {
        final Disc sut = new Disc("title", "barcode", null, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorRejectsTooSmallFSK() {
        final Disc sut = new Disc("title", "barcode", "director", -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorRejectsTooBigFSK() {
        final Disc sut = new Disc("title", "barcode", "director", 19);
    }

    @Test
    public void discIsEqualToItself() {
        final Disc sut = new Disc("title", "barcode", "director", 0);
        assertEquals(sut, sut);
    }

    @Test
    public void discIsEqualToEqualDisc() {
        final Disc sutA = new Disc("title", "barcode", "director", 0);
        final Disc sutB = new Disc("title", "barcode", "director", 0);

        assertEquals(sutA, sutB);
    }

    @Test
    public void discsWithDifferentTitlesAreUnequal() {
        final Disc sutA = new Disc("UNEQUAL", "barcode", "director", 0);
        final Disc sutB = new Disc("title", "barcode", "director", 0);

        assertNotEquals(sutA, sutB);
    }

    @Test
    public void discsWithDifferentBarcodesAreUnequal() {
        final Disc sutA = new Disc("title", "UNEQUAL", "director", 0);
        final Disc sutB = new Disc("title", "barcode", "director", 0);

        assertNotEquals(sutA, sutB);
    }

    @Test
    public void discsWithDifferentDirectorsAreUnequal() {
        final Disc sutA = new Disc("title", "barcode", "UNEQUAL", 0);
        final Disc sutB = new Disc("title", "barcode", "director", 0);

        assertNotEquals(sutA, sutB);
    }

    @Test
    public void discsWithDifferentFSKSAreUnequal() {
        final Disc sutA = new Disc("title", "barcode", "director", 0);
        final Disc sutB = new Disc("title", "barcode", "director", 12);

        assertNotEquals(sutA, sutB);
    }

    @Test
    public void discIsUnequalToNull() {
        final Disc sut = new Disc("title", "barcode", "director", 0);
        assertNotEquals(sut, null);
    }

    @Test
    public void discIsUnequalToObject() {
        final Disc sut = new Disc("title", "barcode", "director", 0);
        assertNotEquals(sut, new Object());
    }
}