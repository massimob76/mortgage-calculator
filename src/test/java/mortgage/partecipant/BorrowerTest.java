package mortgage.partecipant;

import static org.junit.Assert.*;

import org.junit.Test;

public class BorrowerTest {
	
	@Test
	public void addShareAddsCorrectAmountOfShares() {
		Borrower borrower = new Borrower("me", 10.00);
		borrower.addShare(20.00);
		assertEquals(30.00, borrower.getShare(), 0);
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
