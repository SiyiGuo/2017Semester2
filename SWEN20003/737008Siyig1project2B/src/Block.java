public class Block extends Sprite{
	/**
	 * Block logic class which as parent class for 
	 * stone, ice, tnt
	 */
	
	/**
	 * Constructor for the Block
	 * @param image_src the image file
	 * @param csvX the coordinate in csv file
	 * @param csvY the coordinate in csv file
	 */
	public Block(String image_src, int csvX, int csvY) {
		super(image_src, csvX, csvY);
	}
	
	/*This class is created for future possible extension*/
	/*As well as help logic thinking*/
}
