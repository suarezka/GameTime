package checkers;

import javax.swing.JOptionPane;

/**********************************************************************
 * CheckerModel class implements ICheckerModel interface for game play.
 * 
 * @author Nate Benson, Kaye Suarez, Jake Young
 * @version 1.0 
 **********************************************************************/
public class CheckerModel implements ICheckerModel {

	/** board. */
	private IGamePiece[][] board; 

	/** current piece. */
	private IGamePiece currentPiece;

	/** player value. */
	private Player player;
	
	/** total number of red pieces. */
	private int redTotal = 12;

	/** total number of gray pieces. */
	private int grayTotal = 12;

	/** board size. */
	private static final int BOARDSIZE = 8;

	/** jump size. */
	private static final int JUMP = 2;


	/************************************************ 
	 * Constructor adds red and gray pieces to board.
	 ************************************************/
	public CheckerModel() {

		//create the board
		board = new IGamePiece[BOARDSIZE][BOARDSIZE];

		//sets who goes first
		player = Player.RED;

		//add gray pieces to board
		board[0][0] = new NormalPiece(Player.GRAY);
		board[0][2] = new NormalPiece(Player.GRAY);
		board[0][4] = new NormalPiece(Player.GRAY);
		board[0][6] = new NormalPiece(Player.GRAY);

		board[1][1] = new NormalPiece(Player.GRAY);
		board[1][3] = new NormalPiece(Player.GRAY);
		board[1][5] = new NormalPiece(Player.GRAY);
		board[1][7] = new NormalPiece(Player.GRAY);

		board[2][0] = new NormalPiece(Player.GRAY);
		board[2][2] = new NormalPiece(Player.GRAY);
		board[2][4] = new NormalPiece(Player.GRAY);
		board[2][6] = new NormalPiece(Player.GRAY);

		//add red pieces to board
		board[7][1] = new NormalPiece(Player.RED);
		board[7][3] = new NormalPiece(Player.RED);
		board[7][5] = new NormalPiece(Player.RED);
		board[7][7] = new NormalPiece(Player.RED);

		board[6][0] = new NormalPiece(Player.RED);
		board[6][2] = new NormalPiece(Player.RED);
		board[6][4] = new NormalPiece(Player.RED);
		board[6][6] = new NormalPiece(Player.RED);

		board[5][1] = new NormalPiece(Player.RED);
		board[5][3] = new NormalPiece(Player.RED);
		board[5][5] = new NormalPiece(Player.RED);
		board[5][7] = new NormalPiece(Player.RED);

	}

	/***********************************************
	 * isValidMove checks if provided move is valid.
	 * @param move current move
	 * @return true if valid, false if not
	 ***********************************************/
	public final boolean isValidMove(final Move move) {
		int row = move.getCurrentRow();
		int col = move.getCurrentCol();
		
		return pieceAt(row, col).isValidMove(move, board);
	}

	/**************************************
	 * Handles the piece movement on board.
	 * @param move new piece position 
	 **************************************/
	public final void move(final Move move) {
		
		int row = move.getCurrentRow();
		int col = move.getCurrentCol();
		int newR = move.getNewRow();
		int newC = move.getNewCol();

		if (pieceAt(row, col) != null) {
			//if(pieceAt(row, col).isValidMove(
			//											move, board)) {
			if (isValidMove(move)) {
				System.out.println("Valid");

				if (pieceAt(newR, newC) == null) {

					//Move piece to new location
					board[newR][newC] 
							= board[row][col];

					board[row][col] = null;

					//DOWN DIAGONAL JUMP
					if (row + JUMP == newR) {
						if (getCurrentPlayer() == Player.RED) {
							grayTotal--;
						} else {
							redTotal--;
						}

						//RIGHT
						if (newC > col) {
							board[row + 1][col + 1] = null;


							//LEFT
						} else if (newC < col) {
							board[row + 1][col - 1] = null;
						}

						//UP DIAGONAL JUMP
					} else if (row - JUMP == newR) {
						if (getCurrentPlayer() == Player.RED) {
							grayTotal--;
						} else {
							redTotal--;
						}

						//RIGHT
						if (newC > col) {
							board[row - 1][col + 1] = null;

							//LEFT
						} else if (newC < col) {
							board[row - 1][col - 1] = null;
						}
					}	

					//CHECK IF GAME IS OVER
					if (gameOver()) {
						JOptionPane.showMessageDialog(null, 
								getCurrentPlayer() + " WINS!");
					}
					
					player = player.next();
					System.out.println("REDS: " + redTotal
										+ "\nGRAYS: " + grayTotal);
					System.out.println(player);

				}
				//}
			} else {
				JOptionPane.showMessageDialog(null, 
						"That is not a valid move. Please try another.");
				return;
			}
		}
		System.out.println(player);
	}

	@Override
	public final boolean gameOver() {
		if (redTotal == 0 || grayTotal == 0) {
			return true;
		}
		
		return false;
	}

	@Override
	public final Player getCurrentPlayer() {
		return player;
	}

	/*************************************
	 * setCurrentPlayer sets player value.
	 * @param p player
	 *************************************/
	public final void setCurrentPlayer(final Player p) {
		this.player = p;
	}

	/**********************************************
	 * pieceAt returns the piece at given position.
	 * @param row given row
	 * @param column given column
	 * @return piece value
	 **********************************************/
	public final IGamePiece pieceAt(final int row, final int column) {
		return board[row][column];
	}

	/********************************************
	 * getCurrentPiece returns the current piece.
	 * @return piece value
	 ********************************************/
	public final IGamePiece getCurrentPiece() {
		return currentPiece;
	}

	/****************************************************
	 * setCurrentPiece sets given piece as current piece.
	 * @param p new current piece
	 ****************************************************/
	public final void setCurrentPiece(final IGamePiece p) {
		this.currentPiece = p;
	}
	
	/************************************************************
	 * getRedTotal returns the number of red pieces on the board.
	 * @return red pieces
	 ************************************************************/
	public final int getRedTotal() {
		return redTotal;
	}
	
	/**************************************************************
	 * getGrayTotal returns the number of gray pieces on the board.
	 * @return red pieces
	 **************************************************************/
	public final int getGrayTotal() {
		return grayTotal;
	}
	
	/************************************************************
	 * getRedTotal sets the number of red pieces on the board.
	 * @param total red pieces
	 ************************************************************/
	public final void setRedTotal(final int total) {
		redTotal = total;
	}
	
	/**************************************************************
	 * getGrayTotal sets the number of gray pieces on the board.
	 * @param total gray pieces 
	 **************************************************************/
	public final void setGrayTotal(final int total) {
		grayTotal = total;
	}
	
	/********************************
	 * Removes piece from the board.
	 * @param row current row
	 * @param column current column
	 ********************************/
	public final void removePiece(final int row, final int column) {
		 board[row][column] = null;
	}
		 	
	/**************************************************
	 * Creates a King at given location.
	 * @param row current row
	 * @param column current column
	 * @param isKing true if king, otherwise false
	 * @param isRed true if red player, otherwise gray
	 **************************************************/
	public final void createPiece(final int row, final int column, 
								final boolean isKing, final boolean isRed) {
		if (isKing && isRed) {
			board[row][column] = new Kings(Player.RED);
			
		} else if (isKing && !isRed) {
			board[row][column] = new Kings(Player.GRAY);
			
		} else if (!isKing && isRed) {
			board[row][column] = new NormalPiece(Player.RED);
			
		} else {
			board[row][column] = new NormalPiece(Player.GRAY);
		}
	}
	
	/**************************************************************
	 * Makes normal piece a king once reaches the end of the board.
	 **************************************************************/
	public final void kingMe() {
		for (int a = 0; a < BOARDSIZE; a++) {
			if (pieceAt(0, a) != null && pieceAt(0, a).player() == Player.RED) {
				board[0][a] = null;
				board[0][a] = new Kings(Player.RED);
				return;
			}
			int b = BOARDSIZE - 1;
			if (pieceAt(b, a) != null 
					&& pieceAt(b, a).player() == Player.GRAY) {
				board[b][a] = new Kings(Player.GRAY);
				return;
			}
		}
	}	
}
