package mortgage.shareholder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shareholders {
	
	private Map<String, Shareholder> holders = new HashMap<String, Shareholder>();

	public void add(Shareholder shareholder) {
		if (holders.get(shareholder.getName()) == null) {
			addIt(shareholder);
		} else {
			
			throw new IllegalArgumentException("Shareholder with the same name is already existing!"
					+ " shareholder: " + holders.get(shareholder.getName())
					+ " trying to add: " + shareholder);
		}
		
	}
	
	public void modify(Shareholder shareholder) {
		if (holders.get(shareholder.getName()) == null) {
			throw new IllegalArgumentException("Shareholder with name: " + shareholder.getName() + " does not exists!");
			
		} else {
			addIt(shareholder);
			
		}
	}
	
	public Borrower getBorrower(String string) {
		Shareholder shareholder = holders.get(string);
		if (shareholder instanceof Borrower) {
			return (Borrower)shareholder;
		} else {
			throw new IllegalArgumentException("This is not a Borrower: " + shareholder);
		}
	}
	
	public List<Shareholder> getAll() {
		List<Shareholder> retVal = new ArrayList<Shareholder>();
		for (String name: holders.keySet()) {
			retVal.add(holders.get(name));
		}
		return retVal;
	}
	
	private void addIt(Shareholder shareholder) {
		holders.put(shareholder.getName(), shareholder);
	}

	public Lender getLender(String string) {
		Shareholder shareholder = holders.get(string);
		if (shareholder instanceof Lender) {
			return (Lender)shareholder;
		} else {
			throw new IllegalArgumentException("This is not a Lender: " + shareholder);
		}
	}
	
	@Override
	public boolean equals(Object shareholders) {
		return (shareholders instanceof Shareholders) && this.holders.equals(((Shareholders)shareholders).holders);
	}
	
	@Override
	public String toString() {
		return this.holders.toString();
	}

	public boolean isEmpty() {
		return holders.isEmpty();
	}

}
