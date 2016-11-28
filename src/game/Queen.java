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
		
		int row = move.getCurrentRow();
		int col = move.getCurrentCol();
		int newR = move.getNewRow();
		int newC = move.getNewCol();
		
		if (!(Math.abs(row - newR) 
				== Math.abs(col - newC))) {
			if (!(row == newR 
					|| col == newC)) {
				return false;
			}
		}
		
		if (newR > row) {
			
			//SOUTHEAST
			if (newC > col) {
				for (int j = row + 1, i = col + 1; 
						j != newR && i != newC; j++, i++) {
					
					if (board[j][i] != null) {
						return false;
					}
				}
			}
			
			//SOUTH
			if (newC == col) {
				for (int i = row + 1; i != newR; i++) {
					
					if (board[i][col] != null) {
						return false;
					}
				}
			}
			
			//SOUTHWEST
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
			
			//NORTHWEST
			if (newC > col) {
				for (int j = row - 1, 
						i = col + 1; 
						j != newR && i != newC; j--, i++) {
					
					if (board[j][i] != null) {
						return false;
					}
				}
			}
			
			//NORTH
			if (newC == col) {
				
				for (int i = newR + 1; 
						i != row; i++) {
					
					if (board[i][col] != null) {
						return false;
					}
				}
			}
			
			//NORTHWEST
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
		
		if (newR == row) {
			
			//EAST
			if (newC > col) {
				for (int i = col + 1; i != newC; i++) {
					if (board[row][i] != null) {
						return false;
					}
				}
			}
			
			//WEST
			if (newC < col) {
				for (int i = newC + 1; i != col; i++) {
					if (board[row][i] != null) {
						return false;
					}
				}
			}
		}
		return true;
	}

}

