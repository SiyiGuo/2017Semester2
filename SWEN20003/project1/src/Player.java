import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Player extends Sprite{
	private String tileType = "";
	private Image tile = null;
	private float x;
	private float y;
	
	public Player(String image_src, float x, float y) {
		super(image_src, x, y);
	}

	
}
