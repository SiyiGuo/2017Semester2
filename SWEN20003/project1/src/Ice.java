
public class Ice extends Block{
	
	private boolean isMoving;
	private int movingDirection;
	
	public Ice(String image_src, int csvX, int csvY) {
		super(image_src, csvX, csvY);
	}
	
	public void update(Position newPosition, int timeCount) {
		
	}
	
	public int findSlideDirection(Position nxtPosition) {
		return -1;
	}

}
