import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Loader {	    
		
    /** Loading the Sprites Given a map's file
     * And return a 3-dimension array of Sprite
     * with [x][y][top_bottom] for index
     * @param filename, a .lvl file in csv format
     */
	public static Sprite[][][] loadSprites(String filename) {
		/*level[0] basic data about width, height, size
		 * 
		 *level[1,2,3,4] = [tile, tileX, tileY, top or bottom]
		 */
		String[][] level = readCSV(filename);
		int size = Integer.parseInt(level[0][2]);
		int width = Integer.parseInt(level[0][0]);
		int height = Integer.parseInt(level[0][1]);
		
		
		/*initialise the 3dimension array*/
		Sprite[][][] sprites = new Sprite[width][height][World.MAX_SPRITE_NUM]; 
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				for (int k = 0 ; k < World.MAX_SPRITE_NUM; k++) {
					sprites[i][j][k] = null;
				}
			}
		}
		
		/* Inside for loop , read all data from csv
		 * and create the object
		 */
		for (int i = 0; i < size; i++) {
			//read tile data
			int x = Integer.parseInt(level[2][i]);
			int y = Integer.parseInt(level[3][i]);
			String spToporBottom= level[4][i];
			
			int gameCoor[] = {x,y};
			String spType = level[1][i];
			
			
			//create sprite object and add to the arrayList
			Sprite sprite = createSprite(spType,gameCoor[0],gameCoor[1]);
			if (spToporBottom.equals(World.TOP)){
				sprites[x][y][World.TOP_SPRITE] = sprite;
			} else if (spToporBottom.equals(World.MIDDLE)){
				sprites[x][y][World.MID_SPRITE] = sprite;
			}else {
				sprites[x][y][World.BOT_SPRITE] = sprite;
			}
		}
		//create object for player model
		return sprites;
	}
	
	/**
	 * Create a sprite 
	 * @param spType: A string specific what type is it
	 * @param gameX: the x coordinate
	 * @param gameY: the Y coordinate
	 */
	private static Sprite createSprite(String spType, int gameX, int gameY) {
		Sprite sprite = null;
		switch (spType) {
			//Player
			case Sprite.PLAYER:
				sprite = new Player(spType, gameX, gameY);
				break;
			
			//NPC
			case Sprite.SKELETON:
				sprite = new Skeleton(Sprite.SKULL, gameX, gameY);
				break;
			case Sprite.ROGUE:
				sprite = new Rogue(Sprite.ROGUE, gameX, gameY);
				break;
			
			
			//Lower Tiles
			case Sprite.TARGET:
				sprite = new Tile(Sprite.TARGET, gameX, gameY);
				break;
			
				//Upper Tiles
			case Sprite.CRACKED:
				sprite = new CrackedWall(Sprite.CRACKED_WALL, gameX, gameY);
				break;
			case Sprite.WALL:
				sprite = new Tile(Sprite.WALL, gameX, gameY);
				break;
			
			//all blocks
			case Sprite.STONE:
				sprite = new Stone(Sprite.STONE , gameX, gameY);
				break;
			case Sprite.ICE:
				sprite = new Ice(Sprite.ICE, gameX, gameY);
				break;
			case Sprite.TNT:
				sprite = new Tnt(Sprite.TNT, gameX, gameY);
				break;
			
			//The rest Default Stuff
			default:
				sprite = new Sprite(spType, gameX, gameY);
				break;
		}
		return sprite;
	}
	
	/** read the csv map and return an array of array
	 * {{width, height}, typeArray, XArray, YArray, TOP_BOTTOM Array}
     * @param filename
     */
	private static String[][] readCSV(String filename){ 
		/*in reading CSV, we do the sorting at the same data
		 * By reading "FLOOR, TARGET" to the front
		 * And "STONE, WALL, Player" at the end
		 */
		ArrayList<String> spType = new ArrayList<String>();
		ArrayList<String> spX = new ArrayList<String>();
		ArrayList<String> spY = new ArrayList<String>();
		ArrayList<String> spTB = new ArrayList<String>(); //define sprite is at top or bottom
		String width = "";
		String height = "";
		
		//Read CSV
		String line = "";
		String CSVSplit = ",";
		BufferedReader level = null;
		
		//read world size
		try {
			level = new BufferedReader(new FileReader(filename));
			line = level.readLine();
			//since the first line is dimension of map
			width = line.split(CSVSplit)[0];
			height = line.split(CSVSplit)[1];
			
			//read rest element into 3 array
			while ((line = level.readLine()) != null) {
				//the first line is tile, the rest two are coordinate
				//lindata = [Type, x, y];
				String[] linedata = line.split(CSVSplit);
				if (linedata[0].equals(Sprite.FLOOR)){
					
					/*if it is tile type , we load it first
					 **/
					spType.add(0,linedata[0]);
					spX.add(0,linedata[1]);
					spY.add(0,linedata[2]);
					spTB.add(0, World.BOTTOM);
				} else if(linedata[0].equals(Sprite.TARGET) || linedata[0].equals(Sprite.SWITCH)) {
					spType.add(0,linedata[0]);
					spX.add(0,linedata[1]);
					spY.add(0,linedata[2]);
					spTB.add(0, World.MIDDLE);
				}else {
					//for the rest element, we add as normal
					spType.add(linedata[0]);
					spX.add(linedata[1]);
					spY.add(linedata[2]);
					spTB.add(World.TOP);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (level != null) {
				try {
					level.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		String[][] result = new String[][] {
			//the first array record width, height, and number of tiles
			//plus a player
			{width, height, new Integer(spType.size()).toString()},
			spType.toArray(new String[spType.size()]),
			spX.toArray(new String[spX.size()]),
			spY.toArray(new String[spY.size()]),
			spTB.toArray(new String[spTB.size()])
		};
		return result;
	}
}
