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
import java.util.Arrays;

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
	private ArrayList<Rechteck> arRechteck;
	private int counterCommand = 0;
	private int counterState = 0;
	
	
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
		JButton cmdStep = new JButton();
		cmdStep.setText("Step");
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
		tbZahlen.setColumns(20);
		pButtons.add(tbZahlen);
		setVisible(true);
		
		cmdAnzahl.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				anzahl = Integer.parseInt(tbAnzahl.getText());
				int pWidth = panel.getWidth();
				int pHeight = panel.getHeight();
				int widthRect = Math.round((pWidth - 10 * anzahl) / anzahl);
				
				Heapsort heapsort = new Heapsort(anzahl, false);
				arCommand = new ArrayList<Command>();
				arCommand = heapsort.sort();
				
				arRechteck = new ArrayList<Rechteck>();
				int tmpWidth = 0;
				String zahlenLBL = "";
				int[] arInt = arCommand.get(0).State.clone();
				Arrays.sort(arInt);
				int max = arInt[arInt.length-1];
				for(int j = 0; j < arCommand.get(0).State.length; j++){
					int zahl = arCommand.get(0).State[j];
					zahlenLBL += "" + zahl + "; ";
					Rechteck tmpRect = new Rechteck(tmpWidth, Math.round((pHeight - 10) - ((pHeight / max)*zahl)), widthRect, Math.round((pHeight / max)*zahl), zahl);
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
				
				
				class ThreadPaint extends Thread {
						@Override
				        public void run() {
				        	int pWidth = panel.getWidth();
							int pHeight = panel.getHeight();
							int widthRect = Math.round((pWidth - 10 * anzahl) / anzahl);   

							for(int i = 0; i < arCommand.size(); i++){
								boolean swap = false;
								int first = -1;
								int second = -1;
								if(arCommand.get(i).Type.toString().equals("Swap")){
									swap = true;
									first = arCommand.get(i).FirstIndex;
									second = arCommand.get(i).FirstIndex;
								}
								int tmpWidth = 0;
								arRechteck.clear();
								int[] arInt = arCommand.get(i).State.clone();
								Arrays.sort(arInt);
								int max = arInt[arInt.length-1];
								for(int j = 0; j < arCommand.get(i).State.length; j++){
									int zahl = arCommand.get(i).State[j];
									Rechteck tmpRect = new Rechteck(tmpWidth, Math.round(pHeight - 10 - ((pHeight / max)*zahl)), widthRect, Math.round((pHeight / max)*zahl), zahl);
									if(swap == true){
										System.out.println("SWAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAP");
										if(j == first){
											tmpRect.setColor(Color.red);
											System.out.println("ROT");
										}
										if(j == second){
											tmpRect.setColor(Color.red);
											System.out.println("GRAAAAAAAAAA");
										}
									}
									arRechteck.add(tmpRect);
									tmpWidth += widthRect + 10;
									counterState++;
									
								}
								panel.setArRect(arRechteck);
								
								repaint();
								System.out.println(arCommand.get(i));
								counterCommand++;
								try {
									sleep(500);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
				        }
				    
			    }
				
				ThreadPaint threadPaint = new ThreadPaint();
				threadPaint.start();
				
//				new Thread() {
//			        public void run() {
//			        	int pWidth = panel.getWidth();
//						int pHeight = panel.getHeight();
//						int widthRect = Math.round((pWidth - 10 * anzahl) / anzahl);   
//
//						for(int i = 0; i < arCommand.size(); i++){
//							int tmpWidth = 0;
//							arRechteck.clear();
//							int[] arInt = arCommand.get(i).State;
//							Arrays.sort(arInt);
//							int max = arInt[arInt.length-1];
//							for(int j = 0; j < arCommand.get(i).State.length; j++){
//								int zahl = arCommand.get(i).State[j];
//								Rechteck tmpRect = new Rechteck(tmpWidth, Math.round(pHeight - 10 - ((pHeight / max)*zahl)), widthRect, Math.round((pHeight / max)*zahl), zahl);
//								arRechteck.add(tmpRect);
//								tmpWidth += widthRect + 10;
//		//						repaint();
//		//						panel.repaint(new Rectangle(0,0,(int)pWidth, (int)pHeight));
//								counterState++;
//								
//							}
//							panel.setArRect(arRechteck);
//							
//							repaint();
//							counterCommand++;
//							try {
//								sleep(50);
//							} catch (InterruptedException e1) {
//								// TODO Auto-generated catch block
//								e1.printStackTrace();
//							}
//						}
//			        }
//			    }.run();
			}
		});
		
		cmdStop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		cmdStep.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		//int anzahl = Integer.parseInt(tbAnzahl.getText());
		
		
		
	}
	
	public ArrayList<Rechteck> getArRechteck(){
		return arRechteck;
	}
}
