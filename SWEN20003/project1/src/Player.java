import org.newdawn.slick.Input;

public class Player extends Sprite{
	//inside Sprite Class we deals with screen coordinate
	public Player(String image_src, float x, float y) {
		super(image_src, x, y);
	}

	public void update(Input input, int delta) {
		if(input.isKeyPressed(Input.KEY_UP)) {
			float move = this.getY() - TILE_SIZE;
			this.setY(move);
		}
		
		if(input.isKeyPressed(Input.KEY_DOWN)) {
			float move = this.getY() + TILE_SIZE;
			this.setY(move);
		}
		
		if(input.isKeyPressed(Input.KEY_LEFT)) {
			float move = this.getX() - TILE_SIZE;
			this.setX(move);
		}
			
		if(input.isKeyPressed(Input.KEY_RIGHT)) {
			float move = this.getX() + TILE_SIZE;
			this.setX(move);
		}
		printInfo();
	}
}
