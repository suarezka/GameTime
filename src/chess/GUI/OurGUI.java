package chess.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import chess.ChessModel;
import gametime.GUI.GameGUI;
import gvprojects.chess.model.IChessModel;
import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;

/**********************************************************************
 * OurGUI runs the chess game.
 * 
 * @author Nate Benson, Kaye Suarez, Jake Young
 * @version 1.0 
 **********************************************************************/
public class OurGUI implements ActionListener {
	
	/** JFrame value. */
	private JFrame frame;
	
	/** JPanel value. */
	private JPanel panel;
	
	/** ButtonListener value. */
	private ButtonListener bl;
	
	/** JMenuBar value. */
	private JMenuBar menus;
	
	/** JMenu value. */
	private JMenu options;
	
	/** JMenuItem value. */
	private JMenuItem newGame, quit, mainMenu;
	
	/** JButton value. */
	private JButton[][] chessBoard;
	
	/** JLabel value. */
	private JLabel status;
	
	/** IChessModel value. */
	private IChessModel game;
	
	/** First and Second Row and Column value. */
	private int firstR, firstC, secondR, secondC;
	
	/** black pawn image. */
	private Image bPawn;
	
	/** white pawn image. */
	private Image wPawn;
	
	/** black rook image. */
	private Image bRook;
	
	/** white rook image. */
	private Image wRook;
	
	/** black knight image. */
	private Image bKnight;
	
	/** white knight image. */
	private Image wKnight;
	
	/** black bishop image. */
	private Image bBishop;
	
	/** white bishop image. */
	private Image wBishop;
	
	/** black queen image. */
	private Image bQueen;
	
	/** white queen image. */
	private Image wQueen;
	
	/** black king image. */
	private Image bKing;
	
	/** white king image. */
	private Image wKing;
	
	/** black pawn image icon. */
	private ImageIcon blackPawn;
	
	/** white pawn image icon. */
	private ImageIcon whitePawn;
	
	/** black rook image icon. */
	private ImageIcon blackRook;
	
	/** white rook image icon. */
	private ImageIcon whiteRook;
	
	/** black knight image icon. */
	private ImageIcon blackKnight;
	
	/** white knight image icon. */
	private ImageIcon whiteKnight;
	
	/** black bishop image icon. */
	private ImageIcon blackBishop;
	
	/** white bishop image icon. */
	private ImageIcon whiteBishop;
	
	/** black queen image icon. */
	private ImageIcon blackQueen;
	
	/** white queen image icon. */
	private ImageIcon whiteQueen;
	
	/** black king image icon. */
	private ImageIcon blackKing;
	
	/** white king image icon. */
	private ImageIcon whiteKing;
	
	/** Image Size. */
	private static final int IMAGE_SIZE = 64;
	
	/** Resized Image. */
	private static final int NEW_SIZE = 69;
	
	/** Board Size. */
	private static final int BOARD_SIZE = 8;
	
	/************************************************************
	 * GUI constructor.
	 ************************************************************/
	public OurGUI() {
		
		firstR = firstC = -1;
		frame = new JFrame("CHESS....CHESSECKERSZ!");
		panel = new JPanel();
		bl = new ButtonListener();
		
		panel.setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));
		chessBoard = new JButton[BOARD_SIZE][BOARD_SIZE];
		
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
	 * Set pieces in the starting positions.
	 * 
	 * @param board 2D JButton array
	 ****************************************************************/
	public final void setBoard(final JButton[][] board) {
		
		//Clear panel to reset it
		panel.removeAll();
		
		//Create JButtons for each square of the board
		for (int k = 0; k < chessBoard.length; k++) {
			for (int m = 0; m < chessBoard[k].length; m++) {
				
				//Set up the JButtons
				chessBoard[k][m] = new JButton();
				chessBoard[k][m].setPreferredSize(
						new Dimension(NEW_SIZE, NEW_SIZE));
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
	
		addIcons();
		//Loops through board and sets icons on JButtons
		for (int r = 0; r < game.numRows(); r++) {
			for (int c = 0; c < game.numColumns(); c++) {
				
				//Doesn't pass a blank piece through
				if (game.pieceAt(r, c) != null) {
					chessBoard[r][c].setIcon(findIcon(game.pieceAt(r, c)));
				}
			}
		}
		
	}
	
	/*********************************************************
	 * addIcons method loads and add images for chess pieces.
	 *********************************************************/
	private void addIcons() {
		try {
			
			bPawn = ImageIO.read(
					getClass().getResource("/Pictures/blackPawn.png"));
			wPawn = ImageIO.read(
					getClass().getResource("/Pictures/whitePawn.png"));
			bRook = ImageIO.read(
					getClass().getResource("/Pictures/blackRook.png"));
			wRook = ImageIO.read(
					getClass().getResource("/Pictures/whiteRook.png"));
			bKnight = ImageIO.read(
					getClass().getResource("/Pictures/blackKnight.png"));
			wKnight = ImageIO.read(
					getClass().getResource("/Pictures/whiteKnight.png"));
			bBishop = ImageIO.read(
					getClass().getResource("/Pictures/blackBishop.png"));
			wBishop = ImageIO.read(
					getClass().getResource("/Pictures/whiteBishop.png"));
			bQueen = ImageIO.read(
					getClass().getResource("/Pictures/blackQueen.png"));
			wQueen = ImageIO.read(
					getClass().getResource("/Pictures/whiteQueen.png"));
			bKing = ImageIO.read(
					getClass().getResource("/Pictures/blackKing.png"));
			wKing = ImageIO.read(
					getClass().getResource("/Pictures/whiteKing.png"));
			
		} catch (IOException e) {
			System.out.println("5");
		} finally {
			
			blackPawn = new ImageIcon(bPawn);
			whitePawn = new ImageIcon(wPawn);
			blackRook = new ImageIcon(bRook);
			whiteRook = new ImageIcon(wRook);
			blackKnight = new ImageIcon(bKnight);
			whiteKnight = new ImageIcon(wKnight);
			blackBishop = new ImageIcon(bBishop);
			whiteBishop = new ImageIcon(wBishop);
			blackQueen = new ImageIcon(bQueen);
			whiteQueen = new ImageIcon(wQueen);
			blackKing = new ImageIcon(bKing);
			whiteKing = new ImageIcon(wKing);
			
		}
	}
	
	/************************************************************
	 * Helper to take a piece and determine it's proper icon.
	 * 
	 * @param piece Piece that needs icon
	 * @return  Proper ImageIcon for piece
	 ************************************************************/
	private ImageIcon findIcon(final IChessPiece piece) {
		String imageName = "";
		ImageIcon icon;
		
		//determines what color piece is
		if (piece.player() == Player.WHITE) {
			imageName += "white";
		} else { 
			imageName += "black";
		}
		imageName += piece.type();
		
		//Determines piece type
		switch (imageName) {
		case "blackRook":	icon = blackRook;
			  			break;
		case "blackKnight":icon = blackKnight;
						break;
		case "blackBishop":icon = blackBishop;
						break;
		case "blackQueen":	icon = blackQueen;
						break;
		case "blackKing":	icon = blackKing;
						break;
		case "blackPawn":	icon = blackPawn;
						break;
		case "whiteRook":	icon = whiteRook;
						break;
		case "whiteKnight":icon = whiteKnight;
						break;
		case "whiteBishop":icon = whiteBishop;
						break;
		case "whiteQueen":	icon = whiteQueen;
						break;
		case "whiteKing":	icon = whiteKing;
						break;
		case "whitePawn":	icon = whitePawn;
						break;
		default:		icon = null;
						break;
		}
		
		return icon;
	}
	
	/************************************************************
	 * Main Method to initialize GUI.
	 * @param args main arguments
	 ************************************************************/
	public static void main(final String[] args) {
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
	private void movePiece(final int fromR, final int fromC, 
							final int toR, final int toC) {
		Move move = new Move(fromR, fromC, toR, toC);
		
		try {
			game.move(move);
			
			//Move the icon to square
			chessBoard[toR][toC].setIcon(
								chessBoard[fromR][fromC].getIcon());
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
    public static ImageIcon loadIcon(final String name) {
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
     * Set Up Menus.
     ***************************************************************/
    private void menuSetup() {
    	//Create menu and menu items
    	options = new JMenu("Options");
    	newGame = new JMenuItem("New Game");
    	quit = new JMenuItem("Quit");
	mainMenu = new JMenuItem("Main Menu");
    	
    	newGame.addActionListener(bl);
    	quit.addActionListener(bl);
	mainMenu.addActionListener(bl);
    	
    	options.add(newGame);
    	options.add(quit);
	options.add(mainMenu);
    	
    	menus = new JMenuBar();
    	menus.add(options);
    	
    	frame.setJMenuBar(menus);
    }
	
    /************************************************
     * Highlights possible moves for selected piece.
     * @param r row
     * @param c column
     * @param p IChesspiece
     ************************************************/
    private void highlight(final int r, final int c, final IChessPiece p) {
    	//import chess.ChessPiece
    	if (game.pieceAt(r, c) != null) {
    		for (int a = 0; a < BOARD_SIZE; a++) {
    			for (int b = 0; b < BOARD_SIZE; b++) {
    	    		    Move m = new Move(r, c, a, b);
    	    		    if (game.isValidMove(m)) {
    	    			chessBoard[a][b].setBackground(Color.GREEN);
    	    		    }
    		        }
    		}
    	} else {
    		return;
    	}
    }
    
    /****************************************
     * Removes highlighted path after move.
     ****************************************/
    private void removeHighlight() {
    	for (int a = 0; a < BOARD_SIZE; a++) {
    		for (int b = 0; b < BOARD_SIZE; b++) {
    			if ((a + b) % 2 == 0) {
    				chessBoard[a][b].setBackground(new Color(120, 40, 84));
    				
    			} else {
    				chessBoard[a][b].setBackground(new Color(200, 200, 50));
    			}
    		}
    	}
    }
    
    /************************************
     * Buttoner Listener.
     ************************************/
    private class ButtonListener implements ActionListener {
    	
    	/**************************
    	 * Handles menu clicks.
    	 * @param e mouse click
    	 **************************/
    	public void actionPerformed(final ActionEvent e) {
    		if (e.getSource() == quit) {
			System.exit(0);
		}
			
		if (e.getSource() == newGame) {
			setBoard(chessBoard);
		}
		if (e.getSource() == mainMenu) {
			panel.setVisible(false);
			frame.setVisible(false);
			GameGUI.main(null);
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
	public final void actionPerformed(final ActionEvent e) {
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
                        status.setText(String.format("Move from (%d,%d) to?",
                                firstR, firstC));
                        highlight(k, m, game.pieceAt(k, m));
                        
					//Second button selected (to location)
					} else {
						secondR = k;
                        secondC = m;
                        status.setText(String.format("(%d,%d) ==> (%d,%d)",
                                firstR, firstC, secondR, secondC));
                        
                        movePiece(firstR, firstC, secondR, secondC);
                        removeHighlight();
                        if (game.inCheck()) {
            				if (game.isComplete()) {
            					JOptionPane.showMessageDialog(
            							null, "Game Over!");
            					System.exit(0);
            				} else {
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

