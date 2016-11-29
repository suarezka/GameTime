package game;

/***************************************************
 * Class for Bishop Chess Piece.
 *
 * @author Nate Benson, Jake Young, Kaye Suarez
 * @version 2.0
 ***************************************************/

public class Rook extends ChessPiece {

	/************************************************************
	 * Constructor for the rook class.
	 * 
	 * @param p Player to be owning this rook
	 ************************************************************/
	public Rook(final Player p) {
		super(p);
	}

	/************************************************************
	 * Returns the type of this piece, "Rook".
	 *  
	 * @return  String word that describes type of piece (rook)
	 ************************************************************/
	@Override
	public final String type() {
		return "Rook";
	}

	/************************************************************
	 * Determines if a rook can make the desired move.
	 * 
	 * @param move Move desired to be made by rook
	 * @param board current board
	 * @return  True if is valid move
	 ************************************************************/
	public final boolean isValidMove(final Move move, 
										final IChessPiece[][] board) {

		//Getting move data for Rook piece
		int fromC = move.getCurrentCol();
		int fromR = move.getCurrentRow();
		int toC = move.getNewCol();
		int toR = move.getNewRow();

		//Parent isValidMove method
		if (!super.isValidMove(move, board)) {
			return false;
		}


		//Check if move is either front/back or side to side
		if (fromR != toR && fromC != toC) {
			return false;
		}

		//Check if horizontal / vertical path is clear
		if (!isPathClear(fromR, fromC, toR, toC, board)) {
			return false;
		}
		
		//Checks if destination contains piece that is friendly
		if (board[toR][toC] != null 
				&& board[toR][toC].player() == this.player()) {
			return false;
		}

		return true;
	}
}

