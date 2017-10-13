import org.newdawn.slick.Graphics;

public class Ice extends Block{
	
	public static final int NOCHANGE = 0;
	public static final int UNDEFINED = 0;
	public static final String YAXIS = "yaxis";
	public static final String XAXIS = "xaxis";
	
	private boolean isMoving = false;
	private int movingDirection;
	private String slideAxis;
	private int lastMovingTime = 0;
	
	
	public Ice(String image_src, int csvX, int csvY) {
		super(image_src, csvX, csvY);
	}
	
	public void render(Graphics g, int gameWidth, int gameHeight) {
		super.render(g, gameWidth, gameHeight);
		if (isMoving) {
			update(movingDirection, slideAxis, World.getTimeCount());
		}
	}
	
	public void update(int MovDir, String axis, int timeCount) {
		
		Position oldPos = super.getPosition();
		Position newPos = oldPos;
		
		if (timeCount - lastMovingTime == World.ZEROPOINT25Seconds) {
			if (axis.equals(YAXIS)) {
				newPos = new Position(oldPos.gameX, oldPos.gameY + MovDir);
			}else {
				newPos = new Position(oldPos.gameX + MovDir, oldPos.gameY);
			}
			
			//if we can still moving
			if (boxCanMove(newPos)) {
				super.setPosition(newPos);
				World.changeSpriteLoc(newPos, oldPos);
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
	
	
	public boolean collisionEffect(Position fromPos) {		
		isMoving = true;
		
		Position oldPos = super.getPosition();
		Position newPos = oldPos;
		
		int changeInX = oldPos.gameX - fromPos.gameX;
		int changeInY = oldPos.gameY - fromPos.gameY;
		
		if (changeInY == NOCHANGE) {
			movingDirection = changeInX;
			newPos = firstSliding(oldPos, movingDirection, XAXIS, World.getTimeCount());
		} else {
			movingDirection = changeInY;
			newPos = firstSliding(oldPos, movingDirection, YAXIS, World.getTimeCount());
		}
		
		if (boxCanMove(newPos)) {
			super.setPosition(newPos);
			World.changeSpriteLoc(newPos, oldPos);
			return false;
		}
		return true;
	}
	
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
	
	public boolean boxCanMove(Position newPos) {
		Sprite nxtSprite = World.getSprite(newPos);
		//if rest, we should not move
		if (nxtSprite instanceof Block || 
			nxtSprite.getTileType().equals(Sprite.WALL) ||
			nxtSprite instanceof CrackedWall||
			nxtSprite instanceof Door ||
			nxtSprite instanceof Npc ||
			nxtSprite instanceof Player){
			return false;
		} else {
			return true;
		}
	}
}
