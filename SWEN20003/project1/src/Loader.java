import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Loader {	
	
	
 	/** screen width, in pixels */
    public static final int SCREEN_WIDTH = 800;
    /** screen height, in pixels */
    public static final int SCREEN_HEIGHT = 600;
    /** size of the tiles, in pixels */
    public static final int TILE_SIZE = 32;
    
	// Converts a world coordinate to a tile coordinate,
	// and returns if that location is a blocked tile
	public static boolean isBlocked(float x, float y) {
		// Default to blocked
		return true;
	}
		
	/**
	 * Loads the sprites from a given file.
	 * @param filename
	 * @return
	 */
	public static Sprite[] loadSprites(String filename) {
		/**level[0] basic data about width, height, size
		 *level[1,2,3] = [tile, tileX, tileY]
		 */
		String[][] level = readCSV(filename);
		int size = Integer.parseInt(level[0][2]);
		float width = Float.parseFloat(level[0][0]);
		float height = Float.parseFloat(level[0][1]);
		/**
		 * Inside for loop , read all tile data
		 * Then the last data in CSV is player data
		 */
		Sprite[] sprites = new Sprite[size]; //this object will be returned
		for (int i = 0; i < size - 1; i++) {
			//read tile data
			float tileX = Float.parseFloat(level[2][i]);
			float tileY = Float.parseFloat(level[3][i]);
			
			//convert tile coordinate into the screen coordinate
			float ScrCoor[] = tileToScreen(tileX, tileY, width, height);
			String tile = level[1][i];
			
			//create sprite object and add
			sprites[i] = new Sprite(tile,ScrCoor[0],ScrCoor[1]);
		}
		
		//create object for player model
		float ScrCoor[] = tileToScreen(Float.parseFloat(level[2][size-1]), Float.parseFloat(level[3][size-1]), width, height);
		sprites[size - 1] = new Sprite("player_left", 
										ScrCoor[0], 
										ScrCoor[1]);
		return sprites;
	}
	
	private static float[] tileToScreen(float tileX, float tileY, float width, float height) {
		/**ince we use draw centre function, we need to add 16 to the coordinate
		 * As by documentation for draw_Centred
		 * Draw the image based on it's center
		 *Parameters:
		 *x - The x coordinate to place the image's center at
		 *y - The y coordinate to place the image's center at
		 */
	
		float x = (SCREEN_WIDTH - width*TILE_SIZE)/ 2 + tileX*TILE_SIZE+TILE_SIZE / 2;
		float y = (SCREEN_HEIGHT - height*TILE_SIZE)/ 2 + tileY*TILE_SIZE+TILE_SIZE / 2;
		float result[] = {x,y};
		return result;
	}
	
	private static String[][] readCSV(String filename){
		ArrayList<String> tile = new ArrayList<String>();
		ArrayList<String> tileX = new ArrayList<String>();
		ArrayList<String> tileY = new ArrayList<String>();
		String width = "";
		String height = "";
		
		//Read CSV
		String line = "";
		String csvSplit = ",";
		BufferedReader level = null;
		//read world size
		try {
			level = new BufferedReader(new FileReader(filename));
			line = level.readLine();
			width = line.split(csvSplit)[0];
			height = line.split(csvSplit)[1];
			//read rest element into 3 array
			while ((line = level.readLine()) != null) {
				String[] linedata = line.split(csvSplit);
				tile.add(linedata[0]);
				tileX.add(linedata[1]);
				tileY.add(linedata[2]);
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
			{width, height, new Integer(tile.size()).toString()},
			tile.toArray(new String[tile.size()]),
			tileX.toArray(new String[tileX.size()]),
			tileY.toArray(new String[tileY.size()])
		};
		return result;
	}
}
