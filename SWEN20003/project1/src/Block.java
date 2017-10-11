import org.newdawn.slick.Input;

public class Block extends Sprite{

	public Block(String image_src, int csvX, int csvY) {
		super(image_src, csvX, csvY);
		// TODO Auto-generated constructor stub
	}
	
	public Position nxtPost(Input fromDirection) {
		return new Position(-1,-1);
	}
	
	public boolean canMove(Position nxtPosition){
		return false;
	}

}
