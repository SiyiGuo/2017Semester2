
public class Skeleton extends Npc{

	public Skeleton(String image_src, int csvX, int csvY) {
		super(image_src, csvX, csvY);
		// TODO Auto-generated constructor stub
	}

	public Position moveUpDown() {
		return new Position(-1,-1);
	}
}
