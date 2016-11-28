package game;

/***************************************************
 * Class for Bishop Chess Piece.
 *
 * @author Nate Benson, Jake Young, Kaye Suarez
 * @version 2.0
 ***************************************************/

public class Rook extends ChessPiece {

	/** Rook hasMoved boolean. */
	public boolean hasMoved = false;

	/********************************
	 * Sets player of piece.
	 * @param player owner of piece
	 ********************************/
	protected Rook(final Player player) {
		super(player);
		this.owner = player;
	}

	/** Owner of piece. */
	private Player owner;


	/************************
	 * Return type of piece.
	 * @return type
	 ************************/
	public final String type() {
		return "Rook";
	}


	/*************************
	 * Return owner of piece.
	 * @return owner
	 ************************/
	public final Player player() {
		return owner;
	}


	/*******************************************
	 * Returns valid move for Rook.
	 * @param move given move
	 * @param board current board
	 * @return true if valid, false if not
	 ******************************************/
	public final boolean isValidMove(
			final Move move, final IChessPiece[][] board) {
		
		int row = move.getCurrentRow();
		int col = move.getCurrentCol();
		int newR = move.getNewRow();
		int newC = move.getNewCol();

		/*holds a step value*/
		int stepValue = 0;	
		//check the super 
		if (super.isValidMove(move, board)) {
			//check that its a valid Rook move
			if (!(row == newR || col == newC)) {
				return false;
				
			} else {
				//check that to move is along a row
				if (newR - row != 0) {
					stepValue = (-(row - newR) / Math.abs(row - newR));
					
					
					for (int i = (row + stepValue); 
							 i != newR; i = i + stepValue) {
						
						if (board[i][col] != null) {
							return false;
						}
					}
					//hasMoved = true;
					return true;
					
				} else {
					stepValue = 
							(-(col - newC) / Math.abs(col - newC));
					
					for (int i = (col + stepValue); 
								i != newC; i = i + stepValue) {
						
						if (board[row][i] != null) {
							return false;
						}
					}
					//hasMoved = true;
					return true;
				}
			}
		} else {
			return false;
		}
	}
}

