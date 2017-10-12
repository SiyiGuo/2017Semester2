import org.newdawn.slick.Input;

public class Player extends Sprite{
	//inside Sprite Class we deals with screen coordinate
	public Player(String image_src, int x, int y) {
		super(Sprite.PLAYERFILE, x, y);
	}
	
	public void update(Input input, int delta) {
	}
	
	public void test_child() {
		System.out.println("Hi thi sis from child class");
	}
}
