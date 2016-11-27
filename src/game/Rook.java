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

		/*holds a step value*/
		int stepValue = 0;	
		//check the super 
		if (super.isValidMove(move, board)) {
			//check that its a valid Rook move
			if (!(move.fromRow == move.toRow 
					|| move.fromColumn == move.toColumn)) {
				return false;
				
			} else {
				//check that to move is along a row
				if (move.toRow - move.fromRow != 0) {
					stepValue = 
							(-(move.fromRow - move.toRow)
									/ Math.abs(move.fromRow - move.toRow));
					
					
					for (int i = (move.fromRow + stepValue); 
								i != move.toRow; i = i + stepValue) {
						
						if (board[i][move.fromColumn] != null) {
							return false;
						}
					}
					//hasMoved = true;
					return true;
					
				} else {
					stepValue = 
							(-(move.fromColumn - move.toColumn) 
									/ Math.abs(move.fromColumn - move.toColumn));
					
					for (int i = (move.fromColumn + stepValue); 
								i != move.toColumn; i = i + stepValue) {
						if (board[move.fromRow][i] != null) {
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

