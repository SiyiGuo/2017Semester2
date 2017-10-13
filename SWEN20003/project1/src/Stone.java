
public class Stone extends Block{
	/**
	 * The stone class which used to create stone object
	 */
	
	/**
	 * Stone constructor 
	 * @param image_src the image file which stone has
	 * @param csvX the X coordinate which read from csv file
	 * @param csvY the Y coordinate which read from csv file
	 */
	public Stone(String image_src, int csvX, int csvY) {
		super(image_src, csvX, csvY);
	}
		
	/**
	 * This function doing the box moving when it is pushed
	 * @param fromPos the direction which it is being pushed
	 * @return false if it is pushed, true if it cannot push
	 */
	public boolean collisionEffect(Position fromPos) {
		Position oldPos = super.getPosition();
		
		int changeInX = oldPos.gameX - fromPos.gameX;
		int changeInY = oldPos.gameY - fromPos.gameY;
		
		Position newPos = new Position(oldPos.gameX + changeInX, oldPos.gameY + changeInY);
		
		if (boxCanMove(newPos)) {
			super.setPosition(newPos);
			World.changeSpriteLoc(newPos, oldPos);
			return false;
		}
		return true;
	}
	
	/**
	 * Show whether a box can be pushed
	 * @param newPos: next position of the box
	 * @return true if it can be moved, false if it cannot
	 */
	public boolean boxCanMove(Position newPos) {
		Sprite nxtSprite = World.getSprite(newPos);
		
		if (nxtSprite instanceof Block || 
			nxtSprite.getTileType().equals(Sprite.WALL) ||
			nxtSprite instanceof CrackedWall||
			(nxtSprite instanceof Door && !(((Door)nxtSprite).isOpen()))||
			nxtSprite instanceof Npc){
			return false;
		} else {
			return true;
		}
	}
}
