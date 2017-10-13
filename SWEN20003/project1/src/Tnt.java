import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tnt extends Block{
	
	private Image exploEffect;
	private boolean effectOn = false; //the effect is default to be false
	private int starTime = 0;
	
	
	public Tnt(String image_src, int csvX, int csvY) {
		super(image_src, csvX, csvY);
		//adding the explosion effect
		try {
			exploEffect = new Image(App.IMAGE_FOLDER + Sprite.EXPLOSION + App.IMAGE_SUFFIX);
		} catch (SlickException e) {
			e.printStackTrace();
		} 
	}
	
	public void update(int timeCount) {
		if (effectOn) {
			super.setImage(exploEffect);
		}
		
		if (timeCount - starTime == World.ZEROPOINT4Seconds && effectOn) {
			World.destroySprite(super.getPosition());
		}
	}
	
	public void render(Graphics g, int gameWidth, int gameHeight) {
		super.render(g, gameWidth, gameHeight);
		update(World.getTimeCount());
	}
	
	public void explosion(Position newPos) {
		effectOn = true;
		starTime = World.getTimeCount();
		World.setTopSprite(newPos, super.getPosition());
		World.destroyTntHistory();
		super.setPosition(newPos);
	}
	
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
		
		//if effect on, we still need to update player
		if (effectOn) {
			return false;
		}
		
		return true;
	}
	
	public boolean boxCanMove(Position newPos) {
		Sprite nxtSprite = World.getSprite(newPos);
		
		//Check whether it is cracked Wall
		if (nxtSprite instanceof CrackedWall) {
			explosion(newPos);
			//as if it is explosion, we have another mechanism to move tnt
			return false;
		}
		
		//if rest, we should not move
		if (nxtSprite instanceof Block || 
			nxtSprite.getTileType().equals(Sprite.WALL) ||
			nxtSprite instanceof CrackedWall||
			nxtSprite instanceof Door ||
			nxtSprite instanceof Npc){
			return false;
		} else {
			return true;
		}
	}

	public boolean isExploding() {
		if(effectOn) {
			return true;
		}
		return false;
	}
}
