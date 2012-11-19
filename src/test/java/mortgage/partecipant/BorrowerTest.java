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

}
