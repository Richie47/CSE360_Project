package project;

import java.awt.EventQueue;

public class App {
	public static void main(String[] args) {
		/**Run this file to open the GUI**/
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Design app = new Design();
					app.appMain.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
