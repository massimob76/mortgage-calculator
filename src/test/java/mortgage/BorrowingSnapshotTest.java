package mortgage;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BorrowingSnapshotTest {
	
	private BorrowingSnapshot iut;
	
	private static final double INITIAL_BORROWING = 10000;
	private static final float ANNUAL_INTEREST_RATE = 3.24f;
	private static final double DELTA = 0.005;
	
	@Before
	public void setUp() {
		iut = new BorrowingSnapshot(INITIAL_BORROWING, ANNUAL_INTEREST_RATE);
	}
	
	@Test
	public void getResidualBorrowingAfterZeroDays() {
		int days = 0;
		double expected = INITIAL_BORROWING;
		double actual = iut.getResidualBorrowing(days).getBorrowing();
		assertEquals(expected, actual, DELTA);
	}
	
	@Test
	public void getResidualBorrowingAfterOneDay() {
		int days = 1;
		double expected = 10000.89;
		double actual = iut.getResidualBorrowing(days).getBorrowing();
		assertEquals(expected, actual, DELTA);
	}
	
	@Test
	public void getResidualBorrowingAfterOneYear() {
		int days = 365;
		double expected = 10329.29;
		double actual = iut.getResidualBorrowing(days).getBorrowing();
		assertEquals(expected, actual, DELTA);
	}
	
	@Test
	public void getResidualBorrowingAfterOneYearHavingPaidACertainAmount() {
		int days = 365;
		double paidIn = 100;
		double expected = 10229.29;
		double actual = iut.getResidualBorrowing(days, paidIn).getBorrowing();
		assertEquals(expected, actual, DELTA);
	}

}
