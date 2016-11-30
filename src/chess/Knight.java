package chess;

import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;

/**********************************************************************
 * Knight class controls Knight game piece.
 * 
 * @author Nate Benson, Kaye Suarez, Jake Young
 * @version 1.0 
 **********************************************************************/
public class Knight extends ChessPiece {

	/************************************************************
	 * Constructor for the knight class.
	 * 
	 * @param p Player who owns the chess Piece
	 ************************************************************/
	public Knight(final Player p) {
		super(p);
	}

	/************************************************************
	 * Returns the type of piece, knight in this case.
	 * 
	 * @return  Returns string of the piece type
	 ************************************************************/
	@Override
	public final String type() {
		return "Knight";
	}

	/************************************************************
	 * Returns if a move is valid for a knight to make.
	 * 
	 * @param move The desired move to be made
	 * @param board The game board which the piece moves on
	 * @return  True if the move is valid
	 ************************************************************/
	public final boolean isValidMove(final Move move, 
									final IChessPiece[][] board) {
		
		//Checking with parent class
		if (!super.isValidMove(move, board)) {
			return false;
		}
		
		int fromC = move.fromColumn;
		int fromR = move.fromRow;
		int toC = move.toColumn;
		int toR = move.toRow;
		
		//Setting up arrays of valid moves for a knight to make
		//Arrays hold coordinates of valid moves for a knight
		final int[] cols = {1, 2, 2, 1, -1, -2, -2, -1};
		final int[] rows = {-2, -1, 1, 2, 2, 1, -1, -2};
		
		//Looping through coordinates to see if moves match valid coordinates
		for (int k = 0; k < cols.length; k++) {
			if (toC == cols[k] + fromC && toR == rows[k] + fromR) {
				return true;
			}
		}
		
		return false;
	}
	
}
