package mortgage;

public class InterestCalculator {
	
	private final double borrowing;
	private final double multiplicand;
	
	
	public InterestCalculator(double borrowing, double annualInterestRate) {
		this.borrowing = borrowing;
		this.multiplicand = Math.log(annualInterestRate / 100 / 365 + 1);
	}
	
	public double getResidualBorrowing(int days) {
		return Math.exp(multiplicand * days) * borrowing;
	}

}
