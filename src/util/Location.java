package util;

/**
 * Location object
 * @author Joseph & Adam
 *
 */

public class Location {
	private int i;
	private int j;
	
	public Location(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	public int getI() {
		return this.i;
	}
	
	public int getJ() {
		return this.j;
	}
	
	public String toString() {
		return this.i + " " + this.j;
	}
	
	public boolean equals(Object o) {
		
		if( o instanceof Location) {
			if(((Location) o).getI() == this.getI() && ((Location)o).getJ() == this.getJ()) {
				return true;
			} 
			else {
				return false;
			}
		} else {
			return false;
		}
	}	
}