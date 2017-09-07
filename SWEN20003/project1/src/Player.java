import org.newdawn.slick.Input;

public class Player extends Sprite{
	//inside Sprite Class we deals with screen coordinate
	public Player(String image_src, float x, float y) {
		super(image_src, x, y);
	}
	
	public void update(Input input, int delta) {
		/**
		 * This update method is created for Boxes
		 * Whereas Player update method is created in player class
		 * but also for box
		 */
		if(input.isKeyPressed(Input.KEY_UP)) {
			float moveY = this.getY() - TILE_SIZE;
			String destination = MoveTo(this.getX(), moveY);
			if (destination.equals(WALL)) {
				//do nothing
			} else if (destination.equals(STONE)) {
				float StoneMoveY = moveY - TILE_SIZE;
				//the push stone
				//if push successful, we move player
				if (Sprite.pushStone(this.getX(), StoneMoveY, input, delta)) {
					this.setY(moveY);
				}
			} else {
				this.setY(moveY);
			}
		}
		
		if(input.isKeyPressed(Input.KEY_DOWN)) {
			float moveY = this.getY() + TILE_SIZE;
			String destination = MoveTo(this.getX(), moveY);
			if (destination.equals(WALL)) {
				//do nothing
			} else if (destination.equals(STONE)) {
				float StoneMoveY = moveY - TILE_SIZE;
				//the push stone
				//if push successful, we move player
				if (Sprite.pushStone(this.getX(), StoneMoveY, input, delta)) {
					this.setY(moveY);
				}
			} else {
				this.setY(moveY);
			}
		}
		
		if(input.isKeyPressed(Input.KEY_LEFT)) {
			float moveX = this.getX() - TILE_SIZE;
			String destination = MoveTo(moveX, this.getY());
			if (destination.equals(WALL)) {
				//do nothing
			} else if (destination.equals(STONE)) {
				float StoneMoveX = moveX - TILE_SIZE;
				//the push stone
				//if push successful, we move player
				if (Sprite.pushStone(StoneMoveX, this.getY(), input, delta)) {
					this.setX(moveX);
				}
			} else {
				this.setX(moveX);
			}
		}
			
		if(input.isKeyPressed(Input.KEY_RIGHT)) {
			float moveX = this.getX() + TILE_SIZE;
			String destination = MoveTo(moveX, this.getY());
			if (destination.equals(WALL)) {
				//do nothing
			} else if (destination.equals(STONE)) {
				float StoneMoveX = moveX + TILE_SIZE;
				//the push stone
				//if push successful, we move player
				if (Sprite.pushStone(StoneMoveX, this.getY(), input, delta)) {
					this.setX(moveX);
				}
			} else {
				this.setX(moveX);
			}
		}
	}
	
	public String MoveTo(float x, float y) {
		/*this function take the planning x and y as input
		 * and return true if it is a valid move
		 * Else return false.
		 */
		String tileType = "";
		for(int i = 0; i < sprites.length; i++) {
			float BlockedX = sprites[i].getX();
			float BlockedY = sprites[i].getY();
			tileType =sprites[i].getTileType(); 
			if (x == BlockedX & y == BlockedY) {
				break;
			}
		}
		return tileType;
	}
}
