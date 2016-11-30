package checkers.GUI;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/************************************************
 * Creates Checker GUI for user game play.
 * 
 * @author Nate Benson, Kaye Suarez, Jake Young
 * @version 1.0 
 ************************************************/
public class CheckerGUI {
	
	/** JFrame for Checker GUI. */
	private static JFrame frame;
	
	/*********************************
	 * Returns JFrame for Checker GUI.
	 * @return frame 
	 *********************************/
	public static final JFrame getJFrame() {
		return frame;
	}
	
	/***************************************
	 * Main for creating checker GUI.
	 * @param args command arguments
	 ***************************************/
	public static void main(String[] args) {
		
		frame = new JFrame("Checkers");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CheckerPanel panel = new CheckerPanel();
		frame.getContentPane().add(panel, BorderLayout.EAST);
		frame.setAlwaysOnTop(true);
		frame.setJMenuBar(panel.getJMenuBar());
		frame.pack();
		frame.setVisible(true);
		
	}
}
