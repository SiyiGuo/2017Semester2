
public class Mage extends Npc{
	/**
	 * Mage class which created for mage object
	 */

	//constant that related to the moving direction of mage
	public static final int SGN = -1;
	public static final int CHANGEDIR = -1;
	
	//the direction
	private int MovDir = SGN;
	
	/**
	 * Constructor for the mage
	 * @param image_src the image file of the mage
	 * @param csvX the coordinate read from csv file
	 * @param csvY the coordinate read from csv file
	 */
	public Mage(String image_src, int csvX, int csvY) {
		super(image_src, csvX, csvY);
	}
	
	/**
	 * The update function for the Mage
	 * Which update position of Mage according to algorithm 1
	 */
	public void update() {
		Position oldPos = super.getPosition();
		
		Position newPos = moveAlgo1();
		
		super.setPosition(newPos);

		World.changeSpriteLoc(newPos, oldPos);
	}
	
	/**
	 * Calculate the position for the moving algorithm
	 * @return the new Position of mage
	 */
	public Position moveAlgo1() {
		Position playerPos = World.getPlayer().getPosition();
		
		Position magePos = super.getPosition();
		
		Position nxtPos = new Position(magePos.gameX, magePos.gameY);
		
		int distX = playerPos.gameX - magePos.gameX;
		int distY = playerPos.gameY - magePos.gameY;
		
		//Determine the SGN
		if (distX < 0) {
			MovDir = SGN;
		} else {
			MovDir = SGN * CHANGEDIR;
		}
		
		//calculating next Position
		if (Math.abs(distX) > Math.abs(distY)) {
			nxtPos = new Position(magePos.gameX + MovDir, magePos.gameY);
		} else {
			nxtPos = new Position(magePos.gameX, magePos.gameY - MovDir);
		}
		
		//dealing with the collision
		Sprite nxtSprite = World.getSprite(nxtPos);
		
		if (World.isBlocked(nxtPos) || nxtSprite instanceof Npc){
			MovDir = CHANGEDIR * MovDir;
			nxtPos = magePos;
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
	 * The collison effect when other object makes contact with this object
	 * @param the position which active sprite come to touch this sprite
	 * @return true is active sprite should not be update positin, false if should update
	 */
	public boolean collisionEffect(Position fromPos) {
		World.restartLevel();
		return true;
	}
}
