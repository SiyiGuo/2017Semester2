import org.newdawn.slick.Image;

public class Tnt extends Block{
	
	private Image exploEffect;
	private boolean effectOn;
	private boolean visible;
	
	public Tnt(String image_src, int csvX, int csvY) {
		super(image_src, csvX, csvY);
		// TODO Auto-generated constructor stub
	}
	
	public void update() {
		
	}
	
	public void render(int timeCount) {
		
	}
	
	public void explosion() {
		
	}
	
	public boolean canMove(Position nxtPos) {
		return false;
	}

}
