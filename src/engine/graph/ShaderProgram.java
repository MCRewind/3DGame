package engine.graph;

import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_LINK_STATUS;
import static org.lwjgl.opengl.GL20.GL_VALIDATE_STATUS;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glDeleteProgram;
import static org.lwjgl.opengl.GL20.glDetachShader;
import static org.lwjgl.opengl.GL20.glGetProgramInfoLog;
import static org.lwjgl.opengl.GL20.glGetProgrami;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL20.glGetShaderi;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL20.glValidateProgram;
import static org.lwjgl.opengl.GL20.glUniformMatrix4fv;

import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

public class ShaderProgram {

	//id used to represent a program
    private final int programId;

    //id used to represent a vertex shader
    private int vertexShaderId;

    //id used to represent a fragment shader
    private int fragmentShaderId;

    //lookup table of shader uniform variables
    private final Map<String, Integer> uniforms;
    
    public ShaderProgram() throws Exception {
    	//sets the programs id to a new program object
        programId = glCreateProgram();
        if (programId == 0) {
            throw new Exception("Could not create Shader");
        }
        
        uniforms = new HashMap<>();
    }

    //creates a vertex shader with specified source code
    public void createVertexShader(String shaderCode) throws Exception {
        vertexShaderId = createShader(shaderCode, GL_VERTEX_SHADER);
    }

    //creates a fragment shader with specified source code
    public void createFragmentShader(String shaderCode) throws Exception {
        fragmentShaderId = createShader(shaderCode, GL_FRAGMENT_SHADER);
    }
    
    //queries a uniform names location in memory and attaches it to a key
    public void createUniform(String uniformName) throws Exception {
    	int uniformLocation = glGetUniformLocation(programId, uniformName);
    	if (uniformLocation < 0) {
    		throw new Exception("Could not find uniform: " + uniformName);
    	}
    	uniforms.put(uniformName, uniformLocation);
    }
    
    /*
    *uniform variables are used to efficiently pass data to and from the CPU and GPU
    */
    
    //set a specific uniform value
    public void setUniform(String uniformName, Matrix4f value){
    	//dump the matrix into the float buffer
    	//size is 16 because matrix is 4x4
    	FloatBuffer fb = BufferUtils.createFloatBuffer(16);
    	value.get(fb);
    	glUniformMatrix4fv(uniforms.get(uniformName), false, fb);
    }
    
    //compiles a shader, attaches it to a program, then returns the shaderId
    protected int createShader(String shaderCode, int shaderType) throws Exception {
        int shaderId = glCreateShader(shaderType);
        if (shaderId == 0) {
            throw new Exception("Error creating shader. Code: " + shaderId);
        }

        glShaderSource(shaderId, shaderCode);
        glCompileShader(shaderId);

        if (glGetShaderi(shaderId, GL_COMPILE_STATUS) == 0) {
            throw new Exception("Error compiling Shader code: " + glGetShaderInfoLog(shaderId, 1024));
        }

        glAttachShader(programId, shaderId);

        return shaderId;
    }

    //validates a program and links it to a shader
    public void link() throws Exception {
        glLinkProgram(programId);
        if (glGetProgrami(programId, GL_LINK_STATUS) == 0) {
            throw new Exception("Error linking Shader code: " + glGetProgramInfoLog(programId, 1024));        }

        glValidateProgram(programId);
        if (glGetProgrami(programId, GL_VALIDATE_STATUS) == 0) {
            System.err.println("Warning validating Shader code: " + glGetProgramInfoLog(programId, 1024));
        }

    }

    //installs a program object as part of current rendering state
    public void bind() {
        glUseProgram(programId);
    }

    //removes a program object from the current rendering state.
    public void unbind() {
        glUseProgram(0);
    }

    //unbinds programs, detaches shaders, then deletes programs
    public void cleanup() {
        unbind();
        if (programId != 0) {
            if (vertexShaderId != 0) {
                glDetachShader(programId, vertexShaderId);
            }
            if (fragmentShaderId != 0) {
                glDetachShader(programId, fragmentShaderId);
            }
            glDeleteProgram(programId);
        }
    }
}