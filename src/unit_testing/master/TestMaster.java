package unit_testing.master;

import unit_testing.editor.Editor;
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
		Editor E = new Editor();
		Visualizer V = new Visualizer();
		Master Ma = new Master(M, E, V);
		Ma.start();
	}
	
}
