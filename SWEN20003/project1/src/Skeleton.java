
public class Skeleton extends Npc{

	public static final int UP = -1;
	public static final int CHANGEDIR = -1;
	
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
	public void update(int timeCount) {
		Position oldPos = super.getPosition();
		
		Position newPos = moveUpDown(timeCount);
		
		super.setPosition(newPos);
			
		World.changeSpriteLoc(newPos, oldPos);
	}
	
	/**
	 * Calculating the next Position of the Skeleton
	 * If collide with Player, we restart the Level
	 * @param int timeCount, how many times has the game passed
	 * @return the next Position
	 */
	public Position moveUpDown(int timeCount) {
		
		//If one second has passed, move skeleton
		if (timeCount % World.ONESECOND == 0) {
			Position OriginalPos = super.getPosition();
			Position nxtPos = new Position(OriginalPos.gameX, OriginalPos.gameY + MovDir);
			
			Sprite nxtSprite = World.getSprite(nxtPos);
			//if blocked, we turn round
			//This is count as a action. ie Skeleton will not move after reverse direction
			if (World.isBlocked(nxtPos) ||  
				nxtSprite instanceof Block ||
				nxtSprite instanceof Npc){
				MovDir = CHANGEDIR * MovDir;
				nxtPos = OriginalPos;
			}
			
			//If catch the Player, we restart the level
			if (nxtSprite instanceof Player){
				//restart the level
				World.restartLevel();
				//and stay at initial position for this round
				nxtPos = super.getPosition();
			}
			
			return nxtPos;
		}
		
		return super.getPosition();
	}
	
	public boolean collisionEffect(Position fromPos) {
		World.restartLevel();
		return true;
	}
}
