package game;

/***************************************************
 * Class for Queen Chess Piece.
 *
 * @author Nate Benson, Jake Young, Kaye Suarez
 * @version 2.0
 ***************************************************/

public class Queen extends ChessPiece {
	
	/** Player of piece. */
	private Player owner;

	
	/********************************
	 * Sets owner of piece.
	 * @param player owner of piece.
	 ********************************/
	protected Queen(final Player player) {
		super(player);
		this.owner = player;
	}

	
	/*************************
	 * Returns type of piece.
	 * @return type
	 *************************/
	public final String type() {
		return "Queen";
	}
	
	/**************************
	 * Return owner of piece.
	 * @return owner
	 **************************/
	public final Player player() {
		return owner;
	}
	
	
	/*******************************************
	 * Returns valid move for Queen.
	 * @param move given move
	 * @param board current board
	 * @return true if valid, false if not
	 ******************************************/
	public final boolean isValidMove(
				final Move move, final IChessPiece[][] board) {
		
		if (!(super.isValidMove(move, board))) {
			return false;
		}
		if (!(Math.abs(move.fromRow - move.toRow) 
				== Math.abs(move.fromColumn - move.toColumn))) {
			if (!(move.fromRow == move.toRow 
					|| move.fromColumn == move.toColumn)) {
				return false;
			}
		}
		if (move.toRow > move.fromRow) {
			
			//SOUTHEAST
			if (move.toColumn > move.fromColumn) {
				for (int j = move.fromRow + 1, 
						i = move.fromColumn + 1; 
						j != move.toRow && i != move.toColumn; j++, i++) {
					
					if (board[j][i] != null) {
						return false;
					}
				}
			}
			
			//SOUTH
			if (move.toColumn == move.fromColumn) {
				for (int i = move.fromRow + 1; i != move.toRow; i++) {
					
					if (board[i][move.fromColumn] != null) {
						return false;
					}
				}
			}
			
			//SOUTHWEST
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
			
			//NORTHWEST
			if (move.toColumn > move.fromColumn) {
				for (int j = move.fromRow - 1, 
						i = move.fromColumn + 1; 
						j != move.toRow && i != move.toColumn; j--, i++) {
					
					if (board[j][i] != null) {
						return false;
					}
				}
			}
			
			//NORTH
			if (move.toColumn == move.fromColumn) {
				
				for (int i = move.toRow + 1; 
						i != move.fromRow; i++) {
					
					if (board[i][move.fromColumn] != null) {
						return false;
					}
				}
			}
			
			//NORTHWEST
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
		
		if (move.toRow == move.fromRow) {
			
			//EAST
			if (move.toColumn > move.fromColumn) {
				for (int i = move.fromColumn + 1; i != move.toColumn; i++) {
					if (board[move.fromRow][i] != null) {
						return false;
					}
				}
			}
			
			//WEST
			if (move.toColumn < move.fromColumn) {
				for (int i = move.toColumn + 1; i != move.fromColumn; i++) {
					if (board[move.fromRow][i] != null) {
						return false;
					}
				}
			}
		}
		return true;
	}

}

