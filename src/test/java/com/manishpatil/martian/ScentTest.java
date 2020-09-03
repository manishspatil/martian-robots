package com.manishpatil.martian;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScentTest {

    @Test
    void testEquals_Reflexive() {
        Scent scent1 = new Scent(new Coordinates(1, 2), Direction.East);

        assertEquals(scent1, scent1, "Equal test is not Reflexive.");
    }

    @Test
    void testEquals_Symmetric() {
        Scent scent1 = new Scent(new Coordinates(1, 2), Direction.East);
        Scent scent2 = new Scent(new Coordinates(1, 2), Direction.East);

        assertTrue(scent1.equals(scent2) && scent2.equals(scent1), "Equal test is not Symmetric.");
    }

    @SuppressWarnings("DuplicateCondition")
    @Test
    void testEquals_Consistent() {
        Scent scent1 = new Scent(new Coordinates(1, 2), Direction.East);
        Scent scent2 = new Scent(new Coordinates(1, 2), Direction.East);

        assertTrue(scent1.equals(scent2) && scent1.equals(scent2) && scent1.equals(scent2)
                && scent1.equals(scent2), "Equal test is not Consistent.");
    }


    @Test
    void testEquals_Transitive() {
        Scent scent1 = new Scent(new Coordinates(1, 2), Direction.East);
        Scent scent2 = new Scent(new Coordinates(1, 2), Direction.East);
        Scent scent3 = new Scent(new Coordinates(1, 2), Direction.East);

        assertEquals(scent1.equals(scent2) && scent2.equals(scent3), scent1.equals(scent3), "Equal test is not Transitive.");
    }

    @Test
    void neverEqualsDifferentScents() {
        Scent scent1 = new Scent(new Coordinates(1, 2), Direction.East);
        Scent scent2 = new Scent(new Coordinates(20, 20), Direction.East);

        assertNotEquals(scent2, scent1, "Scent 1 must be never equals to Scent 2.");
    }

    @Test
    void neverEqualsNull() {
        Scent scent1 = new Scent(new Coordinates(1, 2), Direction.East);

        assertNotEquals(scent1, null, "Scent 1 must never equals to null.");
    }

    @Test
    void testEqualsObjectMustHaveSameHashCode() {
        Scent scent1 = new Scent(new Coordinates(1, 2), Direction.East);
        Scent scent2 = new Scent(new Coordinates(1, 2), Direction.East);

        assertEquals(scent1.equals(scent2), scent1.hashCode() == scent2.hashCode(), "HashCode of 2 equal Scent objects must be same.");
    }

    @Test
    void testHashCode_Reflexive() {
        Scent scent1 = new Scent(new Coordinates(1, 2), Direction.East);

        assertEquals(scent1.hashCode(), scent1.hashCode(), "HashCode test is not Reflexive.");
    }

    @Test
    void testHashCode_Symmetric() {
        Scent scent1 = new Scent(new Coordinates(1, 2), Direction.East);
        Scent scent2 = new Scent(new Coordinates(1, 2), Direction.East);

        assertEquals(scent2.hashCode(), scent1.hashCode(), "HashCode test is not Symmetric.");
    }

    @Test
    void testHashCode_Consistent() {
        Scent scent = new Scent(new Coordinates(1, 2), Direction.East);

        int hashCode1 = scent.hashCode();
        int hashCode2 = scent.hashCode();
        int hashCode3 = scent.hashCode();
        int hashCode4 = scent.hashCode();

        assertTrue((hashCode1 == hashCode2) && (hashCode2 == hashCode3) && (hashCode3 == hashCode4), "HashCode test is not Consistent.");
    }

    @Test
    void testHashCode_Transitive() {
        Scent scent1 = new Scent(new Coordinates(1, 2), Direction.East);
        Scent scent2 = new Scent(new Coordinates(1, 2), Direction.East);
        Scent scent3 = new Scent(new Coordinates(1, 2), Direction.East);

        assertEquals((scent1.hashCode() == scent2.hashCode()) && (scent2.hashCode() == scent3.hashCode()), scent1.hashCode() == scent3.hashCode(), "HashCode test is not Transitive.");
    }
}