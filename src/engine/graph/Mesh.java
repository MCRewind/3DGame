package engine.graph;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Mesh {

	//id used to represent the VAO
    private final int vaoId;

    //id used to represent the position VBO
    private final int posVboId;

    //id used to represent the index VBO
    private final int idxVboId;
    
    //id used to represent the color VBO
    private final int colorVboId;

    //number of vertices in the mesh
    private final int vertexCount;

    //creates the VBOs and the VAO
    public Mesh(float[] positions, float[] colors, int[] indices) {
        vertexCount = indices.length;

        //generates vertex array object names and binds them to an id
        vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);

        //position VBO
        posVboId = glGenBuffers();
        FloatBuffer posBuffer = BufferUtils.createFloatBuffer(positions.length);
        posBuffer.put(positions).flip();
        glBindBuffer(GL_ARRAY_BUFFER, posVboId);
        glBufferData(GL_ARRAY_BUFFER, posBuffer, GL_STATIC_DRAW);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

        //color VBO
        colorVboId = glGenBuffers();
        FloatBuffer colorBuffer = BufferUtils.createFloatBuffer(colors.length);
        colorBuffer.put(colors).flip();
        glBindBuffer(GL_ARRAY_BUFFER, colorVboId);
        glBufferData(GL_ARRAY_BUFFER, colorBuffer, GL_STATIC_DRAW);
        glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0);
        
        //index VBO
        idxVboId = glGenBuffers();
        IntBuffer indicesBuffer = BufferUtils.createIntBuffer(indices.length);
        indicesBuffer.put(indices).flip();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, idxVboId);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    //returns the vao's id
    public int getVaoId() {
        return vaoId;
    }

    //returns number of vertices in the mesh
    public int getVertexCount() {
        return vertexCount;
    }

    //deleted VBOs and VAO
    public void cleanUp() {
        glDisableVertexAttribArray(0);

        // Delete the VBOs
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glDeleteBuffers(posVboId);
        glDeleteBuffers(idxVboId);
        glDeleteBuffers(colorVboId);

        // Delete the VAO
        glBindVertexArray(0);
        glDeleteVertexArrays(vaoId);
    }
}