package mortgage.shareholder;

public class Borrower implements Shareholder {
	
	private final String name;
	private double share;
	
	public Borrower(String name, double share) {
		this.name = name;
		this.share = share;
	}
	
	public String getName() {
		return name;
	}
	
	public double getShare() {
		return share;
	}
	
	public void addShare(double share) {
		this.share += share;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("name: ");
		sb.append(name);
		sb.append(", share: ");
		sb.append(String.format("%.2f", share));
		return sb.toString();
	}

}
