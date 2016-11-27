package game;

/******************************************************************************
 * ICheckerModel interface for game play: moving, winning and rotating players.
 * 
 * @author Nate Benson, Kaye Suarez, Jake Young
 * @version 1.0 
 ******************************************************************************/
public interface ICheckerModel {
	
	//boolean isValidMove(Move move);
	
	/*******************************************
	 * move handles movement of pieces on board.
	 * @param move given move
	 *******************************************/
	void move(Move move); 
	
	/************************************
	 * gameOver checks if game ended.
	 * @return true if win, false if not
	 ************************************/
	boolean gameOver();
	
	
	/***************************************
	 * Return the piece at a given location.
	 * @param row given row
	 * @param column given column
	 * @return piece at location
	 ***************************************/
	IGamePiece pieceAt(final int row, final int column);
	
	/***********************************************************
	 * getCurrentPlayer returns the value of the current player.
	 * @return player value
	 ***********************************************************/
	Player getCurrentPlayer();
	
	/************************************************************
	 * getRedTotal returns the number of red pieces on the board.
	 * @return red pieces
	 ************************************************************/
	int getRedTotal();
	
	/**************************************************************
	 * getGrayTotal returns the number of gray pieces on the board.
	 * @return red pieces
	 **************************************************************/
	int getGrayTotal();
	
	/************************************************************
	 * getRedTotal sets the number of red pieces on the board.
	 * @param total red pieces
	 ************************************************************/
	void setRedTotal(final int total);
	
	/**************************************************************
	 * getGrayTotal sets the number of gray pieces on the board.
	 * @param total gray pieces 
	 **************************************************************/
	void setGrayTotal(final int total);
	
}
