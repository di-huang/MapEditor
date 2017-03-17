package unit_testing.master;

import unit_testing.editor.Dashboard;
import unit_testing.map.Map;
import unit_testing.visualizer.Visualizer;

/**
 * 
 * @author dihuang
 *
 */
public class TestMaster {
	
	public static void main(String[] args) {
		Map M = new Map();
		Dashboard D = new Dashboard();
		Visualizer V = new Visualizer();
		Master Ma = new Master(M, D, V);
		Ma.start();
	}
	
}
