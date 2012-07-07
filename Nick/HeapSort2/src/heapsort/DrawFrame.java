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
import javax.swing.JSlider;
import javax.swing.JTextField;

public class DrawFrame extends JFrame {
	private JTextField tbAnzahl;
	private DrawPanel panel;
	private DrawPanel panel2;
	private JTextField tbZahlen;
	private int anzahl;
	private ArrayList<Command> arCommand;
	private ArrayList<Rechteck> arRechteck;
	private int counterCommand = 0;
	private int counterState = 0;
	private ThreadPaint threadPaint = new ThreadPaint();
	private int geschwindigkeit = 50;

	public static void main(String[] args) {
		DrawFrame frame = new DrawFrame();
	}

	public DrawFrame() {
		setTitle("HeapSort");
		setSize(700, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel = new DrawPanel(200, 150);
		panel.setBackground(Color.white);
		panel2 = new DrawPanel(panel.getWidth(), panel.getHeight());
		panel2.setBackground(Color.black);
		setLayout(new BorderLayout());
		JPanel pButtons = new JPanel();
		pButtons.setLayout(new FlowLayout(FlowLayout.LEFT));
		tbAnzahl = new JTextField();
		tbAnzahl.setColumns(10);
		
		JSlider sliderGeschwindigkeit = new JSlider(10, 10000, 500);
		
		JPanel panelBG = new JPanel();
		panelBG.setLayout(new BorderLayout());
		
		
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
		pButtons.add(sliderGeschwindigkeit);
		geschwindigkeit = sliderGeschwindigkeit.getValue();
		tbZahlen = new JTextField();
		tbZahlen.setColumns(20);
		
		add(pButtons, BorderLayout.PAGE_START);
		add(panel, BorderLayout.CENTER);
//		add(panel2, BorderLayout.CENTER);
		
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
				int max = arInt[arInt.length - 1];
				for (int j = 0; j < arCommand.get(0).State.length; j++) {
					int zahl = arCommand.get(0).State[j];
					zahlenLBL += "" + zahl + "; ";
					Rechteck tmpRect = new Rechteck(tmpWidth, Math.round((pHeight - 10) - (((pHeight - 10) / max) * zahl)),
													widthRect, Math.round((pHeight / max) * zahl), zahl);
					arRechteck.add(tmpRect);
					tmpWidth += widthRect + 10;
				}

				tbZahlen.setText(zahlenLBL);
				panel.setArRect(arRechteck);
				panel.repaint();

			}
		});

		cmdStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				threadPaint.start();
			}
		});

		cmdStop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				threadPaint.interrupt();
			}
		});

		cmdStep.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		// int anzahl = Integer.parseInt(tbAnzahl.getText());

	}

	public void drawState(int i, int tmpWidth, int pHeight,int widthRect, int max, boolean bool) {

		boolean swap = false;
		boolean comparisonParent = false;
		boolean comparisonFlat = false;
		boolean finish = false;
		int first = -1;
		int second = -1;
		arRechteck.clear();
		for (int j = 0; j < arCommand.get(i).State.length; j++) {
			
			if(bool == true){
				if(!(arCommand.get(i).Type.toString().equals("End"))){
					if (arCommand.get(i + 1).Type.toString().equals("Swap")) {
						swap = true;
						first = arCommand.get(i + 1).FirstIndex;
						second = arCommand.get(i + 1).SecondIndex;
					}
					if (arCommand.get(i + 1).Type.toString().equals("ComparisonParent")) {
						comparisonParent = true;
						first = arCommand.get(i + 1).FirstIndex;
						second = arCommand.get(i + 1).SecondIndex;
					}
					if (arCommand.get(i + 1).Type.toString().equals("ComparisonFlat")) {
						comparisonFlat = true;
						first = arCommand.get(i + 1).FirstIndex;
						second = arCommand.get(i + 1).SecondIndex;
					}
					if (arCommand.get(i + 1).Type.toString().equals("Finish")) {
						finish = true;
						first = arCommand.get(i + 1).FirstIndex;
						second = arCommand.get(i + 1).SecondIndex;
					}
				}
			}
			
			int zahl = arCommand.get(i).State[j];
			Rechteck tmpRect = new Rechteck(tmpWidth, Math.round(pHeight - 10 - (((pHeight - 10) / max) * zahl)), widthRect,
					Math.round((pHeight / max) * zahl), zahl);
			if(bool == true){
				if (swap == true) {
					if (j == first) {
						tmpRect.setColor(Color.red);
					}
					if (j == second) {
						tmpRect.setColor(Color.red);
					}
				}
				if (comparisonParent == true) {
					if (j == first) {
						tmpRect.setColor(Color.orange);
					}
					if (j == second) {
						tmpRect.setColor(Color.blue);
					}
				}
				if (comparisonFlat == true) {
					if (j == first) {
						tmpRect.setColor(Color.blue);
					}
					if (j == second) {
						tmpRect.setColor(Color.blue);
					}
				}
				if (finish == true) {
					if (j == first) {
						tmpRect.setColor(Color.magenta);
					}
					if (j == second) {
						tmpRect.setColor(Color.magenta);
					}
				}
			}else{
				tmpRect.setColor(Color.black);
			}
			
			arRechteck.add(tmpRect);
			tmpWidth += widthRect + 10;
			counterState++;

		}

	}

	public ArrayList<Rechteck> getArRechteck() {
		return arRechteck;
	}

	class ThreadPaint extends Thread {
		@Override
		public void run() {
			int pWidth = panel.getWidth();
			int pHeight = panel.getHeight();
			int widthRect = Math.round((pWidth - 10 * anzahl) / anzahl);
			boolean bool;

			for (int i = 0; i < arCommand.size(); i++) {
				
				bool = false;
				int tmpWidth = 0;
				arRechteck.clear();
				int[] arInt = arCommand.get(i).State.clone();
				Arrays.sort(arInt);
				int max = arInt[arInt.length - 1];

				drawState(i, tmpWidth, pHeight, widthRect, max, bool);
				panel.setArRect(arRechteck);
				panel.repaint();
				try {
					threadPaint.sleep(geschwindigkeit);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				bool = true;
				drawState(i, tmpWidth, pHeight, widthRect, max, bool);
				panel.setArRect(arRechteck);
				panel.repaint();
				try {
					threadPaint.sleep(geschwindigkeit);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				System.out.println(arCommand.get(i));
				counterCommand++;
				

			}

		}
	}
}
