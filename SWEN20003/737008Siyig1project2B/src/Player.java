import org.newdawn.slick.Input;

public class Player extends Sprite{
	/**
	 * This is the player class used to create player
	 * Which is also upda method
	 */
	
	/**
	 * constructor for the player
	 * @param image_src the player image file
	 * @param x the X coordinate read from csv
	 * @param y the Y coordinate read from csv
	 */
	public Player(String image_src, int x, int y) {
		super(Sprite.PLAYERFILE, x, y);
	}
	
	/**
	 * Thake the input and updat the Player
	 * @param update
	 */
	public void update(Input input) {
		//calculating the next Position which player will move to
		Position oldPos = super.getPosition();
		Position newPos = null;
		if(input.isKeyPressed(Input.KEY_UP)) {
			int moveY = oldPos.gameY - 1;
			int moveX = oldPos.gameX;
			newPos = new Position(moveX, moveY);
		}
		
		if(input.isKeyPressed(Input.KEY_DOWN)) {
			int moveY = oldPos.gameY + 1;
			int moveX = oldPos.gameX;
			newPos = new Position(moveX, moveY);
		}
		
		if(input.isKeyPressed(Input.KEY_LEFT)) {
			int moveX = oldPos.gameX - 1;
			int moveY = oldPos.gameY;
			newPos = new Position(moveX, moveY);
		}
			
		if(input.isKeyPressed(Input.KEY_RIGHT)) {
			int moveX = oldPos.gameX + 1;
			int moveY = oldPos.gameY;
			newPos = new Position(moveX, moveY);
		}
		
		//if player move, see what happened
		if(newPos != null) {
			World.setMoveCount();
			//if it is tile class, we cannot move
			if (!World.isBlocked(newPos)) {
				Sprite nxtSprite = World.getSprite(newPos);

				if (nxtSprite.collisionEffect(oldPos)) {
					//the case which we do not update player position
					/*For example:
					 * push box into the wall
					 * restart the world
					 */
				}else {
					//Finally this can do, we update the player Position
					super.setPosition(newPos);
					World.changeSpriteLoc(newPos, oldPos);
					
					//Then we Need to Move Rogue and Mage
					//As they move each time Players made a Move
				}
			}
			
			//We then updae switch, rogue and mage after player changed
			Switch sswitch = World.getSwitch();
			if (sswitch != null) {
				sswitch.update();
			}
			
			Rogue rogue = World.getRogue();
			if (rogue != null) {
				rogue.update();
			}
			
			Mage mage = World.getMage();
			if (mage != null) {
				mage.update();
			}
		}
	}
}
