package mortgage.shareholder;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ShareholdersTest {
	
	private Shareholders iut;
	
	@Before
	public void setUp() {
		iut = new Shareholders();
	}
	
	@Test
	public void shouldBeAbleToAddAShareHolder() {
		List<Shareholder> expected = new ArrayList<Shareholder>();
		expected.add(new Borrower("Massimo", 20.00));

		iut.add(new Borrower("Massimo", 20.00));
		List<Shareholder> actual = iut.getAll();
		assertEquals(expected, actual);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotBeAbleToAddAShareholderWithTheSameName() {
		iut.add(new Borrower("Massimo", 20.00));
		iut.add(new Borrower("Massimo", 30.00));
	}
	
	@Test
	public void shouldBeAbleToModifyAnExistentShareholder() {
		List<Shareholder> expected = new ArrayList<Shareholder>();
		expected.add(new Borrower("Massimo", 30.00));
		
		iut.add(new Borrower("Massimo", 20.00));
		iut.modify(new Borrower("Massimo", 30.00));
		List<Shareholder> actual = iut.getAll();
		assertEquals(expected, actual);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotBeAbleToModifyANonExistentShareholder() {
		iut.add(new Borrower("Massimo", 20.00));
		iut.modify(new Borrower("Paula", 30.00));
	}
	
	@Test
	public void shouldBeAbleToGetAnExistentBorrower() {
		Shareholder expected = new Borrower("Massimo", 20.00);
		iut.add(expected);
		Shareholder actual = iut.getBorrower("Massimo");
		assertEquals(expected, actual);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getBorrowerShouldNotReturnALender() {
		iut.add(new Lender("Massimo", 3.0f, 10000.00, 70.00));
		iut.getBorrower("Massimo");
	}
	
	@Test
	public void shouldBeAbleToGetAnExistentLender() {
		Shareholder expected = new Lender("Yorkshire BS", 3.0f, 10000.00, 70.00);
		iut.add(expected);
		Shareholder actual = iut.getLender("Yorkshire BS");
		assertEquals(expected, actual);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getLenderShouldNotReturnABorrower() {
		iut.add(new Borrower("Massimo", 20.00));
		iut.getLender("Massimo");
	}

}
