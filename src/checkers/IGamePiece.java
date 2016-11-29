package checkers;

/***********************************************
 * IGamePiece interface for game pieces.
 * 
 * @author Nate Benson, Kaye Suarez, Jake Young
 * @version 1.0 
 ***********************************************/
public interface IGamePiece {
	
	/**************************
	 * Set player of piece.
	 * @param p owner of piece 
	 **************************/
	void setPlayer(Player p);
	 
	/****************************************** 
	 * Returns the player that owns this piece.
	 * @return player of piece 
	 ******************************************/
	Player player();
	
	/****************************************** 
	 * Returns color of piece. 
	 * @return color
	 ******************************************/
	String type();
	
	/****************************************** 
	 * Validates movement.
	 * @param move current move
	 * @param board current board
	 * @return true of valid, false if not 
	 ******************************************/
	boolean isValidMove(Move move, IGamePiece[][] board);
	
	
	
}
