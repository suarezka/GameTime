package game;

import static org.junit.Assert.*;

import org.junit.Test;

public class KingTest extends ChessPieceTest {

	@Override
	protected IChessPiece make(Player p) {
		return new King(p);
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
	public void typeTest() throws Exception {
		assertEquals("King", piece.type());
	}

	@Test
	public void inCheck() throws Exception {
		board[0][0] = piece;
		board[0][1] = new Rook(piece.player().next());
		
		assertTrue("inCheck Test", ((King) piece).isInCheck(0,0,board));
	}
	
	@Test
	public void notInCheck() throws Exception {
		board[0][0] = piece;
		board[0][1] = new Bishop(piece.player().next());
		
		assertFalse("Not inCheck Test", ((King) piece).isInCheck(0, 0, board));
	}
	
	@Test
	public void canMoveRight() throws Exception {
		board[0][0] = piece;
		Move move = new Move(0, 0, 0, 1);
		
		assertTrue("King Move Right", piece.isValidMove(move, board));
	}
	
	@Test
	public void canMoveLeft() throws Exception {
		board[7][7] = piece;
		Move move = new Move(7, 7, 7, 6);
		
		assertTrue("King Move Left", piece.isValidMove(move, board));
	}
	
	@Test
	public void canMoveUp() throws Exception {
		board[7][7] = piece;
		Move move = new Move(7, 7, 6, 7);
		
		assertTrue("King Move Up", piece.isValidMove(move, board));
	}
	
	@Test
	public void canMoveDown() throws Exception {
		board[0][0] = piece;
		Move move = new Move(0, 0, 1, 0);
		
		assertTrue("King Move Down", piece.isValidMove(move, board));
	}
	
	@Test
	public void canMoveRightDown() throws Exception {
		board[0][0] = piece;
		Move move = new Move(0, 0, 1, 1);
		
		assertTrue("King DownRight Move", piece.isValidMove(move, board));
	}
	
	@Test
	public void canMoveDownLeft() throws Exception {
		board[0][7] = piece;
		Move move = new Move(0, 7, 1, 6);
		
		assertTrue("King DownLeft Move", piece.isValidMove(move, board));
	}
	
	@Test
	public void canMoveRightUp() throws Exception {
		board[7][0] = piece;
		Move move = new Move(7, 0, 6, 1);
		
		assertTrue("King UpRight Move", piece.isValidMove(move, board));
	}
	
	@Test
	public void canMoveLeftUp() throws Exception {
		board[7][7] = piece;
		Move move = new Move(7, 7, 6, 6);
		
		assertTrue("King LeftUp Move", piece.isValidMove(move, board));
	}
	
	@Test
	public void cantAttackOtherKing() throws Exception {
		board[6][2] = piece;
		board[6][4] = new King(piece.player().next());
		
		assertFalse("Kings Dont Attack", piece.isValidMove(
				new Move(6, 2, 6, 3), board));
	}
	
	@Test
	public void doesMakeArrayList() throws Exception {
		board[6][2] = piece;
		board[6][4] = new Rook(piece.player().next());
		
		((King) piece).isInCheck(6, 2, board);
		
		int toR = ((King) piece).getAttackers().get(0).getNewRow();
		int toC = ((King) piece).getAttackers().get(0).getNewCol();
		int fromR = ((King) piece).getAttackers().get(0).getCurrentRow();
		int fromC = ((King) piece).getAttackers().get(0).getCurrentCol();
		
		assertEquals("List Row Test", toR, 6);
		assertEquals("List Row Test", toC, 2);
		assertEquals("List Row Test", fromR, 6);
		assertEquals("List Row Test", fromC, 4);
	}

}
