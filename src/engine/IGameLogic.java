package engine;

import engine.graph.Camera;

public interface IGameLogic {

	//game logic method interface, self explanatory
	
	void init(Window window) throws Exception;
	
	void input(Window window);
	
	void update(float interval);
	
	void render(Window window, Camera camera);
	
	void cleanup();
	
}
