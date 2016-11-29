package chess;

import static org.junit.Assert.*;
import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;

import org.junit.Test;

/************************************************************
 * CIS 163-07
 * Chess Project
 * JUnit Test for the Pawn Class
 *
 * @author DaiLynn Dietz
 * @author Kaye Suarez
 * @version Mar 18, 2014
 ************************************************************/
public class BlackPawnTest extends ChessPieceTest {

	@Override
	protected IChessPiece make(Player p) {
		return new Pawn(p.next());
	}

	@Override
	protected Move getValidMove(int fromRow, int fromCol) {
		
		//Checks color of pawn because pawns only 
		//can move forward, not backwards
		//Doesnt handle pawns at the end of the board
		if(piece.player() == Player.BLACK){
			return new Move(fromRow, fromCol, fromRow - 1, fromCol);
		}else{
			return new Move(fromRow, fromCol, fromRow + 1, fromCol);
		}
	}
	
	@Test
	public void typeTest() throws Exception {
		assertEquals("Pawn", piece.type());
	}
	
	@Test
	public void regularCaptureUp() throws Exception {
		board[2][5] = piece;
		IChessPiece otherPiece = new Pawn(Player.WHITE);
		board[3][4] = otherPiece;
		Move capture = new Move(2, 5, 3, 4);
		assertTrue("Black Pawn Capture Down Test", piece.isValidMove(capture, board));
	}
	
	@Test
	public void doesntJump() throws Exception {
		board[1][4] = piece;
		board[2][4] = new Pawn(Player.WHITE);
		
		assertFalse(piece.isValidMove(new Move(1, 4, 2, 4), board));
	}

	@Test
	public void cantAttackForward() throws Exception {
		board[4][4] = piece;
		board[5][4] = new Pawn(Player.WHITE);
		
		assertFalse(piece.isValidMove(new Move(4, 4, 5, 4), board));
	}
	
	@Test
	public void cantGoBackwards() throws Exception {
		board[1][4] = piece;
		assertFalse(piece.isValidMove(new Move(1, 4, 0, 4), board));
	}
	
	@Test
	public void doesntGoSideways() throws Exception {
		board[1][4] = piece;
		assertFalse(piece.isValidMove(new Move(1, 4, 1, 3), board));
	}
	
	@Test
	public void doesntAttackOnDouble() throws Exception {
		board[1][2] = piece;
		board[3][3] = new Pawn(Player.BLACK);
		
		assertFalse(piece.isValidMove(new Move(1, 2, 3, 3), board));
	}
	
	@Test
	public void cantDoubleTwice() throws Exception {
		board[4][2] = piece;
		
		assertFalse(piece.isValidMove(new Move(4, 2, 2, 2), board));
	}
	
	@Test
	public void cantAttackNull() throws Exception {
		board[3][7] = piece;
		
		assertFalse(piece.isValidMove(new Move(3, 7, 2, 6), board));
	}
	
	@Test
	public void canMoveForward() throws Exception {
		board[3][3] = piece;
		
		assertTrue(piece.isValidMove(new Move(3, 3, 4, 3), board));
	}
	
	@Test
	public void canMoveDoubleNormal() throws Exception {
		board[1][5] = piece;
		
		assertTrue(piece.isValidMove(new Move(1, 5, 3, 5), board));
	}
}

