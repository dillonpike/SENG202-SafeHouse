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
     * Tests the validation of an Iucr
     */
    public void testValidateIucr() {
        String candidate = "051B";
        assertTrue(ValidateCrime.validateIucr(candidate));
    }

    /**
     * Tests the validation of a Primary Description
     */
    public void testValidatePrimary() {
        String candidate = "ASSAULT";
        assertTrue(ValidateCrime.validatePrimary(candidate));
    }

    /**
     * Tests the validation of an FBI CD
     */
    public void testValidateFbiCD() {
        String candidate = "90B"; //This is the code for loitering!
        assertTrue(ValidateCrime.validateFbiCD(candidate));
    }
}