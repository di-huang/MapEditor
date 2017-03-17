package unit_testing.entity;

import java.awt.Color;

import unit_testing.environment.Path;

/**
 * 
 * @author dihuang
 *
 */
public class SolidEntity extends AbstractEntity implements Entity, Path{
	
	@Override
	public boolean isBlocked() {
		return blocked;
	}
	
	/**
	 * Following codes are for mock test
	 */
    private Color color;
    private boolean blocked;
    
    public SolidEntity(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        HP = 100;
        this.color = color;
        blocked = true;
    }

	public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
}
