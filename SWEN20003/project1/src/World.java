

import java.util.*;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class World {
	public static final int UNDEFINED = -1;
	
	/**Game's width and height**/
	public int gameWidth = UNDEFINED;
	public int gameHeight = UNDEFINED;
	
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
    
    private int level = LEVEL0;
	private int[] levels = {LEVEL0, LEVEL1, LEVEL2, LEVEL3, LEVEL4, LEVEL5};
    
    public static final String MAPROOT = "res/levels/";
    public static final String MAPSUFFIX = ".lvl";
	private String mapFile = MAPROOT + Integer.toString(levels[level]) + MAPSUFFIX;
	
	
	/**set up for counting the gaming environment **/
	private int updateCount = 0;
	private int moveCount = 0;
	private ArrayList<Sprite[]> historyMove = new ArrayList<Sprite[]>();
	
	/**Sprites on the level**/
	private ArrayList<Sprite>[][] sprites = null; 
	private Player player = null;
	
	public World() {
		initializeLevel(mapFile);
	}
		
	
	public void update(Input input, int delta) {
		player.update(input, delta);
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < Sprite.sprites.length; i++) {
			Sprite.sprites[i].render(g);
		}
		player.render(g);
	}
	
	
	/** functions have effect on the whole world**/
	public void initializeLevel(String mapFile) {
		//Loading Sprites object array
		sprites = Loader.loadSprites(mapFile);
		
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
	
	/**small helper functions in world**/
	public Sprite getNxtSprite(Position position) {
		return sprites[position.X][position.Y].get(0);
	}
	
	public float[] posToScreen(Position position) {
		float x = (App.SCREEN_WIDTH - gameWidth * App.TILE_SIZE) / 2 
					+ position.X * App.TILE_SIZE
					+ App.TILE_SIZE /2;
		float y = (App.SCREEN_HEIGHT - gameHeight * App.TILE_SIZE) / 2
					+ position.Y * App.TILE_SIZE
					+ App.TILE_SIZE / 2;
		
		float result[] = {x,y};
		return result;
	}
}
