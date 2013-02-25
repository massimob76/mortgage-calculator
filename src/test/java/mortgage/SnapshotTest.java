package mortgage;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Calendar;
import java.util.LinkedList;

import junitx.util.PrivateAccessor;

import mortgage.Snapshot;
import mortgage.Snapshot.Operation;
import mortgage.shareholder.Borrower;
import mortgage.shareholder.Lender;
import mortgage.shareholder.Shareholder;

import org.junit.Before;
import org.junit.Test;

public class SnapshotTest {
	
	private Snapshot snapshot;
	private Shareholder borrowerMassimo = new Borrower("Massimo", 20.00);
	private Shareholder borrowerMassimoUpdate = new Borrower("Massimo", 30.00);
	private Shareholder borrowerPaula = new Borrower("Paula", 10.00);
    private	Shareholder lender = new Lender("Yorkshire BS", 3.0f, 10000.00, 70.00);
    private	Shareholder lenderUpdate = new Lender("Yorkshire BS", 3.0f, 10000.00, 60.00);

    
    @Before
    public void setUp() throws ParseException {
    	snapshot = new Snapshot();
    }
	
    
    @SuppressWarnings("unchecked")
    @Test
    public void addShareholder() throws NoSuchFieldException {
    	Operation operation = new Snapshot.Operation();
    	operation.add(borrowerMassimo);
    	LinkedList<Shareholder> toBeAdded = (LinkedList<Shareholder>)PrivateAccessor.getField(operation, "toBeAdded");
    	assertEquals(borrowerMassimo, toBeAdded.pop());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void updateShareholder() throws NoSuchFieldException {
    	Operation operation = new Snapshot.Operation();
    	operation.update(borrowerMassimo);
    	LinkedList<Shareholder> toBeUpdated = (LinkedList<Shareholder>)PrivateAccessor.getField(operation, "toBeUpdated");
    	assertEquals(borrowerMassimo, toBeUpdated.pop());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void removeShareholder() throws NoSuchFieldException {
    	Operation operation = new Snapshot.Operation();
    	operation.remove(borrowerMassimo);
    	LinkedList<Shareholder> toBeRemoved = (LinkedList<Shareholder>)PrivateAccessor.getField(operation, "toBeRemoved");
    	assertEquals(borrowerMassimo, toBeRemoved.pop());
    }

    
    @Test
    public void getShareholderByName() {
    	commitSetUpOperation();
    	Shareholder actual = snapshot.getShareholderByName("Massimo");
    	assertEquals(borrowerMassimo, actual);
    }
    
    
    @Test(expected=IllegalArgumentException.class)
    public void getShareholderByNameThrowsAnExceptionWhenShareholderDoesNotExist() {
    	commitSetUpOperation();
    	snapshot.getShareholderByName("Not existent shareholder");
    }
    
    @Test
    public void commitAddsTheListOfShareholders() {
    	commitSetUpOperation();
    	assertEquals(borrowerMassimo, snapshot.getShareholderByName("Massimo"));
    	assertEquals(borrowerPaula, snapshot.getShareholderByName("Paula"));
    	assertEquals(lender, snapshot.getShareholderByName("Yorkshire BS"));
    }
    
    @Test
    public void commitUpdatesTheListOfShareholders() {
    	commitSetUpOperation();
    	commitUpdateOperation();
    	assertEquals(borrowerMassimoUpdate, snapshot.getShareholderByName("Massimo"));
    	assertEquals(borrowerPaula, snapshot.getShareholderByName("Paula"));
    	assertEquals(lenderUpdate, snapshot.getShareholderByName("Yorkshire BS"));
    }
    
    @Test
    public void commitUpdateAndDeletesTheListOfShareholders() {
    	commitSetUpOperation();
    	commitUpdateAndDeleteOperation();
    	assertEquals(borrowerMassimoUpdate, snapshot.getShareholderByName("Massimo"));
    	assertEquals(lender, snapshot.getShareholderByName("Yorkshire BS"));
    }
    
    @Test
    public void commitThrowsAnExceptionIfTheTotalAmountOfShareIsNot100AndDoesntUpdateTheListOfShareholders() {
    	commitSetUpOperation();
    	try {
    		commitWithOddSharesOperation();
    		fail();
    		
    	} catch(AssertionError e) {
    		assertEquals(borrowerMassimo, snapshot.getShareholderByName("Massimo"));
    		assertEquals(borrowerPaula, snapshot.getShareholderByName("Paula"));
    		assertEquals(lender, snapshot.getShareholderByName("Yorkshire BS"));
    	}
    }
    
    //@Test
    public void toStringReturnsANicelyFormattedRepresentationOfTheCurrentBorrowings() {
    	commitSetUpOperation();
    	String expected = 
    			"timestamp: February 20, 2013, name: Yorkshire BS, annual interest rate: 3.00%, borrowing: 10000.00, share: 70.00\n" +
                "name: Massimo, share: 20.00\n" +
    			"name: Paula, share: 10.00\n";
    	String actual = snapshot.toString();
    	assertEquals(expected, actual);
    }


	private void commitWithOddSharesOperation() {
		Operation op = new Operation();
		op.update(borrowerMassimoUpdate);
    	snapshot.commit(op);
	}


	private void commitSetUpOperation() {
		Operation op = new Operation();
    	op.add(borrowerMassimo);
    	op.add(borrowerPaula);
    	op.add(lender);
    	snapshot.commit(op);
	}

	private void commitUpdateOperation() {
		Operation op = new Operation();
		op.update(borrowerMassimoUpdate);
		op.update(lenderUpdate);
		snapshot.commit(op);
	}

	private void commitUpdateAndDeleteOperation() {
		Operation op = new Operation();
		op.update(borrowerMassimoUpdate);
		op.remove(borrowerPaula);
		snapshot.commit(op);
	}
}
