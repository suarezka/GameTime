package game;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;

public class GameGUI {
	
	public static JFrame menu;
	
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
