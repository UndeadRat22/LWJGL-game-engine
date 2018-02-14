package engine;

import mesh.Material;
import mesh.Mesh;
import mesh.ObjParser;
import mesh.textures.Texture;
import objects.GameObject;
import objects.components.Camera;
import objects.components.Light;
import objects.components.MeshRenderer;
import objects.components.Model;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Camera mainCamera;
    private Light  mainLight;
    private List<GameObject> gameObjects = new ArrayList<>();
    private List<GameObject> gameObjectsToRender = new ArrayList<>();

    public Game(){

    }
    //Instantiate all of the GameObjects here;
    public void start(){
        Texture texture = new Texture("resources/test_texture.png");
        Mesh mesh = ObjParser.parseObjMesh("resources/obj/cube2.obj");
        Model model = new Model(mesh, texture, new Material(.1f, 1));
        GameObject cameraGo = new GameObject(
                new Vector3f(0, 0, 0),
                new Vector3f(0, 0, 0),
                new Vector3f(0, 0, 0));
        mainCamera = (Camera) cameraGo.addComponent(new Camera());
        cameraGo.name = "Camera";
        GameObject gameObject = new GameObject(
                new Vector3f(0, 0, -5),
                new Vector3f(0, 0, 0),
                new Vector3f(.01f, .01f, .01f));
        gameObject.addComponent(model);
        MeshRenderer meshRenderer = new MeshRenderer();
        gameObject.addComponent(meshRenderer);
        gameObject.name = "modeled object";
        GameObject lightGo = new GameObject(
                new Vector3f(0, 10, -10),
                new Vector3f(0, 0, 0),
                new Vector3f(0, 0, 0));
        mainLight = (Light) lightGo.addComponent(new Light());
        gameObject.name = "light";
        gameObjects.add(cameraGo);
        gameObjects.add(lightGo);
        gameObjects.add(gameObject);
    }

    public void update(){
        gameObjectsToRender.clear();
        gameObjects.forEach(gameObject ->
        {
            gameObject.update();
            if (gameObject.getComponent(Model.class) != null)
                gameObjectsToRender.add(gameObject);
        });
    }

    public List<GameObject> getGameObjectsToRender() {
        return gameObjectsToRender;
    }

    public Camera getMainCamera() {
        return mainCamera;
    }

    public Light getMainLight() {
        return mainLight;
    }
}
