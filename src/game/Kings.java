package game;

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
		
		if (move.fromRow != move.toRow && move.fromColumn != move.toColumn) {
			if (Math.abs(move.fromRow - move.toRow) == 1) {
				if (Math.abs(move.fromColumn - move.toColumn) == 1) {
					return true;
				}
			}
			//Jumps
			//up
			if (move.toRow - move.fromRow == 2) {
				//right
				if (move.toColumn - move.fromColumn == 2) {
					if (board[move.fromRow + 1][move.fromColumn + 1] != null) {
						if (board[move.fromRow + 1][move.fromColumn + 1]
								.player() != owner) {
							return true;
						}
					}
				}
				//left
				if (move.fromColumn - move.toColumn == 2) {
					if (board[move.fromRow + 1][move.fromColumn - 1] != null) {
						if (board[move.fromRow + 1][move.fromColumn - 1]
								.player() != owner) {
							return true;
						}
					}
				}
			}
			//down jump
			if (move.fromRow - move.toRow == 2) {
				//right
				if (move.toColumn - move.fromColumn == 2) {
					if (board[move.fromRow - 1][move.fromColumn + 1] != null) {
						if (board[move.fromRow - 1][move.fromColumn + 1]
								.player() != owner) {
							return true;
						}
					}
				}
				//left
				if (move.fromColumn - move.toColumn == 2) {
					if (board[move.fromRow - 1][move.fromColumn - 1] != null) {
						if (board[move.fromRow - 1][move.fromColumn - 1]
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
