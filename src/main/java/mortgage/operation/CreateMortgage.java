package mortgage.operation;

import mortgage.shareholder.Lender;
import mortgage.shareholder.Shareholder;
import mortgage.shareholder.Shareholders;

public class CreateMortgage implements Operation {
	
	private final Shareholder[] shareholders;
	
	public CreateMortgage(Shareholder... shareholders) {
		this.shareholders = shareholders;
	}
	
	public void applyTo(Shareholders shareholders) {
		if (shareholders.isEmpty()) {
			verifyThereIsAtLeastOneLender();
			for (Shareholder shareholder: this.shareholders) {
				shareholders.add(shareholder);
			}
		} else {
			throw new IllegalArgumentException("the shareholder obj should be empty: " + shareholders);
		}

	}

	private void verifyThereIsAtLeastOneLender() {
		for (Shareholder shareholder: shareholders) {
			if (shareholder instanceof Lender) return;
		}
		throw new IllegalArgumentException("There are no lenders within the shareholders; shareholders: " + shareholders);
		
	}

}
