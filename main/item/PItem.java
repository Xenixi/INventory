package inventory.main.item;

public class PItem extends Item{
	public PItem(String name, String desc, double itemCost, double itemPrice) {
		super(name, desc, itemCost, itemPrice);
	}
	public PItem(String name) {
		super(name);
	}
	public PItem(String name, String desc) {
		super(name, desc);
	}
	public PItem(String name, String desc, String instructions, double itemCost, double itemPrice) {
		super(name, desc, instructions, itemCost, itemPrice);
	}
	public PItem(String name, String desc, String instructions) {
		super(name, desc, instructions);
	}
	////
	private String itemLocation = "";
	private int[] dimensions = new int[] {0,0,0}; //defaults to 0x0x0 (LxWxH)
	
	public void setItemLocation(String itemLocation) {
		this.itemLocation = itemLocation;
	}
	public void setItemDimensions(int l, int w, int h) {
		dimensions[0] = l;
		dimensions[1] = w;
		dimensions[2] = h;
	}
	public int[] getDimensions() {
		return dimensions;
	}
	public String getLocation() {
		return itemLocation;
	}
	
}
