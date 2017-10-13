
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
		Sprite topSprite = World.getTopSprite(super.getPosition());
		if (topSprite != null) {
			if (topSprite instanceof Block) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}
}
