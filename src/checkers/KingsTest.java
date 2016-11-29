package checkers;

import static org.junit.Assert.*;
import org.junit.Test;

/************************************************************
 * JUnit testing for the Kings Class.
 * 
 * @author Nate Benson, Jake Young, Kaye Suarez
 * @version 1.0
 ************************************************************/
public class KingsTest extends GamePieceTest {

	@Override
	protected final IGamePiece make(final Player p) {
		return new Kings(p);
	}

	@Override
	protected final Move getValidMove(final int fromRow, final int fromCol) {
		
		if (piece.player() == Player.RED) {

			return new Move(fromRow, fromCol, fromRow - 1, fromCol);			
		} else {
			return new Move(fromRow, fromCol, fromRow + 1, fromCol);
		}
	}
	
	/******************************
	 * Checks if piece is "King".
	 * @throws Exception 
	 ******************************/
	@Test
	public final void typeTest() throws Exception {

		assertEquals("King", piece.type());
	}
	
	/******************************************
	 * Checks if piece can move diagonal right.
	 * @throws Exception 
	 ******************************************/
	@Test
	public final void moveDiagRight() throws Exception {
		board[5][1] = piece;

		assertTrue(piece.isValidMove(new Move(5, 1, 4, 2), board));
	}
	
	/*****************************************
	 * Checks if piece can move diagonal left.
	 * @throws Exception 
	 *****************************************/
	@Test
	public final void moveDiagLeft() throws Exception {
		board[5][1] = piece;
	
		assertTrue(piece.isValidMove(new Move(5, 1, 4, 0), board));
	}
	
	/**************************************************
	 * Checks if piece can move jump up diagonal right.
	 * @throws Exception 
	 **************************************************/
	@Test
	public final void jumpDiagUpRight() throws Exception {
		board[5][3] = piece;
		IGamePiece piece2 = make(Player.GRAY);
		board[4][4] = piece2;
	
		assertTrue(piece.isValidMove(new Move(5, 3, 3, 5), board));
	}
	
	/***************************************************
	 * Checks if piece can move jump down diagonal left.
	 * @throws Exception 
	 ***************************************************/
	@Test
	public final void jumpDiagUpLeft() throws Exception {
		board[5][3] = piece;
		IGamePiece piece2 = make(Player.GRAY);
		board[4][2] = piece2;

		assertTrue(piece.isValidMove(new Move(5, 3, 3, 1), board));
	}
	
	/**********************************************
	 * Checks valid back right diagonal movement.
	 * @throws Exception 
	 **********************************************/
	@Test
	public final void diagRightBack() throws Exception {
		board[4][4] = piece;
 
		assertTrue(piece.isValidMove(new Move(4, 4, 5, 5), board));
	}
	
	/**********************************************
	 * Checks valid back right diagonal movement.
	 * @throws Exception 
	 **********************************************/
	@Test
	public final void diagLeftBack() throws Exception {
		board[4][4] = piece;
 
		assertTrue(piece.isValidMove(new Move(4, 4, 5, 3), board));
	}
	
	/*******************************************
	 * Checks if invalid to move piece vertical.
	 * @throws Exception 
	 *******************************************/
	@Test
	public final void cantMoveForwards() throws Exception {
		board[5][3] = piece;

		assertFalse(piece.isValidMove(new Move(5, 3, 4, 3), board));
	}
	
	
	/*********************************************
	 * Checks invalid vertical backwards movement.
	 * @throws Exception 
	 *********************************************/
	@Test
	public final void cantMoveBackwards() throws Exception {
		board[5][3] = piece;

		assertFalse(piece.isValidMove(new Move(5, 3, 6, 3), board));
	}
	
	/***********************************
	 * Checks invalid movement sideways.
	 * @throws Exception 
	 ***********************************/
	@Test
	public final void cantMoveSideways() throws Exception {
		board[5][3] = piece;

		assertFalse(piece.isValidMove(new Move(5, 3, 5, 4), board));
	}
	
	/************************************************
	 * Checks when jumping piece, only jumps 1 space.
	 * @throws Exception 
	 ************************************************/
	@Test
	public final void tooManySpacesRed() throws Exception {
		board[5][3] = piece;
		IGamePiece piece2 = make(Player.GRAY);
		board[4][4] = piece2;

		assertFalse(piece.isValidMove(new Move(5, 3, 2, 6), board));
	}
	
	/************************************************
	 * Checks when jumping piece, only jumps 1 space.
	 * @throws Exception 
	 ************************************************/
	@Test
	public final void tooManySpacesGray() throws Exception {
		board[3][5] = piece;
		IGamePiece piece2 = make(Player.GRAY);
		board[2][6] = piece2;

		assertFalse(piece.isValidMove(new Move(2, 6, 5, 3), board));
	}	
}
