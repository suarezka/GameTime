package checkers;

/******************************************************
 * King class extends GamePiece for King functionality.
 * 
 * @author Nate Benson, Kaye Suarez, Jake Young  
 * @version 1.0 
 ******************************************************/
public class Kings extends GamePiece {
	
	/** Owner of the piece. */
	public Player owner;
	
	/******************************* 
	 * Constructor for King piece.
	 * @param player owner of piece
	 *******************************/
	protected Kings(final Player player) {
		this.owner = player;
	}
	
	/********************************
	 * Return owner of piece.
	 * @return player owner of piece
	 *******************************/
	public final Player player() {
		return owner;
	}

	@Override
	public final String type() {
		return "King";
	}

	@Override
	public final boolean isValidMove(
			             final Move move, final IGamePiece[][] board) {
		
		int row = move.getCurrentRow();
		int col = move.getCurrentCol();
		int newR = move.getNewRow();
		int newC = move.getNewCol();
		
		if (row != newR && col != newC) {
			if (Math.abs(row - newR) == 1) {
				if (Math.abs(col - newC) == 1) {
					return true;
				}
			}
			//Jumps
			//up
			if (newR - row == 2) {
				//right
				if (newC - col == 2) {
					if (board[row + 1][col + 1] != null) {
						if (board[row + 1][col + 1]
								.player() != owner) {
							return true;
						}
					}
				}
				//left
				if (col - newC == 2) {
					if (board[row + 1][col - 1] != null) {
						if (board[row + 1][col - 1]
								.player() != owner) {
							return true;
						}
					}
				}
			}
			//down jump
			if (row - newR == 2) {
				//right
				if (newC - col == 2) {
					if (board[row - 1][col + 1] != null) {
						if (board[row - 1][col + 1]
								.player() != owner) {
							return true;
						}
					}
				}
				//left
				if (col - newC == 2) {
					if (board[row - 1][col - 1] != null) {
						if (board[row - 1][col - 1]
								.player() != owner) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
