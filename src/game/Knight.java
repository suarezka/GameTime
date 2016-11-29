package game;


/***************************************************
 * Class for King Chess Piece.
 *
 * @author Nate Benson, Jake Young, Kaye Suarez
 * @version 2.0
 ***************************************************/
public class Knight extends ChessPiece {
	
	/************************************************************
	 * Constructor for the knight class.
	 * 
	 * @param p Player who owns the chess Piece
	 ************************************************************/
	public Knight(final Player p) {
		super(p);
	}

	/************************************************************
	 * Returns the type of piece, knight in this case.
	 * 
	 * @return  Returns string of the piece type
	 ************************************************************/
	@Override
	public final String type() {
		return "Knight";
	}

	/************************************************************
	 * Returns if a move is valid for a knight to make.
	 * 
	 * @param move The desired move to be made
	 * @param board The game board which the piece moves on
	 * @return  True if the move is valid
	 ************************************************************/
	public final boolean isValidMove(final Move move, 
									final IChessPiece[][] board) { 
		
		//Checking with parent class
		if (!super.isValidMove(move, board)) {
			return false;
		}
		
		int fromC = move.getCurrentCol();
		int fromR = move.getCurrentRow();
		int toC = move.getNewCol();
		int toR = move.getNewRow();
		
		//Setting up arrays of valid moves for a knight to make
		//Arrays hold coordinates of valid moves for a knight
		final int[] COLS = {1, 2, 2, 1, -1, -2, -2, -1};
		final int[] ROWS = {-2, -1, 1, 2, 2, 1, -1, -2};
		
		//Looping through coordinates to see if moves match valid coordinates
		for (int k = 0; k < COLS.length; k++) {
			if (toC == COLS[k] + fromC && toR == ROWS[k] + fromR) {
				return true;
			}
		}
		
		return false;
	}
}

