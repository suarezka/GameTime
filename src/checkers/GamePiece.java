package checkers;

/***********************************************************************
 * GamePiece implements the IGamePiece interface to create board pieces.
 * 
 * @author Nate Benson, Kaye Suarez, Jake Young
 * @version 1.0 
 ***********************************************************************/
public abstract class GamePiece implements IGamePiece {

	/** owner of piece. */
	private Player owner;
	 
	/*********************************************
	 * Set the owner of the piece as given player.
	 * @param player owner of piece.
	 *********************************************/
	public final void setPlayer(final Player player) {
		this.owner = player;
	}
	
	/***************************
	 * Returns player of piece.
	 * @return player of piece
	 **************************/
	public Player player() {
		return owner;
	}

	/***************************
	 * Returns type of piece.
	 * @return type of piece
	 ***************************/
	public abstract String type();

	//public abstract boolean isValidMove(GamePiece game);
	
}
