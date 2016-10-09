package game;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

import org.joml.Matrix4f;

import engine.GameItem;
import engine.Utils;
import engine.Window;
import engine.graph.Mesh;
import engine.graph.ShaderProgram;
import engine.graph.Transformation;

public class Renderer {

    private ShaderProgram shaderProgram;

    /**
     * Fov in Radians
     */
    
    //field of view
    private static final float FOV = (float) Math.toRadians(60.0f);
    
    //render distance min
    private static final float Z_NEAR = 0.01f;
    
    //render distance max
    private static final float Z_FAR = 1000.0f;
    
    private Transformation transformation;
    
    public Renderer() {
    	transformation = new Transformation();
    }

    //initializes shaders and links them to the program
    //adds uniform variables to a lookup hashmap
    public void init(Window window) throws Exception {
        shaderProgram = new ShaderProgram();
        shaderProgram.createVertexShader(Utils.loadResource("/res/vertex.vs"));
        shaderProgram.createFragmentShader(Utils.loadResource("/res/fragment.fs"));
        shaderProgram.link();
        
        //creates a uniform variable for the projection and world matrices
        shaderProgram.createUniform("projectionMatrix");
        shaderProgram.createUniform("worldMatrix");
        
        //set clear color
        window.setClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    }

    //clears the window
    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    //deals with window resizing, binds the shaderProgram, and draws the mesh then unbinds the shaderProgram
    public void render(Window window, GameItem[] gameItems) {
        clear();

        if (window.isResized()) {
            glViewport(0, 0, window.getWidth(), window.getHeight());
            window.setResized(false);
        }

        //binds the shader program
        shaderProgram.bind();
        
        //update projection matrix
        Matrix4f projectionMatrix = transformation.getProjectionMatrix(FOV, window.getWidth(), window.getHeight(), Z_NEAR, Z_FAR);
        //sets the projection matrix uniform variable to the value of the actual projection matrix
        shaderProgram.setUniform("projectionMatrix", projectionMatrix);

        //render each gameItem
        for(GameItem gameItem : gameItems){
        	//set the world matrix for this item
        	Matrix4f worldMatrix = 
        		transformation.getWorldMatrix(
        				gameItem.getPosition(),
        				gameItem.getRotation(), 
        				gameItem.getScale());
        	shaderProgram.setUniform("worldMatrix", worldMatrix);
        	//render the mesh for this gameItem
        	gameItem.getMesh().render();
        }
        
        shaderProgram.unbind();
    }

    //calls the shader's cleanup methods
    public void cleanup() {
        if (shaderProgram != null) {
            shaderProgram.cleanup();
        }
    }
}