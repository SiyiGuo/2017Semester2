
public class Rogue extends Npc{

	public Rogue(String image_src, int csvX, int csvY) {
		super(image_src, csvX, csvY);
	}
	
	public Position moveLeftRight() {
		return new Position(-1,-1);
	}
}
