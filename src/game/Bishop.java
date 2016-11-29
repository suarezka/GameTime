package game;


/***************************************************
 * Class for Bishop Chess Piece.
 *
 * @author Nate Benson, Jake Young, Kaye Suarez
 * @version 2.0
 ***************************************************/
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
		if (!super.isValidMove(move, board)){
			return false;
		}

		//Getting move data for Rook piece
		int fromC = move.getCurrentCol();
		int fromR = move.getCurrentRow();
		int toC = move.getNewCol();
		int toR = move.getCurrentRow();


		//makes sure its a true diagonal path
		if (Math.abs(fromC - toC) != Math.abs(fromR - toR)){
			return false;
		}
		
		//Check if diagonal path is clear
		if (!isPathClear(fromR, fromC, toR, toC, board)) {
			return false;
		}

		return true;
	}
}

