package com.tdd;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StrCalc 
{
	// Method signature as per the instructed
	public int add(String numbers) 
	{
		if (numbers.length() < 2) 
		{
			if (numbers.isEmpty()) 
			{
				return 0;
			} 
			else 
			{
				return convertToInt(numbers);
			}
		}
		else
		{		
			// Using list for functional programming approach
			List<String> numbersLIST = Arrays.asList(replaceSpecialChar(numbers));
			return calc(numbersLIST);
		}
	}

	private int calc(List<String> numbersLIST) 
	{
		
		numbersLIST = numbersLIST.stream()
					// consider only numeric values for processing
					// Remove the Unwanted elements from the list also remove numbers => 1000
				.filter(string -> (!string.isBlank() || !string.isEmpty() ) && (convertToInt(string) < 1000) )
				.collect( Collectors.toList() );
		
		int sum = 0;
		StringBuilder negativeString = new StringBuilder();

		// Calculate the Sum of the numbers 
		for (String number : numbersLIST)
		{
			// Negative numbers Exception handling done here 
			if (convertToInt(number) < 0) 
			{
				if (negativeString.toString().equals(""))
				{
					negativeString = new StringBuilder(number);
				}
				else
				{
					negativeString.append(",").append(number);
				}
			}
			else
			{
				sum += convertToInt(number);
			}
		}

		// Negative numbers Exception throw here 
		if (!negativeString.toString().equals("")) 
		{
			throw new IllegalArgumentException("Negatives not allowed: " + negativeString);
		}
		return sum;
	}
	
	// Convert string to number
	private int convertToInt(String num) 
	{
		return Integer.parseInt(num);
	}
	
	// To remove special characters from a given string and return array of string
	private String[] replaceSpecialChar(String s)
	{
		String delimiter = ",";
		s = s.replaceAll("[^0-9]", ",");
		String[] numList = splitNumbers(s, delimiter);
		return numList;
	}
	
	// To split the numbers string based on the divider
	private String[] splitNumbers(String numbers, String divider) 
	{
		return numbers.split(divider);
	}
	
}
