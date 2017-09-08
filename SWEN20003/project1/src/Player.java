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
			float nextY = this.getY() - TILE_SIZE;
			int nextID = findNextID(this.getX(), nextY);
			if (sprites[nextID].getTileType().equals(WALL)) {
				//do nothing
			} else if (sprites[nextID].getTileType().equals(STONE)) {
				float stoneNextY = nextY - TILE_SIZE;
				int stoneID = nextID;
				if (Sprite.pushStone(this.getX(), stoneNextY, stoneID, Input.KEY_UP)) {
					this.setY(nextY);
				}
			} else {
				this.setY(nextY);
			}
		}
		
		if(input.isKeyPressed(Input.KEY_DOWN)) {
			float nextY = this.getY() + TILE_SIZE;
			int nextID = findNextID(this.getX(), nextY);
			if (sprites[nextID].getTileType().equals(WALL)) {
				//do nothing
			} else if (sprites[nextID].getTileType().equals(STONE)) {
				float stoneNextY = nextY + TILE_SIZE;
				int stoneID = nextID;
				if (Sprite.pushStone(this.getX(), stoneNextY, stoneID, Input.KEY_DOWN)) {
					this.setY(nextY);
				}
			} else {
				this.setY(nextY);
			}
		}
		
		if(input.isKeyPressed(Input.KEY_LEFT)) {
			float nextX = this.getX() - TILE_SIZE;
			int nextID = findNextID(nextX, this.getY());
			if (sprites[nextID].getTileType().equals(WALL)) {
				//do nothing
			} else if (sprites[nextID].getTileType().equals(STONE)) {
				float stoneNextX = nextX - TILE_SIZE;
				int stoneID = nextID;
				if (Sprite.pushStone(stoneNextX, this.getY(), stoneID, Input.KEY_LEFT)) {
					this.setX(nextX);
				}
			} else {
				this.setX(nextX);
			}
		}
			
		if(input.isKeyPressed(Input.KEY_RIGHT)) {
			float nextX = this.getX() + TILE_SIZE;
			int nextID = findNextID(nextX, this.getY());
			if (sprites[nextID].getTileType().equals(WALL)) {
				//do nothing
			} else if (sprites[nextID].getTileType().equals(STONE)) {
				float stoneNextX = nextX + TILE_SIZE;
				int stoneID = nextID;
				if (Sprite.pushStone(stoneNextX, this.getY(), stoneID, Input.KEY_RIGHT)) {
					this.setX(nextX);
				}
			} else {
				this.setX(nextX);
			}
		}
	}
	
	public int findNextID(float x, float y) {
		/*this function take the planning x and y as input
		 * and return the id in Sprite of your next next
		 */
		int id = -1;
		for(int i = 0; i < sprites.length; i++) {
			float BlockedX = sprites[i].getX();
			float BlockedY = sprites[i].getY();
			String tileType =sprites[i].getTileType(); 
			if (x == BlockedX & y == BlockedY) {
				id = i;
				if (tileType.equals(STONE)) {
					id = i;
					return id;
				}
			}
		}
		return id;
	}
}
