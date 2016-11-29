package checkers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


/************************************************************
 * JUnit testing for the GamePiece Class.
 * @author Nate Benson, Jake Young, Kaye Suarez
 * @version 1.0
 ************************************************************/
public abstract class GamePieceTest {

	/** Fixed Board size. */ 
	private static final int BOARD_SIZE = 8;

	/** Array of GamePieces. */ 
	protected IGamePiece[][] board;
	
	/** Single GamePiece. */
	protected IGamePiece piece;
	
	/** Second GamePiece. */
	protected IGamePiece piece2;

	/***********************************
	 * Setup empty array of game pieces.
	 ***********************************/
	@Before
	public final void makeBoard() {
		// Don't put any pieces on the board.
		board = new IGamePiece[BOARD_SIZE][BOARD_SIZE];
		piece = make(Player.RED);
	}

	/*********************
	 * Set owner of piece.
	 * @param p player
	 * @return IGamePiece 
	 *********************/
	protected abstract IGamePiece make(Player p);

	/*********************
	 * Set owner of piece.
	 * @param fromRow 
	 * @param fromCol 
	 * @return move 
	 *********************/
	protected abstract Move getValidMove(int fromRow, int fromCol);
	
	/******************************************
	 * Creates piece and checks owner of piece.
	 * @throws Throwable 
	 ******************************************/
	@Test
	public final void redPiece() throws Throwable {
		piece.setPlayer(Player.RED);
		
		assertEquals("RED", piece.player().name());
	}
	
	/******************************************
	 * Creates piece and checks owner of piece.
	 * @throws Throwable 
	 ******************************************/
	@Test
	public final void grayPiece() throws Throwable {
		piece.setPlayer(Player.GRAY);
		
		assertEquals("RED", piece.player().name());
	}

	/************************************************
	 * Checks if location is occupied by same player.
	 * @throws Throwable 
	 ************************************************/
	@Test
	public final void complainTargetOccupiedBySamePlayer() throws Throwable {
		
		Move move = getValidMove(2, 4);
		
		int row = move.getCurrentRow();
		int col = move.getCurrentCol();
		int newR = move.getNewRow();
		int newC = move.getNewCol();
		
		board[newR][newC] = make(Player.RED);
		board[row][col] = piece;
		assertFalse("GamePiece Test", piece.isValidMove(move, board));
	}

}
