package seng202.team8.controller;

import junit.framework.TestCase;

/**
 * Tests some methods of ValidateCrime
 * Mainly just to make sure the Regex was done correctly
 */
public class ValidateCrimeTest extends TestCase {

    /**
     * Tests the validation of a case number
     */
    public void testValidateCaseNum() {
        String candidate = "JE505667";
        assertTrue(ValidateCrime.validateCaseNum(candidate));
    }

    /**
     * Tests an invalid case number
     * checking if it is actually treated as invalid
     */
    public void testInvalidCaseNum() {
        String candidate = "1242B";
        //The case number is incorrectly formatted
        assertFalse(ValidateCrime.validateCaseNum(candidate));
    }

    /**
     * Tests the validation of an Iucr
     */
    public void testValidateIucr() {
        String candidate = "051B";
        assertTrue(ValidateCrime.validateIucr(candidate));
    }

    /**
     * Tests invalid IUCR's
     */
    public void testInvalidIucr() {
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
    public void testValidatePrimary() {
        String candidate = "ASSAULT";
        assertTrue(ValidateCrime.validatePrimary(candidate));
        //Also test spaces (somewhat trivial, but important to check if the regex is right)
        candidate = "OTHER OFFENSE";
        assertTrue(ValidateCrime.validatePrimary(candidate));
    }

    /**
     * Tests an invalid Primary Description
     */
    public void testInvalidPrimary() {
        String candidate = "2 COOL 4 SKOOL";
        assertFalse(ValidateCrime.validatePrimary(candidate));
    }

    /**
     * Tests the validation of an FBI CD
     */
    public void testValidateFbiCD() {
        String candidate = "90B"; //This is the code for loitering!
        assertTrue(ValidateCrime.validateFbiCD(candidate));
    }

    /**
     * Tests an invalid FBI code
     * Only tests incorrect formats
     */
    public void testInvalidFbiCd() {
        String candidate = "BAD"; //An invalid code
        assertFalse(ValidateCrime.validateFbiCD(candidate));
    }

    /**
     * Tests an FBI code
     * that is too long to be valid
     */
    public void testLongFbiCd() {
        String candidate = "972B"; //An invalid code - too long
        assertFalse(ValidateCrime.validateFbiCD(candidate));
    }

    /**
     * Tests an FBI code of length 2
     * One with a number end
     * and another with a letter end
     */
    public void testTwoLengthFbiCd() {
        String candidate = "06";
        assertTrue(ValidateCrime.validateFbiCD(candidate));
        candidate = "2C";
        assertTrue(ValidateCrime.validateFbiCD(candidate));
    }
}