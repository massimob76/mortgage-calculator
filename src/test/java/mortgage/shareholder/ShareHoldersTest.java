package mortgage.shareholder;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Calendar;
import java.util.LinkedList;

import junitx.util.PrivateAccessor;

import mortgage.shareholder.Borrower;
import mortgage.shareholder.Lender;
import mortgage.shareholder.Shareholder;
import mortgage.shareholder.Shareholders;
import mortgage.shareholder.Shareholders.Operation;

import org.junit.Before;
import org.junit.Test;

public class ShareHoldersTest {
	
	private Shareholders shareholders;
	private Shareholder borrowerMassimo = new Borrower("Massimo", 20.00);
	private Shareholder borrowerMassimoUpdate = new Borrower("Massimo", 30.00);
	private Shareholder borrowerPaula = new Borrower("Paula", 10.00);
    private	Shareholder lender = new Lender(Calendar.getInstance(), "Yorkshire BS", 3.0f, 10000.00, 70.00);
    private	Shareholder lenderUpdate = new Lender(Calendar.getInstance(), "Yorkshire BS", 3.0f, 10000.00, 60.00);

    
    @Before
    public void setUp() throws ParseException {
    	shareholders = new Shareholders();
    }
	
    
    @SuppressWarnings("unchecked")
    @Test
    public void addShareholder() throws NoSuchFieldException {
    	Operation operation = new Shareholders.Operation();
    	operation.add(borrowerMassimo);
    	LinkedList<Shareholder> toBeAdded = (LinkedList<Shareholder>)PrivateAccessor.getField(operation, "toBeAdded");
    	assertEquals(borrowerMassimo, toBeAdded.pop());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void updateShareholder() throws NoSuchFieldException {
    	Operation operation = new Shareholders.Operation();
    	operation.update(borrowerMassimo);
    	LinkedList<Shareholder> toBeUpdated = (LinkedList<Shareholder>)PrivateAccessor.getField(operation, "toBeUpdated");
    	assertEquals(borrowerMassimo, toBeUpdated.pop());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void removeShareholder() throws NoSuchFieldException {
    	Operation operation = new Shareholders.Operation();
    	operation.remove(borrowerMassimo);
    	LinkedList<Shareholder> toBeRemoved = (LinkedList<Shareholder>)PrivateAccessor.getField(operation, "toBeRemoved");
    	assertEquals(borrowerMassimo, toBeRemoved.pop());
    }

    
    @Test
    public void getShareholderByName() {
    	commitSetUpOperation();
    	Shareholder actual = shareholders.getShareholderByName("Massimo");
    	assertEquals(borrowerMassimo, actual);
    }
    
    
    @Test(expected=IllegalArgumentException.class)
    public void getShareholderByNameThrowsAnExceptionWhenShareholderDoesNotExist() {
    	commitSetUpOperation();
    	shareholders.getShareholderByName("Not existent shareholder");
    }
    
    @Test
    public void commitAddsTheListOfShareholders() {
    	commitSetUpOperation();
    	assertEquals(borrowerMassimo, shareholders.getShareholderByName("Massimo"));
    	assertEquals(borrowerPaula, shareholders.getShareholderByName("Paula"));
    	assertEquals(lender, shareholders.getShareholderByName("Yorkshire BS"));
    }
    
    @Test
    public void commitUpdatesTheListOfShareholders() {
    	commitSetUpOperation();
    	commitUpdateOperation();
    	assertEquals(borrowerMassimoUpdate, shareholders.getShareholderByName("Massimo"));
    	assertEquals(borrowerPaula, shareholders.getShareholderByName("Paula"));
    	assertEquals(lenderUpdate, shareholders.getShareholderByName("Yorkshire BS"));
    }
    
    @Test
    public void commitUpdateAndDeletesTheListOfShareholders() {
    	commitSetUpOperation();
    	commitUpdateAndDeleteOperation();
    	assertEquals(borrowerMassimoUpdate, shareholders.getShareholderByName("Massimo"));
    	assertEquals(lender, shareholders.getShareholderByName("Yorkshire BS"));
    }
    
    @Test
    public void commitThrowsAnExceptionIfTheTotalAmountOfShareIsNot100AndDoesntUpdateTheListOfShareholders() {
    	commitSetUpOperation();
    	try {
    		commitWithOddSharesOperation();
    		fail();
    		
    	} catch(AssertionError e) {
    		assertEquals(borrowerMassimo, shareholders.getShareholderByName("Massimo"));
    		assertEquals(borrowerPaula, shareholders.getShareholderByName("Paula"));
    		assertEquals(lender, shareholders.getShareholderByName("Yorkshire BS"));
    	}
    }


	private void commitWithOddSharesOperation() {
		Operation op = new Operation();
		op.update(borrowerMassimoUpdate);
    	shareholders.commit(op);
	}


	private void commitSetUpOperation() {
		Operation op = new Operation();
    	op.add(borrowerMassimo);
    	op.add(borrowerPaula);
    	op.add(lender);
    	shareholders.commit(op);
	}

	private void commitUpdateOperation() {
		Operation op = new Operation();
		op.update(borrowerMassimoUpdate);
		op.update(lenderUpdate);
		shareholders.commit(op);
	}

	private void commitUpdateAndDeleteOperation() {
		Operation op = new Operation();
		op.update(borrowerMassimoUpdate);
		op.remove(borrowerPaula);
		shareholders.commit(op);
	}
}
