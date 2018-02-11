package engine;

import objects.GameObject;
import objects.components.Camera;
import objects.components.Light;
import objects.components.Model;
import shaders.StaticShader;
import utility.Maths;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RenderManager {
    private StaticShader shader;
    private Renderer renderer;

    private Map<Model, List<GameObject>> gameObjects = new HashMap<>();

    public RenderManager(StaticShader shader, Renderer renderer){
        this.shader = shader;
        this.renderer = renderer;
    }

    public void render(Light light, Camera camera){
        renderer.prepare();
        shader.start();
        shader.loadLight(light);
        shader.loadViewMatrix(Maths.createViewMatrix(camera));
        renderer.render(gameObjects);
        shader.stop();
        gameObjects.clear();
    }

    public void queueGameObject(GameObject gameObject){
        Model model = gameObject.getComponent(Model.class);
        List<GameObject> instances = gameObjects.get(model);
        if(instances != null)
            instances.add(gameObject);
        else{
            List<GameObject> newInstances = new ArrayList<>();
            newInstances.add(gameObject);
            gameObjects.put(model, newInstances);
        }

    }

    public void cleanUp(){
        shader.discardProgram();
    }
}
