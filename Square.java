import java.awt.Color;
import java.awt.Graphics;

/**
 * One Square on our Tetris Grid or one square in our Tetris game piece
 * 
 * @author CSC 143
 */
public class Square {
	private Grid grid; // the environment where this Square is

	private int row, col; // the grid location of this Square
	
	private int fRow, fCol; // the grid location of this Square
	
	private boolean ableToMove; // true if this Square can move

	private Color color; // the color of this Square

	// possible move directions are defined by the Game class

	// dimensions of a Square
	public static final int WIDTH = 20;

	public static final int HEIGHT = 20;
	
	private int newRowAfterRotation, newColAfterRotation; // The grid location of the square after rotation the piece
	
	/**
	 * Creates a square
	 * 
	 * @param g
	 *            the Grid for this Square
	 * @param row
	 *            the row of this Square in the Grid
	 * @param col
	 *            the column of this Square in the Grid
	 * @param c
	 *            the Color of this Square
	 * @param mobile
	 *            true if this Square can move
	 * 
	 * @throws IllegalArgumentException
	 *             if row and col not within the Grid
	 */
	public Square(Grid g, int row, int col, Color c, boolean mobile) {
		if (row < 0 || row > Grid.HEIGHT - 1)
			throw new IllegalArgumentException("Invalid row =" + row);
		if (col < 0 || col > Grid.WIDTH - 1)
			throw new IllegalArgumentException("Invalid column  = " + col);

		// initialize instance variables
		grid = g;
		this.row = row;
		this.col = col;
		
		fRow = row;
		fCol = col;
		
		color = c;
		ableToMove = mobile;
	}

	/**
	 * Returns the row for this Square
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Returns the column for this Square
	 */
	public int getCol() {
		return col;
	}
	
	/**
	 * Returns true if this Square can move 1 spot in direction d
	 * 
	 * @param direction
	 *            the direction to test for possible move
	 */
	public boolean canMove(Direction direction) {
		if (!ableToMove)
			return false;

		boolean move = true;
		// if the given direction is blocked, we can't move
		// remember to check the edges of the grid
		switch (direction) {
		case DOWN:
		case DROP:
			if (row == (Grid.HEIGHT - 1) || grid.isSet(row + 1, col))
				move = false;
			break;

		// currently doesn't support checking LEFT or RIGHT
		// MODIFY so that it correctly returns if it can move left or right
		case LEFT:
			if (col == (Grid.WIDTH - (Grid.WIDTH)) || grid.isSet(row, col - 1))
				move = false;
			break;
		case RIGHT:
			if (col == (Grid.WIDTH - 1) || grid.isSet(row, col + 1))
				move = false;
			break;
		}
		return move;
	}

	/**
	 * Returns true if this Square can move 1 spot in direction d
	 * @param square
	 * @return
	 */
	public boolean canRotate(Square square) {
		if (!ableToMove) {
	      return false;
	    }

	    int squareRow = square.getRow();
	    int squareCol = square.getCol();
	    int newRow = squareRow + col - squareCol;
	    int newCol = squareCol - row + squareRow;

	    if (newCol >= Grid.WIDTH || newRow >= Grid.HEIGHT || newCol < 0 || newRow < 0) {
	      return false;
	    }

	    for (int i = Math.min(this.col, newCol); i <= Math.max(this.col, newCol); i ++) {
	      for (int j = Math.min(this.row, newRow); j <= Math.max(this.row, newRow); j ++) {
	        if (grid.isSet(j, i)) {
	          return false;
	        }
	      }
	    }
	    return true;
	}
	  
	/**
	 * moves this square in the given direction if possible.
	 * 
	 * The square will not move if the direction is blocked, or if the square is
	 * unable to move.
	 * 
	 * If it attempts to move DOWN and it can't, the square is frozen and cannot
	 * move anymore
	 * 
	 * @param direction
	 *            the direction to move
	 */
	public void move(Direction direction) {
		if (canMove(direction)) {
			switch (direction) {
			case DOWN:
			case DROP:
				row = row + 1;
				break;

			// currently doesn't support moving LEFT or RIGHT
			// MODIFY so that the Square moves appropriately
			case LEFT:
				col = col - 1;
				break;
				
			case RIGHT:
				col = col + 1;
				break;
			case ROTATERIGHT:
				break;
			}
		}
	}
	
	/**
	 * Rotates the square
	 * @param square
	 */
	public void rotate(Square square) {
		int temp = row;
	    row = square.row + col - square.col;
	    col = square.col - temp + square.row;
	}
	
	/**
	 * Changes the color of this square
	 * 
	 * @param c
	 *            the new color
	 */
	public void setColor(Color c) {
		color = c;
	}

	/**
	 * Gets the color of this square
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Draws this square on the given graphics context
	 */
	public void draw(Graphics g) {

		// calculate the upper left (x,y) coordinate of this square
		int actualX = Grid.LEFT + col * WIDTH;
		int actualY = Grid.TOP + row * HEIGHT;
		g.setColor(color);
		g.fillRect(actualX, actualY, WIDTH, HEIGHT);
		// black border (if not empty)
		if (!color.equals(Grid.EMPTY)) {
			g.setColor(Color.BLACK);
			g.drawRect(actualX, actualY, WIDTH, HEIGHT);
		}
	}
	
	public void drawNextPiece(Graphics g) {

		// calculate the upper left (x,y) coordinate of this square
		int actualX = (fCol + 14) * WIDTH;
		int actualY = (fRow + 3) * HEIGHT;
		g.setColor(color);
		g.fillRect(actualX, actualY, WIDTH, HEIGHT);
		// black border (if not empty)
		if (!color.equals(Grid.EMPTY)) {
			g.setColor(Color.BLACK);
			g.drawRect(actualX, actualY, WIDTH, HEIGHT);
		}
	}
}
