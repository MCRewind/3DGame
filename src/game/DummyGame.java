package game;

import org.joml.Vector2f;
import org.joml.Vector3f;
import static org.lwjgl.glfw.GLFW.*;
import engine.GameItem;
import engine.IGameLogic;
import engine.MouseInput;
import engine.SceneLight;
import engine.Window;
import engine.graph.Camera;
import engine.graph.DirectionalLight;
import engine.graph.Material;
import engine.graph.Mesh;
import engine.graph.OBJLoader;
import engine.graph.PointLight;
import engine.graph.Renderer;
import engine.graph.SpotLight;
import engine.graph.Texture;

public class DummyGame implements IGameLogic {

	private static final float MOUSE_SENSITIVITY = 0.2f;

    private final Vector3f cameraInc;

    private final Renderer renderer;

    private final Camera camera;

    private GameItem[] gameItems;

    private SceneLight sceneLight;
    
    private Hud hud;
    
    private float lightAngle;
    
    private static final float CAMERA_POS_STEP = 0.05f;

    private float spotAngle = 0;

    private float spotInc = 1;
    
    private int spotArrPos = 0;
    
    private int pointArrPos = 0;
    
    private boolean whatLight = true;
    //true = point
    //false = spot
    
    public DummyGame() {
        renderer = new Renderer();
        camera = new Camera();
        cameraInc = new Vector3f(0.0f, 0.0f, 0.0f);
        lightAngle = -90;
    }

    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);
       
        float reflectance = 1f;
        
        Mesh mesh2 = OBJLoader.loadMesh("/res/models/cube.obj");
        Texture texture2 = new Texture("/res/textures/grassblock.png");
        Material material2 = new Material(texture2, reflectance);
        mesh2.setMaterial(material2);
        
        Mesh mesh = OBJLoader.loadMesh("/res/models/cube.obj");
        Texture texture = new Texture("/res/textures/circlebrick.png");
        Material material = new Material(texture, reflectance);
        mesh.setMaterial(material);
        
        Mesh mesh3 = OBJLoader.loadMesh("/res/models/cube.obj");
        Texture texture3 = new Texture("/res/textures/floor.png");
        Material material3 = new Material(texture3, reflectance);
        mesh3.setMaterial(material3);
        
        GameItem floor1 = new GameItem(mesh3);
        floor1.setScale(1.25f);
        floor1.setPosition(-.5f, -2f, 1);
        GameItem floor2 = new GameItem(mesh3);
        floor2.setScale(1.25f);
        floor2.setPosition(2f, -2f, 1);
        GameItem floor3 = new GameItem(mesh3);
        floor3.setScale(1.25f);
        floor3.setPosition(4.5f, -2f, 1);
        GameItem floor4 = new GameItem(mesh3);
        floor4.setScale(1.25f);
        floor4.setPosition(7f, -2f, 1);
        
        GameItem floor5 = new GameItem(mesh3);
        floor5.setScale(1.25f);
        floor5.setPosition(-.5f, -2f, 3.5f);
        GameItem floor6 = new GameItem(mesh3);
        floor6.setScale(1.25f);
        floor6.setPosition(2f, -2f, 3.5f);
        GameItem floor7 = new GameItem(mesh3);
        floor7.setScale(1.25f);
        floor7.setPosition(4.5f, -2f, 3.5f);
        GameItem floor8 = new GameItem(mesh3);
        floor8.setScale(1.25f);
        floor8.setPosition(7f, -2f, 3.5f);
        
        GameItem floor9 = new GameItem(mesh3);
        floor9.setScale(1.25f);
        floor9.setPosition(-.5f, -2f, -1.5f);
        GameItem floor10 = new GameItem(mesh3);
        floor10.setScale(1.25f);
        floor10.setPosition(2f, -2f, -1.5f);
        GameItem floor11 = new GameItem(mesh3);
        floor11.setScale(1.25f);
        floor11.setPosition(4.5f, -2f, -1.5f);
        GameItem floor12 = new GameItem(mesh3);
        floor12.setScale(1.25f);
        floor12.setPosition(7f, -2f, -1.5f);
        
        GameItem gameItem0 = new GameItem(mesh);
        gameItem0.setScale(0.25f);
        gameItem0.setPosition(-.5f, -.5f, 0);
        GameItem gameItemA = new GameItem(mesh);
        gameItemA.setScale(0.25f);
        gameItemA.setPosition(-.5f, -.5f, 2);
        GameItem gameItemB = new GameItem(mesh);
        gameItemB.setScale(0.25f);
        gameItemB.setPosition(2.5f, -.5f, 2);
        GameItem gameItemC = new GameItem(mesh);
        gameItemC.setScale(0.25f);
        gameItemC.setPosition(2.5f, -.5f, 0);
        
        GameItem gameItem1 = new GameItem(mesh);
        gameItem1.setScale(0.25f);
        gameItem1.setPosition(0, 0, 0);
        GameItem gameItem2 = new GameItem(mesh);
        gameItem2.setScale(0.25f);
        gameItem2.setPosition(0.5f, 0.5f, 0);
        GameItem gameItem3 = new GameItem(mesh);
        gameItem3.setScale(0.25f);
        gameItem3.setPosition(1, 1, 0);
        GameItem gameItem4 = new GameItem(mesh);
        gameItem4.setScale(0.25f);
        gameItem4.setPosition(1.5f, .5f, 0);
        GameItem gameItem5 = new GameItem(mesh);
        gameItem5.setScale(0.25f);
        gameItem5.setPosition(2f, 0f, 0);
        
        GameItem gameItem11 = new GameItem(mesh2);
        gameItem11.setScale(0.25f);
        gameItem11.setPosition(0, -.5f, .5f);
        GameItem gameItem12 = new GameItem(mesh2);
        gameItem12.setScale(0.25f);
        gameItem12.setPosition(0.5f, 0f, .5f);
        GameItem gameItem13 = new GameItem(mesh2);
        gameItem13.setScale(0.25f);
        gameItem13.setPosition(1, .5f, .5f);
        GameItem gameItem14 = new GameItem(mesh2);
        gameItem14.setScale(0.25f);
        gameItem14.setPosition(1.5f, 0f, .5f);
        GameItem gameItem15 = new GameItem(mesh2);
        gameItem15.setScale(0.25f);
        gameItem15.setPosition(2f, -.5f, .5f);
        
        GameItem gameItem16 = new GameItem(mesh2);
        gameItem16.setScale(0.25f);
        gameItem16.setPosition(0, -.5f, 1f);
        GameItem gameItem17 = new GameItem(mesh2);
        gameItem17.setScale(0.25f);
        gameItem17.setPosition(0.5f, 0f, 1f);
        GameItem gameItem18 = new GameItem(mesh2);
        gameItem18.setScale(0.25f);
        gameItem18.setPosition(1, .5f, 1f);
        GameItem gameItem19 = new GameItem(mesh2);
        gameItem19.setScale(0.25f);
        gameItem19.setPosition(1.5f, 0f, 1f);
        GameItem gameItem20 = new GameItem(mesh2);
        gameItem20.setScale(0.25f);
        gameItem20.setPosition(2f, -.5f, 1f);
        
        GameItem gameItem21 = new GameItem(mesh2);
        gameItem21.setScale(0.25f);
        gameItem21.setPosition(0, -.5f, 1.5f);
        GameItem gameItem22 = new GameItem(mesh2);
        gameItem22.setScale(0.25f);
        gameItem22.setPosition(0.5f, 0f, 1.5f);
        GameItem gameItem23 = new GameItem(mesh2);
        gameItem23.setScale(0.25f);
        gameItem23.setPosition(1, .5f, 1.5f);
        GameItem gameItem24 = new GameItem(mesh2);
        gameItem24.setScale(0.25f);
        gameItem24.setPosition(1.5f, 0f, 1.5f);
        GameItem gameItem25 = new GameItem(mesh2);
        gameItem25.setScale(0.25f);
        gameItem25.setPosition(2f, -.5f, 1.5f);


        GameItem gameItem6 = new GameItem(mesh);
        gameItem6.setScale(0.25f);
        gameItem6.setPosition(0, 0, 2f);
        GameItem gameItem7 = new GameItem(mesh);
        gameItem7.setScale(0.25f);
        gameItem7.setPosition(0.5f, 0.5f, 2f);
        GameItem gameItem8 = new GameItem(mesh);
        gameItem8.setScale(0.25f);
        gameItem8.setPosition(1, 1, 2f);
        GameItem gameItem9 = new GameItem(mesh);
        gameItem9.setScale(0.25f);
        gameItem9.setPosition(1.5f, .5f, 2f);
        GameItem gameItem10 = new GameItem(mesh);
        gameItem10.setScale(0.25f);
        gameItem10.setPosition(2f, 0f, 2f);
        gameItems = new GameItem[]{floor1, floor2, floor3, floor4, floor5, floor6, floor7, floor8, floor9, floor10, floor11, floor12, gameItemC, gameItemB, gameItemA, gameItem0, gameItem1, gameItem2, gameItem3, gameItem4, gameItem5, gameItem6, gameItem7, gameItem8, gameItem9, gameItem10, gameItem11, gameItem12, gameItem13, gameItem14, gameItem15, gameItem16, gameItem17, gameItem18, gameItem19, gameItem20, gameItem21, gameItem22, gameItem23, gameItem24, gameItem25};
   
        sceneLight = new SceneLight();
        
        // Ambient Light
        sceneLight.setAmbientLight(new Vector3f(0.3f, 0.3f, 0.3f));

        // Point Light
        Vector3f lightPosition = new Vector3f(0, 0, 1);
        float lightIntensity = 1.0f;
        PointLight pointLight = new PointLight(new Vector3f(1, 1, 1), lightPosition, lightIntensity);
        PointLight.Attenuation att = new PointLight.Attenuation(0.0f, 0.0f, 1.0f);
        pointLight.setAttenuation(att);
        sceneLight.setPointLightList(new PointLight[]{pointLight});

        // Spot Light
        lightPosition = new Vector3f(0, 0.0f, 10f);
        pointLight = new PointLight(new Vector3f(1, 1, 1), lightPosition, lightIntensity);
        att = new PointLight.Attenuation(0.0f, 0.0f, 0.02f);
        pointLight.setAttenuation(att);
        Vector3f coneDir = new Vector3f(0, 0, -1);
        float cutoff = (float) Math.cos(Math.toRadians(140));
        SpotLight spotLight = new SpotLight(pointLight, coneDir, cutoff);
        sceneLight.setSpotLightList(new SpotLight[]{spotLight, new SpotLight(spotLight)});

        lightPosition = new Vector3f(-1, 0, 0);
        sceneLight.setDirectionalLight(new DirectionalLight(new Vector3f(1, 1, 1), lightPosition, lightIntensity));
        
        // Create HUD
        hud = new Hud("DEMO");
    }
    
    @Override
    public void input(Window window, MouseInput mouseInput) {
    	
    	/*CAMERA CONTROLS*/
    	/* W - FORWARDS
    	 * A - LEFT
    	 * S - BACKWARDS
    	 * D - RIGHT
    	 * Z - DOWN
    	 * X - UP
    	 * LEFT MOUSE BUTTON - ROTATE
    	 */
    	
        cameraInc.set(0, 0, 0);
        //camera control
        if (window.isKeyPressed(GLFW_KEY_W)) {
            cameraInc.z = -1;
        } else if (window.isKeyPressed(GLFW_KEY_S)) {
            cameraInc.z = 1;
        }
        if (window.isKeyPressed(GLFW_KEY_A)) {
            cameraInc.x = -1;
        } else if (window.isKeyPressed(GLFW_KEY_D)) {
            cameraInc.x = 1;
        }
        if (window.isKeyPressed(GLFW_KEY_Z)) {
            cameraInc.y = -1;
        } else if (window.isKeyPressed(GLFW_KEY_X)) {
            cameraInc.y = 1;
        }
  
        /*LIGHT CONTROLS*/
        /* I - FORWARD
         * J - LEFT
         * K - BACKWARDS
         * L - RIGHT
         * N - DOWN
         * M - UP
         * T - TOGGLE[SPOT]/[POINT]
         * G - DECREMENT CURRENT LIGHT ARRAY POS
         * H - INCREMENT CURRENT LIGHT ARRAY POS
         */
        
        //light array control
        if (window.isKeyPressed(GLFW_KEY_G)) {
        	if (whatLight){
        		if(pointArrPos > 0){
            		pointArrPos--;
            	}
        	} else {
        		if(spotArrPos > 0){
        			spotArrPos--;
            	}
        	}
        } else if (window.isKeyPressed(GLFW_KEY_H)) {
        	if (whatLight){
        		if(pointArrPos < sceneLight.getPointLightList().length-1){
            		pointArrPos++;
            	}
        	} else {
        		if(spotArrPos < sceneLight.getSpotLightList().length-1){
        			spotArrPos++;
            	}
        	}
        }
        if (window.isKeyPressed(GLFW_KEY_T)) {
            whatLight = !whatLight;
        }
        
        //light control
        if (window.isKeyPressed(GLFW_KEY_I)) {
        	if (whatLight) {
        		this.sceneLight.getPointLightList()[pointArrPos].getPosition().z = sceneLight.getPointLightList()[pointArrPos].getPosition().z + 0.1f;
        	} else {
        		this.sceneLight.getSpotLightList()[spotArrPos].getPointLight().getPosition().z = sceneLight.getSpotLightList()[spotArrPos].getPointLight().getPosition().z + 0.1f;
        	}
        } else if (window.isKeyPressed(GLFW_KEY_K)) {
        	if (whatLight) {
        		this.sceneLight.getPointLightList()[pointArrPos].getPosition().z = sceneLight.getPointLightList()[pointArrPos].getPosition().z - 0.1f;
        	} else {
        		this.sceneLight.getSpotLightList()[spotArrPos].getPointLight().getPosition().z = sceneLight.getSpotLightList()[spotArrPos].getPointLight().getPosition().z - 0.1f;
        	}
        }
        if (window.isKeyPressed(GLFW_KEY_J)) {
        	if (whatLight) {
        		this.sceneLight.getPointLightList()[pointArrPos].getPosition().x = sceneLight.getPointLightList()[pointArrPos].getPosition().x + 0.1f;
        	} else {
        		this.sceneLight.getSpotLightList()[spotArrPos].getPointLight().getPosition().x = sceneLight.getSpotLightList()[spotArrPos].getPointLight().getPosition().x + 0.1f;
        	}
        } else if (window.isKeyPressed(GLFW_KEY_L)) {
        	if (whatLight) {
        		this.sceneLight.getPointLightList()[pointArrPos].getPosition().x = sceneLight.getPointLightList()[pointArrPos].getPosition().x - 0.1f;
        	} else {
        		this.sceneLight.getSpotLightList()[spotArrPos].getPointLight().getPosition().x = sceneLight.getSpotLightList()[spotArrPos].getPointLight().getPosition().x - 0.1f;
        	}
        }
        if (window.isKeyPressed(GLFW_KEY_N)) {
        	if (whatLight) {
        		this.sceneLight.getPointLightList()[pointArrPos].getPosition().y = sceneLight.getPointLightList()[pointArrPos].getPosition().y + 0.1f;
        	} else {
        		this.sceneLight.getSpotLightList()[spotArrPos].getPointLight().getPosition().y = sceneLight.getSpotLightList()[spotArrPos].getPointLight().getPosition().y + 0.1f;
        	}
        } else if (window.isKeyPressed(GLFW_KEY_M)) {
        	if (whatLight) {
        		this.sceneLight.getPointLightList()[pointArrPos].getPosition().y = sceneLight.getPointLightList()[pointArrPos].getPosition().y - 0.1f;
        	} else {
        		this.sceneLight.getSpotLightList()[spotArrPos].getPointLight().getPosition().y = sceneLight.getSpotLightList()[spotArrPos].getPointLight().getPosition().y - 0.1f;
        	}
        }
    }

    @Override
    public void update(float interval, MouseInput mouseInput) {
        // Update camera position
        camera.movePosition(cameraInc.x * CAMERA_POS_STEP, cameraInc.y * CAMERA_POS_STEP, cameraInc.z * CAMERA_POS_STEP);

        // Update camera based on mouse            
        if (mouseInput.isRightButtonPressed()) {
            Vector2f rotVec = mouseInput.getDisplVec();
            camera.moveRotation((rotVec.x * MOUSE_SENSITIVITY)/2, (rotVec.y * MOUSE_SENSITIVITY)/2, 0);
            
            // Update HUD compass
            hud.rotateCompass(camera.getRotation().y);
        }
        
        // Update spot light direction
        spotAngle += spotInc * 0.05f;
        if (spotAngle > 2) {
            spotInc = -1;
        } else if (spotAngle < -2) {
            spotInc = 1;
        }
        double spotAngleRad = Math.toRadians(spotAngle);
        SpotLight[] spotLightList = sceneLight.getSpotLightList();
        Vector3f coneDir = spotLightList[0].getConeDirection();
        coneDir.y = (float) Math.sin(spotAngleRad);

        // Update directional light direction, intensity and color
        DirectionalLight directionalLight = sceneLight.getDirectionalLight();
        lightAngle += 1.1f;
        if (lightAngle > 90) {
            directionalLight.setIntensity(0);
            if (lightAngle >= 360) {
                lightAngle = -90;
            }
        } else if (lightAngle <= -80 || lightAngle >= 80) {
            float factor = 1 - (float) (Math.abs(lightAngle) - 80) / 10.0f;
            directionalLight.setIntensity(factor);
            directionalLight.getColor().y = Math.max(factor, 0.9f);
            directionalLight.getColor().z = Math.max(factor, 0.5f);
        } else {
            directionalLight.setIntensity(1);
            directionalLight.getColor().x = 1;
            directionalLight.getColor().y = 1;
            directionalLight.getColor().z = 1;
        }
        double angRad = Math.toRadians(lightAngle);
        directionalLight.getDirection().x = (float) Math.sin(angRad);
        directionalLight.getDirection().y = (float) Math.cos(angRad);
    }

    @Override
    public void render(Window window, int fps) {
    	hud.updateSize(window);
    	if (fps != 9000){
            hud.setStatusText(Integer.toString(fps));
    	}
        renderer.render(window, camera, gameItems, sceneLight, hud);
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        for (GameItem gameItem : gameItems) {
            gameItem.getMesh().cleanUp();
        }
        hud.cleanup();
    }
}