package game;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ChessPanel extends JPanel {
	
	private JButton[][] board;
	private ChessModel model;
	public static JMenuBar topMenu;
	private JMenuItem newAction;
	private JMenuItem closeAction;
	private JMenuItem mainMenu;
	public Move currentMove;
	private JLabel currPlayer;
	private JPanel rightSide;
	private JLabel knightsB;
	private JLabel bishopB;
	private JLabel rookB;
	private JLabel queenB;
	private JLabel knightsW;
	private JLabel bishopW;
	private JLabel rookW;
	private JLabel queenW;
	private JButton castling;
	public boolean inChk;
	
	//Images
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
	
	
	//declare other instance variables
	
	public ChessPanel() {
		
		addIcons();
		model = new ChessModel();
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(8, 8));
		add(center, BorderLayout.CENTER);
		rightSide = new JPanel();
		rightSide.setLayout(new GridLayout(0, 1));
		add(rightSide, BorderLayout.EAST);
		board = new JButton[8][8];
		
		topMenu = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		newAction = new JMenuItem("New Game");
		closeAction = new JMenuItem("Quit Game");
		mainMenu = new JMenuItem("Main Menu");
		topMenu.add(fileMenu);
		fileMenu.add(newAction);
		fileMenu.add(closeAction);
		fileMenu.add(mainMenu);
		newAction.addMouseListener(listener);
		closeAction.addMouseListener(listener);
		mainMenu.addMouseListener(listener);
		
		currPlayer = new JLabel("Current Player is: \n");
		currPlayer.setHorizontalTextPosition(JLabel.CENTER);
		currPlayer.setVerticalTextPosition(JLabel.TOP);
		rightSide.add(currPlayer);

		rookW = new JLabel();
		rookW.setIcon(whiteRook);
		rightSide.add(rookW);
		
		knightsW = new JLabel();
		knightsW.setIcon(whiteKnight);
		rightSide.add(knightsW);
		
		bishopW = new JLabel();
		bishopW.setIcon(whiteBishop);
		rightSide.add(bishopW);

		queenW = new JLabel();
		queenW.setIcon(whiteQueen);
		rightSide.add(queenW);
		
		queenB = new JLabel();
		queenB.setIcon(blackQueen);
		rightSide.add(queenB);
		
		bishopB = new JLabel();
		bishopB.setIcon(blackBishop);
		rightSide.add(bishopB);
		
		knightsB = new JLabel();
		knightsB.setIcon(blackKnight);
		rightSide.add(knightsB);
		
		rookB = new JLabel();
		rookB.setIcon(blackRook);
		rightSide.add(rookB);
		
		castling = new JButton("Castle");
		castling.addMouseListener(listener);
		rightSide.add(castling);
		
		for(int row = 0; row < 8; row++) {
			for(int col = 0; col < 8; col++) {
				board[row][col] = new JButton("");
				board[row][col].addMouseListener(listener);
				board[row][col].setPreferredSize(new Dimension(100, 100));
				center.add(board[row][col]);
			}
		}
		displayBoard();
	}
	
	//method that updates the board
	private void displayBoard() {
		for(int r = 0; r < 8; r++) {
			for(int c = 0; c < 8; c++) {
				if(r % 2 == c % 2) {
					board[r][c].setBackground(Color.WHITE);
				}
				else {
					board[r][c].setBackground(Color.GRAY);
				}
				if(model.pieceAt(r, c) != null) {
					if(model.pieceAt(r, c).type() == "Pawn") {
						if(model.pieceAt(r, c).player() == Player.WHITE) {
							board[r][c].setIcon(whitePawn);
						}
						else {
							board[r][c].setIcon(blackPawn);
						}
					}
					if(model.pieceAt(r, c).type() == "Rook") {
						if(model.pieceAt(r, c).player() == Player.WHITE) {
							board[r][c].setIcon(whiteRook);
						}
						else {
							board[r][c].setIcon(blackRook);
						}
					}
					if(model.pieceAt(r, c).type() == "Knight") {
						if(model.pieceAt(r, c).player() == Player.WHITE) {
							board[r][c].setIcon(whiteKnight);
						}
						else {
							board[r][c].setIcon(blackKnight);
						}
					}
					if(model.pieceAt(r, c).type() == "Bishop") {
						if(model.pieceAt(r, c).player() == Player.WHITE) {
							board[r][c].setIcon(whiteBishop);
						}
						else {
							board[r][c].setIcon(blackBishop);
						}
					}
					if(model.pieceAt(r, c).type() == "Queen") {
						if(model.pieceAt(r, c).player() == Player.WHITE) {
							board[r][c].setIcon(whiteQueen);
						}
						else {
							board[r][c].setIcon(blackQueen);
						}
					}
					if(model.pieceAt(r, c).type() == "King") {
						if(model.pieceAt(r, c).player() == Player.WHITE) {
							board[r][c].setIcon(whiteKing);
						}
						else {
							board[r][c].setIcon(blackKing);
						}
					}	
				}
				else {
					board[r][c].setIcon(null);
				}
			}
		}
		if(model.currentPlayer() == Player.BLACK) {
			currPlayer.setIcon(blackKing);
		}
		else {
			currPlayer.setIcon(whiteKing);
		}
		rookW.setText("x" + model.getTakenWhiteRook());
		knightsW.setText("x" + model.getTakenWhiteKnight());
		bishopW.setText("x" + model.getTakenWhiteBishop());
		queenW.setText("x" + model.getTakenWhiteQueen());
		queenB.setText("x" + model.getTakenBlackQueen());
		bishopB.setText("x" + model.getTakenBlackBishop());
		knightsB.setText("x" + model.getTakenBlackKnight());
		rookB.setText("x" + model.getTakenBlackRook());
	}
	
	private void addIcons() {
		try{
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
		}
		finally {
			
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
	
	public void highlight(int x, int y) {
		IChessPiece temp = model.pieceAt(x, y);
			for(int t = 0; t < 8; t++) {
				for(int g = 0; g < 8; g++) {
					if(temp.type() == "Pawn") {
						boolean p = ((Pawn) temp).getHasMoved();
						if(p == false) {
							currentMove.toRow = t;
							currentMove.toColumn = g;
							if(model.isValidMove(currentMove)) {
								board[t][g].setBackground(Color.GREEN);
							}
							((Pawn) temp).hasMoved = false;
						}
						else {
							currentMove.toRow = t;
							currentMove.toColumn = g;
							if(model.isValidMove(currentMove)) {
								board[t][g].setBackground(Color.GREEN);
							}
						}
					}
					else {
						currentMove.toRow = t;
						currentMove.toColumn = g;
						if(model.isValidMove(currentMove)) {
							board[t][g].setBackground(Color.GREEN);
						}
					}
				}
			}
		}
//	}
	
	public void reset() {
		model = new ChessModel();
	}
	
	//add other helper methods
	
	
		MouseListener listener = new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				//possibly display piece name	
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getSource() == newAction) {
					reset();
					displayBoard();
				}
				if(e.getSource() == closeAction) {
					System.exit(0);
				}
				if(e.getSource() == mainMenu) {
					setVisible(false);
					ChessGUI.frame.setVisible(false);
					GameGUI.main(null);
				}
				if(e.getSource() == castling) {
					Object[] options = { "Left", "Right" };
					int n = JOptionPane.showOptionDialog(null, "Which side would you like to castle with?", "Castling",
							JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, 
							options, options[0]);
					if(n == 0) {
						//left
						model.castling("Left");
						displayBoard();
					}
					if(n == 1) {
						//right
						model.castling("Right");
						displayBoard();
					}
				}
				for(int a = 0; a < 8; a++) {
					for(int b = 0; b < 8; b++) {
						if(e.getSource() == board[a][b]) {
							if(e.getButton() == MouseEvent.BUTTON1) {
								if(model.pieceAt(a, b) != null) {
									if(model.pieceAt(a, b).player() == model.currentPlayer()) {
										if(inChk ==  true) {
											if(model.pieceAt(a, b) != model.savior) {
												JOptionPane.showMessageDialog(null, "Not a valid move.");
											}
										}
										model.setCurrentPiece(model.pieceAt(a, b));
										currentMove.fromRow = a;
										currentMove.fromColumn = b;
									}
									else if(model.pieceAt(a, b).player() != model.currentPlayer()) {
										currentMove.toRow = a;
										currentMove.toColumn = b;
										//model.removePiece(model.pieceAt(a, b));
										model.move(currentMove);
										model.currentPiece = null;
										displayBoard();
										
										//check for win
										if(model.inCheck(Player.WHITE)) {
											if(model.isComplete() == true) {
												int n = JOptionPane.showConfirmDialog(
													    null,
													    "Checkmate! Black Wins! \n Would you like to restart?",
													    "Game Over",
													    JOptionPane.YES_NO_OPTION);
												if(n == 1) {
													System.exit(0);
												}
												if(n == 0) {
													reset();
												}
											}
											else {
												JOptionPane.showMessageDialog(null, "White is in check!");
												inChk = true;
											}
										}
										if(model.inCheck(Player.BLACK)) {
											if(model.isComplete() == true) {
												int n = JOptionPane.showConfirmDialog(
													    null,
													    "Checkmate! White Wins! \n Would you like to restart?",
													    "Game Over",
													    JOptionPane.YES_NO_OPTION);
												if(n == 1) {
													System.exit(0);
												}
												if(n == 0) {
													reset();
												}
											}
											else {
												JOptionPane.showMessageDialog(null, "Black is in check!");
												inChk = true;
											}
										}
									}	
								}
								else {
									currentMove.toRow = a;
									currentMove.toColumn = b;
									model.move(currentMove);
									model.currentPiece = null;
									displayBoard();
									if(model.inCheck(Player.WHITE)) {
										if(model.isComplete()) {
											int n = JOptionPane.showConfirmDialog(
												    null,
												    "Checkmate! Black Wins! \n Would you like to restart?",
												    "Game Over",
												    JOptionPane.YES_NO_OPTION);
											if(n == 1) {
												System.exit(0);
											}
											if(n == 0) {
												reset();
											}
										}
										else {
											JOptionPane.showMessageDialog(null, "White is in check!");
											inChk = true;
										}
									}
									if(model.inCheck(Player.BLACK)) {
										if(model.isComplete()) {
											int n = JOptionPane.showConfirmDialog(
												    null,
												    "Checkmate! White Wins! \n Would you like to restart?",
												    "Game Over",
												    JOptionPane.YES_NO_OPTION);
											if(n == 1) {
												System.exit(0);
											}
											if(n == 0) {
												reset();
											}
										}
										else {
											JOptionPane.showMessageDialog(null, "Black is in check!");
											inChk = true;
										}
									}
								}
								displayBoard();
							}
							if(e.getButton() == MouseEvent.BUTTON3) {
								displayBoard();
								if(e.getSource() == board[a][b]) {
									if(model.getCurrentPiece() == null) {
										if(model.pieceAt(a, b) != null) {
											if(model.pieceAt(a, b).player()
													== model.currentPlayer()) {
												currentMove.fromRow = a;
												currentMove.fromColumn = b;
												highlight(a, b);
												model.currentPiece = null;
											}
										}
									}
								}
							}
						}
					}
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		
		
	}


