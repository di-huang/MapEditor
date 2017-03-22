package unit_testing.visualizer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import unit_testing.editor.Editor;
import unit_testing.editor.Item;
import unit_testing.editor.Menu;
import unit_testing.editor.MenuItem;
import unit_testing.entity.Entity;
import unit_testing.entity.EntityFactory;
import unit_testing.map.Grid;
import unit_testing.map.Map;
import unit_testing.user_control.InputHandler;

/**
 * 
 * @author dihuang
 *
 */
public class MyPanel extends JPanel {

	private static final long serialVersionUID = -3021966689273095173L;
	private Item itemSelected = null;
	private int x_offset = 0;
	private int y_offset = 0;
	
	boolean editDone = true;
	boolean dragStart = false;
	Grid g;
	
	public MyPanel() {
		JButton jb = new JButton("Done"); 
		jb.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(editDone == true){
					return;
				}
				if(g != null && g.isEmpty()){
					EntityFactory fac = new EntityFactory();
					Entity en = fac.getEntity(itemSelected.description);
					en.setX(g.getX());
					en.setY(g.getY());
					g.setContent(en);
				}
				dragStart = false;
				editDone = true;
				moveItem(itemSelected.x_home, itemSelected.y_home, itemSelected);
				MyPanel.this.requestFocusInWindow();
			}
		});
		jb.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				InputHandler.handle(e);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				MyPanel.this.repaint();
			}
		});
		this.setLayout(new BorderLayout());
		this.add("South", jb);
		
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				ArrayList<Item> items = Editor.items;

				boolean moving = false;
				int index = 0;					// avoid item-passing-underneath-bug
				for (int i = 0; i < items.size(); i++) {
					Item ci = items.get(i);
					if (ci.isWithin(e.getX(), e.getY())) {
						index = i;
						itemSelected = ci;
						editDone = false;
						moving = true;
						x_offset = e.getX() - itemSelected.x;
						y_offset = e.getY() - itemSelected.y;
					}
				}
				
				if (moving) {
					itemSelected.chosen();
					items.remove(index);		// avoid item-passing-underneath-bug
					items.add(itemSelected);	// avoid item-passing-underneath-bug
					repaint();
					return;
				}
				
				if (itemSelected != null){
					g = Map.gridChosen(e.getX(), e.getY());
					if(g == null){
						moveItem(itemSelected.x_home, itemSelected.y_home, itemSelected);
						return;
					}
					
					moveItem(e.getX() - x_offset, e.getY() - y_offset, itemSelected);
				}
			}
			
		});
		
		addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (itemSelected != null && itemSelected.isChosen()) {
					moveItem(e.getX() - x_offset, e.getY() - y_offset, itemSelected);
					if(dragStart){
						g = Map.gridChosen(e.getX(), e.getY());
						if(g != null && g.isEmpty()){
							EntityFactory fac = new EntityFactory();
							Entity en = fac.getEntity(itemSelected.description);
							en.setX(g.getX());
							en.setY(g.getY());
							g.setContent(en);
						}
					}
				}
			}
		});
		
		addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (itemSelected != null){
					itemSelected.finished();
					g = Map.gridChosen(e.getX(), e.getY());
					if(g == null){
						moveItem(itemSelected.x_home, itemSelected.y_home, itemSelected);
						return;
					}
				}
			}
		});
		
		addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_SHIFT){
					dragStart = true;
				}
				InputHandler.handle(e);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				MyPanel.this.repaint();
			}
			public void keyReleased(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_SHIFT){
					dragStart = false;
				}
			}

		});
		
		this.setFocusable(true);
		this.requestFocusInWindow();
		
	}
	
	private void drawMap(int x, int y, Graphics g){
		int height = Map.height;
		int width = Map.width;
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
		int height = Menu.height;
		int width = Menu.width;
    	g.drawLine(x, y, x, y + height);
    	g.drawLine(x, y, x + width, y);
    	g.drawLine(x, y + height, x + width, y + height);
    	g.drawLine(x + width, y, x + width, y + height);
    	
    	// draw menu
    	for (MenuItem mi : Menu.menu) {
            g.setColor(mi.color);
            g.fill3DRect(mi.x_home, mi.y_home, Grid.size, Grid.size, false);
            g.setColor(Color.BLACK);
            g.drawRect(mi.x_home, mi.y_home, Grid.size, Grid.size);
        }
    }
	
	private void drawEntity(int x, int y, Graphics g){
		Grid[][] map = Map.map;
		int X = (x+1)*Grid.size;
		int Y = (y+1)*Grid.size;
		g.setColor(map[x][y].getContent().getColor());
        g.fillRect(X, Y, Grid.size, Grid.size);
	}

	private void moveItem(int x, int y, Item ci) {
		ci.x = x;
		ci.y = y;
		repaint();
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        drawMap(Map.x, Map.y, g);
        drawBoard(Menu.x, Menu.y, g);
        Grid[][] map = Map.map;
        for(int i = 0; i < map.length; i++){
        	for(int j = 0; j < map[i].length; j++){
        		if(!map[i][j].isEmpty()){
        			drawEntity(i, j, g);
        		}
        	}
        }
        
        ArrayList<Item> items = Editor.items;
        for (Item ci : items) {
            g.setColor(ci.color);
            g.fillRect(ci.x, ci.y, Grid.size, Grid.size);
            g.setColor(Color.BLACK);
            g.drawRect(ci.x, ci.y, Grid.size, Grid.size);
        }
    }

}
