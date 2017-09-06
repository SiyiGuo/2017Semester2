

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Sprite {	
	
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
	}
	
	public void render(Graphics g) {
		tile.drawCentered(x, y);
	}
	
	public String getTileType() {
		return tileType;
	}
	
	public float[] getXY() {
		float[] XY = {x,y};
		return XY;
	}
	
	public void printInfo(){
		System.out.println(tileType + " " + Float.toString(x) +" " + Float.toString(y));
	}
}
