
public class PropertyCard {

	private String name;
	private int cost;
	private int rent;
	private boolean _isOwned;
	private int _ownedBy;
	private String nameOwnedBy;
	
	public PropertyCard() {
		name = "";
		cost = -1;
		rent = -1;
		_isOwned = false;
		_ownedBy = -1;
		nameOwnedBy = "";
	}
	
	public PropertyCard(String name, int cost, int rent, boolean _isOwned, int _ownedBy) {
		this.name = name;
		this.cost = cost;
		this.rent = rent;
		this._isOwned = false;
		this._ownedBy = -1;
	}
	
	public String getName() {
		return name;
	}
	public int getCost() {
		return cost;
	}
	public int getRent() {
		return rent;
	}
	public String getOwnedBy() {
		return nameOwnedBy;
	}
	public boolean isOwned() {
		return _isOwned;
	}
	public int ownedBy() {
		return _ownedBy;
	}
	public void setOwnedBy(int n) {
		_ownedBy = n;
	}
	public void setNameOwnedBy(String s) {
		nameOwnedBy = s;
	}
	public void setIsOwned(boolean b) {
		_isOwned = b;
	}
	public String toString() {
		String temp = "Card name: " + name + "\nCard Cost: " + cost + "\nCard rent: " + rent + "\nisOwned: " + _isOwned + "\nOwnedBy: " + _ownedBy + "\n";
		return temp;
	}
}
