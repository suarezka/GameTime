package game;

public interface IChessModel {

	/*****************************************
	 * Returns whether the game is complete.
	 *
	 * @return true if complete, false if not
	 *****************************************/
	boolean isComplete();

	/***************************************************
	 * isValidMove checks if the move can be performed.
	 * @param move given move
	 * @return true if valid, false if not
	 ***************************************************/
	boolean isValidMove(Move move);

	/*****************************************
	 * Create move object for piece movement.
	 * @param move given move
	 *****************************************/
	void move(Move move);

	/******************************************
	 * Checks if piece is in check.
	 * @return true if in check, false if not
	 ******************************************/
	boolean inCheck();

	/******************************
	 * Return the current player.
	 * @return player
	 ******************************/
	Player currentPlayer();

	/*************************************
	 * Return number of columns in board.
	 * @return number of columns
	 *************************************/
	int numColumns();

	/*************************************
	 * Return number of rows in board.
	 * @return number of rows
	 *************************************/
	int numRows();

	/**********************************
	 * Returns pieceAt given position.
	 * @param row given row
	 * @param col given column
	 * @return piece at position
	 **********************************/
	IChessPiece pieceAt(int row, int col);

}
