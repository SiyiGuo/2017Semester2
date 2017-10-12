
//This class is created to record how a array was moved from a Position to Another Position
public class SingleHistory {
	public final Position oldPos;
	public final Position newPos;
	public final Sprite nowSprite;
	public final Sprite beforeSprite;
	
	public SingleHistory(Position newPosition, Position oldPosition, Sprite nnewSprite, Sprite ooldSprite) {
		newPos = newPosition;
		oldPos = oldPosition;
		nowSprite = nnewSprite;
		beforeSprite = ooldSprite;
	}
}
