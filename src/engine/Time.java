package engine;

public class Time {
    public static final long SECOND = 1000000000L;
    private static double delta;

    public static long getTime(){
        return System.nanoTime();
    }

    public static double getDeltaTime() {
        return delta;
    }

    public static void setDeltaTime(double delta){
        Time.delta = delta;
    }
}
