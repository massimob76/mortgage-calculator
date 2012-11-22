package mortgage.partecipant;

import java.util.HashMap;
import java.util.Map;

public class Partecipants {
	
	private Map<String, Borrower> borrowers = new HashMap<String, Borrower>();
	private Map<String, Lender> lenders = new HashMap<String, Lender>();
	
	public void addBorrower(Borrower borrower) {
		borrowers.put(borrower.getName(), borrower);
	}
	
	public void addLender(Lender lender) {
		lenders.put(lender.getName(), lender);
	}
	
	public Borrower getBorrowerByName(String name) {
		Borrower borrower = borrowers.get(name);
		if (borrower == null) {
			throw new IllegalArgumentException("Could not find borrower with name: " + name);
		} else {
			return borrower;
		}
	}
	
	public Lender getLenderByName(String name) {
		Lender lender = lenders.get(name);
		if (lender == null) {
			throw new IllegalArgumentException("Could not find lender with name: " + name);
		} else {
			return lender;
		}
	}

}
