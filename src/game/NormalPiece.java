package game;

/*********************************************************************
 * NormalPiece extends GamePiece class for normal piece functionality.
 * 
 * @author Nate Benson, Kaye Suarez, Jake Young
 * @version 1.0 
 *********************************************************************/
public class NormalPiece extends GamePiece {

	/** owner of piece. */
	public Player owner;

	/** value of jumping a piece. */
	private static final int JUMP = 2; 

	/*******************************
	 * Sets owner of normal piece.
	 * @param player owner of piece
	 *******************************/
	protected NormalPiece(final Player player) {
		this.owner = player;
	}

	/***************************
	 * Returns owner of piece.
	 * @return owner the player
	 ***************************/
	public final Player player() {
		return owner;
	}

	@Override
	public final String type() {
		return "Pawn";
	}

	@Override
	public final boolean isValidMove(
			final Move move, final IGamePiece[][] board) {

		//top left is 0-0 and a GRAY Piece
		if (board[move.fromRow][move.fromColumn].player() == Player.GRAY) {
			if ((move.fromRow != move.toRow) 
					&& (move.fromColumn != move.toColumn)) {
				if (move.toRow - move.fromRow == 1) {
					if (Math.abs(move.fromColumn - move.toColumn) == 1) {
						return true;
					}
				}
				if ((move.toRow == move.fromRow + 2) 
						&& (move.toColumn == move.fromColumn - 2)) {
					if (board[move.fromRow + 1][move.fromColumn - 1] != null
							&& board[move.fromRow + 1][move.fromColumn - 1]
									.player() == Player.RED) {
						return true;
					}
				}
				if ((move.toRow == move.fromRow + 2)
						&& (move.toColumn == move.fromColumn + 2)) {
					if (board[move.fromRow + 1][move.fromColumn + 1] != null
							&& board[move.fromRow + 1][move.fromColumn + 1]
									.player() == Player.RED) {
						return true;
					}
				}
			}
			return false;
		}
		
		if (board[move.fromRow][move.fromColumn].player() == Player.RED) {
			if (move.fromRow != move.toRow 
					&& move.fromColumn != move.toColumn) {
				if (move.toRow - move.fromRow == -1) {
					if (Math.abs(move.fromColumn - move.toColumn) == 1) {
						return true;
					}
				}
				if (move.fromRow == move.toRow + 2 
						&& (move.toColumn == move.fromColumn - 2)) {
					if (board[move.fromRow - 1][move.fromColumn - 1] != null
							&& board[move.fromRow - 1][move.fromColumn - 1]
									.player() == Player.GRAY) {
						return true;
					}
				}
				if (move.fromRow == move.toRow + 2
						&& (move.toColumn == move.fromColumn + 2)) {
					if (board[move.fromRow - 1][move.fromColumn + 1] != null
							&& board[move.fromRow - 1][move.fromColumn + 1]
									.player() == Player.GRAY) {
						return true;
					}
				}
			}
			return false;
		}
		return false;
	}
}
