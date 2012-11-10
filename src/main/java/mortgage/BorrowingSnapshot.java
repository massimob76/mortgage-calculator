package mortgage;

public class BorrowingSnapshot {
	
	private final double borrowing;
	private final double multiplicand;
	
	
	public BorrowingSnapshot(double borrowing, float annualInterestRate) {
		this.borrowing = borrowing;
		this.multiplicand = Math.log((double)annualInterestRate / 100 / 365 + 1);
	}
	
	private BorrowingSnapshot(double borrowing, double multiplicand) {
		this.borrowing = borrowing;
		this.multiplicand = multiplicand;
	}
	
	public BorrowingSnapshot getResidualBorrowing(int days) {
		return getResidualBorrowing(days, 0);
	}
	
	public BorrowingSnapshot getResidualBorrowing(int days, double paidIn) {
		double residualBorrowing = Math.exp(multiplicand * days) * borrowing - paidIn;
		return new BorrowingSnapshot(residualBorrowing, multiplicand);
	}
	
	public double getBorrowing() {
		return borrowing;
	}

}
