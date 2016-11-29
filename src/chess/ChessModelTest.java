package chess;

import static org.junit.Assert.*;
import gvprojects.chess.model.IChessModel;
import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;

import org.junit.Before;
import org.junit.Test;

/************************************************************
 * CIS 163-07
 * Chess Project
 * JUnit testing for the Chess Model Class
 *
 * @author DaiLynn Dietz
 * @author Kaye Suarez
 * @version Mar 18, 2014
 ************************************************************/
public class ChessModelTest {

	protected IChessModel model;
	
	@Before
	public void setup(){
		model = new ChessModel();
	}
	
	@Test
	public void isNotCompleteTest() throws Exception {
		assertFalse(model.inCheck());
		assertFalse("Testing notIsComplete", model.isComplete());
	}

	@Test
	public void isCompleteTest() throws Exception {
		model.move(new Move(6, 5, 5, 5));
		model.move(new Move(1, 4, 2, 4));
		model.move(new Move(6, 6, 4, 6));
		model.move(new Move(0, 3, 4, 7));
		
		assertTrue(model.inCheck());
		assertTrue("Testing isComplete", model.isComplete());
	}
	
	@Test
	public void isCompleteTest2() throws Exception {
		model.move(new Move(6, 4, 5, 4));
		model.move(new Move(1, 5, 2, 5));
		model.move(new Move(7, 3, 5, 5));
		model.move(new Move(1, 6, 3, 6));
		model.move(new Move(5, 5, 3, 7));
		
		assertTrue(model.inCheck());
		assertTrue(model.isComplete());
	}
	
	@Test
	public void checkNotComplete() throws Exception {
		model.move(new Move(6, 4, 5, 4));
		model.move(new Move(1, 5, 2, 5));
		model.move(new Move(7, 3, 3, 7));
		
		assertTrue(model.inCheck());
		assertFalse(model.isComplete());
		assertFalse(model.isValidMove(new Move(1, 6, 3, 6)));
	}
	
	@Test
	public void LoopTest() throws Exception {
		model.move(new Move(6, 4, 5, 4));
		model.move(new Move(1, 5, 2, 5));
		model.move(new Move(7, 3, 3, 7));
		
		for(int r = 0; r < model.numRows(); r++){
			for(int c = 0; c < model.numColumns(); c++){
				for(int i = 0; i < model.numColumns(); i++){
					assertFalse(model.isValidMove(new Move(0, i, r, c)));
				}
			}
		}
	}
	
	@Test
	public void moveWorks() throws Exception {
		IChessPiece piece = model.pieceAt(6, 4);
		model.move(new Move(6, 4, 5, 4));
		
		assertEquals(model.pieceAt(5, 4), piece);
	}
	
	@Test
	public void playerSwitches() throws Exception {
		Player p = model.currentPlayer();
		model.move(new Move(6, 4, 4, 4));
		
		assertTrue(!p.equals(model.currentPlayer()));
	}
	
	@Test
	public void validMoveTest() throws Exception {
		assertTrue(model.isValidMove(new Move(7, 1, 5, 2)));
		model.move(new Move(7, 1, 5, 2));
		assertTrue(model.isValidMove(new Move(1, 3, 3, 3)));
	}
	
	@Test
	public void invalidMoveTest() throws Exception {
		assertFalse(model.isValidMove(new Move(7, 0, 5, 0)));
	}
	
	@Test
	public void invalidMoveTest2() throws Exception {
		assertFalse(model.isValidMove(new Move(7, 0, 5, 3)));
	}
	
	@Test
	public void invalidMoveTest3() throws Exception {
		assertFalse(model.isValidMove(new Move(5, 0, 4, 0)));
	}
}
