package chess;

import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;

/************************************************************
 * CIS 163-07
 * Chess Project
 * Class that controls the rook chess piece behavior
 *
 * @author DaiLynn Dietz
 * @author Kaye Suarez
 * @version Mar 18, 2014
 ************************************************************/
public class Rook extends ChessPiece {

	/************************************************************
	 * Constructor for the rook class
	 * 
	 * @param p Player to be owning this rook
	 ************************************************************/
	public Rook(Player p) {
		super(p);
	}

	/************************************************************
	 * Returns the type of this piece, "Rook"
	 *  
	 * @return  String word that describes type of piece (rook)
	 ************************************************************/
	@Override
	public String type() {
		return "Rook";
	}

	/************************************************************
	 * Determines if a rook can make the desired move
	 * 
	 * @param move Move desired to be made by rook
	 * @return  True if is valid move
	 ************************************************************/
	public boolean isValidMove(Move move, IChessPiece[][] board){

		//Getting move data for Rook piece
		int fromC = move.fromColumn;
		int fromR = move.fromRow;
		int toC = move.toColumn;
		int toR = move.toRow;

		//Parent isValidMove method
		if(!super.isValidMove(move, board)){
			return false;
		}


		//Check if move is either front/back or side to side
		if (fromR != toR && fromC != toC) {
			return false;
		}

		//Check if horizontal / vertical path is clear
		if(!isPathClear(fromR, fromC, toR, toC, board)){
			return false;
		}
		
		//Checks if destination contains piece that is friendly
		if(board[toR][toC] != null && 
				board[toR][toC].player() == this.player()){
			return false;
		}

		return true;
	}
}