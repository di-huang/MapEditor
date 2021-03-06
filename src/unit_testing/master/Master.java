package unit_testing.master;

import unit_testing.editor.Editor;
import unit_testing.map.Map;
import unit_testing.visualizer.Visualizer;

/**
 * 
 * @author dihuang
 *
 */
public class Master implements Tickable, Runnable{
	
	private Map M;
	private Editor E;
	private Visualizer V;
	private boolean running;
	
	public Master(Map m, Editor e, Visualizer v) {
		M = m;
		E = e;
		V = v;
		running = false;
	}
	
	public void start() {
		if(running == true){
			return;
		}
		running = true;
		new Thread(this).start();
	}
	
	public void stop(){
		running = false;
	}

	@Override
	public void run() {
		int tick = 0;
		long now, then = System.currentTimeMillis();;
		while (running) {
			tick++;
			tick();

			now = System.currentTimeMillis();
			if(now - then >= 1000){
				System.out.printf("%d ticks/sec%n", tick);
				tick = 0;
				then = System.currentTimeMillis();
			}
		}
	}
	
	@Override
	public void tick() {
		M.tick();
		E.tick();
		V.tick();
	}
	
}
