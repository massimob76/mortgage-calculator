package mortgage.partecipant;

import static org.junit.Assert.*;
import static mortgage.Helper.*;

import java.text.ParseException;
import java.util.Calendar;

import org.junit.Test;

public class LenderTest {
	
	@Test
	public void setTimestampSetTheCorrectBorrowingOnSameDay() {
		Calendar cal = Calendar.getInstance();
		double expectedBorrowing = 10000;
		Lender lender = new Lender(cal, "my bank", 1.00f, expectedBorrowing, 100);
		lender.setTimestamp(cal);
		double actualBorrowing = lender.getBorrowing();
		assertEquals(expectedBorrowing, actualBorrowing, 0);
	}
	
	@Test
	public void setTimestampSetTheCorrectBorrowingAfterOneDay() throws ParseException {
		double borrowing = 10000;
		double expectedBorrowing = 10000.27;
		Lender lender = new Lender(date("November 17, 2012 6:00:00pm"), "my bank", 1.00f, borrowing, 100);
		lender.setTimestamp(date("November 18, 2012 6:00:00pm"));
		double actualBorrowing = lender.getBorrowing();
		assertEquals(expectedBorrowing, actualBorrowing, 0.005);
	}
	
	@Test
	public void setTimestampSetTheCorrectBorrowingAfterOneYear() throws ParseException {
		double borrowing = 10000;
		double expectedBorrowing = 10100.50;
		Lender lender = new Lender(date("November 17, 2012 6:00:00pm"), "my bank", 1.00f, borrowing, 100);
		lender.setTimestamp(date("November 17, 2013 6:00:00pm"));
		double actualBorrowing = lender.getBorrowing();
		assertEquals(expectedBorrowing, actualBorrowing, 0.005);
	}
	
	@Test
	public void setInterestRateUpdateCorrectBorrowing() throws ParseException {
		double borrowing = 10000;
		Lender lender = new Lender(date("November 17, 2012 6:00:00pm"), "my bank", 1.00f, borrowing, 100);
		lender.setInterestRate(date("November 17, 2013 6:00:00pm"), 2.00f);
		double actualBorrowing = lender.getBorrowing();
		double expectedBorrowing = 10100.50;
		assertEquals(expectedBorrowing, actualBorrowing, 0.005);
	}
	
	@Test
	public void setInterestRateLeadToCorrectBorrowingAfterTwoYears() throws ParseException {
		double borrowing = 10000;
		Lender lender = new Lender(date("November 17, 2012 6:00:00pm"), "my bank", 1.00f, borrowing, 100);
		lender.setInterestRate(date("November 17, 2013 6:00:00pm"), 2.00f);
		lender.setTimestamp(date("November 17, 2014 6:00:00pm"));
		double actualBorrowing = lender.getBorrowing();
		double expectedBorrowing = 10304.54;
		assertEquals(expectedBorrowing, actualBorrowing, 0.005);
	}
	
	@Test
	public void payInReturnsRightNumberOfSharesAndBorrowingAfterZeroDays() throws ParseException {
		double borrowing = 10000;
		Lender lender = new Lender(date("November 17, 2012 6:00:00pm"), "my bank", 1.00f, borrowing, 100);
		double expectedShares = 50;
		double actualShares = lender.payIn(date("November 17, 2012 6:00:00pm"), 5000);
		assertEquals(expectedShares, actualShares, 0.01);
		double expectedBorrowing = 5000;
		double actualBorrowing = lender.getBorrowing();
		assertEquals(expectedBorrowing, actualBorrowing, 0.005);
	}
	
	@Test
	public void payInReturnsRightNumberOfSharesAndBorrowingAfterOneYear() throws ParseException {
		double borrowing = 10000;
		Lender lender = new Lender(date("November 17, 2012 6:00:00pm"), "my bank", 1.00f, borrowing, 100);
		double expectedShares = 49.50;
		double actualShares = lender.payIn(date("November 17, 2013 6:00:00pm"), 5000);
		assertEquals(expectedShares, actualShares, 0.01);
		double expectedBorrowing = 5100.50;
		double actualBorrowing = lender.getBorrowing();
		assertEquals(expectedBorrowing, actualBorrowing, 0.005);
	}
	
	@Test
	public void mixedJourney() throws ParseException {
		double shares;
		Lender lender = new Lender(date("January 1, 2012 6:00:00pm"), "my bank", 20.00f, 100000, 100);
		
		shares = lender.payIn(date("February 1, 2012 6:00:00pm"), 1000);
		assertEquals(100712.67, lender.getBorrowing(), 0.005);
		assertEquals(0.98, shares, 0.005);		
		
		lender.setTimestamp(date("March 1, 2012 6:00:00pm"));
		assertEquals(102325.37, lender.getBorrowing(), 0.005);
		
		lender.setInterestRate(date("April 1, 2012 6:00:00pm"), 30.00f);
		assertEquals(104077.86, lender.getBorrowing(), 0.005);
		
		shares = lender.payIn(date("May 1, 2012 6:00:00pm"), 1000);
		assertEquals(105674.98, lender.getBorrowing(), 0.005);
		assertEquals(0.93, shares, 0.005);		
		
	}

}
