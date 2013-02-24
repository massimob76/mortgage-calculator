package mortgage.helper;

public class Comparison {
	
	private static final float DOUBLE_TOLERANCE = 0.005f;
	
	public static boolean same(double a, double b) {
		double diff = a - b;
		return - DOUBLE_TOLERANCE < diff && diff < DOUBLE_TOLERANCE;
	}

}
