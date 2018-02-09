import engine.Display;
import mesh.Loader;
import mesh.Mesh;
import engine.Renderer;
import mesh.Model;
import mesh.textures.Texture;
import objects.GameObject;
import org.joml.Vector3f;
import shaders.StaticShader;

public class Main {

    public static void main(String[] args) {
        float[] vertices = {
                -0.5f, 0.5f, 0f,//v0
                -0.5f, -0.5f, 0f,//v1
                0.5f, -0.5f, 0f,//v2
                0.5f, 0.5f, 0f,//v3
        };

        int[] indices = {
                0,1,3,//top left triangle (v0, v1, v3)
                3,1,2//bottom right triangle (v3, v1, v2)
        };

        float[] uv = {
                0, 0,
                0, 1,
                1, 1,
                1, 0
        };

        Display display = new Display(1280, 720, "window");
        Mesh mesh = new Mesh(vertices, uv, indices);
        Texture texture = new Texture("resources/test_texture.png");
        Model model = new Model(mesh, texture);
        Renderer renderer = new Renderer();
        StaticShader shader = new StaticShader();

        GameObject go = new GameObject(model, new Vector3f(0, 0, 0),new Vector3f(0, 0, 0),new Vector3f(1, 1, 1));

        while (!display.isCloseRequested())
        {
            renderer.prepare();
            shader.start();
            renderer.render(go,shader);
            shader.stop();
            display.update();
        }
        Loader.discardData();
        shader.discardProgram();
        display.close();
    }
}
