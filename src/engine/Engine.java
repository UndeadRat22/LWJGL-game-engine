package engine;

import mesh.Loader;
import mesh.Material;
import mesh.Mesh;
import objects.components.Model;
import mesh.ObjParser;
import mesh.textures.Texture;
import objects.components.Camera;
import objects.GameObject;
import objects.components.Light;
import org.joml.Vector3f;
import shaders.StaticShader;
import utility.Maths;

public class Engine
{
    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;
    private static final String TITLE = "ENGINE";
    private static final int FPS = 60;

    private RenderManager m_renderer;
    private Display display;
    private Game game;
    private boolean isRunning;

    public Engine(){
        isRunning = false;
    }

    public void start(){
        if(isRunning)
            return;
        init();
        run();
    }

    public void stop(){
        if (!isRunning)
            return;
        isRunning = false;
        cleanUp();
    }

    private void init(){
        display = new Display(WIDTH, HEIGHT, TITLE + " " +WIDTH + "x" +HEIGHT, FPS);
        StaticShader shader = new StaticShader();
        Renderer renderer = new Renderer(display, shader);
        m_renderer = new RenderManager(shader, renderer);
        display.setWindowKeyInputCallback(Input.getKeyCallback());
        display.setWindowMouseMoveCallback(Input.getCursorPositionCallback());
        game = new Game();
    }

    private void run(){
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

    private void render(){
        game.getGameObjectsToRender().forEach(
                gameObject -> {m_renderer.queueGameObject(gameObject);}
        );
        m_renderer.render(game.getMainLight(), game.getMainCamera());

        display.update();
    }

    private void cleanUp(){
        Loader.discardData();
        m_renderer.cleanUp();
        display.close();
    }
}
