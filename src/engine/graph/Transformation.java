package engine.graph;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import engine.GameItem;

public class Transformation {

	//local projection matrix
	private final Matrix4f projectionMatrix;
	 
	//local world matrix
	private final Matrix4f modelViewMatrix;
	
	//local view matrix
	private final Matrix4f viewMatrix;
	
	//local ortho matrix
	 private final Matrix4f orthoMatrix;
	
	//just your local public transformation, tickets are $1.50
	public Transformation(){
		modelViewMatrix = new Matrix4f();
		projectionMatrix = new Matrix4f();
		viewMatrix = new Matrix4f();
		orthoMatrix = new Matrix4f();
	}
	
	//returns a projection matrix with passed in values
	public final Matrix4f getProjectionMatrix(float fov, float width, float height, float zNear, float zFar){
		float aspectRatio = width / height;
		projectionMatrix.identity();
		projectionMatrix.perspective(fov, aspectRatio, zNear, zFar);
		return projectionMatrix;
	}
	
	public Matrix4f getViewMatrix(Camera camera){
		Vector3f cameraPos = camera.getPosition();
		Vector3f rotation = camera.getRotation();
		
		viewMatrix.identity();
		//first do the rotation so camera rotates over its position
		viewMatrix.rotate((float)Math.toRadians(rotation.x), new Vector3f(1, 0, 0)).rotate((float)Math.toRadians(rotation.y), new Vector3f(0, 1, 0));
		//then do translation
		viewMatrix.translate(-cameraPos.x, -cameraPos.y, -cameraPos.z);
		return viewMatrix;
	}
	
	//returns a world matrix with passed in vectors
	public Matrix4f getModelViewMatrix(GameItem gameItem, Matrix4f viewMatrix) {
        Vector3f rotation = gameItem.getRotation();
        modelViewMatrix.identity().translate(gameItem.getPosition()).
                rotateX((float)Math.toRadians(-rotation.x)).
                rotateY((float)Math.toRadians(-rotation.y)).
                rotateZ((float)Math.toRadians(-rotation.z)).
                scale(gameItem.getScale());
        Matrix4f viewCurr = new Matrix4f(viewMatrix);
        return viewCurr.mul(modelViewMatrix);
    }
	
	public final Matrix4f getOrthoProjectionMatrix(float left, float right, float bottom, float top) {
        orthoMatrix.identity();
        orthoMatrix.setOrtho2D(left, right, bottom, top);
        return orthoMatrix;
    }

    public Matrix4f getOrtoProjModelMatrix(GameItem gameItem, Matrix4f orthoMatrix) {
        Vector3f rotation = gameItem.getRotation();
        Matrix4f modelMatrix = new Matrix4f();
        modelMatrix.identity().translate(gameItem.getPosition()).
                rotateX((float)Math.toRadians(-rotation.x)).
                rotateY((float)Math.toRadians(-rotation.y)).
                rotateZ((float)Math.toRadians(-rotation.z)).
                scale(gameItem.getScale());
        Matrix4f orthoMatrixCurr = new Matrix4f(orthoMatrix);
        orthoMatrixCurr.mul(modelMatrix);
        return orthoMatrixCurr;
    }
}