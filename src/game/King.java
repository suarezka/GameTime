package game;

/***************************************************
 * Class for King Chess Piece.
 *
 * @author Nate Benson, Jake Young, Kaye Suarez
 * @version 2.0
 ***************************************************/
public class King extends ChessPiece {

	/** Player of piece. */
	private Player owner;
	
	/** hasMoved value. */
	public boolean hasMoved = false;

	
	/********************************
	 * Create King piece.
	 * @param player owner of piece.
	 ********************************/
	protected King(final Player player) {
		super(player);
		this.owner = player;
	}

	/*************************
	 * Returns type of piece.
	 * @return type
	 *************************/
	public final String type() {
		return "King";
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
			if (Math.abs(newR - row) > 1 || Math.abs(newC - col) > 1) {
				
				return false;
				
			} else {
				//hasMoved = true;
				return true;
			}
		}
		return false;
	}
}
