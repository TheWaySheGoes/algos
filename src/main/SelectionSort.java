package main;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SelectionSort {
	private static long startTime;
	
	public static void selectionSort(int[] sampleArray, int[] timeDx,JPanel displayPanel,JButton btnSelectionSort) {
		new Thread(new Runnable() {
			public void run() {
				startTime=System.currentTimeMillis();
				for (int i = 0; i < sampleArray.length; i++) {
					long startTime = System.currentTimeMillis();
					for (int j = i+1; j < sampleArray.length-1; j++) {
						if(sampleArray[j]<sampleArray[i]) {
							int min = sampleArray[i];
							sampleArray[i]=sampleArray[j];
							sampleArray[j]=min;
							
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
			btnSelectionSort.setText(btnSelectionSort.getText()+(System.currentTimeMillis()-startTime));	
			}
		}).start();	
	}
}
