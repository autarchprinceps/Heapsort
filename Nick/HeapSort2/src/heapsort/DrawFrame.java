package heapsort;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
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
				double anzahl = Integer.parseInt(tbAnzahl.getText());
				double pWidth = panel.getWidth();
				double pHeight = panel.getHeight();
				double widthRect = Math.round((pWidth - 10 * anzahl) / anzahl);
				ArrayList<Rectangle2D> arRechteck = new ArrayList<Rectangle2D>();
				double tmpWidth = 0;
				String zahlenLBL = "";
				for(int i = 0; i < anzahl; i++){
					double zahl = (Math.random() * 100 + 1);
					zahlenLBL += "" + (int)Math.round(zahl) + "; ";
					Rechteck tmpRect = new Rechteck(tmpWidth, pHeight - (zahl/100 * pHeight), widthRect, zahl/100 * pHeight, zahl);
					arRechteck.add(tmpRect);
					tmpWidth += widthRect + 10;
				}
				tbZahlen.setText(zahlenLBL);
				panel.setArRect(arRechteck);
				
				repaint();
			}
		});
		tbZahlen.setColumns(20);
		pButtons.add(tbZahlen);
		setVisible(true);
		
		//int anzahl = Integer.parseInt(tbAnzahl.getText());
		
		
		
	}
}
