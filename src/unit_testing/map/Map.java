package unit_testing.map;

import unit_testing.master.Tickable;

/**
 * 
 * @author dihuang
 *
 */
public class Map implements Tickable{
	
	public static Grid[][] map;
	public static int x, y;
	public static int width, height;		// unit: grid
	
	public Map(int x, int y, int l, int w){
		this.x = x;
		this.y = y;
		width = l;
		height = w;
		init();
	}
	
	/**
	 * Following codes are for mock test
	 */
	public Map(){
		x = Grid.size;
		y = Grid.size;
		width = 20;
		height = 20;
		init();
	}
	
	private void init(){
		map = new Grid[width][height];
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				map[i][j] = new Grid(i, j);
			}
		}
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	
}
