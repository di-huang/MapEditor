package unit_testing.entity;

import java.awt.Color;

/**
 * 
 * @author dihuang
 *
 */
public class EntityFactory {
	
	public Entity getEntity(String des){
		switch (des){
			case "solid":
				return new SolidEntity(Color.RED);
			case "fixed":
				return new FixedEntity(Color.GREEN);
			case "player":
				return new Player(Color.ORANGE);
		}
		return null;
	}
	
}
