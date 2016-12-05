package chess;

import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;

/**********************************************************************
 * Pawn class controls the Pawn game piece.
 * 
 * @author Nate Benson, Kaye Suarez, Jake Young
 * @version 1.0 
 **********************************************************************/
public class Pawn extends ChessPiece {
	
	/************************************************************
	 * Constructor for Pawn Class.
	 * 
	 * @param p Player that owns this piece
	 ************************************************************/
	public Pawn(final Player p) {
		super(p);
	}

	/************************************************************
	 * Returns the piece type, pawn.
	 * 
	 * @return  Returns "Pawn"
	 ************************************************************/
	@Override
	public final String type() {
		return "Pawn";
	}
	
	/************************************************************
	 * Returns if is a valid move for a pawn.
	 * 
	 * @param move Desired move
	 * @param board Board being played on
	 * @return  True if is a valid move for a pawn
	 ************************************************************/
	public final boolean isValidMove(final Move move, 
										final IChessPiece[][] board) {
		
		//Consulting parent class
		if (!super.isValidMove(move, board)) {
			return false;
		}
		
		int fromC = move.fromColumn;
		int fromR = move.fromRow;
		int toC = move.toColumn;
		int toR = move.toRow;
		
		final int whiteStart = 6;
		final int blackStart = 1;
			
		//Checking if is starting from colors starting row
		if ((fromR == whiteStart && player() == Player.WHITE) 
				|| (fromR == blackStart && player() == Player.BLACK)) {
			
			//Seeing if move is bigger than two squares
			if (Math.abs(fromR - toR) > 2) {
				return false;
			}
		} else {

			//Checking if move is bigger than one square 
			//for not first moves
			if (Math.abs(fromR - toR) > 1) {
				return false;
			}
		}
		
		if (Math.abs(fromC - toC) > 1) {
			return false;
		}
		
		//Capture stuff
		if (Math.abs(fromC - toC) == 1 && Math.abs(fromR - toR) == 1) {
			return board[toR][toC] != null && board[toR][toC].player()
					== player().next();
		}
		
		if (board[toR][toC] != null) {
			return false;
		}
		
		//Doesn't allow double more no row movement when attacking
		if (Math.abs(fromC - toC) > 0 && Math.abs(fromR - toR) != 1) {
			return false;
		}
		
		//Doesnt let pieces move backwards
		if (Player.WHITE == player() && board[fromR - 1][toC] != null) {
			return false;
		}
		if (Player.BLACK == player() && board[fromR + 1][toC] != null) {
			return false;
		}
		
		//Backup disallowing backwards movement
		if (Player.WHITE == player() && fromR - toR < 0) {
			return false;
		}
		if (Player.BLACK == player() && fromR - toR > 0) {
			return false;
		}
		
		return true;
	}

}

