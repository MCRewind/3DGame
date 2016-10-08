package engine;
public class Timer {

    private double lastLoopTime;
    
    //starts the last loop time at the current time
    public void init() {
        lastLoopTime = getTime();
    }

    //returns the time
    public double getTime() {
        return System.nanoTime() / 1000_000_000.0;
    }

    //returns the difference between the last time check and the current time
    public float getElapsedTime() {
        double time = getTime();
        float elapsedTime = (float) (time - lastLoopTime);
        lastLoopTime = time;
        return elapsedTime;
    }

    //returns the last loop time
    public double getLastLoopTime() {
        return lastLoopTime;
    }
}