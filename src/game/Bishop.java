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
		
		if (!(super.isValidMove(move, board))) {
			return false;
		}
		
		if (!(Math.abs(move.fromRow - move.toRow)
				== Math.abs(move.fromColumn - move.toColumn))) {
			return false;
		}
		
		if (move.toRow > move.fromRow) {
			//top right
			if (move.toColumn > move.fromColumn) {
				for (int j = move.fromRow + 1, 
						i = move.fromColumn + 1; 
						j != move.toRow && i != move.toColumn; j++, i++) {
					
					if (board[j][i] != null) {
						return false;
					}
				}
			}
			//bottom right
			if (move.toColumn < move.fromColumn) {
				for (int j = move.fromRow + 1, 
						i = move.fromColumn - 1; 
						j != move.toRow && i != move.toColumn; j++, i--) {
					
					if (board[j][i] != null) {
						return false;
					}
				}
			}
		}
		if (move.toRow < move.fromRow) {
			//top left
			if (move.toColumn > move.fromColumn) {
				for (int j = move.fromRow - 1, 
						i = move.fromColumn + 1;
						j != move.toRow && i != move.toColumn; j--, i++) {
					
					if (board[j][i] != null) {
						return false;
					}
				}
			}
			//bottom left
			if (move.toColumn < move.fromColumn) {
				for (int j = move.fromRow - 1, 
						i = move.fromColumn - 1; 
						j != move.toRow && i != move.toColumn; j--, i--) {
					
					if (board[j][i] != null) {
						return false;
					}
				}
			}
		}
		return true;
	}
}

