package seng202.team8.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests some methods of ValidateCrime
 * Mainly just to make sure the Regex was done correctly
 */
class ValidateCrimeTest {

    /**
     * Tests the validation of a case number
     */
    @Test
    void testValidateCaseNum() {
        String candidate = "JE505667";
        assertTrue(ValidateCrime.validateCaseNum(candidate));
    }

    /**
     * Tests an invalid case number
     * checking if it is actually treated as invalid
     */
    @Test
    void testInvalidCaseNum() {
        String candidate = "1242B";
        //The case number is incorrectly formatted
        assertFalse(ValidateCrime.validateCaseNum(candidate));
    }

    /**
     * Tests the validation of an Iucr
     */
    @Test
    void testValidateIucr() {
        String candidate = "051B";
        assertTrue(ValidateCrime.validateIucr(candidate));
    }

    /**
     * Tests invalid IUCR's
     */
    @Test
    void testInvalidIucr() {
        String candidate = "65413";
        //This code is invalid because it is too long.
        assertFalse(ValidateCrime.validateIucr(candidate));
        candidate = "123";
        //This code is invalid because it is too short.
        assertFalse(ValidateCrime.validateIucr(candidate));
    }

    /**
     * Tests the validation of a Primary Description
     */
    @Test
    void testValidatePrimary() {
        String candidate = "ASSAULT";
        assertTrue(ValidateCrime.validatePrimary(candidate));
        //Also test spaces (somewhat trivial, but important to check if the regex is right)
        candidate = "OTHER OFFENSE";
        assertTrue(ValidateCrime.validatePrimary(candidate));
    }

    /**
     * Tests an invalid Primary Description
     */
    @Test
    void testInvalidPrimary() {
        String candidate = "2 COOL 4 SKOOL";
        assertFalse(ValidateCrime.validatePrimary(candidate));
    }

    /**
     * Tests the validation of an FBI CD
     */
    @Test
    void testValidateFbiCD() {
        String candidate = "90B"; //This is the code for loitering!
        assertTrue(ValidateCrime.validateFbiCD(candidate));
    }

    /**
     * Tests an invalid FBI code
     * Only tests incorrect formats
     */
    @Test
    void testInvalidFbiCd() {
        String candidate = "BAD"; //An invalid code
        assertFalse(ValidateCrime.validateFbiCD(candidate));
    }

    /**
     * Tests an FBI code
     * that is too long to be valid
     */
    @Test
    void testLongFbiCd() {
        String candidate = "972B"; //An invalid code - too long
        assertFalse(ValidateCrime.validateFbiCD(candidate));
    }

    /**
     * Tests an FBI code of length 2
     * One with a number end
     * and another with a letter end
     */
    @Test
    void testTwoLengthFbiCd() {
        String candidate = "06";
        assertTrue(ValidateCrime.validateFbiCD(candidate));
        candidate = "2C";
        assertTrue(ValidateCrime.validateFbiCD(candidate));
    }

    /**
     * Tests empty candidates for validateFbiCD
     */
    @Test
    void testEmptyFbiCD() {
        String candidate = "";
        assertFalse(ValidateCrime.validateFbiCD(candidate));

    }

    /**
     * Tests empty candidates for validatePrimary
     */
    @Test
    void testEmptyPrimary() {
        String candidate = "";
        assertFalse(ValidateCrime.validatePrimary(candidate));
    }
}