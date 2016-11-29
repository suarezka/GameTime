package chess;

import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;

/************************************************************
 * CIS 163-07
 * Chess Project
 * Class to handle the behavior for the knight piece
 *
 * @author DaiLynn Dietz
 * @author Kaye Suarez
 * @version Mar 18, 2014
 ************************************************************/
public class Knight extends ChessPiece{

	/************************************************************
	 * Constructor for the knight class
	 * 
	 * @param p Player who owns the chess Piece
	 ************************************************************/
	public Knight(Player p) {
		super(p);
	}

	/************************************************************
	 * Returns the type of piece, knight in this case
	 * 
	 * @return  Returns string of the piece type
	 ************************************************************/
	@Override
	public String type() {
		return "Knight";
	}

	/************************************************************
	 * Returns if a move is valid for a knight to make
	 * 
	 * @param move The desired move to be made
	 * @param board The game board which the piece moves on
	 * @return  True if the move is valid
	 ************************************************************/
	public boolean isValidMove(Move move, IChessPiece[][] board){
		
		//Checking with parent class
		if(!super.isValidMove(move, board)){
			return false;
		}
		
		int fromC = move.fromColumn;
		int fromR = move.fromRow;
		int toC = move.toColumn;
		int toR = move.toRow;
		
		//Setting up arrays of valid moves for a knight to make
		//Arrays hold coordinates of valid moves for a knight
		final int[] COLS = {1, 2, 2, 1, -1, -2, -2, -1};
		final int[] ROWS = {-2, -1, 1, 2, 2, 1, -1, -2};
		
		//Looping through coordinates to see if moves match valid coordinates
		for(int k = 0; k < COLS.length; k++){
			if(toC == COLS[k] + fromC && toR == ROWS[k] + fromR){
				return true;
			}
		}
		
		return false;
	}
	
}
