package mortgage.shareholder;

import java.text.SimpleDateFormat;

import mortgage.helper.Calculator;

import static mortgage.helper.Comparison.same;
import static org.junit.Assert.assertTrue;

public class Lender implements Shareholder {
	
	private static final SimpleDateFormat TIMESTAMP_FORMATTER = new SimpleDateFormat("MMMMMMMMMMMM d, yyyy");
	
	private final String name;
	private float annualInterestRate;
	private double borrowing;
	private double share;
	
	public Lender(String name, float annualInterestRate, double borrowing, double share) {
		this.name = name;
		this.annualInterestRate = annualInterestRate;
		this.borrowing = borrowing;
		this.share = share;
	}
	
	public Lender moveTimeline(int daysShift) {
		assertTrue(daysShift >= 0);
		double borrowing = Calculator.calculateResidualBorrowing(this.borrowing, annualInterestRate, daysShift);
		return new Lender(name, annualInterestRate, borrowing, share);
	}
	
	public void setInterestRate(float annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}
	
	public void payIn(double amount) {
		if (this.borrowing >= amount) {
			this.share = calculateUpdatedShares(amount, borrowing);
			this.borrowing -= amount;
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
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("name: ");
		sb.append(name);
		sb.append(", annual interest rate: ");
		sb.append(String.format("%.2f", annualInterestRate));
		sb.append("%, borrowing: ");
		sb.append(String.format("%.2f", borrowing));
		sb.append(", share: ");
		sb.append(String.format("%.2f", share));
		return sb.toString();

	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Lender) {
			Lender lender = (Lender)o;
			return (lender.name.equals(name) && lender.annualInterestRate == annualInterestRate 
					&& same(lender.borrowing, borrowing) && same(lender.share, share));
		}
		return false;
	}
	
	private double calculateUpdatedShares(double paidInAmount, double borrowing) {
		double updatedShare = share * ( 1 - paidInAmount / borrowing);
		return updatedShare;
	}
	
//	private double calculateUpdatedBorrowing(Calendar updatedTimestamp) {
//		int daysDifference = Calculator.calculateDifferenceInDays(updatedTimestamp, timestamp);
//		double borrowing = Calculator.calculateResidualBorrowing(this.borrowing, annualInterestRate, daysDifference);
//		return borrowing;
//	}
	
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

}
