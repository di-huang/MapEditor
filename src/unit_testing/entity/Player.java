package unit_testing.entity;

import java.awt.Color;

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
	
}
