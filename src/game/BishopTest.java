package game;

import static org.junit.Assert.*;

import org.junit.Test;

public class BishopTest extends ChessPieceTest {

	@Override
	protected IChessPiece make(Player p) {
		return new Bishop(p);
	}

	@Override
	protected Move getValidMove(int fromRow, int fromCol) {
		int toRow = fromRow + 1;
		int toCol = fromCol + 1;
		
		if(toRow > board.length){
			toRow = fromRow - 1;
		}
		if(toCol > board[0].length){
			toCol = fromCol - 1;
		}
		
		return new Move(fromRow, fromCol, toRow, toCol);
	}
	
	@Test
	public void typeTest() throws Exception {
		assertEquals("Bishop", piece.type());
	}
	
	@Test
	   public void canMoveInRDownDiagonalShort() throws Exception {
	      board[1][1] = piece;
	      Move bishopDRight = new Move(1,1,3,3);
	      assertTrue("Bishop RightDown Diagonal Test",
	    		  piece.isValidMove(bishopDRight, board));
	   }
	
	@Test
	   public void canMoveInRUpDiagonalShort() throws Exception {
	      board[3][3] = piece;
	      Move bishopUpRight = new Move(3,3,1,5);
	      assertTrue("Bishop RightUp Diagonal Test",
	    		  piece.isValidMove(bishopUpRight, board));
	   }
	
	@Test
	public void cantMoveStraight() throws Exception {
		board[3][3] = piece;
		Move straight = new Move(3, 3, 3, 5);
		assertFalse("Bishop Straight Move Test",
				piece.isValidMove(straight, board));
	}
	
	@Test
	public void canMoveInLeftDownShort() throws Exception {
		board[5][5] = piece;
		Move lDown = new Move(5, 5, 6, 6);
		assertTrue("Bishop Short L-Down Test",
				piece.isValidMove(lDown, board));
	}
	
	@Test
	public void canMoveInLeftUpShort() throws Exception {
		board[5][5] = piece;
		Move lUp = new Move(5, 5, 4, 4);
		assertTrue("Bishop Short L-Up Test", piece.isValidMove(lUp, board));
	}
	
	@Test
	   public void canMoveInRDownDiagonalLong() throws Exception {
	      board[0][0] = piece;
	      Move bishopDRight = new Move(0,0,7,7);
	      assertTrue("Bishop RightDown Diagonal Test Long", 
	    		  piece.isValidMove(bishopDRight, board));
	   }
	
	@Test
	   public void canMoveInRUpDiagonalLong() throws Exception {
	      board[7][0] = piece;
	      Move bishopUpRight = new Move(7,0,0,7);
	      assertTrue("Bishop RightUp Diagonal Test Long", 
	    		  piece.isValidMove(bishopUpRight, board));
	   }
	
	@Test
	public void canMoveInLeftDownLong() throws Exception {
		board[0][7] = piece;
		Move lDown = new Move(0, 7, 7, 0);
		assertTrue("Bishop Long L-Down Test", piece.isValidMove(lDown, board));
	}
	
	@Test
	public void canMoveInLeftUpLong() throws Exception {
		board[7][7] = piece;
		Move lUp = new Move(7, 7, 0, 0);
		assertTrue("Bishop Long L-Up Test", piece.isValidMove(lUp, board));
	}
	
	@Test
	public void doesntMoveThrough() throws Exception {
		board[0][0] = piece;
		IChessPiece otherPiece = new Bishop(Player.WHITE);
		board[1][1] = otherPiece;
		Move bad = new Move(0, 0, 3, 3);
		assertFalse("Bishop Move Through Test", 
				piece.isValidMove(bad, board));
	}
	
	@Test
	public void doesntJumpOtherPlayer() throws Exception {
		board[5][1] = piece;
		IChessPiece otherPiece = new Bishop(Player.BLACK);
		board[3][3] = otherPiece;
		Move bad = new Move(5,1,1,5);
		assertFalse("Bishop Jump Other Player Test",
				piece.isValidMove(bad, board));
	}
	
	@Test
	public void doesntMoveNotDiagonal() throws Exception {
		int fromR = 3;
		int fromC = 3;
		
		board[fromR][fromC] = piece;
		
		for(int k = 0; k < board.length; k++){
			for(int m = 0; m < board[0].length; m++){
				int rowDiff = Math.abs(fromR - k);
				int colDiff = Math.abs(fromC - m);
				
				Move tempMove = new Move(fromR, fromC, k, m);
				
				if(colDiff == rowDiff && fromR != k && fromC != m){
					assertTrue("Loop bishop true Test",
							piece.isValidMove(tempMove, board));
				}else{
					assertFalse("Loop bishop false Test",
							piece.isValidMove(tempMove, board));
				}
			}
		}
	}

}
