package checkers;

import chess.GUI.*;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class MenuPanel extends JPanel {
	
	private JButton checkerButton;
	private JButton chessButton;
	private JButton settingsButton;
	private JLabel checkers;
	private JLabel chess;
	private JButton quit;
	private JLabel title;
	private JLabel menu;
	private BufferedImage background;
	
	private Image checkerLogo;
	private Image chessLogo;
	private Image settingsLogo;
	private ImageIcon CRL;
	private ImageIcon CL;
	private ImageIcon settings;
	//private ImageIcon bg;
	
	public MenuPanel() {
		
		addImages();
		setPreferredSize(new Dimension(700, 500));
		
		title = new JLabel("CHESSECKERS!");
		title.setFont(new Font("Serif", Font.BOLD, 90));
		title.setForeground(Color.WHITE);
		add(title);
		
		menu = new JLabel("Please select the game you want to play.");
		menu.setFont(new Font("Serif", Font.BOLD, 40));
		menu.setForeground(Color.WHITE);
		add(menu);
		
		JPanel p = new JPanel();
		p.setOpaque(false);
		p.setPreferredSize(new Dimension(700, 250));
		p.setLayout(null);
		add(p);
		
		settingsButton = new JButton(settings);
		settingsButton.setBackground(Color.BLUE);
		settingsButton.setBounds(50, 200, 50, 50);
		//p.add(settingsButton);
		
		chessButton = new JButton(CL);
		chessButton.setBackground(Color.WHITE);
		chessButton.setBounds(70, 100, 200, 150);
		p.add(chessButton);
		
		chess = new JLabel("Chess!");
		chess.setFont(new Font("Serif", Font.BOLD, 55));
		chess.setForeground(Color.WHITE);
		chess.setBounds(90, 20, 170, 100);
		//chess.setLocation(25, 25);
		p.add(chess);
		
		checkerButton = new JButton(CRL);
		checkerButton.setBackground(Color.WHITE);
		checkerButton.setBounds(420, 100, 200, 150);
		p.add(checkerButton);
		
		checkers = new JLabel("Checkers!");
		checkers.setFont(new Font("Serif", Font.BOLD, 50));
		checkers.setForeground(Color.WHITE);
		checkers.setBounds(420, 20, 220, 100);
		p.add(checkers);
		
		chessButton.addMouseListener(listener);
		checkerButton.addMouseListener(listener);
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
	}
	
	private void addImages() {
		try {
			
			chessLogo = ImageIO.read(getClass().getResource("/Pictures/blackPawn.png"));
			Image a = chessLogo.getScaledInstance(125, 125, 125);
			CL = new ImageIcon(a);
			
			checkerLogo = ImageIO.read(getClass().getResource("/Pictures/beer-cap-icon-67249.png"));
			Image b = checkerLogo.getScaledInstance(150, 150, 150);
			CRL = new ImageIcon(b);
			
//			settingsLogo = ImageIO.read(getClass().getResource("/Pictures/settings.png"));
//			Image c = settingsLogo.getScaledInstance(50, 50, 50);
//			settings = new ImageIcon(c);
			
			background = ImageIO.read(getClass().getResource("/Pictures/Background.png"));
			//bg = new ImageIcon(background);
			//bg.drawImage(background, 250, 250, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	MouseListener listener = new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent a) {
			if(a.getSource() == chessButton) {
				OurGUI.main(null);
				setVisible(false);
				GameGUI.menu.setVisible(false);
			}
			if(a.getSource() == checkerButton) {
				CheckerGUI.main(null);
				setVisible(false);
				GameGUI.menu.setVisible(false);
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	};
	
}
