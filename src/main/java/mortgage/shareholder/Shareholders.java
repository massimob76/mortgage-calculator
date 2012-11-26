package mortgage.shareholder;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Shareholders {
	
	private Map<String, Shareholder> shareholders = new HashMap<String, Shareholder>();
	private Map<String, Shareholder> shareholdersClone = new HashMap<String, Shareholder>();
	

	public static class Operation {
		
		private List<Shareholder> toBeAdded = new LinkedList<Shareholder>();
		private List<Shareholder> toBeUpdated = new LinkedList<Shareholder>();
		private List<Shareholder> toBeRemoved = new LinkedList<Shareholder>();
		
		public void add(Shareholder shareholder) {
			toBeAdded.add(shareholder);
		}
		
		public void update(Shareholder shareholder) {
			toBeUpdated.add(shareholder);
		}
		
		public void remove(Shareholder shareholder) {
			toBeRemoved.add(shareholder);
		}
		
	}
	
	
	public Shareholder getShareholderByName(String name) {
		Shareholder shareholder = shareholders.get(name);
		if (shareholder == null) {
			throw new IllegalArgumentException("Could not find shareholder with name: " + name);
		} else {
			return shareholder;
		}
	}

	
	public void commit(Operation operation) {
		resetClones();
		addShareholders(operation.toBeAdded);
		updateShareholders(operation.toBeUpdated);
		removeShareholders(operation.toBeRemoved);
		verifyAndCopyClones();
	}
	
	private void resetClones() {
		shareholdersClone = new HashMap<String, Shareholder>(shareholders);
	}

	private void verifyAndCopyClones() {
		verifyClones();
		shareholders = shareholdersClone;
	}

	private void verifyClones() {
		double totalShare = 0;
		for (String shareholderName: shareholdersClone.keySet()) {
			totalShare += shareholdersClone.get(shareholderName).getShare();
		}
		assertEquals(100, totalShare, 0.005);
	}

	private void addShareholders(List<Shareholder> toBeAdded) {
		for (Shareholder shareholder: toBeAdded) {
			verifyShareholderDoesNotAlreadyExist(shareholder);
			shareholdersClone.put(shareholder.getName(), shareholder);
		}
	}

	private void updateShareholders(List<Shareholder> toBeUpdated) {
		for (Shareholder shareholder: toBeUpdated) {
			verifyShareholderDoesAlreadyExist(shareholder);
			shareholdersClone.put(shareholder.getName(), shareholder);
		}
	}

	private void removeShareholders(List<Shareholder> toBeRemoved) {
		for (Shareholder shareholder: toBeRemoved) {
			verifyShareholderDoesAlreadyExist(shareholder);
			shareholdersClone.remove(shareholder.getName());
		}
		
	}

	private void verifyShareholderDoesNotAlreadyExist(Shareholder shareholder) {
		if (shareholdersClone.get(shareholder.getName())!=null) {
			throw new IllegalArgumentException("shareholder already exists: " + shareholder.toString());
		}
	}
	
	private void verifyShareholderDoesAlreadyExist(Shareholder shareholder) {
		if (shareholdersClone.get(shareholder.getName())==null) {
			throw new IllegalArgumentException("shareholder already exists: " + shareholder.toString());
		}
	}
	
	

}
