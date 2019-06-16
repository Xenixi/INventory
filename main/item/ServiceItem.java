package inventory.main.item;

import java.util.ArrayList;

public class ServiceItem extends VItem {
	public ServiceItem(String name, String desc, double itemCost, double itemPrice) {
		super(name, desc, itemCost, itemPrice);
	}

	public ServiceItem(String name) {
		super(name);
	}

	public ServiceItem(String name, String desc) {
		super(name, desc);
	}

	public ServiceItem(String name, String desc, String instructions, double itemCost, double itemPrice) {
		super(name, desc, instructions, itemCost, itemPrice);
	}

	public ServiceItem(String name, String desc, String instructions) {
		super(name, desc, instructions);
	}

	private boolean serviceInProgress = false;
	private ArrayList<String> available = new ArrayList<String>();
	private int maxCount = 0, currentCount = 0;

	public void setServiceInProgress(boolean inProgress) {
		this.serviceInProgress = inProgress;
	}

	public void addAvailable(String name) {
		this.available.add(name);
	}

	public void resetAvailable() {
		this.available.clear();
	}

	public void removeAvailable(String name) {
		int i = 0;
		for (String availableName : available) {
			if (availableName.equalsIgnoreCase(name)) {
				available.remove(i);
			}
			i++;
		}
	}
	public void setMaxCount(int count) {
		this.maxCount = count;
	}
	public void setCurrentCount(int count) {
		this.currentCount = count;
	}
	
	//getters
	public boolean serviceInProgress() {
		return this.serviceInProgress;
	}
	public ArrayList<String> getAvailable(){
		return this.available;
	}
	public int getMaxCount() {
		return this.maxCount;
	}
	public int getCurrentCount() {
		return this.currentCount;
	}
	
}
