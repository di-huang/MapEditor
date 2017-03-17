package unit_testing.map;

import java.awt.Color;

import unit_testing.entity.Entity;

/**
 * 
 * @author dihuang
 *
 */
public class Grid {
	
	public static final int size = 20;
	
	private Color color;
	private Entity content;
	private int x_map, y_map;
	
	public Grid(int x, int y){
		color = null;
		content = null;
		x_map = x;
		y_map = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Entity getContent() {
		return content;
	}

	public void setContent(Entity content) {
		this.content = content;
	}
	
	public boolean isEmpty(){
		return content == null;
	}

	public int getX() {
		return x_map;
	}

	public int getY() {
		return y_map;
	}
	
	public boolean isWithin(int x_mouse, int y_mouse) {
		int x = (x_map+1)*Grid.size;
		int y = (y_map+1)*Grid.size;
        return x_mouse >= x && x_mouse <= x + size && y_mouse >= y && y_mouse <= y + size;
    }
	
}
