package game;


/***************************************************
 * Class for Bishop Chess Piece.
 *
 * @author Nate Benson, Jake Young, Kaye Suarez
 * @version 2.0
 ***************************************************/
public class Bishop extends ChessPiece {
	
	/** Player. */
	private Player owner;
	
	
	/*******************************
	 * Sets owner of piece. 
	 * @param player owner of piece
	 *******************************/
	protected Bishop(final Player player) {
		super(player);
		this.owner = player;
	}
	
	
	/*************************
	 * Returns type of piece.
	 * @return type
	 *************************/
	public final String type() {
		return "Bishop";
	}
	
	
	/*************************
	 * Returns owner of piece.
	 * @return owner
	 *************************/
	public final Player player() {
		return owner;
	}
	
	
	/***************************************
	 * Checks valid Bishop piece moves.
	 * @param move new bishop position
	 * @param board current board
	 * @return true if valid, false if not
	 ***************************************/
	public final boolean isValidMove(final Move move, 
								final IChessPiece[][] board) {
		
		int row = move.getCurrentRow();
		int col = move.getCurrentCol();
		int newR = move.getNewRow();
		int newC = move.getNewCol();
		
		
		if (!(super.isValidMove(move, board))) {
			return false;
		}
		
		if (!(Math.abs(row - newR)
				== Math.abs(col - newC))) {
			return false;
		}
		
		if (newR > row) {
			//top right
			if (newC > col) {
				for (int j = row + 1, 
						i = col + 1; 
						j != newR && i != newC; j++, i++) {
					
					if (board[j][i] != null) {
						return false;
					}
				}
			}
			//bottom right
			if (newC < col) {
				for (int j = row + 1, 
						i = col - 1; 
						j != newR && i != newC; j++, i--) {
					
					if (board[j][i] != null) {
						return false;
					}
				}
			}
		}
		if (newR < row) {
			//top left
			if (newC > col) {
				for (int j = row - 1, 
						i = col + 1;
						j != newR && i != newC; j--, i++) {
					
					if (board[j][i] != null) {
						return false;
					}
				}
			}
			//bottom left
			if (newC < col) {
				for (int j = row - 1, 
						i = col - 1; 
						j != newR && i != newC; j--, i--) {
					
					if (board[j][i] != null) {
						return false;
					}
				}
			}
		}
		return true;
	}
}

