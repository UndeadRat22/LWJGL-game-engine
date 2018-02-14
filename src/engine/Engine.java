package engine;

import mesh.Loader;
import shaders.StaticShader;

public class Engine
{
    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;
    private static final String TITLE = "ENGINE";
    private static final int FPS = 60;

    private static RenderManager m_renderer;
    private static Game game;
    private static boolean isRunning;

    public static Display display;
    public static StaticShader staticShader;

    public static void start(){
        if(isRunning)
            return;
        init();
        run();
    }

    public static void stop(){
        if (!isRunning)
            return;
        isRunning = false;
        cleanUp();
    }

    private static void init(){
        display = new Display(WIDTH, HEIGHT, TITLE + " " +WIDTH + "x" +HEIGHT, FPS);
        staticShader = new StaticShader();
        display.setWindowKeyInputCallback(Input.getKeyCallback());
        display.setWindowMouseMoveCallback(Input.getCursorPositionCallback());
        RenderManager.initialize();
        game = new Game();
    }

    private static void run(){
        if (isRunning) return;
        game.start();
        isRunning = true;
        boolean render = false;
        final double frameTime = 1.0 / FPS;
        long lastTime = Time.getTime();
        double unprocessedTime = 0;
        while(isRunning){
            long startTime = Time.getTime();
            long passedTime = startTime - lastTime;
            lastTime = startTime;

            unprocessedTime += passedTime / (double)Time.SECOND;
            while(unprocessedTime > frameTime) {
                render = true;
                unprocessedTime -= frameTime;
                if (display.isCloseRequested())
                    stop();

                Time.setDeltaTime(frameTime);
                game.update();
            }
            if (render)
                render();
            else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void render(){
        game.getGameObjectsToRender().forEach(
                gameObject -> {m_renderer.queueGameObject(gameObject);}
        );
        m_renderer.renderGameObjects(game.getMainLight(), game.getMainCamera());

        display.update();
    }

    private static void cleanUp(){
        Loader.discardData();
        m_renderer.cleanUp();
        display.close();
    }
}
