package unit_testing.entity;

import java.awt.Color;

import unit_testing.environment.Passable;

/**
 * 
 * @author dihuang
 *
 */
public class SolidEntity extends Entity implements Passable{
	
	@Override
	public boolean isBlocked() {
		return blocked;
	}
	
	/**
	 * Following codes are for mock test
	 */
    
    private boolean blocked;
    
    public SolidEntity(){
    	HP = 100;
        blocked = true;
        des = "solid";
    }
    
    public SolidEntity(Color color) {
    	this();
        this.color = color;
    }
    
    public SolidEntity(int x, int y, Color color) {
    	this();
        x_map = x;
        y_map = y;
        this.color = color;
    }

}
