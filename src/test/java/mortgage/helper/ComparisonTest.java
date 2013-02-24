package mortgage.helper;

import static mortgage.helper.Comparison.same;
import static org.junit.Assert.*;

import org.junit.Test;

public class ComparisonTest {
	
	@Test
	public void twoIdenticalDoublesShouldBeConsideredTheSame() {
		assertTrue(same(1d, 1d));
	}
	
	@Test
	public void twoDoublesShouldBeConsideredTheSameWithinATolerance() {
		assertTrue(same(1.000d, 1.003d));
	}
	
	@Test
	public void twoDoublesShouldBeConsideredTheSameWithinAToleranceOtherScenario() {
		assertTrue(same(1.003d, 1.000d));
	}
	
	@Test
	public void twoDoublesShouldBeConsideredDifferentIfOutsideTolerance() {
		assertFalse(same(1.000d, 1.006d));
	}

}
