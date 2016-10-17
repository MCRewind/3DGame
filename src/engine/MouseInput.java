package engine;

import org.joml.Vector2d;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFWCursorEnterCallback;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class MouseInput {
	
	private final Vector2d previousPos;

    private final Vector2d currentPos;

    private final Vector2f displVec;

    private boolean inWindow = false;

    private boolean leftButtonPressed = false;

    private boolean rightButtonPressed = false;

    private GLFWCursorPosCallback cursorPosCallback;

    private GLFWCursorEnterCallback cursorEnterCallback;

    private GLFWMouseButtonCallback mouseButtonCallback;
    
    public MouseInput(){
    	previousPos = new Vector2d(-1, -1);
    	currentPos = new Vector2d(0, 0);
    	displVec = new Vector2f();
    }
    
    public void init(Window window){
    	glfwSetCurserPositionCall(window.getWindowHandle(), cursorPosCallback = new GLFWCursorPosCallback() {
    		@Override
    		public void invoke(long window, double xpos, double ypos){
    			currentPos.x = xpos;
    			currentPos.y = ypos;
    		}
    	});
    }
}