package mortgage.helper;

import static org.junit.Assert.*;
import static mortgage.Helper.*;
import static mortgage.helper.Calculator.*;

import java.text.ParseException;


import org.junit.Test;

public class CalculatorTest {
	
	@Test
	public void calculateDifferenceInDaysForSameDate() throws ParseException {
		int expected = 0;
		int actual = calculateDifferenceInDays(date("November 17, 2012 4:00:00pm"), date("November 17, 2012 4:00:00pm"));
		assertEquals(expected, actual);
	}
	
	@Test
	public void calculateDifferenceInDaysForSameDay() throws ParseException {
		int expected = 0;
		int actual = calculateDifferenceInDays(date("November 17, 2012 6:00:00pm"), date("November 17, 2012 2:00:00am"));
		assertEquals(expected, actual);
	}
	
	@Test
	public void calculateDifferenceInDaysForCloseDays() throws ParseException {
		int expected = 1;
		int actual = calculateDifferenceInDays(date("November 18, 2012 10:00:00pm"), date("November 17, 2012 2:00:00am"));
		assertEquals(expected, actual);
	}
	
	@Test
	public void calculateDifferenceInDaysForFarDays() throws ParseException {
		int expected = 500;
		int actual = calculateDifferenceInDays(date("April 2, 2014 10:00:00pm"), date("November 18, 2012 2:00:00am"));
		assertEquals(expected, actual);
	}
	
}
