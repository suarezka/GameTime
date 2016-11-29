package checkers;

/********************************************************************
 * Move constructs the movement of pieces from starting point to end.
 * 
 * @author Nate Benson, Kaye Suarez, Jake Young
 * @version 1.0 
 ********************************************************************/
public class Move {
	
	/** start and end position of piece. */
	private static int fromRow, fromColumn, toRow, toColumn;
	

	/*********************************************************
	 * Move constructs move based from start and end position.
	 * @param row start row
	 * @param col start column
	 * @param row2 end row
	 * @param col2 end column
	 *********************************************************/
	public Move(final int row, final int col, final int row2, final int col2) {
		this.fromRow = row;
		this.fromColumn = col;
		this.toRow = row2;
		this.toColumn = col2;
	}
	
	
	/****************************
	 * Returns the current row.
	 * @return fromRow
	 ****************************/
	public final int getCurrentRow() {
		return fromRow;
	}
	
	
	/******************************
	 * Returns the current column.
	 * @return fromColumn
	 ******************************/
	public final int getCurrentCol() {
		return fromColumn;
	}
	
	
	/****************************
	 * Returns the new row.
	 * @return toRow
	 ****************************/
	public final int getNewRow() {
		return toRow;
	}
	
	/****************************
	 * Returns the new column.
	 * @return toColumn
	 ****************************/
	public final int getNewCol() {
		return toColumn;
	}
}

