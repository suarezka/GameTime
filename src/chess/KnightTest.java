package chess;

import static org.junit.Assert.*;
import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;

import org.junit.Test;

/************************************************************
 * CIS 163-07
 * Chess Project
 * JUnit Test for the knight chess piece class
 *
 * @author DaiLynn Dietz
 * @author Kaye Suarez
 * @version Mar 18, 2014
 ************************************************************/
public class KnightTest extends ChessPieceTest {

	@Override
	public IChessPiece make(Player p) {
		return new Knight(p);
	}

	@Override
	protected Move getValidMove(int fromRow, int fromCol) {
		int toRow = fromRow + 1;
		int toCol = fromCol - 2;
		
		if(toRow >= board.length){
			toRow = fromRow - 1;
		}
		if(toCol < 0){
			toCol = fromCol + 2;
		}
		
		return new Move(fromRow, fromCol, toRow, toCol);
	}

	@Test
	public void canJumpPiece() throws Exception {
		IChessPiece otherPiece = make(Player.WHITE);
		
		board[0][0] = piece;
		board[0][1] = otherPiece;
		
		assertTrue("Knight Test 1", piece.isValidMove(
				new Move(0, 0, 2, 1), board));
	}
	
	@Test
	public void typeTest() throws Exception {
		assertEquals("Knight", piece.type());
	}
	
	@Test
	public void negativeMove() throws Exception {
		board[0][0] = piece;
		
		assertFalse("Knight Test 2", piece.isValidMove(
				new Move(0, 0, -1, 2), board));
	}
	
	
	@Test
	public void invalidMoves() throws Exception {
		int fromR = 3;
		int fromC = 5;
		
		board[fromR][fromC] = piece;
		
		//Loop through cell to see if valid moves
		for (int k = 0; k < board.length; k++) {
			for (int m = 0; m < board[0].length; m++) {
				//Create a temporary move
				Move tempMove = new Move(fromR, fromC, k, m);
				
				//Check if valid move
				if (piece.isValidMove(tempMove, board)) {
					assertTrue("Knight true loop test", 
							piece.isValidMove(tempMove, board));
				} else {
					assertFalse("Knight false loop test", 
							piece.isValidMove(tempMove, board));
				}
			}
		}
	}
	
	@Test
	public void enemyIsNearTest() throws Exception {
		for(int r = 0; r < board.length; r++){
			for(int c = 0; c < board.length; c++){
				board[r][c] = new Knight(piece.player().next());
			}
		}
		
		board[3][3] = piece;
		board[5][4] = null;
		
		assertTrue("Enemy is near test Knight", 
				piece.isValidMove(new Move(3, 3, 5, 4), board));
	}
}
