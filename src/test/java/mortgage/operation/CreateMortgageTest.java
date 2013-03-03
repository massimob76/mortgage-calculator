package mortgage.operation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import mortgage.shareholder.Borrower;
import mortgage.shareholder.Lender;
import mortgage.shareholder.Shareholder;
import mortgage.shareholder.Shareholders;

public class CreateMortgageTest {
	
	private static final Shareholder borrowerMassimo = new Borrower("Massimo", 20.00);
	private static final Shareholder borrowerPaula = new Borrower("Paula", 10.00);
    private static final Shareholder lender = new Lender("Yorkshire BS", 3.0f, 10000.00, 70.00);
    
    private Shareholders shareholders;
    
    @Before
    public void setUp() {
    	shareholders = new Shareholders();
    }

	
	@Test
	public void initializesAMortgage() {
		Shareholders expected = new Shareholders();
		expected.add(borrowerMassimo);
		expected.add(borrowerPaula);
		expected.add(lender);
		
		new CreateMortgage(borrowerMassimo, borrowerPaula, lender).applyTo(shareholders);
		assertEquals(expected, this.shareholders);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void checksIfExistsAtLeastOneLender() {
		new CreateMortgage(borrowerMassimo).applyTo(shareholders);
		fail();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void checksThatTheShareholderContainerIsEmpty() {
		shareholders.add(borrowerMassimo);
		new CreateMortgage(lender).applyTo(shareholders);
		fail();
	}

}
