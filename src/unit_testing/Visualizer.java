package unit_testing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * 
 * @author dihuang
 *
 */
public class Visualizer {
	
	public static final int WIDTH = 500;
	public static final int HEIGHT = 440;
	
	public Visualizer(){
		init();
	}
	
	private JFrame frame;
	private JPanel panel;
	
	private void init(){
		frame = new JFrame("TestVisualizer");
		
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
		
		Screen mp = new Screen();
        // add squares below
        Entity sq3 = new Entity(60, 60, Color.BLUE);
        Entity sq4 = new Entity(80, 80, Color.BLACK);
        Entity sq5 = new Entity(100, 100, Color.LIGHT_GRAY);
        mp.addObj(sq3);
        mp.addObj(sq4);
        mp.addObj(sq5);
        frame.add(mp);
		
		frame.setVisible(true);
	}
	
}

class Screen extends JPanel {

    private ArrayList<Entity> sqlist = new ArrayList<Entity>();
    private Entity sqSelected = null;
    private int offset_x = 0;
    private int offset_y = 0;
    
    public int map_size;
	public int customer_width;
	public int customer_height;
	
    public Screen() {
    	map_size = Visualizer.HEIGHT - 3*Entity.width;
    	customer_width = 3*Entity.width;
    	customer_height = map_size;
        setBorder(BorderFactory.createLineBorder(Color.black));
        
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                System.out.println("Mouse pressed, X: " + e.getX() + " Y: " + e.getY());
                
                boolean selecting = false;
                int index = 0;
                for(int i = 0; i < sqlist.size(); i++){
                    Entity sq = sqlist.get(i);
                    if (sq.isWithin(e.getX(), e.getY()) && i >= index) {
                        index = i;
                        sqSelected = sq;
                        selecting = true;
                        offset_x = e.getX() - sqSelected.x;
                        offset_y = e.getY() - sqSelected.y;
                    }
                }
                
                if(selecting){
                    sqSelected.pressed();
                    sqlist.remove(index);
                    sqlist.add(sqSelected);
                    repaint();
                    return;
                }
                
                moveSquare(e.getX()-offset_x, e.getY()-offset_y, sqSelected);
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (sqSelected.isPressed()) {
                    moveSquare(e.getX()-offset_x, e.getY()-offset_y, sqSelected);
                    System.out.println("Mouse dragged, X: " + e.getX() + " Y: " + e.getY());
                }
            }
        });

        addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                System.out.println("Mouse released, X: " + e.getX() + " Y: " + e.getY());
                sqSelected.released();;
            }
        });
    }
    
    public void addObj(Entity sq){
        sqlist.add(sq);
    }

    private void moveSquare(int x, int y, Entity sq) {
        sq.x = x;
        sq.y = y;
        repaint();
    }

    private void drawMap(int x, int y, Graphics g){
    	g.drawLine(x, y, x, y + map_size);
    	g.drawLine(x, y, x + map_size, y);
    	for(int i = 1; i <= map_size/Entity.width; i++){
			g.drawLine(x, Entity.width*i + y, map_size + x, Entity.width*i + y);
		}
    	for(int i = 1; i <= map_size/Entity.width; i++){
			g.drawLine(Entity.width*i + x, y, Entity.width*i + x, map_size + y);
		}
    }
    
    private void drawCustomer(int x, int y, Graphics g){
    	g.drawLine(x, y, x, y + customer_height);
    	g.drawLine(x, y, x + customer_width, y);
    	g.drawLine(x, y + customer_height, x + customer_width, y + customer_height);
    	g.drawLine(x + customer_width, y, x + customer_width, y + customer_height);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        drawMap(Entity.width, Entity.width, g);
        drawCustomer(map_size + 2*Entity.width, Entity.width, g);
        
        for (Entity sq : sqlist) {
            g.setColor(sq.getColor());
            g.fillRect(sq.x, sq.y, Entity.width, Entity.width);
            g.setColor(Color.BLACK);
            g.drawRect(sq.x, sq.y, Entity.width, Entity.width);
        }
    }
}

