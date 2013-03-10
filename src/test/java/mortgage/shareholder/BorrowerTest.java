package mortgage.shareholder;

import static org.junit.Assert.*;
import static mortgage.Helper.getJsonElement;

import mortgage.shareholder.Borrower;

import org.junit.Test;

public class BorrowerTest {
	
	@Test
	public void addShareAddsCorrectAmountOfShares() {
		Borrower actual = new Borrower("me", 10.00);
		Borrower expected = new Borrower("me", 30.00);
		actual.addShare(20.00);
		assertEquals(expected, actual);
	}
	
	@Test
	public void fromJsonCreatesTheRightObject() {
		Borrower expected = new Borrower("me", 30.00);
		Borrower actual = Borrower.fromJson(getJsonElement("{'name':'me','share':'30.00'}"));
		assertEquals(expected, actual);
	}
	
	@Test
	public void toStringReturnsANicelyFormattedString() {
		Borrower borrower = new Borrower("Massimo", 20.00);
		String expected = "name: Massimo, share: 20.00";
		String actual = borrower.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void toStringReturnsANicelyFormattedStringWithOnlyTwoDecimalDigitsForShare() {
		Borrower borrower = new Borrower("Massimo", 12.3456789);
		String expected = "name: Massimo, share: 12.35";
		String actual = borrower.toString();
		assertEquals(expected, actual);
	}

}
