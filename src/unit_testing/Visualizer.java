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
	
	
	public final int WIDTH;
	public final int HEIGHT;
	
	public Visualizer(){
		WIDTH = 500;
		HEIGHT = 400;
		init();
	}
	
	public Visualizer(int width, int height){
		WIDTH = width;
		HEIGHT = height;
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
		
		MyPanel mp = new MyPanel();
        // add squares below
        TestingSquare sq3 = new TestingSquare(60, 60, Color.BLUE);
        TestingSquare sq4 = new TestingSquare(80, 80, Color.BLACK);
        TestingSquare sq5 = new TestingSquare(100, 100, Color.LIGHT_GRAY);
        mp.addSq(sq3);
        mp.addSq(sq4);
        mp.addSq(sq5);
        frame.add(mp);
        //frame.pack();
		
		frame.setVisible(true);
	}
	
//	public int map_width;
//	public int map_height;
//	public int customer_width;
//	public int customer_height;
//	
//	private void setAreasOnPlane(){
//		map_width = WIDTH - 125;
//		map_height = HEIGHT - 50;
//		customer_width = 50;
//		customer_height = HEIGHT - 50;
//	}
	
}

class MyPanel extends JPanel {

    private ArrayList<TestingSquare> sqlist = new ArrayList<TestingSquare>();
    private TestingSquare sqSelected = null;
    private int offset_x = 0;
    private int offset_y = 0;

    public MyPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));

        final TestingSquare sq1 = new TestingSquare(20, 20, Color.RED);
        final TestingSquare sq2 = new TestingSquare(40, 40, Color.GREEN);
        sqlist.add(sq1);
        sqlist.add(sq2);
        sqSelected = sq1;

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                System.out.println("Mouse pressed, X: " + e.getX() + " Y: " + e.getY());
                
                if (e.getModifiers() == MouseEvent.BUTTON3_MASK) {
                    System.out.println("Swap Colors");
                    swapColors(sq1, sq2);
                    return;
                }
                
                boolean selecting = false;
                int index = 0;
                for(int i = 0; i < sqlist.size(); i++){
                    TestingSquare sq = sqlist.get(i);
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
    
    public void addSq(TestingSquare sq){
        sqlist.add(sq);
    }

    private void swapColors(TestingSquare sq1, TestingSquare sq2) {
        Color temp = sq1.getColor();
        sq1.setColor(sq2.getColor());
        sq2.setColor(temp);
        repaint();
    }

    private void moveSquare(int x, int y, TestingSquare sq) {
        sq.x = x;
        sq.y = y;
        repaint();
    }

    public Dimension getPreferredSize() {
        return new Dimension(250, 200);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (TestingSquare sq : sqlist) {
            g.setColor(sq.getColor());
            g.fillRect(sq.x, sq.y, sq.width, sq.width);
            g.setColor(Color.BLACK);
            g.drawRect(sq.x, sq.y, sq.width, sq.width);
        }
    }
}

