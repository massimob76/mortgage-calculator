package mortgage.operation;

import java.util.List;

import mortgage.shareholder.Lender;
import mortgage.shareholder.Shareholder;

public class CreateMortgage implements Operation {
	
	private final List<Shareholder> shareholders;
	
	public CreateMortgage(List<Shareholder> shareholders) {
		this.shareholders = shareholders;
	}
	
	public List<Shareholder> applyTo(List<Shareholder> emptyList) {
		verifyThereIsAtLeastOneLender();
		return this.shareholders;
	}

	private void verifyThereIsAtLeastOneLender() {
		for (Shareholder shareholder: shareholders) {
			if (shareholder instanceof Lender) return;
		}
		throw new IllegalArgumentException("There are no lenders within the shareholders; shareholders: " + shareholders);
		
	}

}
