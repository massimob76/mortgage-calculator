package mortgage.operation;

import java.util.List;

import mortgage.shareholder.Shareholder;

public interface Operation {
	
	public List<Shareholder> applyTo(List<Shareholder> shareholdersBefore);

}
