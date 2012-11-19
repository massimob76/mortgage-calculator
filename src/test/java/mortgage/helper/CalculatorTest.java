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
	
	@Test
	public void calculateResidualBorrowingForSameDay() {
		double expected = 10000;
		double actual = calculateResidualBorrowing(10000, 5.00f, 0);
		assertEquals(expected, actual, 0);
	}
	
	@Test
	public void calculateResidualBorrowingAfterOneYear() {
		double expected = 10512.67;
		double actual = calculateResidualBorrowing(10000, 5.00f, 365);
		assertEquals(expected, actual, 0.05);
	}
}
