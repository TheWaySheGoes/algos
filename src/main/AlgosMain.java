package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AlgosMain extends JPanel implements ActionListener {
	int[] sampleArray;
	int[] timeDx;
	int sampleSize;
	int rndFaktor;
	boolean drawTime = false;
	JLabel lblTime = new JLabel("TIME");
	JButton btnReset = new JButton("RESET");
	JTextField txtSampleSize = new JTextField("Sample size: ");
	JTextField txtRndFaktor = new JTextField("Rand. faktor: ");

	JCheckBox chkSampleSize = new JCheckBox();
	JButton btnSelectionSort = new JButton("Selection: ");
	JButton btnInsertionSort = new JButton("Insertion: ");
	JButton btnShellSort = new JButton("Shell: ");

	public AlgosMain() {
		this.setLayout(null);
		this.setBounds(5, 5, 800, 550);
		sampleSize = this.getWidth();
		rndFaktor = this.getHeight() - 100;
		this.btnReset.setBounds(5, 5, 100, 30);
		this.add(btnReset);
		btnReset.addActionListener(this);
		this.chkSampleSize.setBounds(5, 35, 25, 25);
		this.add(chkSampleSize);
		this.chkSampleSize.addActionListener(this);
		this.txtSampleSize.setBounds(35, 35, 100, 30);
		this.add(txtSampleSize);
		txtSampleSize.setText("" + sampleSize);
		this.txtRndFaktor.setBounds(145, 35, 100, 30);
		this.add(txtRndFaktor);
		txtRndFaktor.setText("" + rndFaktor);
		this.lblTime.setBounds(250, 35, 100, 30);
		this.lblTime.setForeground(Color.RED);
		this.add(lblTime);
		

		this.btnSelectionSort.setBounds(105, 5, 150, 30);
		this.add(btnSelectionSort);
		btnSelectionSort.addActionListener(this);
		this.btnInsertionSort.setBounds(255, 5, 150, 30);
		this.add(btnInsertionSort);
		btnInsertionSort.addActionListener(this);
		this.btnShellSort.setBounds(405, 5, 150, 30);
		this.add(btnShellSort);
		btnShellSort.addActionListener(this);

		this.setBackground(Color.BLACK);
		graphicSetup();
	}

	private void graphicSetup() {
		drawTime = false;
		makeRandomArray(sampleSize, 0, rndFaktor);
		makeTimeDx(sampleSize, this.getHeight() - 50);
		repaint();
		// repaintBars();
		// selectionSort();
	}

	private void makeTimeDx(int size, int val) {
		timeDx = new int[size];
		for (int i = 0; i < size; i++) {
			timeDx[i] = val;
		}
	}

	private void makeRandomArray(int size, int min, int max) {
		sampleArray = new int[size];
		Random rndm = new Random();
		for (int i = 0; i < size; i++) {
			sampleArray[i] = rndm.nextInt(max);
		}
	}

	// n^2
	private void selectionSort() {
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < sampleArray.length; i++) {
					int min = sampleArray[i];
					long startTime = System.currentTimeMillis();
					for (int j = i + 1; j < sampleArray.length - 1; j++) {
						if (sampleArray[j] < sampleArray[i]) {
							sampleArray[i] = sampleArray[j];
							sampleArray[j] = min;

							repaint();
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					for (int j = i; j < timeDx.length; j++) {

						timeDx[j] += (System.currentTimeMillis() - startTime) * -1 / 10;
						System.out.println((System.currentTimeMillis() - startTime) * -1 / 10);
					}
				}
				drawTime = true;
				repaint();
			}
		}).start();
	}

	public void paint(Graphics g) {
		super.paint(g);

		int x = 5;
		for (int i = 0; i < sampleArray.length; i++) {
			g.setColor(Color.white);
			g.drawLine(x, this.getHeight(), x, this.getHeight() - sampleArray[i]);
			x += this.getWidth() / sampleArray.length;
		}

		if (drawTime) {
			x = 5;
			for (int i = 1; i < timeDx.length; i++) {
				g.setColor(Color.RED);
				g.drawLine(x, timeDx[i - 1], x + this.getWidth() / sampleArray.length, timeDx[i]);
				x += this.getWidth() / sampleArray.length;
			}

		}

	}

	public void update(Graphics g) {
		paint(g);
	}

	public static void main(String[] args) {
		AlgosMain al = new AlgosMain();
		JFrame frame = new JFrame();
		frame.setBackground(Color.BLACK);
		frame.setLayout(null);
		frame.setBounds(0, 0, 810, 620);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Pong by Lukas Kurasinski");
		frame.add(al);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.addKeyListener(null);
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		if (a.getSource() == btnReset) {
			if (chkSampleSize.isSelected()) {
				try {
					rndFaktor = Integer.parseInt(this.txtRndFaktor.getText());
					sampleSize = Integer.parseInt(this.txtSampleSize.getText());
				} catch (NumberFormatException e) {
					System.err.println(e);
				}
				;
			} else {
				this.sampleSize = this.getWidth();
				this.rndFaktor = this.getHeight() - 100;
			}
			graphicSetup();
		}
		if (a.getSource() == btnSelectionSort) {
			// selectionSort();
			this.btnSelectionSort.setText("Selection: ");
			SelectionSort.selectionSort(sampleArray, timeDx, this, btnSelectionSort);
			drawTime = true;
			repaint();
		}
		if (a.getSource() == btnInsertionSort) {
			this.btnInsertionSort.setText("Insertion: ");
			InsertionSort.insertionSort(sampleArray, timeDx, this,btnInsertionSort);
			drawTime = true;
			repaint();
		}
		if (a.getSource() == btnShellSort) {
			this.btnShellSort.setText("Shell: ");
			ShellSort.shellSort(sampleArray, timeDx, this,btnShellSort);
			drawTime = true;
			repaint();
		}
		
		
		
	}

}
