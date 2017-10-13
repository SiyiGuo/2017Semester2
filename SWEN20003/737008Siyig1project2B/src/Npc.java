
public class Npc extends Sprite{
	/**
	 * Npc class which help logic thinkg
	 * This class is servers as parent class for Npc
	 * Mage, Skeleton, Rogue
	 */

	/**
	 * Constructor for all rogue, mage, skeleton
	 * @param image_src the iamge file for them
	 * @param csvX the coordinate read from mapfile
	 * @param csvY the coordiante read from mapfile
	 */
	public Npc(String image_src, int csvX, int csvY) {
		super(image_src, csvX, csvY);
	}
	
	/*This class is created for future extension, as well as help logically thinking*/
}
