package chess;

import java.util.ArrayList;

import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;

/**********************************************************************
 * King class controls the King game pieces.
 * 
 * @author Nate Benson, Kaye Suarez, Jake Young
 * @version 1.0 
 **********************************************************************/
public class King extends ChessPiece {
	
	/** Array List to keep track of who can attack. */
	private ArrayList<Move> attackers;

	/************************************************************
	 * Constructor for the King Class.
	 * 
	 * @param p Player that owns this piece
	 ************************************************************/
	public King(final Player p) {
		super(p);
		attackers = new ArrayList<Move>();
	}

	/************************************************************
	 * Returns that this piece is a king.
	 * 
	 * @return  Returns word king in string form
	 ************************************************************/
	@Override
	public final String type() {
		return "King";
	}

	/************************************************************
	 * Returns if this is a valid move for a king or not.
	 * 
	 * @param move Desired move
	 * @param board Board being played on
	 * @return  True if is a valid move
	 ************************************************************/
	public final boolean isValidMove(final Move move, 
									final IChessPiece[][] board) {

		//Consulting parent class
		if (!super.isValidMove(move, board)) {
			return false;
		}

		//Getting move data for King piece
		int fromC = move.fromColumn;
		int fromR = move.fromRow;
		int toC = move.toColumn;
		int toR = move.toRow;

		//Coordinates for possible moves by a king object
		final int[] MOVE_ROW = {-1, -1, 0, 1, 1, 1, 0, -1};
		final int[] MOVE_COL = {0, 1, 1, 1, 0, -1, -1, -1};
		
		//Looping through surrounding area around to destination
		for (int m = 0; m < MOVE_ROW.length; m++) {
			int row = toR + MOVE_ROW[m]; 
			int col = toC + MOVE_COL[m];
			
			//Avoiding index out of bounds
			if (row >= board.length || col >= board[0].length 
					|| row < 0 || col < 0) {
				continue;
			}
			
			//Checking if there's an opposite color king nearby 
			//to avoid infinite looping
			if (board[row][col] != null 
					&& board[row][col].type().equals("King") 
					&& board[row][col].player() != this.player()) {
				return false;
			}
		}

		//Disallows moving into a check position
//		if(isInCheck(toR, toC, board)){
//			return false;
//		}


		//Checks only possible moves for match with desired move
		//Returns true if all previous conditions met as well as this
		for (int k = 0; k < MOVE_ROW.length; k++) {
			if (fromR + MOVE_ROW[k] == toR && fromC + MOVE_COL[k] == toC) {
				return true;
			}
		}

		return false;
	}

	/************************************************************
	 * Returns true when the king is in check.
	 * 
	 * @param row Row of the king
	 * @param col Column of the king
	 * @param board Board being played on
	 * @return  True when the king is in check
	 ************************************************************/
	public final boolean isInCheck(final int row, final int col, 
									final IChessPiece[][] board) {
		boolean inCheck = false;
		
		//Clearing attackers list to avoid duplicates
		attackers.clear();
		
		//Looping through board
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				
				//Checks if there is a player and if the player 
				//is different than this player
				if (board[r][c] != null && board[r][c].player() 
						!= this.player()) {
					Move move = new Move(r, c, row, col);
					
					//Sees if piece can move to king
					if (board[r][c].isValidMove(move, board)) {
						inCheck = true;
						attackers.add(move);
					}
				}
			}
		}

		return inCheck;
	}
	
	/************************************************************
	 * Getter for attacker list.
	 * 
	 * @return  Attacker ArrayList
	 ************************************************************/
	public final ArrayList<Move> getAttackers() {
		return attackers;
	}

}
