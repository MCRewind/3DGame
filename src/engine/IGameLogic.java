package engine;

public interface IGameLogic {

	//game logic method interface, self explanatory
	
	void init(Window window) throws Exception;
	
	void input(Window window);
	
	void update(float interval);
	
	void render(Window window);
	
	void cleanup();
	
}
