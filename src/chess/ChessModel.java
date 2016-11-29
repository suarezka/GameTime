package chess;

import java.awt.Point;
import java.util.ArrayList;

import gvprojects.chess.model.IChessModel;
import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;

/**********************************************************************
 * ChessModel class implements various methods for game play.
 * 
 * @author Nate Benson, Kaye Suarez, Jake Young
 * @version 1.0 
 **********************************************************************/
public class ChessModel implements IChessModel {

	//Instance Variables
	/** Current Player. */
	private Player curPlayer;
	
	/** Game in Progress value. */
	private boolean gameInProgress;
	
	/** Current board. */
	private IChessPiece[][] board;
	
	/** Attack moves for black. */
	private ArrayList<Move> attackMovesB;
	
	/** Attack moves for white. */
	private ArrayList<Move> attackMovesW;


	/************************************************************
	 * ChessModel constructor.
	 ************************************************************/
	public ChessModel() {
		//Create game board
		board = new IChessPiece[8][8];
		curPlayer = Player.WHITE;
		gameInProgress = true;
		attackMovesB = new ArrayList<Move>();
		attackMovesW = new ArrayList<Move>();

		//Set black pieces on board
		board[0][0] = new Rook(Player.BLACK);
		board[0][1] = new Knight(Player.BLACK);
		board[0][2] = new Bishop(Player.BLACK);
		board[0][3] = new Queen(Player.BLACK);
		board[0][4] = new King(Player.BLACK);
		board[0][5] = new Bishop(Player.BLACK);
		board[0][6] = new Knight(Player.BLACK);
		board[0][7] = new Rook(Player.BLACK);
		board[1][0] = new Pawn(Player.BLACK);
		board[1][1] = new Pawn(Player.BLACK);
		board[1][2] = new Pawn(Player.BLACK);
		board[1][3] = new Pawn(Player.BLACK);
		board[1][4] = new Pawn(Player.BLACK);
		board[1][5] = new Pawn(Player.BLACK);
		board[1][6] = new Pawn(Player.BLACK);
		board[1][7] = new Pawn(Player.BLACK);

		//Set white pieces on board
		board[7][0] = new Rook(Player.WHITE);
		board[7][1] = new Knight(Player.WHITE);
		board[7][2] = new Bishop(Player.WHITE);
		board[7][3] = new Queen(Player.WHITE);
		board[7][4] = new King(Player.WHITE);
		board[7][5] = new Bishop(Player.WHITE);
		board[7][6] = new Knight(Player.WHITE);
		board[7][7] = new Rook(Player.WHITE);
		board[6][0] = new Pawn(Player.WHITE);
		board[6][1] = new Pawn(Player.WHITE);
		board[6][2] = new Pawn(Player.WHITE);
		board[6][3] = new Pawn(Player.WHITE);
		board[6][4] = new Pawn(Player.WHITE);
		board[6][5] = new Pawn(Player.WHITE);
		board[6][6] = new Pawn(Player.WHITE);
		board[6][7] = new Pawn(Player.WHITE);

	}

	/************************************************************
	 * Gives the current player in game.
	 * 
	 * @return  Current Player
	 ************************************************************/
	@Override
	public final Player currentPlayer() {
		return curPlayer;
	}

	/************************************************************
	 * Returns if the game is in check, regardless of color.
	 * 
	 * @return  True if game is in check
	 ************************************************************/
	@Override
	public final boolean inCheck() {
		boolean isInCheck = false;

		//Clearing arrayList to avoid
		attackMovesB.clear();
		attackMovesW.clear();

		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {

				if (board[r][c] == null) {
					continue;
				}

				//Checks if piece at board is a king in check
				if (board[r][c].type().equals("King") 
						&& ((King) board[r][c]).isInCheck(r, c, board)) {
					isInCheck = true;

					//Checks player color to fill appropriate array list
					if (board[r][c].player() == Player.BLACK) {
						attackMovesB = ((King) board[r][c]).getAttackers();
					} else {
						attackMovesW = ((King) board[r][c]).getAttackers();
					}
				}
			}
		}
		return isInCheck;
	}

	/************************************************************
	 * Returns if there is a checkmate or not.
	 * 
	 * @return True if there is a checkmate
	 ************************************************************/
	@Override
	public final boolean isComplete() {

		//Checks if in check first, also as result fills
		//array lists of attackers
		if (!inCheck()) {
			return false;
		}

		//Possible king moves
		int[] MOVE_R = {-1, -1, 0, 1, 1, 1, 0, -1};
		int[] MOVE_C = {0, 1, 1, 1, 0, -1, -1, -1};

		//Checks if black king is in check
		if (attackMovesB.size() == 1) {
			int kingR = attackMovesB.get(0).toRow;
			int kingC = attackMovesB.get(0).toColumn;

			//Checks if king can escape
			for (int k = 0; k < MOVE_R.length; k++) {

				King piece = ((King) board[kingR][kingC]);
				int row = MOVE_R[k] + kingR;
				int col = MOVE_C[k] + kingC;

				//Checks if king can safely move away
				if (piece.isValidMove(new Move(kingR, kingC, row, col), board) 
						&& !piece.isInCheck(row, col, board)) {
					return false;
				}
			}

			//Otherwise, checks if piece can block move
			for (int r = 0; r < board.length; r++) {
				for (int c = 0; c < board[0].length; c++) {

					if (board[r][c] == null) {
						continue;
					}
					
					if (board[r][c].player() != Player.BLACK 
							|| board[r][c].type().equals("King")) {
						continue;
					}


					//Gets path the attacking piece plans to take
					ArrayList<Point> path = pathGetter(attackMovesB.get(0));

					//Sees if piece can block attacking pieces path
					for (Point p: path) {
						if (board[r][c].isValidMove(new Move(r, c, 
								(int) p.getX(), (int) p.getY()), board)) {
							return false;
						}
					}
				}
			}

			//Checks if white king is in check
		} else if (attackMovesW.size() == 1) {
			int kingR = attackMovesW.get(0).toRow;
			int kingC = attackMovesW.get(0).toColumn;


			//Loops through possible king escapes
			for (int k = 0; k < MOVE_R.length; k++) {

				King piece = ((King) board[kingR][kingC]);
				int row = MOVE_R[k] + kingR;
				int col = MOVE_C[k] + kingC;

				//Checks if move is valid and if it will take king out of check
				if (piece.isValidMove(new Move(kingR, kingC, row, col), board) 
						&& !piece.isInCheck(row, col, board)) {
					return false;
				}

			}

			//Loops through board
			for (int r = 0; r < board.length; r++) {
				for (int c = 0; c < board[0].length; c++) {

					if (board[r][c] == null) {
						continue; 
					}
					
					if (board[r][c].player() != Player.WHITE 
							|| board[r][c].type().equals("King")) {
						continue;
					}

					//Gets intended attack path
					ArrayList<Point> path = pathGetter(attackMovesW.get(0));

					//Loops through intended path and tries to block
					for (Point p: path) {
						if (board[r][c].isValidMove(new Move(r, c, 
								(int) p.getX(), (int) p.getY()), board)) {
							return false;
						}
					}
				}
			}
		}

		gameInProgress = false;
		return true;
	}


	/************************************************************
	 * Gets the path traveled by the attacking piece.
	 * 
	 * @param move Intended move by attacking piece
	 * @return  ArrayList of points along path
	 ************************************************************/
	private ArrayList<Point> pathGetter(final Move move) {
		ArrayList<Point> path = new ArrayList<Point>();

		int fromR = move.fromRow;
		int fromC = move.fromColumn;

		//Checking if the methods of ChessPiece can be used
		if (board[fromR][fromC].type().equals("Queen") 
				|| board[fromR][fromC].type().equals("Rook") 
				|| board[fromR][fromC].type().equals("Bishop")) {

			//			board[fromR][fromC].isValidMove(move, board);
			((ChessPiece) board[fromR][fromC]).isPathClear(
					fromR, fromC, move.toRow, move.toColumn, board);
			path = ((ChessPiece) board[fromR][fromC]).getPiecePath();

			//Checking if piece has no path, and just needs
			//to be removed to block
		} else if (board[fromR][fromC].type().equals("Pawn") 
				|| board[fromR][fromC].type().equals("Knight")) {
			path.add(new Point(fromR, fromC));

			//Else case is for kings which dont attack.
		} else {
			path = null;
		}

		return path;
	}

	/************************************************************
	 * isValidMove for model, returns if  a move is valid.
	 * 
	 * @param move Move being checked for validity
	 * @return  True if move is valid
	 ************************************************************/
	@Override
	public final boolean isValidMove(final Move move) {

		int toR = move.toRow;
		int toC = move.toColumn;
		int fromR = move.fromRow;
		int fromC = move.fromColumn;

		//Doesnt allow pieces to move out of turn
		if (board[fromR][fromC] == null) {
			return false;
		}
		
		if (board[fromR][fromC].player() != currentPlayer()) {
			return false;
		}

		//Doesnt allow invalid moves for piece behavior
		if (!board[fromR][fromC].isValidMove(move, board)) {
			return false;
		}

		//If game is in check, tries to move pieces out of check
		if (inCheck()) {
			ArrayList<Point> path;

			//Sets path to appropriate attacking path
			if (Player.BLACK == curPlayer && !attackMovesB.isEmpty()) {
				path = pathGetter(attackMovesB.get(0));	
			} else if (Player.WHITE == curPlayer && !attackMovesW.isEmpty()) {
				path = pathGetter(attackMovesW.get(0));
			} else {
				return true;
			}

			//Checks if king can move away
			if (board[fromR][fromC].type().equals("King")) {
				if (((King) board[fromR][fromC]).isInCheck(toR, toC, board)) {
					return false;
				}

				//Checks if piece intends to move into block attack
			} else if (!path.contains(new Point(toR, toC))) {
				return false;
			}
		}
		return true;
	}

	/************************************************************
	 * Method to actually move pieces around on the board.
	 * 
	 * @param move Desired move to be made
	 ************************************************************/
	@Override
	public final void move(final Move move) {
		if (!isValidMove(move)) {
			throw new IllegalArgumentException("Not a valid move");
		}

		board[move.toRow][move.toColumn] = board[move.fromRow][move.fromColumn];
		board[move.fromRow][move.fromColumn] = null;
		curPlayer = curPlayer.next();
	}

	/************************************************************
	 * Returns the number of columns in board[][].
	 * 
	 * @return  Length of board[0]
	 ************************************************************/
	@Override
	public final int numColumns() {
		return board[0].length;
	}

	/************************************************************
	 * Returns the number of rows in board[][].
	 * 
	 * @return  Length of board
	 ************************************************************/
	@Override
	public final int numRows() {
		return board.length;
	}

	/************************************************************
	 * returns piece at the given location on the board.
	 * 
	 * @param row Row where piece is found
	 * @param col Column where piece is found
	 * @return  IChessPiece from the board at desired location
	 ************************************************************/
	@Override
	public final IChessPiece pieceAt(final int row, final int col) {
		return board[row][col];
	}

}
