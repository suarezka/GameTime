package checkers;

/***********************************************
 * Player color values.
 * 
 * @author Nate Benson, Kaye Suarez, Jake Young
 * @version 1.0 
 ***********************************************/
public enum Player {
	
	/** Players. */
	GRAY, RED, BLACK, WHITE;
	
	/********************* 
	 * Return next player. 
	 * @return color 
	 *********************/
	public Player next() {
		if (this == GRAY || this == RED) {
			
			if (this == GRAY) {
				return RED;
			} else {
				return GRAY;
			}

		} else {
			
			if (this == BLACK) {
				return WHITE;
			} else {
				return BLACK;
			}
		}
	}
}
