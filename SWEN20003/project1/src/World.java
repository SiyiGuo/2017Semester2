import java.util.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class World {
	/*Time constqant*/
	public static final int ONESECOND = 100; //as min time update interval is 0.1 seconds, 10 updates means 1 second
	public static final int ZEROPOINT4Seconds = 40;
	public static final int ZEROPOINT25Seconds = 25;
	
	/*Sprite Structure on a single place*/
	public static final int UNDEFINED = -1;
	public static final int MAX_SPRITE_NUM = 3;
	public static final int TOP_SPRITE = 2;
	public static final int MID_SPRITE = 1;
	public static final int BOT_SPRITE = 0;
	public static final String TOP = "top";
	public static final String MIDDLE = "middle";
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
    private static int[] levels = {LEVEL0, LEVEL1, LEVEL2, LEVEL3, LEVEL4, LEVEL5};
    
    /*instance variable relate to specific world*/
    private static int level = LEVEL0;
	
	/*Game's width and height*/
	private static int gameWidth = UNDEFINED;
	private static int gameHeight = UNDEFINED;
	
	/*set up for counting the gaming environment */
	private static int timeCount = 0;
	private static int moveCount = 0;
	private static ArrayList<SingleHistory[]> historyMove = new ArrayList<SingleHistory[]>();
	private static ArrayList<SingleHistory> thisUpdateChange = new ArrayList<SingleHistory>(); 
	
	/*Sprites on the level*/
	private static Sprite[][][] sprites = null; 
	private static Player player = null;
	private static Skeleton skeleton = null;
	private static Rogue rogue = null;
	private static Mage mage = null;
	private static Switch sswitch = null;
	private static Tile[] targets = null;
	
	
	
	/*Constructor for the world*/
	public World() {
		initializeLevel(level);
	}
		
	
	public void update(Input input, int delta) {
		
		if (input.isKeyPressed(Input.KEY_R)) {
			restartLevel();
		} else if (input.isKeyPressed(Input.KEY_Z)){
			undo();
		} else {
			// record that time has passed by 0.1 seconds
			timeCount++;
			
			//For this update, create a new list to record change in this time
			thisUpdateChange = new ArrayList<SingleHistory>();
			
			//Skeleton update First
			if (skeleton != null) {
				//Save the Original Position of Skeleton
				skeleton.update(timeCount);
			}
						
			//updating the Player
			player.update(input);
			
			//In the end, record what has changed
			if (thisUpdateChange.size() != 0) {
				historyMove.add(thisUpdateChange.toArray(new SingleHistory[thisUpdateChange.size()]));
			}
			
			//Check whether we have finish this level
			if(levelCompleted()) {
				toNextLevel(level += 1);
			}
		}
	}
	
	
	public void render(Graphics g) {
		g.drawString("Moves: " + Integer.toString(moveCount), 0,0);
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
		
		/*Loading the Player*/
		if (player != null) {
			player.render(g, gameWidth, gameHeight);
		}
	}
	
	
	/** functions have effect on the whole world**/
	public static void initializeLevel(int level) {
		//Loading Sprites object array
		//Sprites is a three dimension variable with [x][y][top/bottom]
		String mapFile = MAPROOT + Integer.toString(levels[level]) + MAPSUFFIX;
		sprites = Loader.loadSprites(mapFile);
		
		gameWidth = sprites.length;
		//Just take the first array's size as height as they all got uniform size
		//at the 2nd dimension
		gameHeight = sprites[0].length;
		
		
		//Reset everything
		//reset time count to 0 as level has restart
		timeCount = 0;
		//When level restart, move count is also seet to zero
		//as move count decrease when we undo a action
		moveCount = 0;
		
		//reset all the characters to null
		player = null;
		skeleton = null;
		rogue = null;
		mage = null;
		
		//reset targets to null
		targets = null;
		
		//Empty the History
		historyMove = new ArrayList<SingleHistory[]>();
		//Find the reference to all the Npc and Player Sprite
		findPlayerNpcTargetsSwitch();
	}
	
	public static void restartLevel(){
		initializeLevel(level);
	}
	
	/*function called when Initialize the level*/
	public static void findPlayerNpcTargetsSwitch() {
		ArrayList<Tile> targetList = new ArrayList<Tile>();
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
						
						if (tileType.equals(Sprite.MAGE)) {
							mage = (Mage)sprite;
						}
						
						if (tileType.equals(Sprite.SWITCH)) {
							sswitch = (Switch)sprite;
						}
						
						if (tileType.equals(Sprite.TARGET)) {
							targetList.add((Tile) sprite);
						}
					}
				}
			}
		}
		
		//convert all the target back to the array;
		targets = targetList.toArray(new Tile[targetList.size()]);
	}
	
	/**
	 * This function check whether current level has complete
	 * @return true if yes, false if not
	 */
	public static boolean levelCompleted() {
		for(Tile target: targets) {
			Position targetLoc = target.getPosition();
			if (!(sprites[targetLoc.gameX][targetLoc.gameY][TOP_SPRITE] instanceof Stone || 
				  sprites[targetLoc.gameX][targetLoc.gameY][TOP_SPRITE] instanceof Ice)){
				return false;
			}
		}
		return true;
	}
	
	public void undo() {
		if (moveCount > 0) {
			moveCount--;
			SingleHistory[] lastUpdateChanges = historyMove.get(historyMove.size() - 1);
			historyMove.remove(historyMove.size() - 1);			
			for (int i = lastUpdateChanges.length - 1; i >=0; i--) {
				SingleHistory singleSpChange = lastUpdateChanges[i];
				
				if (singleSpChange == null){
					continue;
				}
				
				Position nowPos = singleSpChange.newPos;
				Position beforePos = singleSpChange.oldPos;
				
				if (! Position.equal(nowPos, beforePos)) {
					sprites[beforePos.gameX][beforePos.gameY][TOP_SPRITE] = singleSpChange.nowSprite;
					sprites[nowPos.gameX][nowPos.gameY][TOP_SPRITE] = singleSpChange.beforeSprite;
					
					if (singleSpChange.nowSprite != null ) {
						singleSpChange.nowSprite.setPosition(beforePos);
					}
					
					if (singleSpChange.beforeSprite != null) {
						singleSpChange.beforeSprite.setPosition(nowPos);
					}
				}
			}
		}
	}
	
	public void toNextLevel(int nxtLevel) {
		if (nxtLevel <= LEVEL5) {
			level = nxtLevel;
			initializeLevel(nxtLevel);
		}
		
	}
	
	/**functions have effect on history move the sprites**/
	
	/*function called in the update method*/
	public static void changeSpriteLoc(Position newPos, Position oldPos) {
		//get Original Sprite as Cargo
		Sprite sprite = sprites[oldPos.gameX][oldPos.gameY][TOP_SPRITE];
		
		//destroy original sprite
		sprites[oldPos.gameX][oldPos.gameY][TOP_SPRITE] = null;
		
		//Move the  sprite to new Position
		sprites[newPos.gameX][newPos.gameY][TOP_SPRITE] = sprite;	
		
		if (! (sprite instanceof Skeleton)) {
			thisUpdateChange.add(new SingleHistory(newPos, oldPos, sprite, null));
		}
	}
	
	public static boolean isBlocked(Position target) {
		//if switch exist in this level
		if (sswitch != null) {
			Sprite tempSprite= sprites[target.gameX][target.gameY][MID_SPRITE];
			if (tempSprite != null) {
				if (tempSprite.getTileType().equals(Sprite.DOOR)){
					System.out.println("Checking door");
					Door door = (Door)sprites[target.gameX][target.gameY][MID_SPRITE];
					if(door.isOpen()) {
						return false;
					} else {
						return true;
					}			
				}
			}
		}
		
		//If switch does not exist, we just check top sprite
		if (sprites[target.gameX][target.gameY][TOP_SPRITE] != null) {
			String spriteType = sprites[target.gameX][target.gameY][TOP_SPRITE].getTileType();
			switch (spriteType) {
				case Sprite.WALL:
					return true;
				case Sprite.CRACKED_WALL:
					return true;
				case Sprite.TNT:
					Tnt tnt = (Tnt)sprites[target.gameX][target.gameY][TOP_SPRITE];
					if (tnt.isExploding()) {
						return true;
					} else {
						return false;
					}
				default:
					return false;
			}
		}
		return false;
	}
	
	
	/*Setter and Getter*/
	public static void setMoveCount() {
		moveCount++;
	}
	
	public static Switch getSwitch() {
		return sswitch;
	}
	
	public static Player getPlayer() {
		return player;
	}
	
	public static Rogue getRogue() {
		return rogue;
	}
	
	public static Mage getMage() {
		return mage;
	}
	
	public static int getTimeCount() {
		return timeCount;
	}
	/**
	 * This function take a Sprite's original Position and Move it to New Position
	 * @param sprite teh Sprite that need to be changed
	 * @param OriPos The original Sprite
	 */
	public static Sprite getSprite(Position index) {
		if (sprites[index.gameX][index.gameY][TOP_SPRITE] != null) {
			return sprites[index.gameX][index.gameY][TOP_SPRITE];
		} else if (sprites[index.gameX][index.gameY][MID_SPRITE] != null) {
			return sprites[index.gameX][index.gameY][MID_SPRITE];
		} else {
			return sprites[index.gameX][index.gameY][BOT_SPRITE];
		}
	}
	
	public static void setTopSprite(Position newPos, Position oldPos) {
		//get Original Sprite as Cargo
		Sprite sprite = sprites[oldPos.gameX][oldPos.gameY][TOP_SPRITE];
		
		//Move the  sprite to new Position
		sprites[newPos.gameX][newPos.gameY][TOP_SPRITE] = sprite;
				
		//destroy original sprite
		sprites[oldPos.gameX][oldPos.gameY][TOP_SPRITE] = null;
		
		
	}
	
	public static void destroySprite(Position index) {
		sprites[index.gameX][index.gameY][TOP_SPRITE] = null;
	}
	
	public static void destroyTntHistory() {
		for (SingleHistory[] singleHistoryMove: historyMove) {
			int index = 0;
			for (SingleHistory eachChange: singleHistoryMove) {
				if (eachChange != null) {
					if (eachChange.nowSprite != null) {
						if (eachChange.nowSprite.getTileType().equals(Sprite.TNT)){
							singleHistoryMove[index]= null;
						}
					}
				}
				index++;
			}
		}
	}
	
	public static Sprite getTopSprite(Position index) {
		return sprites[index.gameX][index.gameY][TOP_SPRITE];
	}
	
}
