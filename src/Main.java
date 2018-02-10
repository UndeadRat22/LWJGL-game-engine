import engine.Display;
import mesh.Loader;
import mesh.Mesh;
import engine.Renderer;
import mesh.Model;
import mesh.ObjParser;
import mesh.textures.Texture;
import objects.Camera;
import objects.GameObject;
import org.joml.Vector3f;
import shaders.StaticShader;
import utility.Maths;

public class Main {

    public static void main(String[] args) {
        float[] vertices = {
                -0.5f,0.5f,-0.5f,
                -0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,-0.5f,
                0.5f,0.5f,-0.5f,

                -0.5f,0.5f,0.5f,
                -0.5f,-0.5f,0.5f,
                0.5f,-0.5f,0.5f,
                0.5f,0.5f,0.5f,

                0.5f,0.5f,-0.5f,
                0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,0.5f,
                0.5f,0.5f,0.5f,

                -0.5f,0.5f,-0.5f,
                -0.5f,-0.5f,-0.5f,
                -0.5f,-0.5f,0.5f,
                -0.5f,0.5f,0.5f,

                -0.5f,0.5f,0.5f,
                -0.5f,0.5f,-0.5f,
                0.5f,0.5f,-0.5f,
                0.5f,0.5f,0.5f,

                -0.5f,-0.5f,0.5f,
                -0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,0.5f

        };

        float[] uv = {

                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0,
                0,0,
                0,1,
                1,1,
                1,0


        };

        int[] indices = {
                0,1,3,
                3,1,2,
                4,5,7,
                7,5,6,
                8,9,11,
                11,9,10,
                12,13,15,
                15,13,14,
                16,17,19,
                19,17,18,
                20,21,23,
                23,21,22

        };

        Display display = new Display(1280, 720, "window");
        //Mesh mesh = new Mesh(vertices, uv, indices);
        Mesh mesh = ObjParser.parseObjMesh("house_obj");
        Texture texture = new Texture("resources/test_texture.png");
        Model model = new Model(mesh, texture);
        StaticShader shader = new StaticShader();
        Renderer renderer = new Renderer(display, shader);
        Camera camera = new Camera();
        display.setWindowKeyInputCallback(camera.getGlfwKeyCallback());

        GameObject go = new GameObject(model,
                new Vector3f(0, 0, -10),
                new Vector3f(0, 0, 0),
                new Vector3f(.01f, .01f, .01f));
        Vector3f rotation = new Vector3f();
        while (!display.isCloseRequested())
        {
            //rotation.x += 2f;
            //rotation.y += 2f;
            //Transform.translate(go.getTransform(), new Vector3f(0f, 0, -0.02f));
            go.getTransform().setRotation(rotation);
            renderer.prepare();
            shader.start();
            shader.loadViewMatrix(Maths.createViewMatrix(camera));
            renderer.render(go,shader);
            shader.stop();
            display.update();
        }
        Loader.discardData();
        shader.discardProgram();
        display.close();
    }
}
