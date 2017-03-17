package unit_testing.entity;

import java.awt.Color;

import unit_testing.editor.Dashboard;
import unit_testing.map.Grid;

/**
 * 
 * @author dihuang
 *
 */
public class CustomizedEntity implements Entity{
	
	private boolean chosen = false;
	
	public boolean isChosen(){
		return chosen;
	}
	
	public void chosen(){
		chosen = true;
	}
	
	public void finished(){
		chosen = false;
	}
	
	/**
	 * Following codes are for mock test
	 */
	public int size;
	public Color color;
	public int pos;			// The position on the customer board
	public int x, y;		// x and y coordinates on the map
	
	public CustomizedEntity(int pos, Color color){
		this.pos = pos;
		this.color = color;
		size = Grid.size;
		x = Dashboard.x + Grid.size;
		y = Grid.size * pos;
	}
	
	public boolean isWithin(int xmouse, int ymouse) {
        return xmouse >= x && xmouse <= x + size && ymouse >= y && ymouse <= y + size;
    }
	
}
