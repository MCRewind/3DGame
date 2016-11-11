package engine;

import engine.graph.Camera;

public interface IGameLogic {

	//game logic method interface, self explanatory
	
	void init(Window window) throws Exception;
	
	void input(Window window, MouseInput mouseInput);
	
	void update(float interval, MouseInput mouseInput);
	
	void render(Window window, int fps);
	
	void cleanup();
	
}
