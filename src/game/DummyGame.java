package game;

import org.joml.Vector2f;
import org.joml.Vector3f;
import static org.lwjgl.glfw.GLFW.*;
import engine.GameItem;
import engine.IGameLogic;
import engine.MouseInput;
import engine.Window;
import engine.graph.Camera;
import engine.graph.Mesh;
import engine.graph.Texture;

public class DummyGame implements IGameLogic {

    private static final float MOUSE_SENSITIVITY = 0.4f;

    private final Vector3f cameraInc;

    private final Renderer renderer;

    private final Camera camera;

    private GameItem[] gameItems;

    private static final float CAMERA_POS_STEP = 0.05f;

    public DummyGame() {
        renderer = new Renderer();
        camera = new Camera();
        cameraInc = new Vector3f(0, 0, 0);
    }

    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);
        // Create the Mesh
        float[] positions = new float[]{
            // V0
            -0.5f, 0.5f, 0.5f,
            // V1
            -0.5f, -0.5f, 0.5f,
            // V2
            0.5f, -0.5f, 0.5f,
            // V3
            0.5f, 0.5f, 0.5f,
            // V4
            -0.5f, 0.5f, -0.5f,
            // V5
            0.5f, 0.5f, -0.5f,
            // V6
            -0.5f, -0.5f, -0.5f,
            // V7
            0.5f, -0.5f, -0.5f,
            // For text coords in top face
            // V8: V4 repeated
            -0.5f, 0.5f, -0.5f,
            // V9: V5 repeated
            0.5f, 0.5f, -0.5f,
            // V10: V0 repeated
            -0.5f, 0.5f, 0.5f,
            // V11: V3 repeated
            0.5f, 0.5f, 0.5f,
            // For text coords in right face
            // V12: V3 repeated
            0.5f, 0.5f, 0.5f,
            // V13: V2 repeated
            0.5f, -0.5f, 0.5f,
            // For text coords in left face
            // V14: V0 repeated
            -0.5f, 0.5f, 0.5f,
            // V15: V1 repeated
            -0.5f, -0.5f, 0.5f,
            // For text coords in bottom face
            // V16: V6 repeated
            -0.5f, -0.5f, -0.5f,
            // V17: V7 repeated
            0.5f, -0.5f, -0.5f,
            // V18: V1 repeated
            -0.5f, -0.5f, 0.5f,
            // V19: V2 repeated
            0.5f, -0.5f, 0.5f,};
        float[] textCoords = new float[]{
            0.0f, 0.0f,
            0.0f, 0.5f,
            0.5f, 0.5f,
            0.5f, 0.0f,
            0.0f, 0.0f,
            0.5f, 0.0f,
            0.0f, 0.5f,
            0.5f, 0.5f,
            // For text coords in top face
            0.0f, 0.5f,
            0.5f, 0.5f,
            0.0f, 1.0f,
            0.5f, 1.0f,
            // For text coords in right face
            0.0f, 0.0f,
            0.0f, 0.5f,
            // For text coords in left face
            0.5f, 0.0f,
            0.5f, 0.5f,
            // For text coords in bottom face
            0.5f, 0.0f,
            1.0f, 0.0f,
            0.5f, 0.5f,
            1.0f, 0.5f,};
        int[] indices = new int[]{
            // Front face
            0, 1, 3, 3, 1, 2,
            // Top Face
            8, 10, 11, 9, 8, 11,
            // Right face
            12, 13, 7, 5, 12, 7,
            // Left face
            14, 15, 6, 4, 14, 6,
            // Bottom face
            16, 18, 19, 17, 16, 19,
            // Back face
            4, 6, 7, 5, 4, 7,};
        Texture texture = new Texture("/res/textures/grassblock.png");
        Mesh mesh2 = new Mesh(positions, textCoords, indices, texture);
        
        Texture texture2 = new Texture("/res/textures/circlebrick.png");
        Mesh mesh = new Mesh(positions, textCoords, indices, texture2);
        
        GameItem gameItem0 = new GameItem(mesh);
        gameItem0.setScale(0.5f);
        gameItem0.setPosition(-.5f, -.5f, 0);
        GameItem gameItemA = new GameItem(mesh);
        gameItemA.setScale(0.5f);
        gameItemA.setPosition(-.5f, -.5f, 2);
        GameItem gameItemB = new GameItem(mesh);
        gameItemB.setScale(0.5f);
        gameItemB.setPosition(2.5f, -.5f, 2);
        GameItem gameItemC = new GameItem(mesh);
        gameItemC.setScale(0.5f);
        gameItemC.setPosition(2.5f, -.5f, 0);
        
        GameItem gameItem1 = new GameItem(mesh);
        gameItem1.setScale(0.5f);
        gameItem1.setPosition(0, 0, 0);
        GameItem gameItem2 = new GameItem(mesh);
        gameItem2.setScale(0.5f);
        gameItem2.setPosition(0.5f, 0.5f, 0);
        GameItem gameItem3 = new GameItem(mesh);
        gameItem3.setScale(0.5f);
        gameItem3.setPosition(1, 1, 0);
        GameItem gameItem4 = new GameItem(mesh);
        gameItem4.setScale(0.5f);
        gameItem4.setPosition(1.5f, .5f, 0);
        GameItem gameItem5 = new GameItem(mesh);
        gameItem5.setScale(0.5f);
        gameItem5.setPosition(2f, 0f, 0);
        
        GameItem gameItem11 = new GameItem(mesh2);
        gameItem11.setScale(0.5f);
        gameItem11.setPosition(0, -.5f, .5f);
        GameItem gameItem12 = new GameItem(mesh2);
        gameItem12.setScale(0.5f);
        gameItem12.setPosition(0.5f, 0f, .5f);
        GameItem gameItem13 = new GameItem(mesh2);
        gameItem13.setScale(0.5f);
        gameItem13.setPosition(1, .5f, .5f);
        GameItem gameItem14 = new GameItem(mesh2);
        gameItem14.setScale(0.5f);
        gameItem14.setPosition(1.5f, 0f, .5f);
        GameItem gameItem15 = new GameItem(mesh2);
        gameItem15.setScale(0.5f);
        gameItem15.setPosition(2f, -.5f, .5f);
        
        GameItem gameItem16 = new GameItem(mesh2);
        gameItem16.setScale(0.5f);
        gameItem16.setPosition(0, -.5f, 1f);
        GameItem gameItem17 = new GameItem(mesh2);
        gameItem17.setScale(0.5f);
        gameItem17.setPosition(0.5f, 0f, 1f);
        GameItem gameItem18 = new GameItem(mesh2);
        gameItem18.setScale(0.5f);
        gameItem18.setPosition(1, .5f, 1f);
        GameItem gameItem19 = new GameItem(mesh2);
        gameItem19.setScale(0.5f);
        gameItem19.setPosition(1.5f, 0f, 1f);
        GameItem gameItem20 = new GameItem(mesh2);
        gameItem20.setScale(0.5f);
        gameItem20.setPosition(2f, -.5f, 1f);
        
        GameItem gameItem21 = new GameItem(mesh2);
        gameItem21.setScale(0.5f);
        gameItem21.setPosition(0, -.5f, 1.5f);
        GameItem gameItem22 = new GameItem(mesh2);
        gameItem22.setScale(0.5f);
        gameItem22.setPosition(0.5f, 0f, 1.5f);
        GameItem gameItem23 = new GameItem(mesh2);
        gameItem23.setScale(0.5f);
        gameItem23.setPosition(1, .5f, 1.5f);
        GameItem gameItem24 = new GameItem(mesh2);
        gameItem24.setScale(0.5f);
        gameItem24.setPosition(1.5f, 0f, 1.5f);
        GameItem gameItem25 = new GameItem(mesh2);
        gameItem25.setScale(0.5f);
        gameItem25.setPosition(2f, -.5f, 1.5f);


        GameItem gameItem6 = new GameItem(mesh);
        gameItem6.setScale(0.5f);
        gameItem6.setPosition(0, 0, 2f);
        GameItem gameItem7 = new GameItem(mesh);
        gameItem7.setScale(0.5f);
        gameItem7.setPosition(0.5f, 0.5f, 2f);
        GameItem gameItem8 = new GameItem(mesh);
        gameItem8.setScale(0.5f);
        gameItem8.setPosition(1, 1, 2f);
        GameItem gameItem9 = new GameItem(mesh);
        gameItem9.setScale(0.5f);
        gameItem9.setPosition(1.5f, .5f, 2f);
        GameItem gameItem10 = new GameItem(mesh);
        gameItem10.setScale(0.5f);
        gameItem10.setPosition(2f, 0f, 2f);
        gameItems = new GameItem[]{gameItemC, gameItemB, gameItemA, gameItem0, gameItem1, gameItem2, gameItem3, gameItem4, gameItem5, gameItem6, gameItem7, gameItem8, gameItem9, gameItem10, gameItem11, gameItem12, gameItem13, gameItem14, gameItem15, gameItem16, gameItem17, gameItem18, gameItem19, gameItem20, gameItem21, gameItem22, gameItem23, gameItem24, gameItem25};
    }

    @Override
    public void input(Window window, MouseInput mouseInput) {
        cameraInc.set(0, 0, 0);
        if (window.isKeyPressed(GLFW_KEY_W)) {
            cameraInc.z = -.5f;
        } else if (window.isKeyPressed(GLFW_KEY_S)) {
            cameraInc.z = .5f;
        }
        if (window.isKeyPressed(GLFW_KEY_A)) {
            cameraInc.x = -.5f;
        } else if (window.isKeyPressed(GLFW_KEY_D)) {
            cameraInc.x = .5f;
        }
        if (window.isKeyPressed(GLFW_KEY_Z)) {
            cameraInc.y = -.5f;
        } else if (window.isKeyPressed(GLFW_KEY_X)) {
            cameraInc.y = .5f;
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
        }
    }

    @Override
    public void render(Window window) {
        renderer.render(window, gameItems, camera);
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        for (GameItem gameItem : gameItems) {
            gameItem.getMesh().cleanUp();
        }
    }
}