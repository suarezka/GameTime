package chess;

import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;
import chess.Rook;

import org.junit.Test;

import static org.junit.Assert.*;

/************************************************************
 * CIS 163-07
 * Chess Project 
 * JUnit Test for the Rook class
 * 
 * @author DaiLynn Dietz 
 * @author Kaye Suarez
 * @author Zachary Kurmas & Hans Dulimarta
 * @version Mar 18, 2014
 ***********************************************************/
// Created 12/8/12 at 9:01 PM
// (C) Zachary Kurmas 2012

public class RookTest extends ChessPieceTest {

   protected IChessPiece make(Player p) {
      return new Rook(p);
   }

   @Override
   protected Move getValidMove(int row, int col) {
      int newRow = row + 1;
      if (newRow >= board.length) {
         newRow = row - 1;
      }
      return new Move(row, col, newRow, col);
   }

   @Test
   public void canMoveInRight() throws Exception {
      board[1][1] = piece;
      Move rookRight = new Move(1,1,1,2);
      assertTrue("Rook Test 1", piece.isValidMove(rookRight, board));
   }

   @Test
   public void canMoveInLeft() throws Exception {
      board[4][4] = piece;
      assertTrue("Rook Test 1", piece.isValidMove(new Move(4, 4, 4, 3), board));
   }
   
   @Test
   public void canMoveUp() throws Exception {
	   board[5][5] = piece;
	   assertTrue("Rook Test 1", piece.isValidMove(new Move(5,5,4,5), board));
   }

   @Test
   public void canMoveDown() throws Exception {
	   board[1][1] = piece;
	   assertTrue("Should be possible Rook move down",
			   piece.isValidMove(new Move(1,1,2,1), board));
   }
   
   @Test
   public void isRook() throws Exception {
	   assertEquals("Rook", piece.type());
   }
   
   @Test
   public void checkInvalidMoves() throws Exception {
	   final int startRow = 3;
	   final int startCol = 3;
	   
	   board[startRow][startCol] = piece;
	   
	   //Looping through entire board and trying to move piece there
	   for(int row = 0; row < board.length; row++){
		   for(int col = 0; col < board[0].length; col++){
			   
			   boolean canMove = piece.isValidMove(
					   new Move(startRow, startCol, row, col), board);
			   
			   //Ensures piece can move in straight line
			   //Uses exclusive or operator (^) to make sure piece isnt 
			   //tested at start location
			   if(row == startRow ^ col == startCol){
				   assertTrue(canMove);
				   
			    //Ensures piece cant move other directions
			   }else{
				   assertFalse(canMove);
			   }
		   }
	   }
   }
   
   @Test
   public void pieceInWayRight() throws Exception {
	   IChessPiece otherPiece = make(Player.WHITE);
	   
	   board[0][0] = piece;
	   board[0][3] = otherPiece;
	   
	   assertFalse("Rook pieceInTheWay Test 1", piece.isValidMove(
			   new Move(0, 0, 0, 4), board));
   }
   
   @Test
   public void pieceInWayLeft() throws Exception {
	   IChessPiece otherPiece = make(Player.WHITE);
	   
	   board[6][4] = piece;
	   board[6][2] = otherPiece;
	   Move rookLeft = new Move(6, 4, 6, 1);
	   
	   assertFalse("Rook pieceInTheWay Test 2", 
			   piece.isValidMove(rookLeft, board));
   }
   
   @Test
   public void pieceInWayUp() throws Exception {
	   IChessPiece otherPiece = make(Player.WHITE);
	   
	   board[5][2] = piece;
	   board[4][2] = otherPiece;
	   Move rookUp = new Move(5, 2, 3, 2);
	   
	   assertFalse("Rook pieceInTheWay Test 3", 
			   piece.isValidMove(rookUp, board));
   }
   
   @Test
   public void pieceInWayDown() throws Exception {
	   IChessPiece otherPiece = make(Player.WHITE);
	   
	   board[2][3] = piece;
	   board[4][3] = otherPiece;
	   Move rookDown = new Move(2, 3, 4, 3);
	   
	   assertFalse("Rook pieceInTheWay Test 4", 
			   piece.isValidMove(rookDown, board));
   }
   
   @Test
   public void pieceOutOfWayHorizontal() throws Exception {
	   IChessPiece otherPiece = make(Player.WHITE);
	   
	   board[3][3] = piece;
	   board[3][5] = otherPiece;
	   
	   assertTrue("Rook pieceOutOfWay Test 1", piece.isValidMove(
			   new Move(3, 3, 3, 4), board));
   }
   
   @Test
   public void pieceOutOfWayVertical() throws Exception {
	   IChessPiece otherPiece = make(Player.WHITE);
	   
	   board[2][2] = piece;
	   board[5][2] = otherPiece;
	   
	   assertTrue("Rook pieceOutOfWay Test 2", piece.isValidMove(
			   new Move(2, 2, 4, 2), board));
   } 
   
   @Test
   public void enemyIsNearTest() throws Exception {
	   
	   //Making enemies
	   IChessPiece first = make(piece.player().next());
	   IChessPiece second = make(piece.player().next());
	   IChessPiece third = make(piece.player().next());
	   
	   //Placing pieces near destination
	   board[0][1] = piece;
	   board[3][1] = first;
	   board[2][0] = second;
	   board[2][2] = third;
	   
	   assertTrue("Rook enemyIsNearTest", 
			   piece.isValidMove(new Move(0,1,2,1), board));
   }
   
   @Test
   public void longDownMove() throws Exception {
	   board[0][2] = piece;
	   Move move = new Move(0,2,7,2);
	   assertTrue("Long Down Rook Test", piece.isValidMove(move, board));
   }
   
   @Test
   public void longUpMove() throws Exception {
	   board[7][2] = piece;
	   Move move = new Move(7,2,0,2);
	   assertTrue("Long Down Rook Test", piece.isValidMove(move, board));
   }
   
   @Test
   public void longRightMove() throws Exception {
	   board[0][0] = piece;
	   Move move = new Move(0,0,0,7);
	   assertTrue("Long Down Rook Test", piece.isValidMove(move, board));
   }
   
   @Test
   public void longLeftMove() throws Exception {
	   board[0][7] = piece;
	   Move move = new Move(0,7,0,0);
	   assertTrue("Long Down Rook Test", piece.isValidMove(move, board));
   }
}
