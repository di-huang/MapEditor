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
	private int x, y;
	
	public Grid(int x, int y){
		color = null;
		content = null;
		this.x = x;
		this.y = y;
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

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
}
