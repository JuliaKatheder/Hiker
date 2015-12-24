package com.ibm.katheder.map;


public class Hiker {
	
	private GeoMap geoMap;
	
	private MapPosition position;
	private MapPosition destination;
	
	private int climbingKitCount;
	
	public Hiker(GeoMap geoMap) {
		this(geoMap, new MapPosition(0, 0), new MapPosition(19, 19));
	}
	
	public Hiker(GeoMap geoMap, int posX, int posY, int destX, int destY, int climbingKitCount) {
		this(geoMap, new MapPosition(posX, posY), new MapPosition(destX, destY));
		this.climbingKitCount = climbingKitCount;
	}
	
	public Hiker(GeoMap geoMap, MapPosition startPoint, MapPosition destination) {
		this.geoMap = geoMap;
		this.position = startPoint;
		this.destination = destination;
		this.climbingKitCount = 1; // Default amount
	}
	
	public GeoMap getGeoMap() {
		return geoMap;
	}
	
	public MapPosition getPosition() {
		return this.position;
	}

	public void setPosition(MapPosition position) {
		this.position = position;
	}
	
	public MapPosition getDestination() {
		return this.destination;
	}

	public void setDestination(MapPosition destination) {
		this.destination = destination;
	}
	
	public boolean hasClimbingKit() {
		return (climbingKitCount > 0) ? true : false;
	}
	
	public void useClimbingKit() {
		if(this.hasClimbingKit()) {			
			climbingKitCount--;
		} else {
			// TODO throw an error ot sth
		}
	}
	
	public int getPositionX() {
		return (int)position.getX();
	}

	public int getPositionY() {
		return (int)position.getY();
	}
	
	public int getDestinationX() {
		return (int)destination.getX();
	}

	public void setDestinationX(int positionX) {
		this.destination.setLocation(positionX, this.destination.getY());
	}

	public int getDestinationY() {
		return (int)destination.getY();
	}

	public void setDestinationY(int positionY) {
		this.destination.setLocation(this.destination.getX(), positionY);
	}
	
}