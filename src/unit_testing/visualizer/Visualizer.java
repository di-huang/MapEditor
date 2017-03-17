package unit_testing.visualizer;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import unit_testing.InputHandler.InputHandler;
import unit_testing.master.Tickable;

/**
 * 
 * @author dihuang
 *
 */
public class Visualizer implements Tickable{
	
	public static final int WIDTH = 520;
	public static final int HEIGHT = 460;
	
	public Visualizer(){
		init();
	}
	
	private JFrame frame;
	private MyPanel panel;
	
	private void init(){
		frame = new JFrame("MockVisualizer");
		
		// obtain information about current screen
		Dimension screen_size = Toolkit.getDefaultToolkit().getScreenSize();
		int screen_width = (int)screen_size.getWidth();
		int screen_height = (int)screen_size.getHeight();
		
		// calculate preferred location
		int frame_x = (screen_width - WIDTH) / 2;
		int frame_y = (screen_height - HEIGHT) / 2;
		
		// set location and size of the frame
		frame.setLocation(frame_x, frame_y);
		frame.setSize(WIDTH, HEIGHT);
		
		// set other characteristics about the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		//frame.setLayout(null);
		
		// add a panel on it
		panel = new MyPanel();
		frame.add(panel);
		
		frame.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				InputHandler.handle(e);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				panel.repaint();
			}
		});
		
		frame.setVisible(true);
	}

	@Override
	public void tick() {

	}
	
}

