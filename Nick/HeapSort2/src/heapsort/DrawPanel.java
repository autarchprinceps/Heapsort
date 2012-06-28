package heapsort;
import java.awt.Color;
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
	
	@Override
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.white);
		g2.fillRect(0, 0, 700, 600);
		g2.setColor(Color.black);
		for(int i = 0; i < arRect.size(); i++){
			g2.draw(arRect.get(i));
			
		}
		System.out.println(arRect.size());
//		try {
//			Thread.sleep(100);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public void setArRect(ArrayList<Rectangle2D> arRect){
//		this.arRect.clear();
		
		this.arRect = arRect;
		System.out.println(arRect.size());
	}
}
