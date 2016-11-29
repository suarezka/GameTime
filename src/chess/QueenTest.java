package chess;

import static org.junit.Assert.*;
import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;

import org.junit.Test;

/************************************************************
 * CIS 163-07
 * Chess Project 
 * JUnit Test for the Queen Class
 *
 * @author DaiLynn Dietz
 * @author Kaye Suarez
 * @version Mar 18, 2014
 ************************************************************/
public class QueenTest extends ChessPieceTest {

	@Override
	protected IChessPiece make(Player p) {
		return new Queen(p);
	}

	@Override
	protected Move getValidMove(int fromRow, int fromCol) {
		int newRow = fromRow + 1;
	      if (newRow >= board.length) {
	         newRow = fromRow - 1;
	      }
	      return new Move(fromRow, fromCol, newRow, fromCol);
	}

	@Test
	public void canMoveRightShort() throws Exception {
		board[0][0] = piece;
		
		assertTrue("Queen Test 1", piece.isValidMove(
				new Move(0, 0, 0, 1), board));
	}
	
	@Test
	public void canMoveRightLong() throws Exception {
		board[0][0] = piece;
		
		assertTrue("Queen Test 2", piece.isValidMove(
				new Move(0, 0, 0, 6), board));
	}
	
	@Test
	public void canMoveLeftShort() throws Exception {
		board[0][7] = piece;
		
		assertTrue("Queen Test 3", piece.isValidMove(
				new Move(0, 7, 0, 6), board));
	}
	
	@Test
	public void canMoveLeftLong() throws Exception {
		board[0][7] = piece;
		
		assertTrue("Queen Test 4", piece.isValidMove(
				new Move(0, 7, 0, 0), board));
	}
	
	@Test
	public void canMoveDownShort() throws Exception {
		board[0][7] = piece;
		
		assertTrue("Queen Test 5", piece.isValidMove(
				new Move(0, 7, 1, 7), board));
	}
	
	@Test
	public void canMoveDownLong() throws Exception {
		board[0][7] = piece;
		
		assertTrue("Queen Test 6", piece.isValidMove(
				new Move(0, 7, 7, 7), board));
	}
	
	@Test
	public void canMoveUpShort() throws Exception {
		board[7][7] = piece;
		
		assertTrue("Queen Test 7", piece.isValidMove(
				new Move(7, 7, 6, 7), board));
	}
	
	@Test
	public void canMoveUpLong() throws Exception {
		board[7][7] = piece;
		
		assertTrue("Queen Test 8", piece.isValidMove(
				new Move(7, 7, 0, 7), board));
	}
	
	@Test
	public void canMoveLeftDownLong() throws Exception {
		board[0][7] = piece;
		
		assertTrue("Queen Test 9", piece.isValidMove(
				new Move(0, 7, 7, 0), board));	
	}
	
	@Test
	public void canMoveLeftDownShort() throws Exception {
		board[0][7] = piece;
		
		assertTrue("Queen Test 10", piece.isValidMove(
				new Move(0, 7, 1, 6), board));	
	}
	
	@Test
	public void canMoveRightDownLong() throws Exception {
		board[0][0] = piece;
		
		assertTrue("Queen Test 11", piece.isValidMove(
				new Move(0, 0, 7, 7), board));	
	}
	
	@Test
	public void canMoveRightDownShort() throws Exception {
		board[0][0] = piece;
		
		assertTrue("Queen Test 12", piece.isValidMove(
				new Move(0, 0, 1, 1), board));	
	}
	
	@Test
	public void canMoveLeftUpLong() throws Exception {
		board[7][7] = piece;
		
		assertTrue("Queen Test 13", piece.isValidMove(
				new Move(7, 7, 0, 0), board));	
	}
	
	@Test
	public void canMoveLeftUpShort() throws Exception {
		board[7][7] = piece;
		
		assertTrue("Queen Test 14", piece.isValidMove(
				new Move(7, 7, 6, 6), board));	
	}
	
	@Test
	public void canMoveRightUpLong() throws Exception {
		board[7][0] = piece;
		
		assertTrue("Queen Test 15", piece.isValidMove(
				new Move(7, 0, 0, 7), board));	
	}
	
	@Test
	public void canMoveRightUpShort() throws Exception {
		board[7][0] = piece;
		
		assertTrue("Queen Test 16", piece.isValidMove(
				new Move(7, 0, 6, 1), board));	
	}
	
	@Test
	public void typeTest() throws Exception {
		assertEquals("Queen", piece.type());
	}
	
	@Test
	public void enemyIsNearQueen() throws Exception {
		for(int r = 0; r < board.length; r++){
			for(int c = 0; c < board[0].length; c++){
				board[r][c] = new Queen(piece.player().next());
			}
		}
		
		for(int k = 1; k < board.length; k++){
			board[k][k] = null;
		}
		
		board[0][0] = piece;
		
		assertTrue("Enemy is near queen", 
				piece.isValidMove(new Move(0, 0, 7, 7), board));
	}
	
	@Test
	public void doesntGoThrough() throws Exception {
		board[0][0] = piece;
		board[0][2] = new Queen(piece.player().next());
		
		assertFalse("BadMove Queen", 
				piece.isValidMove(new Move(0, 0, 0, 3), board));
	}
	
	@Test
	public void badQueenMove() throws Exception {
		
		Rook r = new Rook(Player.WHITE);
		Bishop b = new Bishop(Player.WHITE);
		
		//Set black pieces on board
				board[0][0] = new Rook(Player.BLACK);
				board[0][1] = new Knight(Player.BLACK);
				board[0][2] = new Bishop(Player.BLACK);
				board[0][3] = new Queen(Player.BLACK);
				board[0][4] = new King(Player.BLACK);
				board[0][5] = new Bishop(Player.BLACK);
				board[0][6] = new Knight(Player.BLACK);
				board[0][7] = new Rook(Player.BLACK);
				board[1][0] = new Pawn(Player.BLACK);
				board[1][1] = new Pawn(Player.BLACK);
				board[1][2] = new Pawn(Player.BLACK);
				board[1][3] = new Pawn(Player.BLACK);
				board[1][4] = new Pawn(Player.BLACK);
				board[1][5] = new Pawn(Player.BLACK);
				board[1][6] = new Pawn(Player.BLACK);
				board[1][7] = new Pawn(Player.BLACK);

				//Set white pieces on board
				board[7][0] = new Rook(Player.WHITE);
				board[7][1] = new Knight(Player.WHITE);
				board[7][2] = new Bishop(Player.WHITE);
				board[7][3] = piece;
				board[7][4] = new King(Player.WHITE);
				board[7][5] = b;
				board[7][6] = new Knight(Player.WHITE);
				board[7][7] = r;
				board[6][0] = new Pawn(Player.WHITE);
				board[6][1] = new Pawn(Player.WHITE);
				board[6][2] = new Pawn(Player.WHITE);
				board[6][3] = new Pawn(Player.WHITE);
				board[6][4] = new Pawn(Player.WHITE);
				board[6][5] = new Pawn(Player.WHITE);
				board[6][6] = new Pawn(Player.WHITE);
				board[6][7] = new Pawn(Player.WHITE);
				
				
				
				assertFalse(b.isValidMove(new Move(7, 5, 5, 7), board));
				assertFalse(r.isValidMove(new Move(7, 7,2, 6), board));
				assertFalse(piece.isValidMove(new Move(7, 3, 0, 4), board));
	}
}
