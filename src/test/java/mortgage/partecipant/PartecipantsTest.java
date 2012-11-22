package mortgage.partecipant;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class PartecipantsTest {
	
	private Partecipants iut;
	private Borrower borrowerMassimo = new Borrower("Massimo", 20.00);
	private Borrower borrowerPaula = new Borrower("Paula", 10.00);
    private	Lender lender = new Lender(Calendar.getInstance(), "Yorkshire BS", 3.0f, 10000.00, 70.00);
	
	@Before
	public void setUp() throws ParseException {
		iut = new Partecipants();
		iut.addBorrower(borrowerMassimo);
		iut.addBorrower(borrowerPaula);
		iut.addLender(lender);
	}
	
	@Test
	public void getBorrowerByNameReturnsTheRightBorrower() {
		Borrower actual = iut.getBorrowerByName("Massimo");
		assertEquals(borrowerMassimo, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getBorrowerByNameThrowsAnExceptionWhenBorrowerDoesNotExist() {
		iut.getBorrowerByName("Not existent borrower");
	}
	
	@Test
	public void getLenderByNameReturnsTheRightLender() {
		Lender actual = iut.getLenderByName("Yorkshire BS");
		assertEquals(lender, actual);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getLenderByNameThrowsAnExceptionWhenLenderDoesNotExist() {
		iut.getLenderByName("Not existent lender");
	}

}
