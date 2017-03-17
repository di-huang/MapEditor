package unit_testing.entity;

import java.awt.Color;
import java.awt.event.KeyEvent;

import unit_testing.environment.Passable;
import unit_testing.map.Grid;
import unit_testing.map.Map;

/**
 * 
 * @author dihuang
 *
 */
public class Player extends Entity implements Movable{
	
	public Player(){
		HP = 100;
		des = "player";
	}
	
	public Player(Color color){
		this();
		this.color = color;
	}

	@Override
	public void move(KeyEvent k) {
		Grid[][] map = Map.map;
		int x_temp = x_map, y_temp = y_map;
		switch(k.getKeyCode()){
			case KeyEvent.VK_UP:
				y_temp -= 1;
				break;
			case KeyEvent.VK_DOWN:
				y_temp += 1;
				break;
			case KeyEvent.VK_LEFT:
				x_temp -= 1;
				break;
			case KeyEvent.VK_RIGHT:
				x_temp += 1;
				break;
			default:
				return;
		}
		if(isReachable(x_temp, y_temp)){
			map[x_temp][y_temp].setContent(this);
			map[x_map][y_map].setContent(null);
			x_map = x_temp;
			y_map = y_temp;
		}
	}
	
	private boolean isReachable(int x, int y){
		if(x < 0 || x >= Map.width_num || y < 0 || y >= Map.height_num){
			return false;
		}
		Grid[][] map = Map.map;
		Entity en = map[x][y].getContent();
		if(en == null){
			return true;
		}
		if(en instanceof Passable){
			Passable pEn = (Passable) en;
			if(pEn.isBlocked()){
				return false;
			}
			return true;
		}else{
			return false;
		}
	}
	
}
