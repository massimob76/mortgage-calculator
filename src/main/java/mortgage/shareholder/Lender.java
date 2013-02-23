package mortgage.shareholder;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import mortgage.helper.Calculator;

public class Lender implements Shareholder {
	
	private static final SimpleDateFormat TIMESTAMP_FORMATTER = new SimpleDateFormat("MMMMMMMMMMMM d, yyyy");
	private static final float DOUBLE_TOLERANCE = 0.005f;
	
	private final String name;
	private final float annualInterestRate;
	private final Calendar timestamp;
	private final double borrowing;
	private final double share;
	
	public Lender(Calendar timestamp, String name, float annualInterestRate, double borrowing, double share) {
		this.timestamp = timestamp;
		this.name = name;
		this.annualInterestRate = annualInterestRate;
		this.borrowing = borrowing;
		this.share = share;
	}
	
	public Lender setTimestamp(Calendar timestamp) {
		double borrowing = calculateUpdatedBorrowing(timestamp);
		return new Lender(timestamp, name, annualInterestRate, borrowing, share);
	}
	
	public Lender setInterestRate(Calendar timestamp, float annualInterestRate) {
		double borrowing = calculateUpdatedBorrowing(timestamp);
		return new Lender(timestamp, name, annualInterestRate, borrowing, share);
	}
	
	public Lender payIn(Calendar timestamp, double amount) {
		double borrowing = calculateUpdatedBorrowing(timestamp); 
		if (borrowing >= amount) {
			double updatedShares = calculateUpdatedShares(amount, borrowing);
			return new Lender(timestamp, name, annualInterestRate, borrowing - amount, updatedShares);
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
		sb.append("timestamp: ");
		sb.append(TIMESTAMP_FORMATTER.format(timestamp.getTime()));
		sb.append(", name: ");
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
			return (lender.timestamp.equals(timestamp) && lender.name.equals(name)
					&& lender.annualInterestRate == annualInterestRate && same(lender.borrowing, borrowing)
					&& same(lender.share, share));
		}
		return false;
	}
	
	private double calculateUpdatedShares(double paidInAmount, double borrowing) {
		double updatedShare = share * ( 1 - paidInAmount / borrowing);
		return updatedShare;
	}
	
	private double calculateUpdatedBorrowing(Calendar updatedTimestamp) {
		int daysDifference = Calculator.calculateDifferenceInDays(updatedTimestamp, timestamp);
		double borrowing = Calculator.calculateResidualBorrowing(this.borrowing, annualInterestRate, daysDifference);
		return borrowing;
	}
	
	private static boolean same(double a, double b) {
		double diff = a - b;
		return - DOUBLE_TOLERANCE < diff && diff < DOUBLE_TOLERANCE;
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

}
