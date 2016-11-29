package checkers;

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

		int row = move.getCurrentRow();
		int col = move.getCurrentCol();
		int newR = move.getNewRow();
		int newC = move.getNewCol();
		
		//top left is 0-0 and a GRAY Piece
		if (board[row][col].player() == Player.GRAY) {
			
			if ((row != newR) && (col != newC)) {
				if (newR - row == 1) {
					if (Math.abs(col - newC) == 1) {
						return true;
					}
				}
				
				if ((newR == row + 2) && (newC == col - 2)) {
					if (board[row + 1][col - 1] != null 
								&& board[row + 1][col - 1].player() 
								== Player.RED) {
						
						return true;
					}
				}
				if ((newR == row + 2)
						&& (newC == col + 2)) {
					if (board[row + 1][col + 1] != null
							&& board[row + 1][col + 1].player()
							== Player.RED) {
						
						return true;
					}
				}
			}
			return false;
		}
		
		if (board[row][col].player() == Player.RED) {
			if (row != newR && col != newC) {
				if (newR - row == -1) {
					if (Math.abs(col - newC) == 1) {
						return true;
					}
				}
				
				if (row == newR + 2 && (newC == col - 2)) {
					if (board[row - 1][col - 1] != null
							&& board[row - 1][col - 1].player() 
							== Player.GRAY) {
						
						return true;
					}
				}
				
				if (row == newR + 2 && (newC == col + 2)) {
					if (board[row - 1][col + 1] != null
							&& board[row - 1][col + 1].player() 
							== Player.GRAY) {
						
						return true;
					}
				}
			}
			return false;
		}
		return false;
	}
}
