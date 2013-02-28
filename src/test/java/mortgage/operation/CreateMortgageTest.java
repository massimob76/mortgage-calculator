package mortgage.operation;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import mortgage.shareholder.Borrower;
import mortgage.shareholder.Lender;
import mortgage.shareholder.Shareholder;

public class CreateMortgageTest {
	
	private Shareholder borrowerMassimo = new Borrower("Massimo", 20.00);
	private Shareholder borrowerPaula = new Borrower("Paula", 10.00);
    private	Shareholder lender = new Lender("Yorkshire BS", 3.0f, 10000.00, 70.00);
    private Shareholder borrowerJohn = new Borrower("John", 70.00);

	
	@Test
	public void initializesAMortgage() {
		List<Shareholder> shareholders = new ArrayList<Shareholder>();
		shareholders.add(borrowerMassimo);
		shareholders.add(borrowerPaula);
		shareholders.add(lender);
		
		List<Shareholder> actual = new CreateMortgage(shareholders).applyTo(new ArrayList<Shareholder>());
		assertEquals(shareholders, actual);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void checksIfExistsAtLeastOneLender() {
		List<Shareholder> shareholders = new ArrayList<Shareholder>();
		shareholders.add(borrowerMassimo);
		shareholders.add(borrowerPaula);
		shareholders.add(borrowerJohn);
		
		new CreateMortgage(shareholders).applyTo(new ArrayList<Shareholder>());
		fail();
	}

}
