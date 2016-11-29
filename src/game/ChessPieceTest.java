package game;

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.Before;
import java.util.Random;
import static org.junit.Assert.assertFalse;

public abstract class ChessPieceTest {

	private static final int BOARD_SIZE = 8;

	   /* These three variables are accessible from your child test classes */
	   protected IChessPiece[][] board;
	   protected IChessPiece piece;
	   protected static Random random;

	   static {
	       random = new Random();
	   }

	   @Before
	   public void makeBoard() {
	      // Don't put any pieces on the board.
	      board = new IChessPiece[BOARD_SIZE][BOARD_SIZE];
	      piece = make(Player.WHITE);
	   }

	   protected abstract IChessPiece make(Player p);

	   protected abstract Move getValidMove(int fromRow, int fromCol);


	   @Test(expected = IllegalArgumentException.class)
	   public void complainsIfFromLocIsNull() throws Throwable {
	      piece.isValidMove(getValidMove(0, 3), board);
	   }

	   @Test(expected = IllegalArgumentException.class)
	   public void complainsIfFromLocIsDifferentObject() throws Throwable {
	      board[1][3] = make(Player.WHITE);
	      assertFalse("ChessPiece Test 2", piece.isValidMove(
	    		  getValidMove(1, 3), board));
	   }

	   @Test
	   public void complainsIfTargetOccupiedBySamePlayer() throws Throwable {
	      Move move = getValidMove(2, 4);
	      board[move.getNewRow()][move.getNewCol()] = make(Player.WHITE);
	      board[move.getCurrentRow()][move.getCurrentCol()] = piece;
	      assertFalse("ChessPiece Test 3", piece.isValidMove(move, board));
	   }

}
