
public class Skeleton extends Npc{

	public static final int UP = -1;
	public static final int ChangeDir = -1;
	
	private int MovDir = UP;
	public Skeleton(String image_src, int csvX, int csvY) {
		super(image_src, csvX, csvY);
		// TODO Auto-generated constructor stub
	}

	/**
	 * THis function is the update method for NPC
	 * Which set the new Position
	 * determined by the algorithm in Specification
	 * @param int timeCount: the counter for time that has elapsed
	 */
	public Position update(int timeCount) {
		Position newPosition = moveUpDown(timeCount);
		super.setPosition(newPosition);
		return newPosition;
	}
	
	public Position moveUpDown(int timeCount) {
		
		//If one second has passed, move skeleton
		if (timeCount % World.ONESECOND == 0) {
			Position OriginalPos = super.getPosition();
			Position nxtPos = new Position(OriginalPos.gameX, OriginalPos.gameY + MovDir);
			
			if (World.isBlocked(nxtPos)){
				MovDir = ChangeDir * MovDir;
				nxtPos = new Position(OriginalPos.gameX, OriginalPos.gameY + MovDir);
			}
			
			return nxtPos;
		}
		
		return super.getPosition();
	}
}
