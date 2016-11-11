package engine;

import org.joml.Vector3f;

import engine.graph.Mesh;

public class GameItem {
	
	//item mesh instance
	private Mesh mesh;
	
	//item position
	private final Vector3f position;
	
	//item scale
	private float scale;
	
	//item rotation
	private final Vector3f rotation;
	
	//sets item mesh and transformation values to defaults
	public GameItem() {
        position = new Vector3f(0, 0, 0);
        scale = 1;
        rotation = new Vector3f(0, 0, 0);
    }
    
    public GameItem(Mesh mesh) {
        this();
        this.mesh = mesh;
    }
	
	/*
	 * all methods below are generic getters and setters
	 */
	
    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(float x, float y, float z) {
        this.position.x = x;
        this.position.y = y;
        this.position.z = z;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(float x, float y, float z) {
        this.rotation.x = x;
        this.rotation.y = y;
        this.rotation.z = z;
    }
    
    public Mesh getMesh() {
        return mesh;
    }
    
    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }
}