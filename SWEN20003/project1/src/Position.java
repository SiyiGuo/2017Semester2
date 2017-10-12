
public class Position {
	public final int gameX;
	public final int gameY;
	
	public Position(int x, int y) {
		gameX = x;
		gameY = y;
	}
	
	public static boolean equal(Position p1, Position p2) {
		if (p1.gameX == p2.gameX && p1.gameY == p2.gameY) {
			return true;
		}else {
			return false;
		}
	}
}
