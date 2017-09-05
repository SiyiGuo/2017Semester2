

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Sprite {	
	
	private String tileType = "";
	private Image tile = null;
	private float tileX;
	private float tileY;
	
	public Sprite(String image_src, float x, float y){
		try {
			tile = new Image("res/" + image_src + ".png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		tileType = image_src;
		tileX = x;
		tileY = y;
	}
	
	public void update(Input input, int delta) {
	}
	
	public void render(Graphics g) {
		tile.drawCentered(tileX, tileY);
	}
	
	public void printInfo(){
		System.out.println(tileType + " " + Float.toString(tileX) +" " + Float.toString(tileY));
	}
}
