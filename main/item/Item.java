package inventory.main.item;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import inventory.gui.comp.ItemListElement;

public class Item {
	private String itemName, itemDesc = "", itemInstructions = "";
	private double itemCost = 0.00; // Cost to maintain item / to re-stock
	private double itemPrice = 0.00; // price of item (if being marketed and sold)
	private boolean isMarketItem = false; // is Item being sold in a marketplace / store? ---- defaults false
	private boolean useItemID = false; // see below -- defaults to false
	private String itemID = "INvDEFAULT00"; // Is the item identified across multiple management systems?
	private ArrayList<Image> itemImages = new ArrayList<>();
	private ArrayList<String> tags = new ArrayList<>();
	private ArrayList<Item> associations = new ArrayList<>();
	private ItemListElement ile = new ItemListElement(this);
	
	// constructors:
	public Item(String name) {
		this.itemName = name;
	}

	public Item(String name, String desc) {
		this.itemName = name;
		this.itemDesc = desc;
	}

	public Item(String name, String desc, String instructions) {
		this.itemName = name;
		this.itemDesc = desc;
		this.itemInstructions = instructions;
	}

	public Item(String name, String desc, String instructions, double itemCost, double itemPrice) {
		this.itemName = name;
		this.itemDesc = desc;
		this.itemInstructions = instructions;
		this.itemCost = itemCost;
		this.itemPrice = itemPrice;
	}

	public Item(String name, String desc, double itemCost, double itemPrice) {
		this.itemName = name;
		this.itemDesc = desc;
		this.itemCost = itemCost;
		this.itemPrice = itemPrice;
	}

	// ************************
	// setters
	public void rename(String newName) {
		this.itemName = newName;
	}

	public void setDesc(String desc) {
		this.itemDesc = desc;
	}

	public void setItemInstructions(String itemInstructions) {
		this.itemInstructions = itemInstructions;
	}

	public void setItemCost(double cost) {
		this.itemCost = cost;
	}

	public void setItemPrice(double price) {
		this.itemPrice = price;
	}

	public void setMarketItem(boolean isMarketItem) {
		this.isMarketItem = isMarketItem;
	}

	public void setUseItemID(boolean useItemID) {
		this.useItemID = useItemID;
	}

	public void setItemID(String ID) {
		this.itemID = ID;
	}

	///
	// getters
	public ItemListElement getItemListUI(Point p) {
		ile.setLocation(p);
		return ile;
	}
	public String getName() {
		return this.itemName;
	}

	public String getDesc() {
		return this.itemDesc;
	}

	public String getItemInstructions() {
		return this.itemInstructions;
	}

	public double getItemCost() {
		return this.itemCost;
	}

	public double getItemPrice() {
		return this.itemPrice;
	}

	public boolean isMarketItem() {
		return this.isMarketItem;
	}

	public boolean useItemID() {
		return this.useItemID;
	}

	public String getItemID() {
		return itemID;
	}

	///
	public void addImage(Image i) {
		itemImages.add(i);
	}

	public void addTag(String tag) {
		tags.add(tag.toLowerCase());
	}

	public void addAssociation(Item i) {
		associations.add(i);
	}

	public void resetImages() {
		itemImages.clear();
	}

	public void resetAssociations() {
		associations.clear();
	}

	public void resetTags() {
		tags.clear();
	}

	public void removeTag(String tag) {
		int i = 0;
		for (String tagEntry : tags) {
			if (tagEntry.equalsIgnoreCase(tag)) {
				tags.remove(i);
			}
			i++;
		}
	}

	public void removeAssocation(Item i) {
		int a = 0;
		for (Item item : associations) {
			if (item.equals(i)) {
				associations.remove(i);
			}
		}
		a++;
	}

	// override:
	public boolean equals(Object o) {
		if (((Item) o).getName().equalsIgnoreCase(this.getName()))
			return true;
		else
			return false;
	}
	public String toString() {
		return getName() + " ID:" + getItemID();  
	}

}
