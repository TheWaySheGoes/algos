package main;

import javax.swing.JButton;
import javax.swing.JPanel;

public class InsertionSort {
	private static long startTime;
	
	public static void insertionSort(int[] sampleArray, int[] timeDx,JPanel displayPanel,JButton btnInsertionSort) {
		new Thread(new Runnable() {
			public void run() {
				startTime=System.currentTimeMillis();
				for (int i = 1; i < sampleArray.length; i++) {
					long startTime = System.currentTimeMillis();
					for (int j = i; j > 0; j--) {
						if(sampleArray[j]<sampleArray[j-1]) {
							int temp = sampleArray[j-1];
							sampleArray[j-1]=sampleArray[j];
							sampleArray[j]=temp;
							displayPanel.repaint();
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				//	for (int j = i; j < timeDx.length; j++) {
						
						timeDx[i]+=(System.currentTimeMillis()-startTime)*-1/10;
						System.out.println((System.currentTimeMillis()-startTime)*-1/10);	
				//	}
				}
				btnInsertionSort.setText(btnInsertionSort.getText()+(System.currentTimeMillis()-startTime));	
			}
		}).start();	
	}
	
}
