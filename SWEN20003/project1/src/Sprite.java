
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
    public static final String SKELETON = "skeleton";
    public static final String SKULL = "skull";
    
    /** Player **/
    public static final String PLAYER = "player";
    public static final String PLAYERFILE = "player_left";
    
    
	//inside Sprite Class we deals with screen coordinate
	private String tileType = "";
	private Image image = null;
	Position gamePosition = null;
	public static Sprite[] sprites = null;
	
	/**this is the constructor for this class
	 * Which create a single sprite
	 * @param image_Src: the name of image in the res folder
	 * @param gameX: the game coordinate which read from csv file
	 * @param gameY: the game coordinate which read from csv file
	 */
	public Sprite(String image_src, int csvX, int csvY){
		try {
			image = new Image(App.IMAGE_FOLDER + image_src + App.IMAGE_SUFFIX);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		tileType = image_src;
		gamePosition = new Position(csvX, csvY);
	}
	
	/**
	 * This method used to read the user input
	 * And update the new sprite coordinate
	 * @param input the user's action on the keyboard
	 */
	public void update(int input) {
		if(input == Input.KEY_UP) {
			int moveY = this.gamePosition.gameY - 1;
			int moveX = this.gamePosition.gameX;
			gamePosition = new Position(moveX, moveY);
		}
		
		if(input == Input.KEY_DOWN) {
			int moveY = this.gamePosition.gameY + 1;
			int moveX = this.gamePosition.gameX;
			gamePosition = new Position(moveX, moveY);
		}
		
		if(input == Input.KEY_LEFT) {
			int moveX = this.gamePosition.gameX - 1;
			int moveY = this.gamePosition.gameY;
			gamePosition = new Position(moveX, moveY);
		}
			
		if(input == Input.KEY_RIGHT) {
			int moveX = this.gamePosition.gameX;
			int moveY = this.gamePosition.gameY;
			gamePosition = new Position(moveX, moveY);
		}
	}
	
	/**
	 * TODO Adding a converting function
	 * This the render function, which we only convert the coordinate system
	 * From game coordinate to screen coordinate at render step.
	 * Which is the final step
	 * @param g
	 */
	public void render(Graphics g, int gameWidth, int gameHeight) {
		float scrX = (App.SCREEN_WIDTH - gameWidth * App.TILE_SIZE) / 2 
				+ gamePosition.gameX * App.TILE_SIZE
				+ App.TILE_SIZE /2;
		float scrY = (App.SCREEN_HEIGHT - gameHeight * App.TILE_SIZE) / 2
				+ gamePosition.gameY * App.TILE_SIZE
				+ App.TILE_SIZE / 2;
		image.drawCentered(scrX, scrY);
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
			Position spriteGamePosition = sprites[i].gamePosition;
			float BlockedX = spriteGamePosition.gameX;
			float BlockedY = spriteGamePosition.gameY;
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
	
	public Position getPosition() {
		return gamePosition;
	}
	
	public void setPosition(Position position) {
		this.gamePosition = position;
	}
	public void printInfo(){
		System.out.println(tileType + " " + Float.toString(gamePosition.gameX) +" " + Float.toString(gamePosition.gameY));
	}
}
