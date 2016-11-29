package game;

/***************************************************
 * Class for Pawn Chess Piece.
 *
 * @author Nate Benson, Jake Young, Kaye Suarez
 * @version 2.0
 ***************************************************/
public class Pawn extends ChessPiece {

	/************************************************************
	 * Constructor for Pawn Class.
	 * 
	 * @param p Player that owns this piece
	 ************************************************************/
	public Pawn(final Player p) {
		super(p);
	}

	/************************************************************
	 * Returns the piece type, pawn.
	 * 
	 * @return  Returns "Pawn"
	 ************************************************************/
	@Override
	public final String type() {
		return "Pawn";
	}

	/************************************************************
	 * Returns if is a valid move for a pawn.
	 * 
	 * @param move Desired move
	 * @param board Board being played on
	 * @return  True if is a valid move for a pawn
	 ************************************************************/
	public final boolean isValidMove(final Move move, 
										final IChessPiece[][] board) {

		//Consulting parent class
		if (!super.isValidMove(move, board)) {
			return false;
		}

		int fromC = move.getCurrentCol();
		int fromR = move.getCurrentRow();
		int toC = move.getNewCol();
		int toR = move.getNewRow();

		final int WHITE_START = 6;
		final int BLACK_START = 1;

		//Checking if is starting from colors starting row
		if ((fromR == WHITE_START && player() == Player.WHITE) 
				|| (fromR == BLACK_START && player() == Player.BLACK)) {

			//Seeing if move is bigger than two squares
			if (Math.abs(fromR - toR) > 2) {
				return false;
			}
			
		} else {

			//Checking if move is bigger than one square 
			//for not first moves
			if (Math.abs(fromR - toR) > 1) {
				return false;
			}
		}

		if (Math.abs(fromC - toC) > 1) {
			return false;
		}

		//Capture stuff
		if (Math.abs(fromC - toC) == 1 && Math.abs(fromR - toR) == 1) {
			if (board[toR][toC] != null && board[toR][toC].player()
					== player().next()) {
				return true;
			} else {
				return false;
			}
		}

		if (board[toR][toC] != null) {
			return false;
		}

		//Doesnt allow double more no row movement when attacking
		if (Math.abs(fromC - toC) > 0 && Math.abs(fromR - toR) != 1) {
			return false;
		}

		//Doesnt let pieces move backwards
		if (Player.WHITE == player() && board[fromR - 1][toC] != null) {
			return false;
		}
		if (Player.BLACK == player() && board[fromR + 1][toC] != null) {
			return false;
		}

		//Backup disallowing backwards movement
		if (Player.WHITE == player() && fromR - toR < 0) {
			return false;
		}
		if (Player.BLACK == player() && fromR - toR > 0) {
			return false;
		}

		return true;
	}

}
