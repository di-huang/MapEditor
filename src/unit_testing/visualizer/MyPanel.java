package unit_testing.visualizer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import unit_testing.editor.Dashboard;
import unit_testing.entity.CustomizedEntity;
import unit_testing.map.Grid;
import unit_testing.map.Map;

public class MyPanel extends JPanel {

	private ArrayList<CustomizedEntity> enlist;
	private CustomizedEntity enSelected = null;
	private int offset_x = 0;
	private int offset_y = 0;
	public static int map_size;
	
	public MyPanel() {
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		map_size = Visualizer.HEIGHT - 3*Grid.size;
		enlist = Dashboard.menu;
		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				System.out.println("Mouse pressed, X: " + e.getX() + " Y: " + e.getY());

				boolean selecting = false;
				int index = 0;
				for (int i = 0; i < enlist.size(); i++) {
					CustomizedEntity en = enlist.get(i);
					if (en.isWithin(e.getX(), e.getY()) && i >= index) {
						index = i;
						enSelected = en;
						selecting = true;
						offset_x = e.getX() - enSelected.x;
						offset_y = e.getY() - enSelected.y;
					}
				}

				if (selecting) {
					enSelected.chosen();
					enlist.remove(index);
					enlist.add(enSelected);
					repaint();
					return;
				}
				
				if (enSelected != null){
					moveEntity(e.getX() - offset_x, e.getY() - offset_y, enSelected);
				}
			}
		});

		addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (enSelected != null && enSelected.isChosen()) {
					moveEntity(e.getX() - offset_x, e.getY() - offset_y, enSelected);
				}
			}
		});

		addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (enSelected != null){
					enSelected.finished();
				}
			}
		});
	}
	
	private void drawMap(int x, int y, Graphics g){
		int height = Map.height * Grid.size;
		int width = Map.width * Grid.size;
    	g.drawLine(x, y, x, y + height);
    	g.drawLine(x, y, x + width, y);
    	for(int i = 1; i <= height/Grid.size; i++){
			g.drawLine(x, Grid.size*i + y, width + x, Grid.size*i + y);
		}
    	for(int i = 1; i <= width/Grid.size; i++){
			g.drawLine(Grid.size*i + x, y, Grid.size*i + x, height + y);
		}
    }
	
	private void drawBoard(int x, int y, Graphics g){
		int height = Dashboard.height * Grid.size;
		int width = Dashboard.width * Grid.size;
    	g.drawLine(x, y, x, y + height);
    	g.drawLine(x, y, x + width, y);
    	g.drawLine(x, y + height, x + width, y + height);
    	g.drawLine(x + width, y, x + width, y + height);
    }

	private void moveEntity(int x, int y, CustomizedEntity en) {
		en.x = x;
		en.y = y;
		repaint();
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        drawMap(Map.x, Map.y, g);
        drawBoard(Dashboard.x, Dashboard.y, g);
        
        for (CustomizedEntity en : enlist) {
            g.setColor(en.color);
            g.fillRect(en.x, en.y, Grid.size, Grid.size);
            g.setColor(Color.BLACK);
            g.drawRect(en.x, en.y, Grid.size, Grid.size);
        }
    }

}
