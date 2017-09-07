

import java.util.Arrays;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class World {
	
	public static final String MAPFILE = "res/levels/0.lvl";
	
	//Create player project
	private Player player = null;
	
	public World() {
		//Loading Sprites object array
		//with the last element recorded Player Object Data
		Sprite.sprites = Loader.loadSprites(MAPFILE);
		
		//create player object from the last element of sprites
		float[] playerXY = {Sprite.sprites[Sprite.sprites.length - 1].getX(),
						Sprite.sprites[Sprite.sprites.length - 1].getY()};
		String playerTileType = Sprite.sprites[Sprite.sprites.length-1].getTileType();
		player = new Player(playerTileType, playerXY[0], playerXY[1]);
		
		//delete the last object in array as new Player Object has created
		Sprite.sprites = Arrays.copyOf(Sprite.sprites, Sprite.sprites.length - 1);
	}
	
	
	public void update(Input input, int delta) {
		player.update(input, delta);
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < Sprite.sprites.length; i++) {
			Sprite.sprites[i].render(g);
		}
		player.render(g);
	}
}
