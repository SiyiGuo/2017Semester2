import org.newdawn.slick.Graphics;

public class Door extends Tile{
	
	//the door is default to be visible
	private boolean visible = true;
	
	public Door(String image_src, int csvX, int csvY) {
		super(image_src, csvX, csvY);
	}
	
	public void render(Graphics g, int gameWidth, int gameHeight) {
		if (visible) {
			super.render(g, gameWidth, gameHeight);
		}
	}
	
	public void closeDoor() {
		visible = true;
	}
	
	public void openDoor() {
		visible = false;
	}
	
	public boolean isOpen() {
		if (visible) {
			return false;
		} else {
			return true;
		}
	}
}
