import engine.Display;
import engine.Loader;
import engine.Mesh;
import engine.Renderer;
import shaders.StaticShader;

public class Main {
    private static float[] vertices = {
            -0.5f, 0.5f, 0f,
            -0.5f, -0.5f, 0f,
            0.5f, -0.5f, 0f,
            0.5f, -0.5f, 0f,
            0.5f, 0.5f, 0f,
            -0.5f, 0.5f, 0f
    };
    public static void main(String[] args) {

        Display display = new Display(1280, 720, "window");
        Mesh mesh = Loader.loadToVAO(vertices);
        Renderer renderer = new Renderer();
        StaticShader shader = new StaticShader();
        while (!display.isCloseRequested())
        {
            renderer.prepare();
            shader.start();
            renderer.render(mesh);
            shader.stop();
            display.update();
        }
        Loader.discardData();
        shader.discardProgram();
        display.close();
    }
}
