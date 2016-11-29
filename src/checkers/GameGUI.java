package checkers;

import java.awt.BorderLayout;
import javax.swing.JFrame;


/**********************************************************************
 * GameGUI is the opening game menu.
 * 
 * @author Nate Benson, Kaye Suarez, Jake Young
 * @version 1.0 
 **********************************************************************/
public class GameGUI {
	
	/** JFrame menu value. */
	public static JFrame menu;
	
	/*********************************
	 * Main method to run GUI.
	 * @param args string args
	 *********************************/
	public static void main(String[] args) {
		menu = new JFrame("Main Menu");
		MenuPanel panel = new MenuPanel();
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.getContentPane().add(panel, BorderLayout.CENTER);
		//menu.setLocationRelativeTo(null);
		menu.setResizable(false);
		menu.setAlwaysOnTop(true);
		menu.pack();
		menu.setVisible(true);
	}
}
