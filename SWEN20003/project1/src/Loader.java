import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Loader {	
	
    
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
    
		
	/**
	 * Loads the sprites from a given file.
	 */
	public static Object[][] loadSprites(String filename) {
		/**level[0] basic data about width, height, size
		 *level[1,2,3] = [tile, tileX, tileY]
		 */
		String[][] level = readCSV(filename);
		int size = Integer.parseInt(level[0][2]);
		int width = Integer.parseInt(level[0][0]);
		int height = Integer.parseInt(level[0][1]);
		
		
		Object[][] sprites = new Object[width][height]; 
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				sprites[i][j] = new ArrayList<Sprite>();
			}
		}
		
		/**
		 * Inside for loop , read all tile data
		 * and create the object
		 */
		for (int i = 0; i < size - 1; i++) {
			//read tile data
			int x = Integer.parseInt(level[2][i]);
			int y = Integer.parseInt(level[3][i]);
			
			//convert tile coordinate into the screen coordinate
			float ScrCoor[] = tileToScreen(tileX, tileY, width, height);
			String tile = level[1][i];
			
			//create sprite object and add
			sprites[i] = new Sprite(tile,ScrCoor[0],ScrCoor[1]);
		}
		
		//create object for player model
		return sprites;
	}
	
	private static String[][] readCSV(String filename){ 
		/*in reading CSV, we do the sorting at the same data
		 * By reading "FLOOR, TARGET" to the front
		 * And "STONE, WALL, Player" at the end
		 */
		ArrayList<String> spType = new ArrayList<String>();
		ArrayList<String> spX = new ArrayList<String>();
		ArrayList<String> spY = new ArrayList<String>();
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
			width = line.split(CSVSplit)[0];
			height = line.split(CSVSplit)[1];
			//read rest element into 3 array
			while ((line = level.readLine()) != null) {
				//the first line is tile, the rest two are coordinate
				String[] linedata = line.split(CSVSplit);
				if (linedata[0].equals(FLOOR) | linedata[0].equals(TARGET) | linedata[0].equals(SWITCH)) {
					/*if it is tile, we load it first
					 **/
					spType.add(0,linedata[0]);
					spX.add(0,linedata[1]);
					spY.add(0,linedata[2]);
				}else {
					//for the rest element, we add as normal
					spType.add(linedata[0]);
					spX.add(linedata[1]);
					spY.add(linedata[2]);
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
			spY.toArray(new String[spY.size()])
		};
		return result;
	}
}
