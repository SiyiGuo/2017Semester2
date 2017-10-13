
public class Rogue extends Npc{
	/**
	 * Rogue class which used to create rogue object
	 */
	
	/*COnstant relate to set the direction of Rogue*/
	public static final int LEFT = -1;
	public static final int CHANGEDIR = -1;
	
	/*The direction of Rogue*/
	private int MovDir = LEFT;
	
	/**
	 * Constructor for rogue
	 * @param image_src the image file name
	 * @param csvX the X coordiante read from mapfile
	 * @param csvY the Y coordinate read from map file
	 */
	public Rogue(String image_src, int csvX, int csvY) {
		super(image_src, csvX, csvY);
	}
	
	/**
	 * The updat method for rogue
	 * Which update according to specification
	 * This is called when Player is update
	 */
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
	 * @return the next Position of rogue
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
	
	/**
	 * THe collision when player made contact with
	 * In which case, it will restart this level
	 * @param: the Position whcih player come fro
	 * @return true if need to restart the world, false if not
	 */
	public boolean collisionEffect(Position fromPos) {
		World.restartLevel();
		return true;
	}
}
