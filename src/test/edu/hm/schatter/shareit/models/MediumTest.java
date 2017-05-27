package edu.hm.schatter.shareit.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class MediumTest {

    private class MediumTester extends Medium {
        MediumTester(String title) {
            super(title);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorRejectsNullTitle() {
        final Medium sut = new MediumTester(null);
    }

    @Test
    public void mediumIsEqualToItself() {
        final Medium sut = new MediumTester("title");
        assertEquals(sut, sut);
    }

    @Test
    public void mediumIsEqualToEqualMedium() {
        final Medium sutA = new MediumTester("title");
        final Medium sutB = new MediumTester("title");
        assertEquals(sutA, sutB);
    }

    @Test
    public void mediumsWithDifferentTitelsAreUnequal() {
        final Medium sutA = new MediumTester("UNEQUAL");
        final Medium sutB = new MediumTester("title");

        assertNotEquals(sutA, sutB);
    }

    @Test
    public void mediumIsUnequalToNull() {
        final Medium sut = new MediumTester("title");
        assertNotEquals(sut, null);
    }

    @Test
    public void mediumIsUnequalToObject() {
        final Medium sut = new MediumTester("title");
        assertNotEquals(sut, new Object());
    }
}