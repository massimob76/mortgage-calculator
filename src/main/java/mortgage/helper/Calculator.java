package mortgage.helper;

import java.util.Calendar;

public class Calculator {
	
	private static final int MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;
	
	public static int calculateDifferenceInDays(Calendar higher, Calendar lower) {
		Calendar cal = Calendar.getInstance();
		
		cal.set(higher.get(Calendar.YEAR), 0, 1); // first of Jan of 'higher' year
		long higherInDays = cal.getTimeInMillis() / MILLIS_IN_A_DAY + higher.get(Calendar.DAY_OF_YEAR);
		
		cal.set(lower.get(Calendar.YEAR), 0, 1); // first of Jan of 'lower' year
		long lowerInDays = cal.getTimeInMillis() / MILLIS_IN_A_DAY + lower.get(Calendar.DAY_OF_YEAR);

		return (int)(higherInDays - lowerInDays);
	}
	
	public static double calculateResidualBorrowing(double initialBorrowing, float annualInterestRate, int days) {
		double multiplicand = Math.log((double)annualInterestRate / 100 / 365 + 1);
		return Math.exp(multiplicand * days) * initialBorrowing;
	}

}
