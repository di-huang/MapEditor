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
	public static int width, height;
	
	public int width_num, height_num;
	
	public Map(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		width_num = w;
		height_num = h;
		width = width_num * Grid.size;
		height = height_num * Grid.size;
		init();
	}
	
	/**
	 * Following codes are for mock test
	 */
	public Map(){
		x = Grid.size;
		y = Grid.size;
		width_num = 20;
		height_num = 20;
		width = width_num * Grid.size;
		height = height_num * Grid.size;
		init();
	}
	
	private void init(){
		map = new Grid[width_num][height_num];
		for(int i = 0; i < width_num; i++){
			for(int j = 0; j < height_num; j++){
				map[i][j] = new Grid(i, j);
			}
		}
	}
	
	public static Grid gridChosen(int x_mouse, int y_mouse){
        for(int i = 0; i < map.length; i++){
        	for(int j = 0; j < map[i].length; j++){
        		if(map[i][j].isWithin(x_mouse, y_mouse)){
        			return map[i][j];
        		}
        	}
        }
		return null;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	
}
