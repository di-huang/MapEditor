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
	public static ArrayList<CustomItem> items = new ArrayList<CustomItem>();
	
	public Editor(){
		MenuItem[] menu = CustomMenu.menu;
		for(int i = 0; i < menu.length; i++){
			items.add(new CustomItem(menu[i].description, menu[i].pos, menu[i].color));
		}
	}
	
	@Override
	public void tick() {
		
	}

}
