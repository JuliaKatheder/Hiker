import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Visualisation extends JFrame{
	
	public enum Colors {
		LIGHTGREEN(82,255,111),
		DARKGREEN(14,133,42),
		GRAY(53,66,66),
		BLUE(95,191,247),
		LIGHTBROWN(255,230,170),
		DARKBROWN(158,142,106);

		private Colors(final Integer red, final Integer green, final Integer blue) {
		    this.red = red;
		    this.green = green;
		    this.blue = blue;
		}

		private final Integer red, green, blue;

		public Color getColor() {
		    return new Color(red, green, blue);
		}
	}
	
	TreasureMap map;
	int unit;
	int length;
	
	public Visualisation(TreasureMap map){
		this.map = map;
		this.unit = 30;
		this.length = map.getSize()*unit;
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(this.length, this.length+20);
		this.setVisible(true);
	}
	
	public void paint(Graphics g) {
		int originX = 0;
		int originY = 20;
		
		System.out.println("Painting the awesome map...");
		
		
		for(int i=0; i<map.getSize();i++){
			for(int j=0; j<map.getSize();j++){
				switch(map.getFieldType(i,j)){
				case PLAIN: g.setColor(Colors.LIGHTGREEN.getColor()); break;
				case WOOD: g.setColor(Colors.DARKGREEN.getColor()); break;
				case ROCK: g.setColor(Colors.GRAY.getColor()); break;
				case WATER: g.setColor(Colors.BLUE.getColor()); break;
				case TRAIL: g.setColor(Colors.LIGHTBROWN.getColor()); break;
				case BRIDGE: g.setColor(Colors.DARKBROWN.getColor()); break;
				}
				g.fillRect(originX + this.unit*i, originY + this.unit*j, this.unit, this.unit);
				
			}
		}
		
		
	}
}
