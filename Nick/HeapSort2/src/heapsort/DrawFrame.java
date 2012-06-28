package heapsort;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class DrawFrame extends JFrame{
	private JTextField tbAnzahl;
	private DrawPanel panel;
	private JTextField tbZahlen;
	private int anzahl;
	private ArrayList<Command> arCommand;
	private ArrayList<Rectangle2D> arRechteck;
	
	
	public static void main(String[] args){
		DrawFrame frame = new DrawFrame();
	}
	
	public DrawFrame(){
		setTitle("HeapSort");
		setSize(700, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel = new DrawPanel(400, 300);
		panel.setBackground(Color.white);
		setLayout(new BorderLayout());
		JPanel pButtons = new JPanel();
		pButtons.setLayout(new FlowLayout(FlowLayout.LEFT));
		tbAnzahl = new JTextField();
		tbAnzahl.setColumns(10);
		JButton cmdAnzahl = new JButton();
		cmdAnzahl.setText("Anzahl setzen");
		JButton cmdStart = new JButton();
		cmdStart.setText("Start");
		JButton cmdStop = new JButton();
		cmdStop.setText("Stop");
		JButton cmdReset = new JButton();
		cmdReset.setText("Reset");
		pButtons.add(tbAnzahl);
		pButtons.add(cmdAnzahl);
		pButtons.add(cmdStart);
		pButtons.add(cmdStop);
		pButtons.add(cmdReset);
		tbZahlen = new JTextField();
		add(pButtons, BorderLayout.PAGE_START);
		add(panel, BorderLayout.CENTER);
		
		cmdAnzahl.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				anzahl = Integer.parseInt(tbAnzahl.getText());
				double pWidth = panel.getWidth();
				double pHeight = panel.getHeight();
				double widthRect = Math.round((pWidth - 10 * anzahl) / anzahl);
				
				Heapsort heapsort = new Heapsort(anzahl, false);
				arCommand = new ArrayList<Command>();
				arCommand = heapsort.sort();
				
				arRechteck = new ArrayList<Rectangle2D>();
				double tmpWidth = 0;
				String zahlenLBL = "";
				for(int j = 0; j < arCommand.get(0).State.length; j++){
					int zahl = arCommand.get(0).State[j];
					zahlenLBL += "" + zahl + "; ";
					Rechteck tmpRect = new Rechteck(tmpWidth, pHeight - (zahl/100 * pHeight), widthRect, zahl/100 * pHeight, zahl);
					arRechteck.add(tmpRect);
					tmpWidth += widthRect + 10;
					
				}
					
				tbZahlen.setText(zahlenLBL);
				panel.setArRect(arRechteck);
				repaint();
				
				
			}
		});
		
		
		
cmdStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				double pWidth = panel.getWidth();
				double pHeight = panel.getHeight();
				double widthRect = Math.round((pWidth - 10 * anzahl) / anzahl);
				for(int i = 0; i < arCommand.size(); i++){
					int tmpWidth = 0;
					arRechteck.clear();
					for(int j = 0; j < arCommand.get(i).State.length; j++){
						double zahl = arCommand.get(i).State[j];
						Rechteck tmpRect = new Rechteck(tmpWidth, pHeight - (zahl/100 * pHeight), widthRect, zahl/100 * pHeight, zahl);
						arRechteck.add(tmpRect);
						tmpWidth += widthRect + 10;
//						repaint();
//						panel.repaint(new Rectangle(0,0,(int)pWidth, (int)pHeight));
						
					}
					panel.setArRect(arRechteck);
					
					repaint();
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		tbZahlen.setColumns(20);
		pButtons.add(tbZahlen);
		setVisible(true);
		
		//int anzahl = Integer.parseInt(tbAnzahl.getText());
		
		
		
	}
}
