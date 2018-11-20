package main;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ShellSort {
	private static long startTime;
	
	public static void shellSort(int[] sampleArray, int[] timeDx, JPanel displayPanel,JButton btnShellSort) {
		new Thread(new Runnable() {
			public void run() {
				startTime=System.currentTimeMillis();
				int h = 1;
				
				while (h < sampleArray.length / 3) {
					h = h * 3 + 1;
				}
				while (h >= 1) {
					for (int i = h; i < sampleArray.length; i++) {
						long startTime = System.currentTimeMillis();
						for (int j = i; j > 0; j--) {
							if (sampleArray[j] < sampleArray[j - 1]) {
								int temp = sampleArray[j - 1];
								sampleArray[j - 1] = sampleArray[j];
								sampleArray[j] = temp;
								displayPanel.repaint();
								try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
						// for (int j = i; j < timeDx.length; j++) {

						timeDx[i] += (System.currentTimeMillis() - startTime) * -1 / 10;
						System.out.println((System.currentTimeMillis() - startTime) * -1 / 10);
						// }
					}
					h=h/3;
				}
				btnShellSort.setText(btnShellSort.getText()+(System.currentTimeMillis()-startTime));	
			}
		}).start();
	}

}
