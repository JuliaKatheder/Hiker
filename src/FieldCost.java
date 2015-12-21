import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FieldCost {
	
	private Map<FieldType, Integer> costs;
	
	public FieldCost(Map<FieldType, Integer> costs) {
		super();
		this.costs = costs;
	}
	
	public FieldCost() {
		this.costs = new HashMap<FieldType, Integer>();
	}

	public Map<FieldType, Integer> getCosts() {
		return costs;
	}

	public void setCosts(Map<FieldType, Integer> costs) {
		this.costs = costs;
	}
	
	public int getCost(FieldType type) {
		return costs.get(type);
	}
	
	public void setCost(FieldType type, int value) {
		this.costs.put(type, value);
	}
	
	public int getMinValue() {
		//System.out.println(this.costs.values());
		return Collections.min(this.costs.values());
	}
}
