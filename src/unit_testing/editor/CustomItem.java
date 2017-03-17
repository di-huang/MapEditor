package unit_testing.editor;

import java.awt.Color;

/**
 * 
 * @author dihuang
 *
 */
public class CustomItem extends MenuItem implements EditMovable{
	
	private boolean chosen = false;
	
	@Override
	public boolean isChosen(){
		return chosen;
	}
	
	@Override
	public void chosen(){
		chosen = true;
	}
	
	@Override
	public void finished(){
		chosen = false;
	}
	
	@Override
	public boolean isWithin(int x_mouse, int y_mouse) {
        return x_mouse >= x && x_mouse <= x + size && y_mouse >= y && y_mouse <= y + size;
    }
	
	/**
	 * Following codes are for mock test
	 */
	public int x, y;		// x and y coordinates on the map
	
	public CustomItem(String des, int pos, Color color){
		super(des, pos, color);
		x = x_home;
		y = y_home;
	}
	
}
