package engine;

import javafx.util.Pair;
import mesh.Mesh;
import objects.GameObject;
import objects.components.Camera;
import objects.components.Light;
import objects.components.Model;
import org.joml.Matrix4f;
import primitives.Transform;
import shaders.StaticShader;
import utility.Maths;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class RenderManager {
    private static final float FOV = 70.0f;
    private static final float NEAR_PLANE = .1f;
    private static final float FAR_PLANE = 1000.0f;

    private static StaticShader staticShader = Engine.staticShader;

    private static Map<Model, List<GameObject>> gameObjects = new HashMap<>();

    public static void initialize(){
        staticShader.start();
        staticShader.loadProjectionMatrix(Maths.createProjectionMatrix(FAR_PLANE, NEAR_PLANE, FOV));
        staticShader.stop();
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
    }


    ///the passed object will no longer be rendered on screen;
    public static void unqueueGameObject(GameObject gameObject){
        if (gameObject == null)
             return;
        Model key = gameObject.getComponent(Model.class);
        List<GameObject> instances = gameObjects.get(key);
        for (GameObject go : instances) {
            if (go == gameObject){
                instances.remove(gameObject);
                if (instances.isEmpty())
                    gameObjects.remove(key);
                return;
            }
        }
    }

    public static void queueGameObject(GameObject gameObject) {
        Model model = gameObject.getComponent(Model.class);
        List<GameObject> instances = gameObjects.get(model);
        if (instances != null)
            instances.add(gameObject);
        else {
            List<GameObject> newInstances = new ArrayList<>();
            newInstances.add(gameObject);
            gameObjects.put(model, newInstances);
        }
    }

    public static void renderGameObjects(Light light, Camera camera){
        prepare();
        staticShader.start();
        staticShader.loadLight(light);
        staticShader.loadViewMatrix(Maths.createViewMatrix(camera));
        renderGameObjects();
        staticShader.stop();
    }

    private static void prepare(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    private static void renderGameObjects(){
        for (Model m : gameObjects.keySet()){
            prepareModel(m);
            List<GameObject> instances = gameObjects.get(m);
            for (GameObject instance : instances) {
                prepareInstance(instance);
                glDrawElements(GL_TRIANGLES, m.getMesh().getTriangles(), GL_UNSIGNED_INT, 0);
            }
            unbindModel();
        }
    }

    private static void prepareModel(Model model){
        Mesh mesh = model.getMesh();
        glBindVertexArray(mesh.getVao());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);
        staticShader.loadMaterial(model.getMaterial());
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, model.getTexture().getTextureID());
    }

    private static void prepareInstance(GameObject gameObject){
        Matrix4f transformationMatrix =
                Maths.createTransformationMatrix(gameObject.getTransform());
        staticShader.loadTransformationMatrix(transformationMatrix);
    }

    private static void unbindModel(){
        glDisableVertexAttribArray(2);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(0);
        glBindVertexArray(0);
    }


    public static void cleanUp(){
        staticShader.discardProgram();
    }
}
