package game;
import engine.GameEngine;
import engine.IGameLogic;

public class Main {
	public static void main(String[] args) {
		try {
			boolean vSync = true;
			IGameLogic gameLogic = new DummyGame();
			//defines a window name, size, refresh type, and logic
			GameEngine gameEng = new GameEngine("GAME", 1920, 1080, vSync, gameLogic);
			//starts game engine
			gameEng.start();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
