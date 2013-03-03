package mortgage.operation;

import mortgage.shareholder.Borrower;
import mortgage.shareholder.Lender;
import mortgage.shareholder.Shareholders;


public class PayIn implements Operation {
	
	private final double amount;
	private final String from;
	private final String to;
	
	
	public PayIn(double amount, String from, String to) {
		this.amount = amount;
		this.from = from;
		this.to = to;
	}

	public void applyTo(Shareholders shareholders) {
		Borrower borrower = shareholders.getBorrower(from);
		Lender lender = shareholders.getLender(to);
		borrower.addShare(lender.payInAndReturnSoldShares(amount));
	}
	
	

}
