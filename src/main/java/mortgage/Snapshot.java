package mortgage;

import static mortgage.helper.Comparison.same;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mortgage.shareholder.Shareholder;

public class Snapshot {
	
	private static final SimpleDateFormat TIMESTAMP_FORMATTER = new SimpleDateFormat("MMMMMMMMMMMM d, yyyy");
	
	private final Calendar timestamp;
	private final List<Shareholder> shareholders;
	
	
    public Snapshot(Calendar timestamp, List<Shareholder> shareholders) {
    	verifyShareholders(shareholders);
    	this.timestamp = timestamp;
    	this.shareholders = shareholders;
    }
    
    public Calendar getTimestamp() {
    	return timestamp;
    }
    
	public Shareholder getShareholderByName(String name) {
		for (Shareholder shareholder: shareholders) {
			if (shareholder.getName() == name) {
				return shareholder;
			}
		}
		throw new IllegalArgumentException("Could not find shareholder with name: " + name);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("timestamp: ");
		sb.append(TIMESTAMP_FORMATTER.format(timestamp.getTime()));
		sb.append("\n");
		for (Shareholder shareholder: shareholders) {
			sb.append(shareholder.toString());
			sb.append("\n");
		}
		return sb.toString();
	}

	private static void verifyShareholders(List<Shareholder> shareholders) {
		double totalShare = 0;
		Set<String> names = new HashSet<String>();
		for (Shareholder shareholder: shareholders) {
			totalShare += shareholder.getShare();
			if (!names.add(shareholder.getName())) {
				throw new IllegalArgumentException("Duplicated shareholder's name: " + shareholder.getName());
			}
		}
		same(100, totalShare);
	}

}
