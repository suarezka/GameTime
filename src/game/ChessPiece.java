package game;

public abstract class ChessPiece implements IChessPiece {
	
	/** Owner of piece. */
	private Player owner;
	
	
	/*******************************
	 * Creates ChessPiece.
	 * @param player owner of piece
	 *******************************/
	protected ChessPiece(final Player player) {
		this.owner = player;
	}
	
	/************************* 
	 * Return type of piece. 
	 * @return type
	 * ***********************/
	public abstract String type();
	
	
	/***************************
	 * Returns owner of piece.
	 * @return owner
	 ***************************/
	public Player player() {
		return owner;
	}
	
	
	/***************************************
	 * Checks valid Chess piece moves.
	 * @param move current and new position
	 * @param board current board
	 * @return true if valid, false if not
	 ***************************************/
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		
		int row = move.getCurrentRow();
		int col = move.getCurrentCol();
		int newR = move.getNewRow();
		int newC = move.getNewCol();
		
		
		//check that move is a move
	 	if (row == newR && col == newC) {
	 		return false;
	 		
	 	//check that move is on the board
	 	} else if (newR < 0 || newR > 7 
	 			|| newC < 0 || newC > 7) {
	 		return false;
	 		
	 	//prevent friendly taking	
	 	} else if (board[newR][newC] != null) {
	 		if (board[newR][newC].player() == owner) {
	 			return false;
	 		}
	 	}
	 	return true;
	}
}


