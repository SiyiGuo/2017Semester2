
public class Rogue extends Npc{
	
	public static final int LEFT = -1;
	public static final int CHANGEDIR = -1;
	
	private int MovDir = LEFT;
	
	public Rogue(String image_src, int csvX, int csvY) {
		super(image_src, csvX, csvY);
	}
	
	public void update() {
		Position oldPos  = super.getPosition();
		
		Position newPos = moveLeftRight();
		
		super.setPosition(newPos);
		
		World.changeSpriteLoc(newPos, oldPos);
	}
	
	
	/**
	 * Calculating the next Position of the Rogue
	 * If collide with Player, we restart the Level
	 * This function will only be called when Player.update is called
	 * @param int timeCount, how many times has the game passed
	 * @return the next Position
	 */
	public Position moveLeftRight() {
		Position oldPos = super.getPosition();
		
		Position nxtPos = new Position(oldPos.gameX + MovDir, oldPos.gameY);
		
		Sprite nxtSprite = World.getSprite(nxtPos);
		
		
		//if blocked, we turn round
		//This is count as a action. ie rogue will not move after reverse direction
		if (nxtSprite instanceof Block) {
			if (nxtSprite.collisionEffect(oldPos)) {
				//In this case Box cannot move, rogue will turn around
				MovDir = CHANGEDIR * MovDir;
				nxtPos = oldPos;
			} 
		}
		
		if (World.isBlocked(nxtPos) || nxtSprite instanceof Npc){
			MovDir = CHANGEDIR * MovDir;
			nxtPos = oldPos;
		}
		
		if (nxtSprite instanceof Player){
			//restart the level
			World.restartLevel();
			//and stay at initial position for this round
			nxtPos = super.getPosition();
		}
		
		return nxtPos;  
	}
	
	public boolean collisionEffect(Position fromPos) {
		World.restartLevel();
		return true;
	}
}
