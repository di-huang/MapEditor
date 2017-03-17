package unit_testing.entity;

import java.awt.Color;

/**
 * 
 * @author dihuang
 *
 */
public abstract class Entity{
	
	protected int x_map, y_map;
	protected int HP;
	protected Color color;
	protected String des;
	
	public int getX() {
		return x_map;
	}

	public void setX(int x) {
		this.x_map = x;
	}

	public int getY() {
		return y_map;
	}

	public void setY(int y) {
		this.y_map = y;
	}
	
	public int getHP(){
    	return HP;
    }
	
	public void setHP(int hp){
    	HP = hp;
    }
	
	public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
	
    public String getDes(){
    	return des;
    }
}
