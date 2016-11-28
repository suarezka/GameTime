package game;


/***************************************************
 * Class for King Chess Piece.
 *
 * @author Nate Benson, Jake Young, Kaye Suarez
 * @version 2.0
 ***************************************************/
public class Knight extends ChessPiece {

	
	/********************************
	 * Create Knight piece.
	 * @param player owner of piece.
	 ********************************/
	protected Knight(final Player player) {
		super(player);
		this.owner = player;
	}

	/** Owner of piece. */
	private Player owner;
	
	
	/*************************
	 * Returns type of piece.
	 * @return type
	 *************************/
	public final String type() {
		return "Knight";
	}
	
	
	/**************************
	 * Return owner of piece.
	 * @return owner
	 **************************/
	public final Player player() {
		return owner;
	}
	
	
	/*******************************************
	 * Returns valid move for King.
	 * @param move given move
	 * @param board current board
	 * @return true if valid, false if not
	 ******************************************/
	public final boolean isValidMove(final Move move, 
							final IChessPiece[][] board) {
		
		int row = move.getCurrentRow();
		int col = move.getCurrentCol();
		int newR = move.getNewRow();
		int newC = move.getNewCol();
		
		if (super.isValidMove(move, board)) {
			//checks that the move is either two(2) rows and one(1) column or 
			//two(2) columns and one(1) row
			if (((Math.abs(row - newR) == 2) && (Math.abs(col - newC) == 1)) 
							|| ((Math.abs(col - newC) == 2) 
							&& (Math.abs(row - newR) == 1))) {
				return true;
			}
			
		} else {
			return false;
		}
		
		return false;
	}
}

