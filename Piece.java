import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public interface Piece {
	

	/**
	 * Draws the piece on the given Graphics context
	 */
	public default void draw(Graphics g) {
		
	}
	
	
	public default void drawNextPiece(Graphics g) {
		
	}
	
	/**
	 * Moves the piece if possible Freeze the piece if it cannot move down
	 * anymore
	 * 
	 * @param direction
	 *            the direction to move
	 */
	public default void move(Direction direction) {
		
	}
	
	/**
	 * Drop down the piece if possible Freeze the piece 
	 * 
	 * @param direction
	 */
	public default void drop(Direction direction) {
		
	}
	
	/**
	 * 
	 * @param direction
	 */
	public default void rotate() { 

	}
	
	/**
	 * Returns the (row,col) grid coordinates occupied by this Piece
	 * 
	 * @return an Array of (row,col) Points
	 */
	public default Point[] getLocations() {
		return null;
	}

	/**
	 * Return the color of this piece
	 */
	public default Color getColor() {
		return null;
	}

	/**
	 * Returns if this piece can move in the given direction
	 * 
	 */
	public default boolean canMove(Direction direction) {
		return false;
	}
	
}
