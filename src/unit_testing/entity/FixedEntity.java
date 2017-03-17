package unit_testing.entity;

import java.awt.Color;

/**
 * 
 * @author dihuang
 *
 */
public class FixedEntity extends Entity{
	
	public FixedEntity(){
		HP = (int) Double.POSITIVE_INFINITY;
		des = "fixed";
	}
	
	public FixedEntity(Color color){
		this();
		this.color = color;
	}
	
}
