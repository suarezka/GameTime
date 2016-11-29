package chess;

import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;

/**********************************************************************
 * Bishop class controls Bishop game pieces.
 * 
 * @author Nate Benson, Kaye Suarez, Jake Young
 * @version 1.0 
 **********************************************************************/
public class Bishop extends ChessPiece {

	/************************************************************
	 * Constructor for the Bishop Class.
	 * 
	 * @param p Player that owns this piece
	 ************************************************************/
	public Bishop(final Player p) {
		super(p);
	}

	/************************************************************
	 * Returns that this piece is a Bishop.
	 * 
	 * @return  Returns word Bishop in string form
	 ************************************************************/
	@Override
	public final String type() {
		return "Bishop";
	}

	/************************************************************
	 * Returns if this is a valid move for a Bishop or not.
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

		//Getting move data for Rook piece
		int fromC = move.fromColumn;
		int fromR = move.fromRow;
		int toC = move.toColumn;
		int toR = move.toRow;


		//makes sure its a true diagonal path
		if (Math.abs(fromC - toC) != Math.abs(fromR - toR)) {
			return false;
		}
		
		//Check if diagonal path is clear
		if (!isPathClear(fromR, fromC, toR, toC, board)) {
			return false;
		}

		return true;
	}
}
