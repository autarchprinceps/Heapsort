package heapsort;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;


public class DrawPanel extends JPanel{
	private int height;
	private int width;
	private ArrayList<Rechteck> arRect = new ArrayList<Rechteck>();
	
	public DrawPanel(int width, int height){
		this.width = width;
		this.height = height;
		this.setDoubleBuffered(true);
	}
	
	@Override
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(Color.white);
		g2.fillRect(0, 0, 700, 600);
		//g2.setColor(Color.black);
		for(int i = 0; i < arRect.size(); i++){
			g2.setColor(arRect.get(i).getColor());
			if(!(g2.getColor() == Color.black)){
				g2.fill(arRect.get(i));
			}else{
				g2.draw(arRect.get(i));
			}
//			System.out.println(g2.getColor());
			
		}
//		System.out.println(arRect.size());
//		try {
//			Thread.sleep(100);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public void setArRect(ArrayList<Rechteck> arRect){
//		this.arRect.clear();
		
		this.arRect = arRect;
//		System.out.println(arRect.size());
	}
}
