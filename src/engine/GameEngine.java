package engine;

public class GameEngine implements Runnable {

	//target frames per second
    public static final int TARGET_FPS = 75;

    //target updates per second
    public static final int TARGET_UPS = 30;

    private final Window window;

    private final Thread gameLoopThread;

    private final Timer timer;

    private final IGameLogic gameLogic;
    
    private final MouseInput mouseInput;

    //initializes the gameloop thread, the window, the gamelogic, and the timer.
    public GameEngine(String windowTitle, int width, int height, boolean vSync, IGameLogic gameLogic) throws Exception {
        gameLoopThread = new Thread(this, "GAME_LOOP_THREAD");
        window = new Window(windowTitle, width, height, vSync);
        this.gameLogic = gameLogic;
        timer = new Timer();
        mouseInput = new MouseInput();
    }

    //starts the gameloop's thread
    public void start() {
        String osName = System.getProperty("os.name");
        if ( osName.contains("Mac") ) {
            gameLoopThread.run();
        } else {
            gameLoopThread.start();
        }
    }

    //does initializations then starts the gameloop, calls cleanup on exit
    @Override
    public void run() {
        try {
            init();
            gameLoop();
        } catch (Exception excp) {
            excp.printStackTrace();
        } finally {
            cleanup();
        }
    }

    //calls the window and timer's init methods and passes the game's init method the main window
    protected void init() throws Exception {
        window.init();
        timer.init();
        mouseInput.init(window);
        gameLogic.init(window);
    }

    //fixed step gameloop with fps limiting and vsync
    protected void gameLoop() {
        float elapsedTime;
        float accumulator = 0f;
        float interval = 1f / TARGET_UPS;

        boolean running = true;
        while (running && !window.windowShouldClose()) {
            elapsedTime = timer.getElapsedTime();
            accumulator += elapsedTime;

            input();

            while (accumulator >= interval) {
                update(interval);
                accumulator -= interval;
            }

            render();
            
            if ( !window.isvSync() ) {
                sync();
            }
        }
    }

    //calls the game's cleanup method
    protected void cleanup() {
        gameLogic.cleanup();
    }

    //vsync method, sleeps thread till current frame has completed
    private void sync() {
        float loopSlot = 1f / TARGET_FPS;
        double endTime = timer.getLastLoopTime() + loopSlot;
        while (timer.getTime() < endTime) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ie) {
            }
        }
    }

    //passes the main window to the game's input method
    protected void input() {
    	 mouseInput.input(window);
    	 gameLogic.input(window, mouseInput);
    }

    //passes the game's update method an interval
    protected void update(float interval) {
        gameLogic.update(interval, mouseInput);
    }

    //passes the game's render method the main window then updates the window
    protected void render() {
        gameLogic.render(window);
        window.update();
    }
}