

import java.util.*;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class World {
	/*Time constqant*/
	public static final int ONESECOND = 10; //as min time update interval is 0.1 seconds, 10 updates means 1 second
	
	/*Sprite Structure on a single place*/
	public static final int UNDEFINED = -1;
	public static final int MAX_SPRITE_NUM = 2;
	public static final int TOP_SPRITE = 1;
	public static final int BOT_SPRITE = 0;
	public static final String TOP = "top";
	public static final String BOTTOM = "bottom";
	
    /** constant set up for mapfile**/
    public static final int LEVEL0 = 0;
    public static final int LEVEL1 = 1;
    public static final int LEVEL2 = 2;
    public static final int LEVEL3 = 3;
    public static final int LEVEL4 = 4;
    public static final int LEVEL5 = 5;
    public static final String MAPROOT = "res/levels/";
    public static final String MAPSUFFIX = ".lvl";
    public static int[] levels = {LEVEL0, LEVEL1, LEVEL2, LEVEL3, LEVEL4, LEVEL5};
    
    /*instance variable relate to specific world*/
    private int level = LEVEL3;
	
	/*Game's width and height*/
	private int gameWidth = UNDEFINED;
	private int gameHeight = UNDEFINED;
	
	/*set up for counting the gaming environment */
	private int timeCount = 0;
	private int moveCount = 0;
	private ArrayList<Sprite[]> historyMove = new ArrayList<Sprite[]>();
	
	/*Sprites on the level*/
	private static Sprite[][][] sprites = null; 
	private Player player = null;
	private Skeleton skeleton = null;
	private Rogue rogue = null;
	
	
	/*Constructor for the world*/
	public World() {
		initializeLevel(level);
	}
		
	
	public void update(Input input, int delta) {
		// record that time has passed by 0.1 seconds
		timeCount++;
		if (skeleton != null) {
			//Save the Original Position of Skeleton
			Position skeOri = skeleton.getPosition();
			//Update and find new Position
			Position newPos = skeleton.update(timeCount);
			
			//destroy original skeleton
			sprites[skeOri.gameX][skeOri.gameY][TOP_SPRITE] = null;
			
			//Update the new Skeleton Position
			sprites[newPos.gameX][newPos.gameY][TOP_SPRITE] = skeleton;
		}
	}
	
	
	public void render(Graphics g) {
		/*Loading the wall and sprite at bottom*/
		for(int i = 0; i < gameWidth; i++) {
			for(int j = 0; j < gameHeight; j++) {
				for (int k = 0; k < MAX_SPRITE_NUM; k++) {
					Sprite sprite = sprites[i][j][k];
					if (sprite != null) {
						sprite.render(g,  gameWidth, gameHeight);
					}
				}
			}
		}
		
		/*Loading the NPC*/
		player.render(g, gameWidth, gameHeight);
	}
	
	
	/** functions have effect on the whole world**/
	public void initializeLevel(int level) {
		//Loading Sprites object array
		//Sprites is a three dimension variable with [x][y][top/bottom]
		String mapFile = MAPROOT + Integer.toString(levels[level]) + MAPSUFFIX;
		sprites = Loader.loadSprites(mapFile);
		gameWidth = sprites.length;
		//Just take the first array's size as height as they all got uniform size
		//at the 2nd dimension
		gameHeight = sprites[0].length;
		//reset time count to 0 as level has restart
		timeCount = 0;
		
		
		findPlayerRef();
	}
	
	public void checkComplete() {
		
	}
	
	public void undo() {
		
	}
	
	public void restartLevel(String mapFile){
		
	}
	
	public void toNextLevel(int nxtLevel) {
		
	}
	
	/**functions have effect on history move the sprites**/
	public void recordChangeSpreites(ArrayList<Sprite>[][] tempSprites) {
		
	}
	
	public void updateSprites(ArrayList<Sprite>[][] tempSprites) {
		
	}
	
	
	/* verty small function*/
	public static boolean isBlocked(Position target) {
		if (sprites[target.gameX][target.gameY][TOP_SPRITE] != null) {
			return true;
		}
		return false;
	}
	
	public void findPlayerRef() {
		for(int i = 0; i < gameWidth; i++) {
			for(int j = 0; j < gameHeight; j++) {
				for (int k = 0; k < MAX_SPRITE_NUM; k++) {
					Sprite sprite = sprites[i][j][k];
					if (sprite != null ) {
						String tileType = sprite.getTileType();
						
						if (tileType.equals(Sprite.PLAYERFILE)) {
							player = (Player)sprite;
						}
						
						if (tileType.equals(Sprite.SKULL)) {
							skeleton = (Skeleton)sprite;
						}
						
						if (tileType.equals(Sprite.ROGUE)) {
							rogue = (Rogue)sprite;
						}
					}
				}
			}
		}
	}
	
	
	/*Setter and Getter*/
}
