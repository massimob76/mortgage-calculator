package mortgage.shareholder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shareholders {
	
	private Map<String, Shareholder> shareholders = new HashMap<String, Shareholder>();

	public void add(Shareholder shareholder) {
		if (shareholders.get(shareholder.getName()) == null) {
			addIt(shareholder);
		} else {
			
			throw new IllegalArgumentException("Shareholder with the same name is already existing!"
					+ " shareholder: " + shareholders.get(shareholder.getName())
					+ " trying to add: " + shareholder);
		}
		
	}
	
	public void modify(Shareholder shareholder) {
		if (shareholders.get(shareholder.getName()) == null) {
			throw new IllegalArgumentException("Shareholder with name: " + shareholder.getName() + " does not exists!");
			
		} else {
			addIt(shareholder);
			
		}
	}
	
	public Borrower getBorrower(String string) {
		Shareholder shareholder = shareholders.get(string);
		if (shareholder instanceof Borrower) {
			return (Borrower)shareholder;
		} else {
			throw new IllegalArgumentException("This is not a Borrower: " + shareholder);
		}
	}
	
	public List<Shareholder> getAll() {
		List<Shareholder> retVal = new ArrayList<Shareholder>();
		for (String name: shareholders.keySet()) {
			retVal.add(shareholders.get(name));
		}
		return retVal;
	}
	
	private void addIt(Shareholder shareholder) {
		shareholders.put(shareholder.getName(), shareholder);
	}

	public Lender getLender(String string) {
		Shareholder shareholder = shareholders.get(string);
		if (shareholder instanceof Lender) {
			return (Lender)shareholder;
		} else {
			throw new IllegalArgumentException("This is not a Lender: " + shareholder);
		}
	}

}
