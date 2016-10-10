package engine.graph;

import de.matthiasmann.twl.utils.PNGDecoder;

public class Texture {

	private static int loadTexture(String fileName) throws Exception {
		//load texture file
		PNGDecoder decoder = new PNGDecoder(Texture.class.getResourceAsStream(fileName));
		return 0;
	}
	
}
