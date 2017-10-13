import org.newdawn.slick.Graphics;

public class Door extends Tile{
	/**
	 * Door class which for door object
	 */
	
	//the door is default to be visible
	private boolean visible = true;
	
	/**
	 * Constructor for the door
	 * @param image_src the image file name
	 * @param csvX coordinate read from map file
	 * @param csvY coordinate read from map file
	 */
	public Door(String image_src, int csvX, int csvY) {
		super(image_src, csvX, csvY);
	}
	
	/**
	 * The render function for Door
	 * If door has been open, it will not be render according to specification
	 */
	public void render(Graphics g, int gameWidth, int gameHeight) {
		if (visible) {
			super.render(g, gameWidth, gameHeight);
		}
	}
	
	/**
	 * This is called when switch have blocks on it
	 * Therefore close the Door
	 */
	public void closeDoor() {
		visible = true;
	}
	
	/**
	 * This is called when there is no block on switch
	 * Therefore open the Door
	 */
	public void openDoor() {
		visible = false;
	}
	
	/**
	 * Check whether the door is open
	 * @return true if door has open, false if not
	 */
	public boolean isOpen() {
		if (visible) {
			return false;
		} else {
			return true;
		}
	}
}
