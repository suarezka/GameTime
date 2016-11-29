package game;

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


public class ChessGUI2 implements ActionListener {
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
	
	private Image bPawn;
	private Image wPawn;
	private Image bRook;
	private Image wRook;
	private Image bKnight;
	private Image wKnight;
	private Image bBishop;
	private Image wBishop;
	private Image bQueen;
	private Image wQueen;
	private Image bKing;
	private Image wKing;
	
	//ImageIcons
	private ImageIcon blackPawn;
	private ImageIcon whitePawn;
	private ImageIcon blackRook;
	private ImageIcon whiteRook;
	private ImageIcon blackKnight;
	private ImageIcon whiteKnight;
	private ImageIcon blackBishop;
	private ImageIcon whiteBishop;
	private ImageIcon blackQueen;
	private ImageIcon whiteQueen;
	private ImageIcon blackKing;
	private ImageIcon whiteKing;
	
	private static final int IMAGE_SIZE = 64;
/**		
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
*/		
	
	/************************************************************
	 * GUI constructor.
	 ************************************************************/
	public ChessGUI2() {
		
		firstR = firstC = -1;
		frame = new JFrame("CHESS....CHESSECKERSZ!");
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
	 * Set pieces in the starting positions.
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
	
	private void addIcons() {
		try {
			
			bPawn = ImageIO.read(getClass().getResource("/Pictures/blackPawn.png"));
			wPawn = ImageIO.read(getClass().getResource("/Pictures/whitePawn.png"));
			bRook = ImageIO.read(getClass().getResource("/Pictures/blackRook.png"));
			wRook = ImageIO.read(getClass().getResource("/Pictures/whiteRook.png"));
			bKnight = ImageIO.read(getClass().getResource("/Pictures/blackKnight.png"));
			wKnight = ImageIO.read(getClass().getResource("/Pictures/whiteKnight.png"));
			bBishop = ImageIO.read(getClass().getResource("/Pictures/blackBishop.png"));
			wBishop = ImageIO.read(getClass().getResource("/Pictures/whiteBishop.png"));
			bQueen = ImageIO.read(getClass().getResource("/Pictures/blackQueen.png"));
			wQueen = ImageIO.read(getClass().getResource("/Pictures/whiteQueen.png"));
			bKing = ImageIO.read(getClass().getResource("/Pictures/blackKing.png"));
			wKing = ImageIO.read(getClass().getResource("/Pictures/whiteKing.png"));
			
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
		new ChessGUI2();
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
    	java.net.URL imgURL = ChessGUI2.class.getResource(name);
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
    		if (e.getSource() == quit) {
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
