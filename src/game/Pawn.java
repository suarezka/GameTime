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
		
		int row = move.getCurrentRow();
		int col = move.getCurrentCol();
		int newR = move.getNewRow();
		int newC = move.getNewCol();

		if (super.isValidMove(move, board)) {
			if (board[row][col].player() == Player.BLACK) {
				if (row < 7) {
					//check if blocked
					if (board[row + 1][col] != null) {
						//check for attack
						if (newR - row == 1 && Math.abs(col - newC) == 1) {

							//check for enemy piece
							if (board[newR][newC] != null && board[newR]
											[newC].player() == Player.WHITE) {

								return true;
							}
							return false;
						}
						return false;
					} else if (hasMoved) {
						//black normal move
						if ((newR - row == 1) && col == newC) {
							return true;

							//black attack after hasMoved	
						} else if (newR - row == 1 
								&& Math.abs(newC 
										- col) == 1) {

							if (board[newR][newC] != null && board[newR]
									 		[newC].player() == Player.WHITE) {
								return true;
							}
							return false;
						}
						return false;

					} else {
						//black first move
						if ((newR - row == 2 || newR - row == 1) 
											 && newC == col) {
							
							//hasMoved = true;
							return true;


							//black attack first move
						} else if (newR - row == 1 
									&& Math.abs(newC - col) == 1) {

							if (board[newR][newC] != null && board[newR]
											[newC].player() == Player.WHITE) {
								//hasMoved = true;
								return true;
							}
							return false;
						}
						return false;
					}
				} else {
					if (row > 0) {
						//check if blocked
						if (board[row - 1][col] != null) {
							//check for attack
							if (row - newR == 1 
									&& Math.abs(col - newC) == 1) {

								//check for enemy piece
								if (board[newR][newC] != null && board[newR]
											[newC].player() == Player.BLACK) {
									
									return true;
								}
								return false;
							}
							return false;
							
						} else if (hasMoved) {
							
							//white normal move
							if ((row - newR == 1) && col == newC) {
								return true;
								
								//white attack
							} else if (row - newR == 1 
									&& Math.abs(col - newC) == 1) {
								
								 //TODO: ADD STATEMENT??
							}
								if (board[newR][newC] != null && board[newR]
											[newC].player() == Player.BLACK) {
									return true;
								}
								return false;
							}
							return false;
						} else {
							//white first move
							if ((row - newR == 2 || row - newR == 1) 
											&& newC == col) {
								
								//hasMoved = true;
								return true;
								
							} else if (row - newR == 1 
										&& Math.abs(col - newC) == 1) {
								
								if (board[newR][newC] != null && board[newR]
											[newC].player() == Player.BLACK) {
									
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
