
public class Mage extends Npc{

	public static final int SGN = -1;
	public static final int CHANGEDIR = -1;
	
	private int MovDir = SGN;
	
	public Mage(String image_src, int csvX, int csvY) {
		super(image_src, csvX, csvY);
	}
	
	public void update() {
		Position oldPos = super.getPosition();
		
		Position newPos = moveAlgo1();
		
		super.setPosition(newPos);

		World.changeSpriteLoc(newPos, oldPos);
	}
	
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
		
		//collision effect
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
	
	public boolean collisionEffect(Position fromPos) {
		World.restartLevel();
		return true;
	}
}
