import org.newdawn.slick.Input;

public class Player extends Sprite{
	//inside Sprite Class we deals with screen coordinate
	public Player(String image_src, float x, float y) {
		super(image_src, x, y);
	}

	public void update(Input input, int delta) {
		if(input.isKeyPressed(Input.KEY_UP)) {
			this.setY(this.getY() - TILE_SIZE);
		}
		
		if(input.isKeyPressed(Input.KEY_DOWN)) {
			this.setY(this.getY() + TILE_SIZE);
		}
		
		if(input.isKeyPressed(Input.KEY_LEFT)) {
			this.setX(this.getX() - TILE_SIZE);
		}
			
		if(input.isKeyPressed(Input.KEY_RIGHT)) {
			this.setX(this.getX() + TILE_SIZE);
		}
	}
}
