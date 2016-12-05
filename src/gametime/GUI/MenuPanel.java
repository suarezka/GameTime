package gametime.GUI;

import chess.GUI.OurGUI;

import javax.swing.JPanel;

import checkers.GUI.CheckerGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**********************************************************************
 * MenuPanel class extends JPanel to create the main menu GUI.
 * 
 * @author Nate Benson, Kaye Suarez, Jake Young
 * @version 2.0 
 **********************************************************************/
public class MenuPanel extends JPanel {
	
	/** JButton values. */
	private JButton checkerButton, chessButton, quit;
	
	/** JLabel checker value. */
	private JLabel checkers, chess, title, menu;

	/** Background Image. */
	private BufferedImage background;
	
	/** Images. */
	private Image checkerLogo, chessLogo, settingsLogo;
	
	/** Image Icons. */
	private ImageIcon crl, cl, settings;
	//private ImageIcon bg;
	
	/** Chess logo size. */
	public static final int CHESS_LOGO = 125;
	
	/** Checker logo size. */
	public static final int CHECK_LOGO = 125;
	
	
	/***************************************
	 * Construct Panel for Main GUI.
	 ***************************************/
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
		
		chessButton = new JButton(cl);
		chessButton.setBackground(Color.WHITE);
		chessButton.setBounds(70, 100, 200, 150);
		p.add(chessButton);
		
		chess = new JLabel("Chess!");
		chess.setFont(new Font("Serif", Font.BOLD, 55));
		chess.setForeground(Color.WHITE);
		chess.setBounds(90, 20, 170, 100);
		//chess.setLocation(25, 25);
		p.add(chess);
		
		checkerButton = new JButton(crl);
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
	protected final void paintComponent(final Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
	}
	
	/************************************************
	 * Method to load and add images for main menu.
	 ************************************************/
	private void addImages() {
		try {
			
			chessLogo = ImageIO.read(
					getClass().getResource("/Pictures/blackPawn.png"));
			Image a = chessLogo.getScaledInstance(
									CHESS_LOGO, CHESS_LOGO, CHESS_LOGO);
			cl = new ImageIcon(a);
			
			checkerLogo = ImageIO.read(
				getClass().getResource("/Pictures/beer-cap-icon-67249.png"));
			Image b = checkerLogo.getScaledInstance(
									CHECK_LOGO, CHECK_LOGO, CHECK_LOGO);
			crl = new ImageIcon(b);
			
//			settingsLogo = ImageIO.read(
//				getClass().getResource("/Pictures/settings.png"));
//			Image c = settingsLogo.getScaledInstance(50, 50, 50);
//			settings = new ImageIcon(c);
			
			background = ImageIO.read(
					getClass().getResource("/Pictures/Background.png"));
			//bg = new ImageIcon(background);
			//bg.drawImage(background, 250, 250, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** Mouse Listener value. */
	private MouseListener listener = new MouseListener() {

		@Override
		public void mouseClicked(final MouseEvent a) {
			if (a.getSource() == chessButton) {
				String[] args = {};
				OurGUI.main(args);
				setVisible(false);
				GameGUI.getJFrame().setVisible(false);
			}
			if (a.getSource() == checkerButton) {
				String[] args = {};
				CheckerGUI.main(args);
				setVisible(false);
				GameGUI.getJFrame().setVisible(false);
			}
		}

		@Override
		public void mouseEntered(final MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(final MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(final MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(final MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	};
	
	/*******************************
	 * Return MouseListener object.
	 * @return listener
	 *******************************/
	public final MouseListener getMouseListener() {
		return listener;
	}
	
}
