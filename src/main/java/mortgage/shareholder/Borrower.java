package mortgage.shareholder;

import static mortgage.helper.Comparison.same;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class Borrower implements Shareholder {
	
	private final String name;
	private double share;
	
	private static Gson gson = new Gson();
	
	public Borrower(String name, double share) {
		this.name = name;
		this.share = share;
	}
	
	public void addShare(double share) {
		this.share += share;
	}
	
	public String getName() {
		return name;
	}
	
	public double getShare() {
		return share;
	}
	
	public static Borrower fromJson(JsonElement jsonElement) {
		return gson.fromJson(jsonElement, Borrower.class);
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
			return (borrower.name.equals(name) && same(borrower.share, this.share));
		}
		return false;
	}

}
