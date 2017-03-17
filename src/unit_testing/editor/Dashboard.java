package unit_testing.editor;

import java.awt.Color;
import java.util.ArrayList;

import unit_testing.entity.CustomizedEntity;
import unit_testing.map.Grid;
import unit_testing.map.Map;

/**
 * 
 * @author dihuang
 *
 */
public class Dashboard{
	
	public static ArrayList<CustomizedEntity> menu = new ArrayList<CustomizedEntity>();
	public static int x, y;
	public static int width, height;
	
	/**
	 * Following codes are for mock test
	 */
	public Dashboard(){
		x = Map.width*Grid.size + 2*Grid.size;
		y = Grid.size;
		width = 3;
		height = 20;
		init();
	}	
	
	private void init(){
		CustomizedEntity en1 = new CustomizedEntity(1, Color.BLUE);
		CustomizedEntity en2 = new CustomizedEntity(2, Color.BLACK);
        CustomizedEntity en3 = new CustomizedEntity(3, Color.LIGHT_GRAY);
        menu.add(en1);
        menu.add(en2);
        menu.add(en3);
	}
	
}
