package game;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class ChessGUI {
	
	public static JFrame frame;
	
	public static void main(String[] args) {
		frame = new JFrame("Chess Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ChessPanel panel = new ChessPanel();
		frame.getContentPane().add(panel, BorderLayout.EAST);
		frame.setJMenuBar(panel.topMenu);
		frame.pack();
		frame.setVisible(true);
	}
	
}

