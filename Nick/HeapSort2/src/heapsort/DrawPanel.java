package heapsort;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;


public class DrawPanel extends JPanel{
	private int height;
	private int width;
	private ArrayList<Rectangle2D> arRect = new ArrayList<Rectangle2D>();
	
	public DrawPanel(int width, int height){
		this.width = width;
		this.height = height;
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		for(int i = 0; i < arRect.size(); i++){
			g2.draw(arRect.get(i));
			
		}
	}
	
	public void setArRect(ArrayList<Rectangle2D> arRect){
		this.arRect = arRect;
	}
}
