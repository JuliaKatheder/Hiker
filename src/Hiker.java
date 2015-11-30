
public class Hiker {
	private int positionX;
	private int positionY;
	private int climbingKitCount;
	
	public Hiker() {
		this.positionX = 0;
		this.positionY = 0;
		this.climbingKitCount = 1;
	}
	
	public Hiker(int positionX, int positionY, int climbingKitCount) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.climbingKitCount = climbingKitCount;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public int getClimbingKitCount() {
		return climbingKitCount;
	}

	public void setClimbingKitCount(int climbingKitCount) {
		this.climbingKitCount = climbingKitCount;
	}
	
	public boolean hasClimbingKit() {
		if(climbingKitCount > 0)
			return true;
		return false;
	}
	
	public void useClimbingKit() {
		if(this.hasClimbingKit())
			climbingKitCount--;
	}
}
