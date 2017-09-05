

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class World {
	
	private Sprite[] sprites = null;
	
	public World() {
		String filename = "res/levels/0.lvl";
		sprites = Loader.loadSprites(filename);
	}
	
	
	public void update(Input input, int delta) {
		
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < sprites.length; i++) {
			sprites[i].render(g);
		}
	}
}
