package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList; 
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**************************************************************
 * CheckerPanel class to create and set up board for game play.
 * 
 * @author Nate Benson, Kaye Suarez, Jake Young
 * @version 1.0 
 **************************************************************/

public class CheckerPanel extends JPanel {
	
	public static JMenuBar topMenu;
	private JMenuItem newGame;
	private JMenuItem quitGame;
	private JMenuItem mainMenu;
	private JMenuItem save;
	private JMenuItem load;
	
	/** board: array of buttons for board tiles. */
	private JButton[][] board;
	
	/** model: instance variable to hold CheckerModel. */
	private CheckerModel model;
	
	/** currentMove: holds value for the current move. */
	public Move currentMove;
	
	/** JPanel next to board. */
	private JPanel rightSide;
	
	/** JLabel to show current player. */
	private JLabel currentPlayer;
	
	/** image for redPieces. */
	private Image redPieces;
	
	/** image for grayPieces. */
	private Image grayPieces;
	
	/** image for redKings. */
	private Image redKings;
	
	/** image for grayKings. */
	private Image grayKings;
	
	/** resized image redKings. */
	private Image redKingsResized;
	
	/** resized image grayKings. */
	private Image grayKingsResized;
	
	/** resized image redPiece. */
	private Image redPieceResize;
	
	/** resized image grayPieceResize. */
	private Image grayPieceResize;
	
	/** red piece icon. */
	private ImageIcon rPieces;
	
	/** gray piece icon. */
	private ImageIcon gPieces;
	
	/** red king icon. */
	private ImageIcon rKings;
	
	/** gray king icon. */
	private ImageIcon gKings;
	
	/** holds board size. */
	private static final int BOARDSIZE = 8;
	
	/** holds board dimension. */
	private static final int BOARDDIM = 80;
	
	/** holds resized red piece image. */
	private static final int REDSIZE = 105;
	
	/** holds resized gray piece image. */
	private static final int GRAYSIZE = 120;
	
	
	/*********************************************
	 * CheckerPanel creates board for game play.
	 *********************************************/
	
	public CheckerPanel() {
			
		addIcons();
		model = new CheckerModel();
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(BOARDSIZE, BOARDSIZE));
		add(center, BorderLayout.CENTER);
		board = new JButton[BOARDSIZE][BOARDSIZE];
		
		topMenu = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		newGame = new JMenuItem("New Game");
		quitGame = new JMenuItem("Exit Game");
		mainMenu = new JMenuItem("Main Menu");
		save = new JMenuItem("Save Game");
		load = new JMenuItem("Load Game");
		topMenu.add(fileMenu);
		fileMenu.add(newGame);
		fileMenu.add(quitGame);
		fileMenu.add(mainMenu);
		fileMenu.add(save);
		fileMenu.add(load);
		newGame.addMouseListener(listener);
		quitGame.addMouseListener(listener);
		mainMenu.addMouseListener(listener);
		save.addMouseListener(listener);
		load.addMouseListener(listener);
		
		
		rightSide = new JPanel();
		rightSide.setLayout(new GridLayout(0, 1));
		add(rightSide, BorderLayout.EAST);
		
		currentPlayer = new JLabel("Current Player is: \n");
		currentPlayer.setHorizontalTextPosition(JLabel.CENTER);
		currentPlayer.setVerticalTextPosition(JLabel.TOP);
		rightSide.add(currentPlayer);
		
		for (int r = 0; r < BOARDSIZE; r++) {
			for (int c = 0; c < BOARDSIZE; c++) {
				
				board[r][c] = new JButton("");
				board[r][c].addMouseListener(listener);
				board[r][c].setPreferredSize(new Dimension(BOARDDIM, BOARDDIM));
				center.add(board[r][c]);
			}
		}
		displayBoard();
	}
	
	/**************************************************************
	 * displayBoard sets icons to the different tiles on the board. 
	 **************************************************************/
	
	private void displayBoard() {
		
		for (int a = 0; a < BOARDSIZE; a++) {
			for (int b = 0; b < BOARDSIZE; b++) {
				
				if (model.getCurrentPlayer() == Player.RED) {
					currentPlayer.setIcon(rPieces);
				}
				if (model.getCurrentPlayer() == Player.GRAY) {
					currentPlayer.setIcon(gPieces);
				}
				
				if (a % 2 == b % 2) {
					board[a][b].setBackground(Color.RED);
					
				} else {
					board[a][b].setBackground(Color.BLACK);
				}
				
				if (model.pieceAt(a, b) != null) {
					if (model.pieceAt(a, b).type().equals("Pawn")) {
						
						if (model.pieceAt(a, b).player() == Player.GRAY) {
							board[a][b].setIcon(gPieces);
						}
						
						if (model.pieceAt(a, b).player() == Player.RED) {
							board[a][b].setIcon(rPieces);
						}
					} else if (model.pieceAt(a, b).type().equals("King")) {
						if (model.pieceAt(a, b).player() == Player.GRAY) {
							board[a][b].setIcon(gKings);
						}
						if (model.pieceAt(a, b).player() == Player.RED) {
							board[a][b].setIcon(rKings);
						}	
					}
					
				} else {
					board[a][b].setIcon(null);
				}
			}
		}
	}
	
	/***********************************************************
	 * addIcons sets the correct image to colored piece on tile.
	 ***********************************************************/
	
	private void addIcons() {
		//load graphics
		try {
			//load images
			redPieces = ImageIO.read(getClass().getResource(
				"/Pictures/beer-cap-icon-67249.png"));
					
			grayPieces = ImageIO.read(getClass().getResource(
				"/Pictures/ff-bottle-cap.png"));
					
			redKings = ImageIO.read(getClass().getResource(
				"/Pictures/kingpieceRED.png"));
					
			grayKings = ImageIO.read(getClass().getResource(
				"/Pictures/kingpieceGRAY.png"));
					
			//resize images
			redPieceResize = 
				redPieces.getScaledInstance(REDSIZE, REDSIZE, REDSIZE);
					
			grayPieceResize = 
				grayPieces.getScaledInstance(GRAYSIZE, GRAYSIZE, GRAYSIZE);
					
			redKingsResized = 
				redKings.getScaledInstance(REDSIZE, REDSIZE, REDSIZE);
					
			grayKingsResized = 
				grayKings.getScaledInstance(GRAYSIZE, GRAYSIZE, GRAYSIZE);
					
			//set icons
			rPieces = new ImageIcon(redPieceResize);
				gPieces = new ImageIcon(grayPieceResize);
				rKings = new ImageIcon(redKingsResized);
				gKings = new ImageIcon(grayKingsResized);
					
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/*****************************************************
	 * reset clears the current game and resets game play. 
	 *****************************************************/
	public final void reset() {
		model = new CheckerModel();
	}
	
	/***************************
	 * Clears the current board.
	 ***************************/
	public final void clearBoard() {
		for (int i = 0; i < BOARDSIZE; i++) {
			for (int j = 0; j < BOARDSIZE; j++) {
				if (model.pieceAt(i, j) != null) {
					model.removePiece(i, j);
				}
			}
		}
	}
	
	/*****************************************
	 * Save the current board to text file.
	 * @param filename file for saving board
	 *****************************************/
	public final void saveBoard(final String filename) {
		try {
			File file = new File(filename);
			Writer w = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			PrintWriter out = new PrintWriter(w);
		 
			for (int i = 0; i < BOARDSIZE; i++) {
				for (int j = 0; j < BOARDSIZE; j++) {
					if (model.pieceAt(i, j) == null) { 
						out.print("X,");
						
					} else if (model.pieceAt(i, j).type().equals("Pawn")) {
						
						if (model.pieceAt(i, j).player() == Player.GRAY) {
							out.print("PG,");
						} else {
							out.print("PR,");
						}
					} else {
						
						if (model.pieceAt(i, j).player() == Player.GRAY) {
							out.print("KG,");
							
						} else {
							out.print("KR,");
						}
					}
				}
			}
			out.close();
		} catch (IOException error1) {
			System.out.println("Failed to save file");
		}
	}

	/************************************
	 * Load board from text file.
	 * @param filename file to load from
	 ************************************/
	public final void readBoard(final String filename) {
		FileInputStream fileByteStream = null;
		Scanner inFS = null;
	
		try {
			// Input a filename and scan the file using commas as delimiter
			fileByteStream = new FileInputStream(filename);
			inFS = new Scanner(fileByteStream, "UTF-8");
			inFS.useDelimiter("[,\r\n]+");
			
			// Read the board to ArrayList
			while (inFS.hasNext()) {
				clearBoard();
				for (int i = 0; i < BOARDSIZE; i++) { 
					for (int j = 0; j < BOARDSIZE; j++) {
						String spot = inFS.next();
						
						if (spot.equals("PG")) {
							model.createPiece(i, j, false, false);
							
						} else if (spot.equals("PR")) {
							model.createPiece(i, j, false, true);
							
						} else if (spot.equals("KG")) {
							model.createPiece(i, j, true, false);
							
						} else if (spot.equals("KR")) {
							model.createPiece(i, j, true, true);
						}
					}
				}	
			}
			fileByteStream.close();
		} catch (FileNotFoundException error1) {
			System.out.println("Failed to open file: " + filename);
		} catch (IOException error2) {
			System.out.println("Oops! There was an error reading " + filename);
		}
	}
	
	
	/**********************
	 * Open the text file.
	 **********************/
	private void openFile() {
		String userDir = System.getProperty("user.dir");
		JFileChooser fc = new JFileChooser(userDir);
		
		// Show File Chooser and wait for user selection
		int returnVal = fc.showOpenDialog(this);
		
		// Did the user select a file?
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String filename = fc.getSelectedFile().getName();
			readBoard(filename);          
		}
	}
	
	/** holds values of mouse listener. */
	MouseListener listener = new MouseListener() {

		@Override
		public void mouseClicked(final MouseEvent a) {
			
		}

		@Override
		public void mouseEntered(final MouseEvent a) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(final MouseEvent a) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(final MouseEvent a) {
			if(a.getSource() == newGame) {
				reset();
				displayBoard();
			}
			if(a.getSource() == quitGame) {
				System.exit(0);
			}
			if(a.getSource() == mainMenu) {
				setVisible(false);
				CheckerGUI.frame.setVisible(false);
				GameGUI.main(null);
			}
			if(a.getSource() == save) {
				String str = JOptionPane.showInputDialog(null, 
						"Enter file name:"); 
				if(str != null) {
					saveBoard(str);
				}
				displayBoard();
			}
			if(a.getSource() == load) {
				openFile();
				displayBoard();
			}
			
			for (int r = 0; r < BOARDSIZE; r++) {
				for (int c = 0; c < BOARDSIZE; c++) {
					
					if (a.getButton() == MouseEvent.BUTTON1) {
						if (a.getSource() == board[r][c]) {
							
							if (model.pieceAt(r, c) != null) {
									
									if (model.pieceAt(r, c).player() 
											== model.getCurrentPlayer()) {
										
										if (model.getCurrentPiece() == null) {
											
											model.setCurrentPiece(
													model.pieceAt(r, c));
											System.out.println(r + " " + c);
											currentMove = new Move(r, c, 0, 0);
											
										} else {
											
											if (model.pieceAt(r, c).player() 
												  == model.getCurrentPlayer()) {
												
												model.setCurrentPiece(
														model.pieceAt(r, c));
												currentMove.fromRow = r;
												currentMove.fromColumn = c;
											}
										}
									}
								
							} else {
								
								System.out.println(r + " " + c);
								currentMove.toRow = r;
								currentMove.toColumn = c;
								model.move(currentMove);
								model.currentPiece = null;
								model.kingMe();
								if(model.gameOver()) {
									//end game
									JOptionPane.showMessageDialog(rightSide, "");
								}
								displayBoard();
							}
						}
					}
				}
			}
			  if (a.getButton() == MouseEvent.BUTTON3) {
//				Object[] options = 
//								{"Resume", "Save", "Load", "Restart", "Quit"};
//				
//				int n = JOptionPane.showOptionDialog(
//						            null, "The Game is Paused.", "Checkers",
//						            JOptionPane.DEFAULT_OPTION, 
//						            JOptionPane.PLAIN_MESSAGE, null, 
//						            options, options[0]);
//				
//				if (n == 0) {
//					displayBoard();
//					
//				} else if (n == 1) {
//					String str = JOptionPane.showInputDialog(null, 
//											"Enter file name:"); 
//					saveBoard(str);
//					displayBoard();
//					
//				} else if (n == 2) {
//					openFile();
//					displayBoard();
//					
//				} else if (n == 3) {
//					reset();
//					displayBoard();
//					
//				} else if (n == 4) {
//					System.exit(0); 
//				}
			}
			  
			  
//			if(a.getSource() == newGame) {
//				reset();
//				displayBoard();
//			}
//			if(a.getSource() == quitGame) {
//				System.exit(0);
//			}
//			if(a.getSource() == mainMenu) {
//				setVisible(false);
//				CheckerGUI.frame.setVisible(false);
//				GameGUI.main(null);
//			}
//			for(int r = 0; r < 8; r++) {
//				for(int c = 0; c < 8; c++) {
//					if(a.getSource() == board[r][c]) {
//						if(a.getButton() == MouseEvent.BUTTON1) {
//							if(model.pieceAt(r, c) != null) {
//								if(model.pieceAt(r, c).player() == model.getCurrentPlayer()) {
//									model.setCurrentPiece(model.pieceAt(r, c));
//									currentMove = new Move(r, c, 0, 0);
//									currentMove.fromRow = r;
//									currentMove.fromColumn = c;
//								}
//								else if(model.pieceAt(r, c).player() != model.getCurrentPlayer()) {
//									currentMove.toRow = r;
//									currentMove.toColumn = c;
//									model.move(currentMove);
//									model.currentPiece = null;
//									model.kingMe();
//									displayBoard();
//									if(model.gameOver()) {
//										//end the game
//									}
//								}
//							}
//						}
//					}
//				}
//			}
		}

		@Override
		public void mouseReleased(final MouseEvent a) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
}
