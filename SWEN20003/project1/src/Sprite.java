
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Sprite {
	/** TILE **/
	public static final String FLOOR = "floor";
	public static final String WALL = "wall";
    public static final String TARGET = "target";
    public static final String CRACKED_WALL = "cracked_wall";
    public static final String SWITCH = "switch";
    public static final String DOOR = "door";
    
    /** BLOCK **/
    public static final String STONE = "stone";
    public static final String ICE = "ice";
    public static final String TNT = "tnt";
    public static final String EXPLOSION = "explosion";
    
    /** NPC **/
    public static final String ROGUE = "rogue";
    public static final String MAGE = "mage";
    public static final String SKELETON = "skull";
    
    /** Player **/
    public static final String PLAYER = "player";
    public static final String PLAYERFILE = "player_left";
    
    
	//inside Sprite Class we deals with screen coordinate
	private String tileType = "";
	private Image tile = null;
	private float gameX;
	private float gameY;
	public static Sprite[] sprites = null;
	
	/**this is the constructor for this class
	 * Which create a single sprite
	 * @param image_Src: the name of image in the res folder
	 * @param gameX: the game coordinate which read from csv file
	 * @param gameY: the game coordinate which read from csv file
	 */
	public Sprite(String image_src, float csvX, float csvY){
		try {
			tile = new Image(App.IMAGE_FOLDER + image_src + App.IMAGE_SUFFIX);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		tileType = image_src;
		gameX = csvX;
		gameY = csvY;
	}
	
	/**
	 * This method used to read the user input
	 * And update the new sprite coordinate
	 * @param input the user's action on the keyboard
	 */
	public void update(int input) {
		if(input == Input.KEY_UP) {
			float moveY = this.getGameY() - 1;
			this.setGameY(moveY);
		}
		
		if(input == Input.KEY_DOWN) {
			float moveY = this.getGameY() + 1;
			this.setGameY(moveY);
		}
		
		if(input == Input.KEY_LEFT) {
			float moveX = this.getGameX() - 1;
			this.setGameX(moveX);
		}
			
		if(input == Input.KEY_RIGHT) {
			float moveX = this.getGameX() + 1;
			this.setGameX(moveX);
		}
	}
	
	/**
	 * TODO Adding a converting function
	 * This the render function, which we only convert the coordinate system
	 * From game coordinate to screen coordinate at render step.
	 * Which is the final step
	 * @param g
	 */
	public void render(Graphics g) {
		float scrX = 0;
		float scrY = 0; 
		tile.drawCentered(scrX, scrY);
	}

	/**
	 * TODO rewrite the pushing stone function
	 * This function used to perform pushing stone
	 */
	public static boolean pushStone(float nextX, float nextY, int stoneID, int input) {
		/*this function take the planning x and y as input
		 * and check whether this stone can be pushed
		 * if successful, return true
		 * else, return false
		 */
		for(int i = 0; i < sprites.length; i++) {
			float BlockedX = sprites[i].gameX;
			float BlockedY = sprites[i].gameY;
			String tileType =sprites[i].tileType; 
			if (nextX == BlockedX & nextY == BlockedY) {
				// if we find the block, we try to push it
				if (tileType.equals(WALL) | tileType.equals(STONE)) {
					return false;
				}
			}
		}
		sprites[stoneID].update(input);
		return true;
	}
	
	
	
	
	/**
	 * Extra helper function for reading and showing data 
	 * All the setter and getter for the class
	 * Which tellse class's basic information
	 */
	public String getTileType() {
		return tileType;
	}
	
	public float getGameX() {
		return this.gameX;
	}
	
	public float getGameY() {
		return this.gameY;
	}
	
	public void setGameX(float x) {
		this.gameX = x;
	}
	
	public void setGameY(float y) {
		this.gameY = y;
	}
	
	public void printInfo(){
		System.out.println(tileType + " " + Float.toString(gameX) +" " + Float.toString(gameY));
	}
}
