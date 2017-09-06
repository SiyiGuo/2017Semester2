

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Sprite {
	/** screen width, in pixels */
    public static final float SCREEN_WIDTH = 800;
    /** screen height, in pixels */
    public static final float SCREEN_HEIGHT = 600;
    /** size of the tiles, in pixels */
    public static final float TILE_SIZE = 32;
	
	//inside Sprite Class we deals with screen coordinate
	private String tileType = "";
	private Image tile = null;
	private float x;
	private float y;
	
	public Sprite(String image_src, float tileX, float tileY){
		try {
			tile = new Image("res/" + image_src + ".png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		tileType = image_src;
		x = tileX;
		y = tileY;
	}
	
	public void update(Input input, int delta) {
		if(input.isKeyPressed(Input.KEY_UP)) {
			y -= TILE_SIZE;
		}
		
		if(input.isKeyPressed(Input.KEY_DOWN)) {
			printInfo();
			y += TILE_SIZE;
			System.out.println("Calling update");
			printInfo();
		}
		
		if(input.isKeyPressed(Input.KEY_LEFT)) {
			x -= TILE_SIZE;
			System.out.println("Calling update");
		}
			
		if(input.isKeyPressed(Input.KEY_RIGHT)) {
			x += TILE_SIZE;
			System.out.println("Calling update");
		}
	}
	
	public void render(Graphics g) {
		tile.drawCentered(x, y);
	}
	
	
	
	public String getTileType() {
		return tileType;
	}
	
	
	public void printInfo(){
		System.out.println(tileType + " " + Float.toString(x) +" " + Float.toString(y));
	}
	
	public float getX() {
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
}
