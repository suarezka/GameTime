package chess;

import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;

/**********************************************************************
 * Queen class controls the Queen game piece.
 * 
 * @author Nate Benson, Kaye Suarez, Jake Young
 * @version 1.0 
 **********************************************************************/
public class Queen extends ChessPiece {

	/************************************************************
	 * Constructor for the Queen class.
	 * 
	 * @param p Player that owns this piece
	 ************************************************************/
	public Queen(final Player p) {
		super(p);
	}

	/************************************************************
	 * Returns the type of piece this is, which is Queen.
	 * 
	 * @return  Returns "Queen"
	 ************************************************************/
	@Override
	public final String type() {
		return "Queen";
	}
	
	/************************************************************
	 * Returns whether or not a move is valid for the queen piece.
	 * 
	 * @param move Desired move
	 * @param board Board played on
	 * @return  True if the move is valid for a queen
	 ************************************************************/
	public final boolean isValidMove(final Move move, 
									final IChessPiece[][] board) {
	
		//Checking with parent class
		if (!super.isValidMove(move, board)) {
			return false;
		}
		
		//Getting move data for Queen piece
		int fromC = move.fromColumn;
		int fromR = move.fromRow;
		int toC = move.toColumn;
		int toR = move.toRow;

		//Check if move is either front/back or side to side
		if (fromR != toR && fromC != toC) {
			//makes sure its a true diagonal path
			if (Math.abs(fromC - toC) != Math.abs(fromR - toR)) {
				return false;
			}
		}
		
		
		
		//Check horizontal and vertical path
		if (!isPathClear(fromR, fromC, toR, toC, board)) {
			return false;
		}
		
		return true;
	}

}
