import engine.Display;
import mesh.Loader;
import mesh.Mesh;
import engine.Renderer;
import mesh.TexturedMesh;
import mesh.textures.Texture;
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
        Mesh mesh = Loader.loadToVAO(vertices, uv, indices);
        Texture texture = new Texture("resources/test_texture.png");
        TexturedMesh model = new TexturedMesh(mesh, texture);
        Renderer renderer = new Renderer();
        StaticShader shader = new StaticShader();
        while (!display.isCloseRequested())
        {
            renderer.prepare();
            shader.start();
            renderer.render(model);
            shader.stop();
            display.update();
        }
        Loader.discardData();
        shader.discardProgram();
        display.close();
    }
}
