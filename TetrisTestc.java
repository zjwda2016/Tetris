import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import java.awt.Color;
import java.awt.Point;

public class TetrisTestc {
	
	/**
	 * Test the motion of a square
	 * assertEquals
	 * @param direction
	 * @param colSquare
	 * @param rowSquare
	 * @param color
	 * @param col
	 * @param row
	 */
	public void assertMotionOfSquare(Direction direction, int colSquare, int rowSquare, Color color, int col, int row) {
		Grid grid = new Grid();
	    Square square = new Square(grid, rowSquare, colSquare, color, true);
		square.move(direction);
	    assertEquals(col, square.getCol());
	    assertEquals(row, square.getRow());
	}

	/**
	 * Test the motion of a square
	 */
	@Test
	public void testMotionOfSquare() {
		assertMotionOfSquare(Direction.LEFT, 5, 15, Color.magenta, 4, 15);
		assertMotionOfSquare(Direction.LEFT, 9, 5, Color.magenta, 8, 5);
	    assertMotionOfSquare(Direction.RIGHT, 5, 15, Color.magenta, 6, 15);
	    assertMotionOfSquare(Direction.RIGHT, 8, 18, Color.magenta, 9, 18);
	}
	
	/**
	 * Test the motion of an L-shaped piece
	 * @param direction
	 * @param c
	 * @param r
	 * @param col
	 * @param row
	 * @param dtimes
	 */
	public void assertMotionOfLShape(Direction direction, int c, int r, int col, int row, int dtimes) {
		Grid grid = new Grid();
	    LShape lshapeTest = new LShape(r, c, grid);
	    
	    switch (direction) {
	    	case LEFT:
	    	case RIGHT:
	    		for(int i = 0; i < dtimes; i++) {
	    			lshapeTest.move(direction);
	    		}
	    		break;
	    	case DROP:
	    		lshapeTest.drop(direction);
	    		break;
	    		
	    }
		Point testPoint = new Point(row, col);
	    assertEquals(testPoint, lshapeTest.getLocations()[1]);
	}
	/**
	 * Test the motion of an L-shaped piece
	 */
	@Test
	public void testMotionOfLShaped() {
		assertMotionOfLShape(Direction.RIGHT, 0, 15, 8, 15, 8);
		assertMotionOfLShape(Direction.LEFT, 8, 15, 0, 15, 8);
		assertMotionOfLShape(Direction.DROP, 7, 1, 7, 18, 1);
	}
	
	/**
	 * 
	 * @param rRow
	 * @param cCol
	 * @param row
	 */
	public void rowTest(int rRow, int cCol, int row) {
		Grid g = new Grid();
		for(int r = 0; r < Grid.HEIGHT; r ++) {
			for(int c = 0; c < Grid.WIDTH; c ++) {
				if(r == rRow && (c == cCol || c == cCol + 1))
					g.set(r, c,Color.BLUE);
				else if(r != rRow)
					g.set(r, c,Color.BLUE);
			}
		}
		g.checkRows();
		for(int r = 0; r < Grid.HEIGHT; r ++) {
			for(int c = 0; c < Grid.WIDTH; c ++) {
				if(r == row && (c == cCol || c == cCol + 1))
					assertTrue(g.isSet(r, c));
				else
					assertFalse(g.isSet(r,c));
			}
		}
	}
	/**
	 * 
	 */
	@Test
	public void testRowInGrid()	{
		rowTest(9, 5, 19);
		rowTest(0, 5, 19);
	}
	
	@Test
	public void rowTest3() {
			Grid g = new Grid();
			for(int r = 0; r < Grid.HEIGHT; r++) {
				for(int c = 0; c < Grid.WIDTH; c++) {
					if(r%2 == 0)
						g.set(r, c,Color.BLUE);
					else if(c == 5)
						g.set(r, c,Color.GREEN);
				}
			}
			g.checkRows();
			for(int r = 0; r < Grid.HEIGHT; r++) {
				for(int c = 0; c < Grid.WIDTH; c++) {
					if(c == 5 && r > Grid.HEIGHT/2 - 1)
						assertTrue(g.isSet(r, c));
					else
						assertFalse(g.isSet(r,c));
				}
			}
		}
	
}
