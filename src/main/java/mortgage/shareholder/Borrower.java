package mortgage.shareholder;

public class Borrower implements Shareholder {
	
	private final String name;
	private final double share;
	
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
	
	public Borrower addShare(double share) {
		return new Borrower(this.name, this.share + share);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("name: ");
		sb.append(name);
		sb.append(", share: ");
		sb.append(String.format("%.2f", share));
		return sb.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Borrower) {
			Borrower borrower = (Borrower)o;
			return (borrower.name == this.name && borrower.share == this.share);
		}
		return false;
	}

}
