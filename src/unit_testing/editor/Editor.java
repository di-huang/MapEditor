package unit_testing.editor;

import java.util.ArrayList;

import unit_testing.master.Tickable;

/**
 * 
 * @author dihuang
 *
 */
public class Editor implements Tickable{
	
	/**
	 * Following codes are for mock test
	 */
	public static ArrayList<Item> items = new ArrayList<Item>();
	
	public Editor(){
		MenuItem[] menu = Menu.menu;
		for(int i = 0; i < menu.length; i++){
			items.add(new Item(menu[i].description, menu[i].pos, menu[i].color));
		}
	}
	
	@Override
	public void tick() {
		
	}

}
