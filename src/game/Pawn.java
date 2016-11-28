package game;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/***************************************************
 * Class for Pawn Chess Piece.
 *
 * @author Nate Benson, Jake Young, Kaye Suarez
 * @version 2.0
 ***************************************************/
public class Pawn extends ChessPiece {

	/** Player of piece. */
	private Player owner;

	/** Piece has moved. */
	public boolean hasMoved = false;

	/***************************
	 * Create Pawn piece.
	 * @param player of piece
	 ***************************/
	protected Pawn(final Player player) {
		super(player);
		this.owner = player;
	}

	/************************
	 * Return type of piece.
	 * @return type 
	 ************************/
	public final String type() {
		return "Pawn";
	}

	/****************************
	 * Returns player of piece.
	 * @return owner of piece
	 ****************************/
	public final Player player() {
		return owner;
	}

	/*******************************************
	 * Returns valid move for Pawn.
	 * @param move given move
	 * @param board current board
	 * @return true if valid, false if not
	 ******************************************/
	public final boolean isValidMove(
			final Move move, final IChessPiece[][] board) {

		if (super.isValidMove(move, board)) {
			if (board[move.fromRow][move.fromColumn].player() == Player.BLACK) {
				if (move.fromRow < 7) {
					//check if blocked
					if (board[move.fromRow + 1][move.fromColumn] != null) {
						//check for attack
						if (move.toRow - move.fromRow == 1 
								&& Math.abs(move.fromColumn 
										- move.toColumn) == 1) {

							//check for enemy piece
							if (board[move.toRow][move.toColumn] != null 
									&& board[move.toRow]
											[move.toColumn].player() 
											== Player.WHITE) {

								return true;
							}
							return false;
						}
						return false;
					} else if (hasMoved) {
						//black normal move
						if ((move.toRow - move.fromRow == 1)
								&& move.fromColumn == move.toColumn) {
							return true;

							//black attack after hasMoved	
						} else if (move.toRow - move.fromRow == 1 
								&& Math.abs(move.toColumn 
										- move.fromColumn) == 1) {

							if (board[move.toRow][move.toColumn] != null 
									&& board[move.toRow]
											[move.toColumn].player() 
											== Player.WHITE) {
								return true;
							}
							return false;
						}
						return false;

					} else {
						//black first move
						if ((move.toRow - move.fromRow == 2 
								|| move.toRow - move.fromRow == 1) 
								&& move.toColumn == move.fromColumn) {
							//hasMoved = true;
							return true;


							//black attack first move
						} else if (move.toRow - move.fromRow == 1 
								&& Math.abs(move.toColumn 
										- move.fromColumn) == 1) {

							if (board[move.toRow][move.toColumn] != null 
									&& board[move.toRow]
											[move.toColumn].player() 
											== Player.WHITE) {
								//hasMoved = true;
								return true;
							}
							return false;
						}
						return false;
					}
				} else {
					if (move.fromRow > 0) {
						//check if blocked
						if (board[move.fromRow - 1][move.fromColumn] != null) {
							//check for attack
							if (move.fromRow - move.toRow == 1 
									&& Math.abs(move.fromColumn 
											- move.toColumn) == 1) {

								//check for enemy piece
								if (board[move.toRow][move.toColumn] != null 
										&& board[move.toRow]
												[move.toColumn].player() 
												== Player.BLACK) {
									
									return true;
								}
								return false;
							}
							return false;
							
						} else if (hasMoved) {
							
							//white normal move
							if ((move.fromRow - move.toRow == 1)
									&& move.fromColumn == move.toColumn) {
								return true;
								
								//white attack
							} else if (move.fromRow - move.toRow == 1 
									&& Math.abs(move.fromColumn 
												- move.toColumn) == 1) {
								
								 //TODO: ADD STATEMENT??
							}
								if (board[move.toRow][move.toColumn] != null 
										&& board[move.toRow]
												[move.toColumn].player() 
												== Player.BLACK) {
									return true;
								}
								return false;
							}
							return false;
						} else {
							//white first move
							if ((move.fromRow - move.toRow == 2 
									|| move.fromRow - move.toRow == 1) 
									&& move.toColumn == move.fromColumn) {
								
								//hasMoved = true;
								return true;
								
							} else if (move.fromRow - move.toRow == 1 
										&& Math.abs(move.fromColumn 
												- move.toColumn) == 1) {
								
								if (board[move.toRow][move.toColumn] != null 
										&& board[move.toRow]
												[move.toColumn].player() 
												== Player.BLACK) {
									
									//hasMoved = true;
									return true;
								}
								return false;
							}
							return false;
						}
					}
				}
			}
		
		return false;
	}

	/*****************************
	 * Return hasMoved value.
	 * @return hasMoved value
	 *****************************/
	public final boolean getHasMoved() {
		return hasMoved;
	}

	/*****************************
	 * Set hasMoved value.
	 * @param value value
	 *****************************/
	public final void setHasMoved(final boolean value) {
		this.hasMoved = value;
	}
}
