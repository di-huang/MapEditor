package unit_testing.user_control;

import java.awt.event.KeyEvent;

import unit_testing.entity.Entity;
import unit_testing.entity.Player;
import unit_testing.map.Grid;
import unit_testing.map.Map;

/**
 * 
 * @author dihuang
 *
 */
public class InputHandler {
	
	public static void handle(KeyEvent k){
		Grid[][] map = Map.map;
		for(int i = 0; i < map.length; i++){
        	for(int j = 0; j < map[i].length; j++){
        		Entity en = map[i][j].getContent();
        		if(en != null && en.getDes() == "player"){
        			Player p = (Player)en;
        			p.move(k);
        			return;
        		}
        	}
        }
	}
	
}
