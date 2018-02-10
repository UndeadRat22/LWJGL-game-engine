package engine;

import mesh.Loader;
import mesh.Mesh;
import objects.components.BaseComponent;
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
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final String TITLE = "ENGINE";
    private static final int FPS = 60;

    private Light light;
    private GameObject gameObject;
    private StaticShader shader;
    private Renderer renderer;
    private Display display;
    private Camera camera;
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
        display = new Display(WIDTH, HEIGHT, TITLE, FPS);
        shader = new StaticShader();
        renderer = new Renderer(display, shader);
        display.setWindowKeyInputCallback(Input.getKeyCallback());
        display.setWindowMouseMoveCallback(Input.getCursorPositionCallback());
        game = new Game();
    }

    private void run(){
        if (isRunning) return;
        Texture texture = new Texture("resources/cruiser.png");
        Mesh mesh = ObjParser.parseObjMesh("resources/obj/cruiser.obj");
        Model model = new Model(mesh, texture);
        GameObject cameraGo = new GameObject(
                new Vector3f(0, 0, 0),
                new Vector3f(0, 0, 0),
                new Vector3f(0, 0, 0));
        camera = (Camera) cameraGo.addComponent(new Camera());
        gameObject = new GameObject(
                new Vector3f(0, 0, -5),
                new Vector3f(0, 0, 0),
                new Vector3f(1f, 1f, 1f));
        gameObject.addComponent(model);
        GameObject lightGo = new GameObject(
                new Vector3f(0, 0, 0),
                new Vector3f(0, 0, 0),
                new Vector3f(0, 0, 0));
        light = new Light();
        lightGo.addComponent(light);
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

                game.input();
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
        light.getGameObject().update();
        camera.getGameObject().update();
        gameObject.update();
        renderer.prepare();
        shader.start();
        shader.loadViewMatrix(Maths.createViewMatrix(camera));
        shader.loadLight(light);
        renderer.render(gameObject ,shader);
        shader.stop();
        display.update();
        game.render();
    }

    private void cleanUp(){
        Loader.discardData();
        shader.discardProgram();
        display.close();
    }
}
