
public class Position {
	/**
	 * Position class
	 * Which cannot be change once created
	 */
	public final int gameX;
	public final int gameY;
	
	/**
	 * Constructor for the position of every object in the game
	 * @param x the coordinate read from map file
	 * @param y the coordinate read from map file
	 */
	public Position(int x, int y) {
		gameX = x;
		gameY = y;
	}
	
	/**
	 * Method used to check whether two position is the same
	 * @param p1 position 1
	 * @param p2 position 2
	 * @return true if they are the same position, false if they are not
	 */
	public static boolean equal(Position p1, Position p2) {
		if (p1.gameX == p2.gameX && p1.gameY == p2.gameY) {
			return true;
		}else {
			return false;
		}
	}
}
