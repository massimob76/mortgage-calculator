package mortgage.shareholder;

import static org.junit.Assert.*;

import java.text.ParseException;

import mortgage.shareholder.Lender;

import org.junit.Test;

public class LenderTest {
	
	@Test(expected = AssertionError.class)
	public void moveTimelineThrowsAnExceptionIfDaysShiftLessThanZero() {
		Lender lender = new Lender("my bank", 1.00f, 10000, 100);
		lender.moveTimeline(-1);
	}
	
	@Test
	public void moveTimelineSetTheCorrectBorrowingAfterZeroDays() {
		Lender lender = new Lender("my bank", 1.00f, 10000, 100);
		
		Lender expected = new Lender("my bank", 1.00f, 10000, 100);
		Lender actual = lender.moveTimeline(0);
		assertEquals(expected, actual);
	}
	
	@Test
	public void moveTimelineSetTheCorrectBorrowingAfterOneDay() throws ParseException {
		Lender lender = new Lender("my bank", 1.00f, 10000, 100);
		
		Lender expected = new Lender("my bank", 1.00f, 10000.27, 100);
		Lender actual = lender.moveTimeline(1);
		assertEquals(expected, actual);
	}
	
	@Test
	public void moveTimelineSetTheCorrectBorrowingAfter365Days() throws ParseException {
		Lender lender = new Lender("my bank", 1.00f, 10000, 100);
		
		Lender expected = new Lender("my bank", 1.00f, 10100.50, 100);
		Lender actual = lender.moveTimeline(365);
		assertEquals(expected, actual);
	}
	
	@Test
	public void setInterestRateUpdateCorrectBorrowing() throws ParseException {
		Lender lender = new Lender("my bank", 1.00f, 10000, 100);
		
		Lender expected = new Lender("my bank", 2.00f, 10000, 100);
		lender.setInterestRate(2.00f);
		assertEquals(expected, lender);
	}
	
	@Test
	public void payInReturnsRightNumberOfSharesAndBorrowing() throws ParseException {
		Lender lender = new Lender("my bank", 1.00f, 10000, 100);
		
		Lender expected = new Lender("my bank", 1.00f, 5000, 50);
		lender.payIn(5000);
		assertEquals(expected, lender);
	}
	
	@Test
	public void mixedJourney() throws ParseException {
		Lender lender = new Lender("my bank", 20.00f, 100000, 100);
		
		Lender expected = new Lender("my bank", 20.00f, 100712.67, 99.02);
		lender = lender.moveTimeline(31);
		lender.payIn(1000);
		assertEquals(expected, lender);
		
		expected = new Lender("my bank", 20.00f, 102325.37, 99.02);
		lender = lender.moveTimeline(29);
		assertEquals(expected, lender);
		
		expected = new Lender("my bank", 30.00f, 104077.86, 99.02);
		lender = lender.moveTimeline(31);
		lender.setInterestRate(30.00f);
		assertEquals(expected, lender);
		
		expected = new Lender("my bank", 30.00f, 105674.98, 98.09);
		lender = lender.moveTimeline(30);
		lender.payIn(1000);
		assertEquals(expected, lender);		
	}
	
	@Test
	public void toStringReturnsANicelyFormattedString() throws ParseException {
		Lender lender = new Lender("Yorkshire BS", 5.00f, 10000, 100);
		String expected = "name: Yorkshire BS, annual interest rate: 5.00%, borrowing: 10000.00, share: 100.00";
		String actual = lender.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void toStringReturnsANicelyFormattedStringDifferentScenario() throws ParseException {
		Lender lender = new Lender("Yorkshire BS", 5.4321f, 12345.6789, 12.345678);
		String expected = "name: Yorkshire BS, annual interest rate: 5.43%, borrowing: 12345.68, share: 12.35";
		String actual = lender.toString();
		assertEquals(expected, actual);
	}

}
