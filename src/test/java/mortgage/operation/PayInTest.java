package mortgage.operation;

import static org.junit.Assert.*;

import mortgage.shareholder.Borrower;
import mortgage.shareholder.Lender;
import mortgage.shareholder.Shareholders;

import org.junit.Before;
import org.junit.Test;

public class PayInTest {
	
	private Shareholders shareholders;
	
	@Before
	public void setUp() {
		buildShareholders();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionIfBorrowerDoesNotExists() {
		new PayIn(0, "not existent borrower", "Yorkshire BS").applyTo(shareholders);
		fail();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionIfLenderDoesNotExists() {
		new PayIn(0, "Massimo", "not existent lender").applyTo(shareholders);
		fail();
	}
	
	@Test
	public void shouldUpdateLenderAndShareholderCorrectly() {
		Shareholders expected = new Shareholders();
		expected.add(new Borrower("Massimo", 30.00));
		expected.add(new Borrower("Paula", 10.00));
		expected.add(new Lender("Yorkshire BS", 3.0f, 6000.00, 60.00));
		
		new PayIn(1000, "Massimo", "Yorkshire BS").applyTo(shareholders);
		assertEquals(expected, shareholders);
	}
	
	private void buildShareholders() {
		shareholders = new Shareholders();
		shareholders.add(new Borrower("Massimo", 20.00));
		shareholders.add(new Borrower("Paula", 10.00));
	    shareholders.add(new Lender("Yorkshire BS", 3.0f, 7000.00, 70.00));
	}

}
