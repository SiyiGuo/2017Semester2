import org.newdawn.slick.Graphics;

public class Ice extends Block{
	/**
	 * Ice class which for ice project
	 */
	
	/*Constanct for used in this class*/
	public static final int NOCHANGE = 0;
	public static final int UNDEFINED = 0;
	public static final String YAXIS = "yaxis";
	public static final String XAXIS = "xaxis";
	
	/*Variable related to the moving processing of ice*/
	private boolean isMoving = false;
	private int movingDirection;
	private String slideAxis;
	private int lastMovingTime = 0;
	
	/**
	 * Constructor for the ice
	 * @param image_src the name of image file
	 * @param csvX the X coordinate read from map file
	 * @param csvY the Y coordinate read from map file
	 */
	public Ice(String image_src, int csvX, int csvY) {
		super(image_src, csvX, csvY);
	}
	
	/**
	 * Render function for ice
	 * This is rendering as normal, except when ice is in moving status
	 * it will also update such new change
	 */
	public void render(Graphics g, int gameWidth, int gameHeight) {
		super.render(g, gameWidth, gameHeight);
		if (isMoving) {
			update(movingDirection, slideAxis, World.getTimeCount());
		}
	}
	
	/**
	 * This function will update the status of ice.
	 * If it is moving, update next positin, time count
	 * Else, reset timeCount to Undefined, isMoving status to false
	 * @param MovDir the direction which ice is moving
	 * @param axis the axis which ice is moving alone
	 * @param timeCount the current time of Update, in 0.01 unit
	 */
	public void update(int MovDir, String axis, int timeCount) {
		
		Position oldPos = super.getPosition();
		Position newPos = oldPos;
		
		//If it is the time which ice should move
		if (timeCount - lastMovingTime == World.ZEROPOINT25Seconds) {
			//Decide which axis
			if (axis.equals(YAXIS)) {
				newPos = new Position(oldPos.gameX, oldPos.gameY + MovDir);
			}else {
				newPos = new Position(oldPos.gameX + MovDir, oldPos.gameY);
			}
			
			//if we can still moving
			if (boxCanMove(newPos)) {
				super.setPosition(newPos);
				World.changeSpriteLoc(newPos, oldPos);
				//Update the time Count
				lastMovingTime = timeCount;
			} else {
				//The case which we cannot move anymore;
				//reset everything
				isMoving = false;
				slideAxis = null;
				movingDirection = UNDEFINED;
				lastMovingTime = UNDEFINED;
			}
		}
	}
	
	/**
	 * Collision function when other thing contact with this object
	 * @param fromPos the position which contact body comes to it
	 * @return true if no action pushing body need to change, false if not
	 */
	public boolean collisionEffect(Position fromPos) {		
		//When it is contacted, chage the status as ice has been pushed
		isMoving = true;
		
		Position oldPos = super.getPosition();
		Position newPos = oldPos;
		
		int changeInX = oldPos.gameX - fromPos.gameX;
		int changeInY = oldPos.gameY - fromPos.gameY;
		
		//Set the sliding direction as well as next position after ice being pushed
		if (changeInY == NOCHANGE) {
			movingDirection = changeInX;
			newPos = firstSliding(oldPos, movingDirection, XAXIS, World.getTimeCount());
		} else {
			movingDirection = changeInY;
			newPos = firstSliding(oldPos, movingDirection, YAXIS, World.getTimeCount());
		}
		
		//If move is valid
		if (boxCanMove(newPos)) {
			super.setPosition(newPos);
			World.changeSpriteLoc(newPos, oldPos);
			return false;
		}
		return true;
	}
	
	/**
	 * This function is only called when Ice is in stable mode
	 * And being pushed by something
	 * @param oldPos the original position of this ice
	 * @param MovDir the direction which ice should move
	 * @param axis the axis which ice should move along
	 * @param timeCount the timeCount of the world time
	 * @return the next position which ice will move after being pushed
	 */
	public Position firstSliding(Position oldPos, int MovDir, String axis, int timeCount) {
		Position newPos = oldPos;
		
		if (axis.equals(YAXIS)){
			newPos = new Position(oldPos.gameX, oldPos.gameY + MovDir);
		}else {
			newPos = new Position(oldPos.gameX + MovDir, oldPos.gameY);
		}
		
		//Record everything to the instance variable
		movingDirection = MovDir;
		lastMovingTime = timeCount;
		slideAxis = axis;
		return newPos;
	}
	
	/**
	 * Take the next possible position of ice
	 * @param newPos the next position that ice will move to
	 * @return true if ice can move to this function, false if not
	 */
	public boolean boxCanMove(Position newPos) {
		Sprite nxtSprite = World.getSprite(newPos);
		
		//if next to ice is block, wall,crackedwall, door, npc and player
		//we should not move the ice
		if (nxtSprite instanceof Block || 
			nxtSprite.getTileType().equals(Sprite.WALL) ||
			nxtSprite instanceof CrackedWall||
			nxtSprite instanceof Door ||
			nxtSprite instanceof Npc ||
			nxtSprite instanceof Player){
			return false;
		} else {
			//the rest case , we can move ice
			return true;
		}
	}
}
