package mortgage.shareholder;

import static org.junit.Assert.*;
import static mortgage.Helper.*;

import java.text.ParseException;
import java.util.Calendar;

import mortgage.shareholder.Lender;

import org.junit.Test;

public class LenderTest {
	
	@Test
	public void setTimestampSetTheCorrectBorrowingOnSameDay() {
		Calendar cal = Calendar.getInstance();
		Lender lender = new Lender(cal, "my bank", 1.00f, 10000, 100);
		
		Lender expected = new Lender(cal, "my bank", 1.00f, 10000, 100);
		Lender actual = lender.setTimestamp(cal);
		assertEquals(expected, actual);
	}
	
	@Test
	public void setTimestampSetTheCorrectBorrowingAfterOneDay() throws ParseException {
		Lender lender = new Lender(date("November 17, 2012 6:00:00pm"), "my bank", 1.00f, 10000, 100);
		
		Lender expected = new Lender(date("November 18, 2012 6:00:00pm"), "my bank", 1.00f, 10000.27, 100);
		Lender actual = lender.setTimestamp(date("November 18, 2012 6:00:00pm"));
		assertEquals(expected, actual);
	}
	
	@Test
	public void setTimestampSetTheCorrectBorrowingAfterOneYear() throws ParseException {
		Lender lender = new Lender(date("November 17, 2012 6:00:00pm"), "my bank", 1.00f, 10000, 100);
		
		Lender expected = new Lender(date("November 17, 2013 6:00:00pm"), "my bank", 1.00f, 10100.50, 100);
		Lender actual = lender.setTimestamp(date("November 17, 2013 6:00:00pm"));
		assertEquals(expected, actual);
	}
	
	@Test
	public void setInterestRateUpdateCorrectBorrowing() throws ParseException {
		Lender lender = new Lender(date("November 17, 2012 6:00:00pm"), "my bank", 1.00f, 10000, 100);
		
		Lender expected = new Lender(date("November 17, 2013 6:00:00pm"), "my bank", 2.00f, 10100.50, 100);
		Lender actual = lender.setInterestRate(date("November 17, 2013 6:00:00pm"), 2.00f);
		assertEquals(expected, actual);
	}
	
	@Test
	public void setInterestRateLeadToCorrectBorrowingAfterTwoYears() throws ParseException {
		Lender lender = new Lender(date("November 17, 2012 6:00:00pm"), "my bank", 1.00f, 10000, 100);
		
		Lender expected = new Lender(date("November 17, 2014 6:00:00pm"), "my bank", 2.00f, 10304.54, 100);
		Lender newInterestLender = lender.setInterestRate(date("November 17, 2013 6:00:00pm"), 2.00f);
		Lender actual = newInterestLender.setTimestamp(date("November 17, 2014 6:00:00pm"));
		assertEquals(expected, actual);
	}
	
	@Test
	public void payInReturnsRightNumberOfSharesAndBorrowingAfterZeroDays() throws ParseException {
		double borrowing = 10000;
		Lender lender = new Lender(date("November 17, 2012 6:00:00pm"), "my bank", 1.00f, borrowing, 100);
		
		Lender expected = new Lender(date("November 17, 2012 6:00:00pm"), "my bank", 1.00f, 5000, 50);
		Lender actual = lender.payIn(date("November 17, 2012 6:00:00pm"), 5000);
		assertEquals(expected, actual);
	}
	
	@Test
	public void payInReturnsRightNumberOfSharesAndBorrowingAfterOneYear() throws ParseException {
		Lender lender = new Lender(date("November 17, 2012 6:00:00pm"), "my bank", 1.00f, 10000, 100);
		
		Lender expected = new Lender(date("November 17, 2013 6:00:00pm"), "my bank", 1.00f, 5100.50, 50.50);
		Lender actual = lender.payIn(date("November 17, 2013 6:00:00pm"), 5000);
		assertEquals(expected, actual);
	}
	
	@Test
	public void mixedJourney() throws ParseException {
		Lender lender = new Lender(date("January 1, 2012 6:00:00pm"), "my bank", 20.00f, 100000, 100);
		
		Lender expected = new Lender(date("February 1, 2012 6:00:00pm"), "my bank", 20.00f, 100712.67, 99.02);
		lender = lender.payIn(date("February 1, 2012 6:00:00pm"), 1000);
		assertEquals(expected, lender);
		
		expected = new Lender(date("March 1, 2012 6:00:00pm"), "my bank", 20.00f, 102325.37, 99.02);
		lender = lender.setTimestamp(date("March 1, 2012 6:00:00pm"));
		assertEquals(expected, lender);
		
		expected = new Lender(date("April 1, 2012 6:00:00pm"), "my bank", 30.00f, 104077.86, 99.02);
		lender = lender.setInterestRate(date("April 1, 2012 6:00:00pm"), 30.00f);
		assertEquals(expected, lender);
		
		expected = new Lender(date("May 1, 2012 6:00:00pm"), "my bank", 30.00f, 105674.98, 98.09);
		lender = lender.payIn(date("May 1, 2012 6:00:00pm"), 1000);
		assertEquals(expected, lender);		
	}
	
	@Test
	public void toStringReturnsANicelyFormattedString() throws ParseException {
		Lender lender = new Lender(date("January 1, 2012 6:00:00pm"), "Yorkshire BS", 5.00f, 10000, 100);
		String expected = "timestamp: January 1, 2012, name: Yorkshire BS, annual interest rate: 5.00%, borrowing: 10000.00, share: 100.00";
		String actual = lender.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void toStringReturnsANicelyFormattedStringDifferentScenario() throws ParseException {
		Lender lender = new Lender(date("March 31, 2012 1:00:00am"), "Yorkshire BS", 5.4321f, 12345.6789, 12.345678);
		String expected = "timestamp: March 31, 2012, name: Yorkshire BS, annual interest rate: 5.43%, borrowing: 12345.68, share: 12.35";
		String actual = lender.toString();
		assertEquals(expected, actual);
	}

}
