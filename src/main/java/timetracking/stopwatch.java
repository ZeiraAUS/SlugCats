public class stopwatch {
    private long startTime;
    private long stopTime;
    private boolean isRunning;

    public stopwatch() {
        this.isRunning = false;
    }

    public void start() {
        if (!isRunning) {
            startTime = System.currentTimeMillis();
            isRunning = true;
        }
    }

    public void stop() {
        if (isRunning) {
            stopTime = System.currentTimeMillis();
            isRunning = false;
        }
    }

    public void reset() {
        startTime = 0;
        stopTime = 0;
        isRunning = false;
    }

/*    public String getelapsedTime() {
        long elaspedTime = isRunning ? System.currentTimeMillis() - startTime : stopTime - startTime;
        long hours = (elaspedTime / )
    }*/
}
