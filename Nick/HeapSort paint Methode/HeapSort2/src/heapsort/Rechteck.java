package heapsort;
import java.awt.Color;
import java.awt.geom.Rectangle2D;


public class Rechteck extends Rectangle2D{
	
	private int x;
	private int y;
	private int width;
	private int height;
	private int value;
	private Color color;
	
	
	public Rechteck(int x, int y, int w, int h, int value){
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.value = value;
		color = Color.black;
	}
	@Override
	public void setRect(double x, double y, double w, double h) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int outcode(double x, double y) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Rectangle2D createIntersection(Rectangle2D r) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle2D createUnion(Rectangle2D r) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return height;
	}
	
	public double getValue() {
		// TODO Auto-generated method stub
		return value;
	}
	public void setColor(Color color){
		this.color = color;
	}
	
	public Color getColor(){
		return color;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String toString(){
		return "value: " + value;
		
	}

}
