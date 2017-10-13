
//This class is created to record how a array was moved from a Position to Another Position
public class SingleHistory {
	/**
	 * The class which used to record how each sprite change their position
	 */
	public final Position oldPos;
	public final Position newPos;
	public final Sprite nowSprite;
	public final Sprite beforeSprite;
	
	/**
	 * COnstructor for the single sprite change
	 * @param newPosition the new position of this sprite
	 * @param oldPosition the original position of thei sprite
	 * @param nnewSprite reference to this sprite
	 * @param ooldSprite the reference to this original sprite
	 */
	public SingleHistory(Position newPosition, Position oldPosition, Sprite nnewSprite, Sprite ooldSprite) {
		newPos = newPosition;
		oldPos = oldPosition;
		nowSprite = nnewSprite;
		beforeSprite = ooldSprite;
	}
}
