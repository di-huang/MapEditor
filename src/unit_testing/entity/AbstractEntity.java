package unit_testing.entity;

/**
 * 
 * @author dihuang
 *
 */
public abstract class AbstractEntity implements Entity{
	
	protected int x, y;
	
	protected int HP;
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getHP(){
    	return HP;
    }
	
	public void setHP(int hp){
    	HP = hp;
    }
	
}
