
public class Hiker {
	private Node position;
	private Node destination;
	private int climbingKitCount;
	
	public Hiker() {
		this.position = new Node(0, 0);
		this.destination = new Node(19, 19);
		this.climbingKitCount = 1;
	}
	
	public Hiker(int positionX, int positionY, int destinationX, int destinationY, int climbingKitCount) {
		this.position = new Node(positionX, positionY);
		this.destination = new Node(destinationX, destinationY);
		this.climbingKitCount = climbingKitCount;
	}
	
	public Node getPosition() {
		return this.position;
	}

	public int getPositionX() {
		return (int)position.getX();
	}

	public void setPositionX(int positionX) {
		this.position.setLocation(positionX, this.position.getY());
	}

	public int getPositionY() {
		return (int)position.getY();
	}

	public void setPositionY(int positionY) {
		this.position.setLocation(this.position.getX(), positionY);
	}
	
	public Node getDestination() {
		return this.destination;
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
