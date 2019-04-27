import java.awt.Color;

/**
 * An L-Shape piece in the Tetris Game.
 * 
 * This piece is made up of 4 squares in the following configuration:
 * 
 * Sq <br>
 * Sq <br>
 * Sq Sq <br>
 * 
 * The game piece "floats above" the Grid. The (row, col) coordinates are the
 * location of the middle Square on the side within the Grid
 * 
 * @author Junqiao
 */
public class TShape extends AbstractPiece {
	

	/**
	 * Creates an TShape piece. See class description for actual location of r
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
	public TShape(int r, int c, Grid g) {
		
		super(r, c, g);

		// Create the squares
		square[0] = new Square(g, r, c - 1, Color.yellow, true);
		square[1] = new Square(g, r, c, Color.yellow, true);
		square[2] = new Square(g, r, c + 1, Color.yellow, true);
		square[3] = new Square(g, r - 1, c, Color.yellow, true);
	}
	
}
