import engine.Display;
import engine.Engine;
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
        Engine engine = new Engine();
        engine.start();
    }
}
