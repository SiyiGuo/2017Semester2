
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Sprite {
	/** TILE **/
	public static final String FLOOR = "floor";
	public static final String WALL = "wall";
    public static final String TARGET = "target";
    public static final String CRACKED = "cracked";
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
    
    /*TODO destroy this variable*/
    public static Sprite[] sprites = null; //this variable shoudl be destroyed once everything finished////
    
	//inside Sprite Class we deals with screen coordinate
	protected String tileType = "";
	protected Image image = null;
	private Position gamePosition = null;
	
	
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
	public void update(Input input) {
	}
	
	/**
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
	 * Collision function
	 * 
	 */
	public boolean collisionEffect(Position fromPosition) {
		return false;
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
	
	public void setImage(Image newImage) {
		image = newImage;
	}
	
	public void setPosition(Position position) {
		this.gamePosition = position;
	}
	public void printInfo(){
		System.out.println(tileType + " " + Float.toString(gamePosition.gameX) +" " + Float.toString(gamePosition.gameY));
	}
}
