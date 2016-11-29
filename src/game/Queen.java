package game;

/***************************************************
 * Class for Queen Chess Piece.
 *
 * @author Nate Benson, Jake Young, Kaye Suarez
 * @version 2.0
 ***************************************************/

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
	
	/**************************************************************
	 * Returns whether or not a move is valid for the queen piece.
	 * 
	 * @param move Desired move
	 * @param board Board played on
	 * @return  True if the move is valid for a queen
	 **************************************************************/
	public final boolean isValidMove(final Move move, 
										final IChessPiece[][] board) {
	
		//Checking with parent class
		if (!super.isValidMove(move, board)) {
			return false;
		}
		
		//Getting move data for Queen piece
		int fromC = move.getCurrentCol();
		int fromR = move.getCurrentRow();
		int toC = move.getNewCol();
		int toR = move.getNewRow();

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

