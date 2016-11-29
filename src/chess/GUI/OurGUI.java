package chess.GUI;

import gvprojects.chess.model.IChessModel;
import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import project3.ChessModel;

/************************************************************
 * CIS 163-07
 * Chess Project
 * GUI For the chess game
 *
 * @author Kaye Suarez
 * @author DaiLynn Dietz
 * @version Mar 18, 2014
 ************************************************************/
public class OurGUI implements ActionListener {
	
	private JFrame frame;
	private JPanel panel;
	private ButtonListener bl;
	private JMenuBar menus;
	private JMenu options;
	private JMenuItem newGame, quit;
	private JButton[][] chessBoard;
	private JLabel status;
	private IChessModel game;
	private int firstR, firstC, secondR, secondC;
	
	private static final int IMAGE_SIZE = 64;
	
	//Images for black pieces
	private ImageIcon b_Rook = loadIcon("images\\b_rook.png"),
			b_Knight = loadIcon("images\\b_knight.png"),
			b_Bish = loadIcon("images\\b_bish.png"),
			b_Queen = loadIcon("images\\b_queen.png"),
			b_King = loadIcon("images\\b_king.png"),
			b_Pawn = loadIcon("images\\b_pawn.png");
	
	//Images for white pieces
	private ImageIcon w_Rook = loadIcon("images\\w_rook.png"),
			w_Knight = loadIcon("images\\w_knight.png"), 
			w_Bish = loadIcon("images\\w_bish.png"),
			w_Queen = loadIcon("images\\w_queen.png"),
			w_King = loadIcon("images\\w_king.png"),
			w_Pawn = loadIcon("images\\w_pawn.png");
	
	
	/************************************************************
	 * GUI constructor
	 ************************************************************/
	public OurGUI() {
		
		firstR = firstC = -1;
		frame = new JFrame("CIS 163 CHESS");
		panel = new JPanel();
		bl = new ButtonListener();
		
		panel.setLayout(new GridLayout(8,8));
		chessBoard = new JButton[8][8];
		
		setBoard(chessBoard);
		
		frame.add(panel, BorderLayout.CENTER);
		status = new JLabel("Welcome! Let's play CHESS! White moves first.");
		frame.add(status, BorderLayout.SOUTH);
		menuSetup();
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	/****************************************************************
	 * Set pieces in the starting positions
	 * 
	 * @param board 2D JButton array
	 ****************************************************************/
	public void setBoard(JButton[][] board) {
		
		//Clear panel to reset it
		panel.removeAll();
		
		//Create JButtons for each square of the board
		for (int k = 0; k < chessBoard.length; k++) {
			for (int m = 0; m < chessBoard[k].length; m++) {
				
				//Set up the JButtons
				chessBoard[k][m] = new JButton();
				chessBoard[k][m].setPreferredSize(
						new Dimension(IMAGE_SIZE + 5, IMAGE_SIZE + 5));
				chessBoard[k][m].addActionListener(this);
				
				//Create alternating background colors
				if ((k + m) % 2 == 0) {
					chessBoard[k][m].setBackground(new Color(120, 40, 84));
				} else {
					chessBoard[k][m].setBackground(new Color(200, 200, 50));
				}
				
				//Add buttons to panel
				panel.add(chessBoard[k][m]);
			}
		}
		
		//Create new game
		game = new ChessModel();
		
		//Loops through board and sets icons on JButtons
		for(int r = 0; r < game.numRows(); r++){
			for(int c = 0; c < game.numColumns(); c++){
				
				//Doesn't pass a blank piece through
				if(game.pieceAt(r, c) != null){
					chessBoard[r][c].setIcon(findIcon(game.pieceAt(r,c)));
				}
			}
		}
	}
	
	/************************************************************
	 * Helper to take a piece and determine it's proper icon
	 * 
	 * @param piece Piece that needs icon
	 * @return  Proper ImageIcon for piece
	 ************************************************************/
	private ImageIcon findIcon(IChessPiece piece){
		String imageName = "";
		ImageIcon icon;
		
		//determines what color piece is
		if(piece.player() == Player.WHITE){
			imageName += "w_";
		}else{
			imageName += "b_";
		}
		imageName += piece.type();
		
		//Determines piece type
		switch (imageName){
		case "b_Rook":	icon = b_Rook;
			  			break;
		case "b_Knight":icon = b_Knight;
						break;
		case "b_Bishop":icon = b_Bish;
						break;
		case "b_Queen":	icon = b_Queen;
						break;
		case "b_King":	icon = b_King;
						break;
		case "b_Pawn":	icon = b_Pawn;
						break;
		case "w_Rook":	icon = w_Rook;
						break;
		case "w_Knight":icon = w_Knight;
						break;
		case "w_Bishop":icon = w_Bish;
						break;
		case "w_Queen":	icon = w_Queen;
						break;
		case "w_King":	icon = w_King;
						break;
		case "w_Pawn":	icon = w_Pawn;
						break;
		default:		icon = null;
						break;
		}
		
		return icon;
	}
	
	/************************************************************
	 * Main Method to initialize GUI
	 ************************************************************/
	public static void main(String[] args) {
		new OurGUI();
	}
	
	/***************************************************************
	 * Method to move a piece.
	 * 
	 * @param fromR current row
	 * @param fromC current column
	 * @param toR row moving to
	 * @param toC row moving to
	 ***************************************************************/
	private void movePiece(int fromR, int fromC, int toR, int toC) {
		Move move = new Move(fromR, fromC, toR, toC);
		
		try {
			game.move(move);
			
			//Move the icon to square
			chessBoard[toR][toC].setIcon
    				(chessBoard[fromR][fromC].getIcon());
			chessBoard[fromR][fromC].setIcon(null);
			
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	
	/****************************************************************
     * Static method to load the ImageIcon from the given location.
     *
     * @param name Name of the file.
     * @return the requested image.
     ****************************************************************/
    public static ImageIcon loadIcon(String name) {
    	java.net.URL imgURL = OurGUI.class.getResource(name);
    	if (imgURL == null) {
    		throw new RuntimeException("Icon resource not found.");
    	}

    	ImageIcon icon = new ImageIcon(imgURL);
    	Image img = icon.getImage();
    	Image resize = img.getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, 
    			Image.SCALE_AREA_AVERAGING);
    	
    	return new ImageIcon(resize);
    }
    
    
    /***************************************************************
     * Set Up Menus
     ***************************************************************/
    private void menuSetup() {
    	//Create menu and menu items
    	options = new JMenu("Options");
    	newGame = new JMenuItem("New Game");
    	quit = new JMenuItem("Quit");
    	
    	newGame.addActionListener(bl);
    	quit.addActionListener(bl);
    	
    	options.add(newGame);
    	options.add(quit);
    	
    	menus = new JMenuBar();
    	menus.add(options);
    	
    	frame.setJMenuBar(menus);
    }
    
    private class ButtonListener implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
    		if(e.getSource() == quit) {
				System.exit(0);
			}
			
			if (e.getSource() == newGame) {
				setBoard(chessBoard);
			}
    	}
    }
    
    
    /*****************************************************************
     * Method that handles the ActionListener when JButtons 
     * are selected.
     * 
     * @param e Event triggering action listener
     *****************************************************************/
	@Override
	public void actionPerformed(ActionEvent e) {
		Object button = e.getSource();
		
		//Loop through to find selected JButton
		for (int k = 0; k < chessBoard.length; k++) {
			for (int m = 0; m < chessBoard[0].length; m++) {
				
				//Selected JButton
				if (button == chessBoard[k][m]) {
					
					//First button selected (from location)
					if (firstC == -1) {
						firstR = k;
                        firstC = m;
                        status.setText(String.format ("Move from (%d,%d) to?",
                                firstR, firstC));
                        
					//Second button selected (to location)
					} else {
						secondR = k;
                        secondC = m;
                        status.setText(String.format ("(%d,%d) ==> (%d,%d)",
                                firstR, firstC, secondR, secondC));
                        
                        movePiece(firstR, firstC, secondR, secondC);
                        if (game.inCheck()) {
            				if(game.isComplete()){
            					JOptionPane.showMessageDialog(
            							null, "Game Over!");
            					System.exit(0);
            				}else{
            					JOptionPane.showMessageDialog(
            							null, "King is in Check!");
            				}
            			}   
                        firstR = firstC = -1;   
					}
				}
			}
		}
	}
}
