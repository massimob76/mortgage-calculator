package mortgage.partecipant;

import java.util.Calendar;

import mortgage.helper.Calculator;

public class Lender {
	
	private final String name;
	private float annualInterestRate;
	private Calendar timestamp;
	private double borrowing;
	private double share;
	
	public Lender(Calendar timestamp, String name, float annualInterestRate, double borrowing, double share) {
		this.timestamp = timestamp;
		this.name = name;
		this.annualInterestRate = annualInterestRate;
		this.borrowing = borrowing;
		this.share = share;
	}
	
	public void setTimestamp(Calendar timestamp) {
		updateBorrowingAndTimestamp(timestamp);
	}
	
	public void setInterestRate(Calendar timestamp, float annualInterestRate) {
		updateBorrowingAndTimestamp(timestamp);
		this.annualInterestRate = annualInterestRate;
	}
	
	public double payIn(Calendar timestamp, double amount) {
		updateBorrowingAndTimestamp(timestamp);
		if (borrowing >= amount) {
			double boughtShare = updateAndReturnBoughtShares(amount);
			borrowing -= amount;
			return boughtShare;
		} else {
			throw new IllegalPayInAmount("amount cannot be higher than residual borrowing", amount, borrowing);
		}
	}
	
	public String getName() {
		return name;
	}
	
	public double getBorrowing() {
		return borrowing;
	}
	
	public double getShare() {
		return share;
	}
	
	private double updateAndReturnBoughtShares(double paidInAmount) {
		double boughtShare = share * paidInAmount / borrowing;
		share -= boughtShare;
		return boughtShare;
	}

	private void updateBorrowingAndTimestamp(Calendar updatedTimestamp) {
		if (updatedTimestamp.before(timestamp))
			throw new IllegalTimestamp("updated timestamp cannot be in the past", timestamp, updatedTimestamp);
		int daysDifference = Calculator.calculateDifferenceInDays(updatedTimestamp, timestamp);
		this.borrowing = Calculator.calculateResidualBorrowing(borrowing, annualInterestRate, daysDifference);
		this.timestamp = updatedTimestamp;
	}
	
	@SuppressWarnings("serial")
	private static class IllegalPayInAmount extends RuntimeException {
		
		private final String msg;
		private final double amount;
		private final double borrowing;
		
		public IllegalPayInAmount(String msg, double amount, double borrowing) {
			this.msg = msg;
			this.amount = amount;
			this.borrowing = borrowing;
		}
		
		public String toString() {
			return "IllegalPayInAmount: msg: " + msg + " amount: " + amount + " borrowing: " + borrowing;
		}
	}
	
	@SuppressWarnings("serial")
	private static class IllegalTimestamp extends RuntimeException {
		
		private final String msg;
		private final Calendar timestamp;
		private final Calendar updatedTimestamp;
		
		public IllegalTimestamp(String msg, Calendar timestamp, Calendar updatedTimestamp) {
			this.msg = msg;
			this.timestamp = timestamp;
			this.updatedTimestamp = updatedTimestamp;
		}

		public String toString() {
			return "IllegalTimestamp: msg: " + msg + " timestamp: " + timestamp + " updated timestamp: " + updatedTimestamp;
		}
	}

}
