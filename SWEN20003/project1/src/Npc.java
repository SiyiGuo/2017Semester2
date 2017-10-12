
public class Npc extends Sprite{

	public Npc(String image_src, int csvX, int csvY) {
		super(image_src, csvX, csvY);
	}
	
	public void update() {
		
	}
	
	public Position nxtPosition() {
		return new Position(-1,-1);
	}
}
