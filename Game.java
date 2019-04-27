import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

/**
 * Manages the game Tetris. Keeps track of the current piece and the grid.
 * Updates the display whenever the state of the game has changed.
 * 
 * @author CSC 143
 */
public class Game {

	private Grid grid; // the grid that makes up the Tetris board

	private Tetris display; // the visual for the Tetris game

	//private LShape piece; // the current piece that is dropping
	
	private Piece piece; // the current piece that is dropping
	
	private Piece nextPiece; // the next piece that will drop
	
	private boolean isOver; // has the game finished?
	
	private int pieceNumber, nextPieceNumber; // The random number of the piece type
	
	Random random = new Random();
	
	/**
	 * Creates a Tetris game
	 * 
	 * @param Tetris
	 *            the display
	 */
	public Game(Tetris display) {
		grid = new Grid();
		this.display = display;
		//piece = new BarShape(1, Grid.WIDTH / 2 - 1, grid);
		
		pieceNumber = random.nextInt(7);
		randomPiece(pieceNumber);
		
		nextPieceNumber  = random.nextInt(7);
		nextRandomPiece(nextPieceNumber);
		
		isOver = false;
	}
	/**
	 * Randomly chooses which shape to instantiate
	 * @param pieceNumber
	 * @return
	 */
	public Piece randomPiece(int pieceNumber) {
		switch(pieceNumber) {
			case 0:
				piece = new LShape(1, Grid.WIDTH / 2 - 1, grid);
				break;
			case 1:
				piece = new ZShape(1, Grid.WIDTH / 2 - 1, grid);
				break;
			case 2:
				piece = new SquareShape(1, Grid.WIDTH / 2 - 1, grid);
				break;
			case 3:
				piece = new JShape(1, Grid.WIDTH / 2 - 1, grid);
				break;
			case 4:
				piece = new TShape(1, Grid.WIDTH / 2 - 1, grid);
				break;
			case 5:
				piece = new SShape(1, Grid.WIDTH / 2 - 1, grid);
				break;
			case 6:
				piece = new BarShape(1, Grid.WIDTH / 2 - 1, grid);
				break;
			
		}
		return piece;
	}
	
	/**
	 * Show next random piece
	 * @param pieceNumber
	 * @return
	 */
	public Piece nextRandomPiece(int pieceNumber) {
		switch(pieceNumber) {
			case 0:
				nextPiece = new LShape(1, Grid.WIDTH / 2 - 1, grid);
				break;
			case 1:
				nextPiece = new ZShape(1, Grid.WIDTH / 2 - 1, grid);
				break;
			case 2:
				nextPiece = new SquareShape(1, Grid.WIDTH / 2 - 1, grid);
				break;
			case 3:
				nextPiece = new JShape(1, Grid.WIDTH / 2 - 1, grid);
				break;
			case 4:
				nextPiece = new TShape(1, Grid.WIDTH / 2 - 1, grid);
				break;
			case 5:
				nextPiece = new SShape(1, Grid.WIDTH / 2 - 1, grid);
				break;
			case 6:
				nextPiece = new BarShape(1, Grid.WIDTH / 2 - 1, grid);
				break;
			
		}
		return nextPiece;
	}
	
	/**
	 * Draws the current state of the game
	 * 
	 * @param g
	 *            the Graphics context on which to draw
	 */
	public void draw(Graphics g) {
		grid.draw(g);
		if (piece != null) {
			piece.draw(g);
			nextPiece.drawNextPiece(g);
		}
	}

	/**
	 * Moves the piece in the given direction
	 * 
	 * @param the
	 *            direction to move
	 */
	public void movePiece(Direction direction) {
		if (piece != null) {
			piece.move(direction);
		}
		updateDisplay();
	}
	
	/**
	 * Drop the piece in the given direction
	 * @param direction
	 */
	public void dropPiece(Direction direction) {
		if (piece != null) {
			piece.drop(direction);
		}
		updateDisplay();
		
	}
	
	/**
	 * Rotate the piece
	 */
	public void rotatePiece() {
		if (piece != null && pieceNumber != 2) { // Not rotate if it is SquareShape.
			piece.rotate();
		}
		updateDisplay();
	}
	
	/**
	 * updatePiece(); display.update(); grid.checkRows();
	 */
	public void updateDisplay() {
		updatePiece();
		display.update();
		grid.checkRows();
	}
	
	/**
	 * Returns true if the game is over
	 */
	public boolean isGameOver() {
		// game is over if the piece occupies the same space as some non-empty
		// part of the grid. Usually happens when a new piece is made
		if (piece == null) {
			return false;
		}

		// check if game is already over
		if (isOver) {
			return true;
		}

		// check every part of the piece
		Point[] p = piece.getLocations();
		for (int i = 0; i < p.length; i++) {
			if (grid.isSet((int) p[i].getX(), (int) p[i].getY())) {
				isOver = true;
				return true;
			}
		}
		return false;
	}

	/** Updates the piece */
	private void updatePiece() {
		if (piece == null) {
			// CREATE A NEW PIECE HERE
			//piece = new LShape(1, Grid.WIDTH/2 -1, grid);
			pieceNumber = nextPieceNumber;
			randomPiece(pieceNumber);
			
			nextPieceNumber = random.nextInt(7);
			
			nextRandomPiece(nextPieceNumber);
		}

		// set Grid positions corresponding to frozen piece
		// and then release the piece
		else if (!piece.canMove(Direction.DOWN)) {
			Point[] p = piece.getLocations();
			Color c = piece.getColor();
			for (int i = 0; i < p.length; i++) {
				grid.set((int) p[i].getX(), (int) p[i].getY(), c);
			}
			piece = null;
		}

	}

}
