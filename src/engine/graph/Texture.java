package engine.graph;

import java.nio.ByteBuffer;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;

public class Texture {

	private static int loadTexture(String fileName) throws Exception {
		//load texture file
		PNGDecoder decoder = new PNGDecoder(Texture.class.getResourceAsStream(fileName));
		//make byte buffer 4 times as big as image size to hold RGBA values for each pixel
		ByteBuffer buf = ByteBuffer.allocateDirect(4 * decoder.getWidth() * decoder.getHeight());
		decoder.decode(buf, decoder.getWidth()*4, Format.RGBA);
		buf.flip();
		
		// Create a new OpenGL texture 
		int textureId = glGenTextures();
		// Bind the texture
		glBindTexture(GL_TEXTURE_2D, textureId);
		glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buf);
		glGenerateMipmap(GL_TEXTURE_2D);
		return 0;
	}
	
	
	
}
