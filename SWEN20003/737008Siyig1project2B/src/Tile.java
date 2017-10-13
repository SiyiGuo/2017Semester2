
public class Tile extends Sprite{
	/**
	 * This class is served as parent class for cracked_wall, switch, target
	 * Also class for floor, wall
	 */
	
	/**
	 * Constructor for tile class
	 * @param image_src the image file name for the object
	 * @param csvX the X coordinate read from csv File
	 * @param csvY the Y coordinate read from csv file
	 */
	public Tile(String image_src, int csvX, int csvY) {
		super(image_src, csvX, csvY);
	}
	
	/*saved for future extension*/
}
