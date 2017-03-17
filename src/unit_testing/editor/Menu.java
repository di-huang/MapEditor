package unit_testing.editor;

import java.awt.Color;

import unit_testing.map.Grid;
import unit_testing.map.Map;

/**
 * 
 * @author dihuang
 *
 */
public class Menu{
	
	/**
	 * Following codes are for mock test
	 */
	public static int x = Map.width + 2*Grid.size;
	public static int y = Grid.size;
	public static int width = 3*Grid.size, height = 20*Grid.size;
	public static MenuItem[] menu = {
			new MenuItem("solid", 1, Color.RED),
			new MenuItem("fixed", 2, Color.GREEN),
			new MenuItem("player", 3, Color.ORANGE),
	};

}

