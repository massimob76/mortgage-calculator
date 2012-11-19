package mortgage.partecipant;

public class Borrower {
	
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

}
