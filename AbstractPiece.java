import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class AbstractPiece implements Piece {
	
	private boolean ableToMove; // can this piece move

	protected Square[] square; // the squares that make up this piece

	// Made up of PIECE_COUNT squares
	private Grid grid; // the board this piece is on

	// number of squares in one Tetris game piece
	private static final int PIECE_COUNT = 4;
	
	/**
	 * Creates an Abstract Shape. See class description for actual location of r
	 * and c
	 * 
	 * @param r
	 *            row location for this piece
	 * @param c
	 *            column location for this piece
	 * @param g
	 *            the grid for this game piece
	 * 
	 */
	public AbstractPiece(int r, int c, Grid g) {
		this.grid = g;
		square = new Square[PIECE_COUNT];
		ableToMove = true;
	}
	
	/**
	 * Draws the piece on the given Graphics context
	 */
	public void draw(Graphics g) {
		for (int i = 0; i < PIECE_COUNT; i++) {
			square[i].draw(g);
		}
	}
	
	/**
	 * Draws the piece on the given Graphics context
	 */
	public void drawNextPiece(Graphics g) {
		for (int i = 0; i < PIECE_COUNT; i++) {
			square[i].drawNextPiece(g);
		}
	}
	
	/**
	 * Moves the piece if possible Freeze the piece if it cannot move down
	 * anymore
	 * 
	 * @param direction
	 *            the direction to move
	 */
	public void move(Direction direction) {
		if (canMove(direction)) {
			for (int i = 0; i < PIECE_COUNT; i++)
				square[i].move(direction);
		}
		// if we couldn't move, see if because we're at the bottom
		else if (direction == Direction.DOWN) {
			ableToMove = false;
		}
	}
	
	/**
	 * Drop down the piece if possible Freeze the piece 
	 * 
	 * @param direction
	 */
	public void drop(Direction direction) {
		while (canMove(direction)) {
			for (int i = 0; i < PIECE_COUNT; i++)
				square[i].move(direction);
		}
		// if we couldn't move, see if because we're at the bottom
		if (direction == Direction.DOWN) {
			ableToMove = false;
		}
	}
	
	/**
	 *  rotate the piece if possible Freeze the piece 
	 */
	public void rotate() {
		if (this.canRotate()) {
			for (int i = 0; i < PIECE_COUNT; i++) {
				square[i].rotate(square[1]);
			}
		}
	}
	
	/**
	 * Returns the (row,col) grid coordinates occupied by this Piece
	 * 
	 * @return an Array of (row,col) Points
	 */
	public Point[] getLocations() {
		Point[] points = new Point[PIECE_COUNT];
		for (int i = 0; i < PIECE_COUNT; i++) {
			points[i] = new Point(square[i].getRow(), square[i].getCol());
		}
		return points;
	}

	/**
	 * Return the color of this piece
	 */
	public Color getColor() {
		// all squares of this piece have the same color
		return square[0].getColor();
	}

	/**
	 * Returns if this piece can move in the given direction
	 * 
	 */
	public boolean canMove(Direction direction) {
		if (!ableToMove)
			return false;

		// Each square must be able to move in that direction
		boolean answer = true;
		for (int i = 0; i < PIECE_COUNT; i++) {
			answer = answer && square[i].canMove(direction);
		}

		return answer;
	}
	

	/**
	 * Returns if this piece can rotate
	 * 
	 */
	public boolean canRotate() {
		if (!ableToMove)
			return false;
	    
	    Boolean answer = true;
	    for (int i = 0; i < PIECE_COUNT; i++) {
	    	answer = answer && square[i].canRotate(square[1]);
	    }
	    return answer;
	}
	
	
	
}
