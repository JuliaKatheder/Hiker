package com.ibm.katheder.map;

public class MapPosition {

	private int y;
	private int x;

	/**
	 * Constructor for a point on the map. 
	 * 
	 * @param y 
	 * 		value on the Y-Axis ("Height" of the map)
	 * @param x
	 * 		value on the X-Axis ("Width" of the map)
	 */
	public MapPosition(int y, int x) {
		this.y = y;
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void setLocation(int y, int x) {
		this.y = y;
		this.x = x;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof MapPosition) {
			final MapPosition compareEle = (MapPosition) obj;
			return compareEle.getX() == this.getX() && compareEle.getY() == this.getY();
		}
		return false;
	}
	
	public String toString(){
		return "Y-Axis: " + this.getY() + " X-Axis: " + this.getX();
	}
}
