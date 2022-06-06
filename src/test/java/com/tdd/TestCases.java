package com.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestCases 
{

    StrCalc strCalc;

    @BeforeEach
    public void init() 
    {
    	// Intialize the value of the object after each test call
    	strCalc = new StrCalc();
    }

    @AfterEach
    public void destroy() 
    {
    	// Remove the object from the memory
    	strCalc = null;
    }

    @Test
    @DisplayName("Test Case - Empty String")
    public void testEmptyString() 
    {
        assertEquals(0, strCalc.add(";    "));
        assertEquals(0, strCalc.add("     "));
        assertEquals(0, strCalc.add(""));
    }

    @Test
    @DisplayName("Test Case - Single Digit")
    public void testAddOneNumber() {
        assertEquals(1, strCalc.add("1"));
    }

    @Test
    @DisplayName("Test Case - Multiple input comma separeted")
    public void testAddMultipleNumbers() {
        assertEquals(193, strCalc.add("145,23,12,13"));
    }

    @Test
    @DisplayName("Test Case - Digits with new line special character")
    public void testNewLine() 
    {
        assertEquals(15, strCalc.add("1\n5,3\n6"));
    }

    @Test
    @DisplayName("Test Case - Negative Digits")
    public void testNegativeDigits() 
    {
        try 
        {
            strCalc.add("-2,2");
        } 
        catch (IllegalArgumentException e) 
        {
            assertEquals(e.getMessage(), "Negatives not allowed: -2");
        }
        
        try 
        {
            strCalc.add("1,-5,3,-5");
        }
        catch (IllegalArgumentException e) 
        {
            assertEquals(e.getMessage(), "Negatives not allowed: -5,-5");
        }
    }

    @Test
    @DisplayName("Test Case - Digit = 1000 , Digits < 1000 , Digits > 1000")
    public void testThousandValidations() 
    {
    	assertEquals(10, strCalc.add("1000,10"));
    	assertEquals(10, strCalc.add("1001,10"));
        assertEquals(1009, strCalc.add("999,10"));
    }

    @Test
    @DisplayName("Test Case - Different Delimiter")
    public void testDifferentDelimiter() 
    {
        assertEquals(3, strCalc.add("//;\n1;2"));
    }
    
    @Test
    @DisplayName("Test Case - Different Delimiter + Digits > 1000 , < 1000 , = 1000")
    public void testDelimiterAndDigitsOverThousand() 
    {
        assertEquals(3, strCalc.add("//;\n1;2\n1001"));
        assertEquals(3, strCalc.add("//;\n1;2\n1000"));
        assertEquals(1002, strCalc.add("//;\n1;2\n999"));
    }
    
    @Test
    @DisplayName("Test Case - Multiple delimiters and Different Types of Demilimiters")
    public void testMultipleDelimiters()
    {
    	assertEquals(6, strCalc.add("//[***]\\n1***2***3"));
    	assertEquals(6, strCalc.add("//[*][%]\\n1*2%3"));
    }
    
    @Test
    @DisplayName("Test Case - Demilimiters with longer length")
    public void testLOngDelimiters()
    {
    	assertEquals(6, strCalc.add("\\n1;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;2;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;3"));
    }

}
