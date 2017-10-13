
public class Switch extends Tile{
	
	private Door door;
	
	public Switch(String image_src, int csvX, int csvY) {
		super(image_src, csvX, csvY);
	}
	
	public void update() {
		if (blockOnIt()) {
			door.openDoor();
		} else {
			door.closeDoor();
		}
	}
	
	public void addDoor(Door ddoor) {
		door = ddoor;
	}
	
	public boolean blockOnIt() {
		if (World.getTopSprite(super.getPosition()) != null) {
			return true;
		} else {
			return false;
		}
	}
}
