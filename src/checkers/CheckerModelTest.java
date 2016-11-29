package checkers;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/************************************************************
 * JUnit testing for the CheckerModel Class.
 * 
 * @author Nate Benson, Kaye Suarez, Jake Young
 * @version 1.0 
 ************************************************************/

public class CheckerModelTest {
	
	/** Test Checker Model. */
	protected ICheckerModel model;
	
	/****************************
	 * Create Test Checker model.
	 ****************************/
	@Before
	public final void setup() {
		model = new CheckerModel();
	}
	
	/**************************************************** 
	 * Test if game is over.  Move to capture last piece.
	 * @throws Exception 
	 ****************************************************/
	@Test
	public final void noGameOver() throws Exception {
		model.move(new Move(5, 3, 4, 2));
	
		assertFalse("Testing notIsComplete", model.gameOver());
	}
	
	/**************************************************** 
	 * Test if game is over.  Move to capture last piece.
	 * @throws Exception 
	 ****************************************************/
	@Test
	public final void yesGameOver() throws Exception {
		
		IGamePiece piece = model.pieceAt(5, 3);
		IGamePiece other = model.pieceAt(2, 6); 
		
		//Manually set totals to simulate end of game
		model.setRedTotal(2);
		model.setGrayTotal(1);
		
		model.move(new Move(5, 3, 4, 4));
		model.move(new Move(2, 6, 3, 5));
		model.move(new Move(4, 4, 2, 6));
		
		int grays = model.getGrayTotal();

		assertEquals(model.pieceAt(2, 6), piece);
		assertEquals(null, model.pieceAt(3, 5));
		assertEquals(0, grays);
		assertTrue(model.gameOver());
	}
	
	
	/****************************
	 * Checks if piece was moved. 
	 * @throws Exception 
	 ****************************/
	@Test
	public final void moveWorks() throws Exception {

		IGamePiece piece = model.pieceAt(5, 3);
		model.move(new Move(5, 3, 4, 2));

		assertEquals(model.pieceAt(4, 2), piece);
		assertEquals(12, model.getRedTotal());
		assertEquals(12, model.getGrayTotal());
	}
	
	/*****************************************
	 * Checks if piece was moved and captured. 
	 * @throws Exception 
	 *****************************************/
	@Test
	public final void captureGrayWorks() throws Exception {

		IGamePiece piece = model.pieceAt(5, 3);
		IGamePiece other = model.pieceAt(2, 6);
		model.move(new Move(5, 3, 4, 4));
		model.move(new Move(2, 6, 3, 5));
		model.move(new Move(4, 4, 2, 6));
		
		int grays = model.getGrayTotal();

		assertEquals(model.pieceAt(2, 6), piece);
		assertEquals(null, model.pieceAt(3, 5));
		assertEquals(12 - 1, grays);
	}
	
	/*****************************************
	 * Checks if piece was moved and captured. 
	 * @throws Exception 
	 *****************************************/
	@Test
	public final void captureGrayWorks2() throws Exception {

		IGamePiece piece = model.pieceAt(5, 3);
		IGamePiece other = model.pieceAt(2, 0);
		model.move(new Move(5, 3, 4, 2));
		model.move(new Move(2, 0, 3, 1));
		model.move(new Move(4, 2, 2, 0));
		
		int grays = model.getGrayTotal();

		assertEquals(model.pieceAt(2, 0), piece);
		assertEquals(null, model.pieceAt(3, 1));
		assertEquals(12 - 1, grays);
	}
	
	
	/*****************************************
	 * Checks if piece was moved and captured. 
	 * @throws Exception 
	 *****************************************/
	@Test
	public final void captureRedWorks() throws Exception {

		IGamePiece piece = model.pieceAt(5, 3);
		IGamePiece other = model.pieceAt(2, 6);
		
		model.move(new Move(5, 5, 4, 6));
		model.move(new Move(2, 6, 3, 5));
		model.move(new Move(5, 3, 4, 4));
		model.move(new Move(3, 5, 5, 3));
		
		int reds = model.getRedTotal();

		assertEquals(model.pieceAt(5, 3), other);
		assertEquals(null, model.pieceAt(4, 4));
		assertEquals(12 - 1, reds);
	}
	
	/*****************************************
	 * Checks if piece was moved and captured. 
	 * @throws Exception 
	 *****************************************/
	@Test
	public final void captureRedWorks2() throws Exception {

		IGamePiece piece = model.pieceAt(5, 3);
		IGamePiece other = model.pieceAt(2, 0);
		
		model.move(new Move(5, 5, 4, 6));
		model.move(new Move(2, 0, 3, 1));
		model.move(new Move(5, 3, 4, 2));
		model.move(new Move(3, 1, 5, 3));
		
		int reds = model.getRedTotal();

		assertEquals(model.pieceAt(5, 3), other);
		assertEquals(null, model.pieceAt(4, 2));
		assertEquals(12 - 1, reds);
	}
	
	/*********************************************************
	 * Checks that piece doesn't move if location is occupied.
	 * @throws Exception 
	 *********************************************************/
	@Test
	public final void invalidMove() throws Exception {
		IGamePiece piece = model.pieceAt(5, 3);
		IGamePiece piece2 = model.pieceAt(2, 0);
		
		model.move(new Move(5, 3, 4, 2));
		model.move(new Move(2, 0, 3, 1));
		model.move(new Move(4, 2, 3, 1));
		
		assertEquals(model.pieceAt(4, 2), piece);
		assertEquals(model.pieceAt(3, 1), piece2);
		assertEquals(12, model.getRedTotal());
		assertEquals(12, model.getGrayTotal());
	}
	
	/*********************************************************
	 * Checks that piece doesn't move if location is occupied.
	 * @throws Exception 
	 *********************************************************/
	@Test
	public final void invalidMove2() throws Exception {
		IGamePiece piece = model.pieceAt(5, 3);
		IGamePiece piece2 = model.pieceAt(2, 0);
		
		model.move(new Move(2, 0, 3, 1));
		model.move(new Move(5, 3, 4, 2));
		model.move(new Move(3, 1, 4, 2));
		
		assertEquals(model.pieceAt(4, 2), piece);
		assertEquals(model.pieceAt(3, 1), piece2);
		assertEquals(12, model.getRedTotal());
		assertEquals(12, model.getGrayTotal());
	}
	
	
	/***************************************
	 * Checks if player switches after turn.
	 * @throws Exception 
	 ***************************************/
	@Test
	public final void playerSwitches() throws Exception {

		Player p = model.getCurrentPlayer();
		model.move(new Move(5, 3, 4, 2));

		assertTrue(!p.equals(model.getCurrentPlayer()));

	}
}
