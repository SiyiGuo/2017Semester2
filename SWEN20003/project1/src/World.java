

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class World {
	
	private Sprite[] sprites = null;
	private Player player = null;
	public World() {
		String filename = "res/levels/0.lvl";
		sprites = Loader.loadSprites(filename);
		
		//create player object
		float[] playerXY = sprites[sprites.length - 1].getXY();
		String playerTileType = sprites[sprites.length-1].getTileType();
		player = new Player(playerTileType, playerXY[0], playerXY[1]);
	}
	
	
	public void update(Input input, int delta) {
		
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < sprites.length - 1; i++) {
			sprites[i].render(g);
		}
		player.render(g);
	}
}
