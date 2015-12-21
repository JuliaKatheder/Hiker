import java.util.ArrayList;

public class PathFinding {
	
	private TreasureMap map;
	private FieldCost costs;
	private FieldType[][] nodes;
	private Node destination;
	private Node position;
	private ArrayList<Node> open;
	private ArrayList<Node> closed;
	private ArrayList<Node> path;
	
	public PathFinding(TreasureMap map) {
		this.map = map;
		this.costs = map.getCosts();
		this.nodes = map.getMap();
		this.destination = map.getHiker().getDestination();
		this.position = map.getHiker().getPosition();
		this.open = new ArrayList<Node>();
		this.open.add(this.position);
		this.closed = new ArrayList<Node>();
		this.path = new ArrayList<Node>();
		this.path.add(this.position);
		
	}
	
	public int findPath() {
		int h = estimateCosts(this.position);
		int g = optimalCosts(this.position);
		int f = h + g;
		System.out.println("h: "+h+" g: "+g+" f: "+f);
		return this.calculateStep();
	}
	
	private int calculateStep() {
		
		if(open.isEmpty())
			return 0;
		
		/* Minimum F */
		int minimum = 0;
		Node nextNode = new Node(0,0);

		for(int i = 0; i < open.size(); i++) {
			int h = estimateCosts(open.get(i));
			int g = optimalCosts(open.get(i));
			int f = h + g;
			if(f < minimum || minimum == 0) {
				nextNode = open.get(i);
				minimum = f;
				System.out.println("minimum: "+minimum);
				
			}
		}
		open.remove(nextNode);
		closed.add(nextNode);
		System.out.println("nextNode: "+nextNode.getX()+" "+nextNode.getY());
		/* Minimum F */
		
		updateOpenList(nextNode);
		
		
		
		return 1;
		
		
		
	}
	
	
	private void updateOpenList(Node n) {
		calculateNeighbours(n);
	}

	private ArrayList<Node> calculateNeighbours(Node n) {
		ArrayList<Node> neighbours = new ArrayList<Node>();
		
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				int x = n.getX()+i;
				int y = n.getY()+j;
				if((j!=i) && (j + i)!=0 && x >= 0 && y >= 0 && x < map.getSize() && y < map.getSize()){
					Node m = new Node(x, y);
					neighbours.add(m);
				}	
			}
		}

		/* Version für diagonales Laufen des Wanderers

		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				int x = n.getX()+i;
				int y = n.getY()+j;
				if((j!=0 || i!=0) && x > 0 && y > 0 && x < map.getSize() && y < map.getSize()){
					Node m = new Node(x, y);
				}	
			}
		}
		 */
		
		return neighbours;
	}
	/*
	private int calculateCosts() {
		
	}*/
	
	private int optimalCosts(Node n) {
		int optimum = 0;
		for(int i = 0; i < path.size(); i++) {
			Node node = path.get(i);
			optimum = optimum + costs.getCost(map.getFieldType(node.getX(), node.getY()));
		}
		return optimum;
	}
	
	private int estimateCosts(Node n) {
		int x = this.destination.getX() - n.getX();
		int y = this.destination.getY() - n.getY();
		
		int estimation = (x + y) * this.costs.getMinValue();
		
		System.out.println("estimation: "+estimation+" x: "+x+" y: "+y+" minCosts: "+this.costs.getMinValue());
		return estimation;	
	}
	
}
