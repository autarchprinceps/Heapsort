package heapsort;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 *
 * @author Patrick Robinson, Nick Herrmannsdörfer, Erwin Stamm
 */
public class ArrayView extends javax.swing.JPanel {
    /**
     * Creates new form ArrayView
     */
    public ArrayView() {
        initComponents();
        this.setDoubleBuffered(true);
    }

	private int height;
	private int width;
	private boolean swap = false;
	private boolean comparisonParent = false;
	private boolean comparisonFlat = false;
	private boolean finish = false;
	private boolean end = false;
	private int counterFinish = 0;
	private int firstIndex = -1;
	private int secondIndex = -1;
	private boolean firstLoop = false;
	private int[] arState;
	private int max;
	private boolean bool;
	private Color firstC;
	private Color secondC;

    public void drawState(int i, Command command, Command commandNext, int widthRect, int max, boolean bool) {
		this.max = max;
		this.bool = bool;
		firstLoop = false;
		for (int j = 0; j < command.State.length; j++) {
			
			if(bool == true){
				if(firstLoop == false){
					if(!(command.Type.toString().equals("End"))){
						if (commandNext.Type.toString().equals("Swap")) {
							swap = true;
							firstIndex = commandNext.FirstIndex;
							secondIndex = commandNext.SecondIndex;
							firstC = Color.red;
							secondC = Color.red;
							firstLoop = true;
						}
						if (commandNext.Type.toString().equals("ComparisonParent")) {
							comparisonParent = true;
							firstIndex = commandNext.FirstIndex;
							secondIndex = commandNext.SecondIndex;
							firstC = Color.orange;
							secondC = Color.blue;
							firstLoop = true;
						}
						if (commandNext.Type.toString().equals("ComparisonFlat")) {
							comparisonFlat = true;
							firstIndex = commandNext.FirstIndex;
							secondIndex = commandNext.SecondIndex;
							firstC = Color.blue;
							secondC = Color.blue;
							firstLoop = true;
						}
						if (commandNext.Type.toString().equals("Finish")) {
							finish = true;
							firstIndex = commandNext.FirstIndex;
							secondIndex = commandNext.SecondIndex;
							firstC = Color.magenta;
							secondC = Color.magenta;
							firstLoop = true;
							counterFinish++;
						}
					}else{
						end = true;
						firstIndex = -1;
						secondIndex = -1;
						firstC = Color.black;
						secondC = Color.black;
					}
				}
			}
		}
		

	}

	@Override
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(Color.white);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		int tmpWidth = 0;
		

		
		
		try{
//			System.out.println(arState.length);
			for(int i = 0; i < arState.length; i++){
				System.out.println(firstIndex);
				if(bool == true){
					if(i == firstIndex){
						g2.setColor(firstC);
					}else if(i == secondIndex){
						g2.setColor(secondC);
					}else{
						g2.setColor(Color.black);
					}
				}else{
					g2.setColor(Color.black);
				}
				
				
				int widthRect = (int) Math.ceil(((this.getWidth() - 10 * arState.length) / arState.length));
				int zahl = arState[i];
//				g2.setColor(Color.black);
				if(g2.getColor() != Color.black){
					g2.fillRect(tmpWidth, Math.round(this.getHeight() - 10 - (((this.getHeight() - 10) / max) * zahl)), widthRect,
							(int)Math.ceil((this.getHeight() / max) * zahl));
				}else{
					g2.drawRect(tmpWidth, Math.round(this.getHeight() - 10 - (((this.getHeight() - 10) / max) * zahl)), widthRect,
							(int)Math.ceil((this.getHeight() / max) * zahl));
				}
				
				tmpWidth += widthRect + 10;
			}
		}catch(NullPointerException e){
			
		}
		
		
		//g2.setColor(Color.black);
//		for(int i = 0; i < arRect.size(); i++){
//			g2.setColor(arRect.get(i).getColor());
//			if(!(g2.getColor() == Color.black)){
//				g2.fill(arRect.get(i));
//			}else{
//				g2.draw(arRect.get(i));
//			}
//			System.out.println(g2.getColor());
			
//		}
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
