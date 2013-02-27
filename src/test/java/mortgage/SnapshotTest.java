package mortgage;

import static org.junit.Assert.*;
import static mortgage.Helper.date;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


import mortgage.Snapshot;
import mortgage.shareholder.Borrower;
import mortgage.shareholder.Lender;
import mortgage.shareholder.Shareholder;

import org.junit.Before;
import org.junit.Test;

public class SnapshotTest {
	
	private Snapshot snapshot;
	private Shareholder borrowerMassimo = new Borrower("Massimo", 20.00);
	private Shareholder borrowerPaula = new Borrower("Paula", 10.00);
    private	Shareholder lender = new Lender("Yorkshire BS", 3.0f, 10000.00, 70.00);

    
    @Before
    public void setUp() throws ParseException {
    	List<Shareholder> shareholders = new ArrayList<Shareholder>();
    	shareholders.add(lender);
    	shareholders.add(borrowerMassimo);
    	shareholders.add(borrowerPaula);
    	
    	snapshot = new Snapshot(date("February 20, 2013"), shareholders);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void aSnapshotCannotContainTwoShareholdersWithTheSameName() throws ParseException {
    	List<Shareholder> shareholders = new ArrayList<Shareholder>();
    	shareholders.add(lender);
    	shareholders.add(borrowerPaula);
    	shareholders.add(borrowerPaula);
    	shareholders.add(borrowerPaula);
    	
    	new Snapshot(date("February 20, 2013"), shareholders);
    }
	
    
    @Test
    public void getShareholderByName() {
    	Shareholder actual = snapshot.getShareholderByName("Massimo");
    	assertEquals(borrowerMassimo, actual);
    }
    
    
    @Test(expected=IllegalArgumentException.class)
    public void getShareholderByNameThrowsAnExceptionWhenShareholderDoesNotExist() {
    	snapshot.getShareholderByName("Not existent shareholder");
    }

    @Test
    public void toStringReturnsANicelyFormattedRepresentationOfTheCurrentBorrowings() {
    	String expected = 
    			"timestamp: February 20, 2013\n" +
    	        "name: Yorkshire BS, annual interest rate: 3.00%, borrowing: 10000.00, share: 70.00\n" +
                "name: Massimo, share: 20.00\n" +
    			"name: Paula, share: 10.00\n";
    	String actual = snapshot.toString();
    	assertEquals(expected, actual);
    }

}
