

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Sprite {
    public static final float TILE_SIZE = 32;
    public static final String WALL = "wall";
    public static final String STONE = "stone";
	
	//inside Sprite Class we deals with screen coordinate
	private String tileType = "";
	private Image tile = null;
	private float x;
	private float y;
	public static Sprite[] sprites = null;
	
	/**this is the constructor for this class
	 * 
	 */
	public Sprite(String image_src, float tileX, float tileY){
		try {
			tile = new Image("res/" + image_src + ".png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		tileType = image_src;
		x = tileX;
		y = tileY;
	}
	
	public void update(Input input, int delta) {
		/**
		 * This update method is created for Stones
		 * Whereas Player update method is created in player class
		 * 
		 * The update for method will only be called
		 * when it is a valid move
		 */
		if(input.isKeyPressed(Input.KEY_UP)) {
			float moveY = this.getY() - TILE_SIZE;
			this.setY(moveY);
		}
		
		if(input.isKeyPressed(Input.KEY_DOWN)) {
			float moveY = this.getY() + TILE_SIZE;
			this.setY(moveY);
		}
		
		if(input.isKeyPressed(Input.KEY_LEFT)) {
			float moveX = this.getX() - TILE_SIZE;
			this.setX(moveX);
		}
			
		if(input.isKeyPressed(Input.KEY_RIGHT)) {
			float moveX = this.getX() + TILE_SIZE;
			this.setX(moveX);
		}
	}
	
	public void render(Graphics g) {
		tile.drawCentered(x, y);
	}

	/**
	 * function used to check whether a move is valid
	 * @param x
	 * @param y
	 * @return
	 */
	public static boolean pushStone(float x, float y, Input input, int delta) {
		/*this function take the planning x and y as input
		 * and check wwhether this stone can be pushed
		 * if successful, return true
		 * else, return false
		 */
		for(int i = 0; i < sprites.length; i++) {
			float BlockedX = sprites[i].x;
			float BlockedY = sprites[i].y;
			String tileType =sprites[i].tileType; 
			if (x == BlockedX & y == BlockedY) {
				// if we find the block, we try to push it
				if (tileType.equals(WALL) | tileType.equals(STONE)) {
					return false;
				}else {
					sprites[i].update(input, delta);
					return true;
				}
			}
		}
		return true;
	}
	
	/**Extra helper function for reading and showing data 
	 *  
	 * 
	 */
	public String getTileType() {
		return tileType;
	}
	
	public float getX() {
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public void printInfo(){
		System.out.println(tileType + " " + Float.toString(x) +" " + Float.toString(y));
	}
}
