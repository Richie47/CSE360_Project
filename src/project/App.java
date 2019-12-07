
/**
 *  implements the main for the function and adds the view
 *  
 *  @author Shashank Ginjpalli
 *  @author Wyatt Turner
 *  @author Richard McKinnon
 *  @author Rachel Ware
 *  @version 1.0
 *  
 *  @team 14
 * 
 *  Class ID: 70641
 *  Final Project
 */

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
