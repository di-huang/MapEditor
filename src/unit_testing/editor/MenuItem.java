package unit_testing.editor;

import java.awt.Color;

import unit_testing.map.Grid;

/**
 * 
 * @author dihuang
 *
 */
public class MenuItem{
	
	/**
	 * Following codes are for mock test
	 */
	public String description;
	public int size;
	public int pos;					// The position on the custom menu
	public Color color;
	public int x_home, y_home;
	
	public MenuItem(String des, int pos, Color color) {
		description = des;
		size = Grid.size;
		this.pos = pos;
		this.color = color;
		x_home = Menu.x + Grid.size;
		y_home = Grid.size * pos;
	}
	
}