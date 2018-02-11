package engine;

import mesh.Material;
import mesh.Mesh;
import mesh.textures.Texture;
import objects.components.Model;
import objects.GameObject;
import org.joml.Matrix4f;
import shaders.StaticShader;
import utility.Maths;

import javax.jws.WebParam;
import javax.xml.soap.Text;

import java.util.List;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Renderer {

    private static final float FOV = 70.0f;
    private static final float NEAR_PLANE = .1f;
    private static final float FAR_PLANE = 1000.0f;

    private Display display;
    private StaticShader shader;

    public Renderer(Display display, StaticShader shader){
        this.display = display;
        this.shader = shader;
        shader.start();
        shader.loadProjectionMatrix(createProjectionMatrix());
        shader.stop();
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
    }

    public void prepare(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void render(Map<Model, List<GameObject>> gameObjects){
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

    private void prepareModel(Model model){
        Mesh mesh = model.getMesh();
        glBindVertexArray(mesh.getVao());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);
        shader.loadMaterial(model.getMaterial());
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, model.getTexture().getTextureID());
    }

    private void unbindModel(){
        glDisableVertexAttribArray(2);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(0);
        glBindVertexArray(0);
    }

    private void prepareInstance(GameObject gameObject){
        Matrix4f transformationMatrix =
                Maths.createTransformationMatrix(gameObject.getTransform());
        shader.loadTransformationMatrix(transformationMatrix);
    }

    private Matrix4f createProjectionMatrix(){
        float aspecpectRatio = (float) display.getDimensions().x / display.getDimensions().y;
        float yscale = (float) ((1f/Math.tan(Math.toRadians((FOV/2f))))*aspecpectRatio);
        float xscale = yscale / aspecpectRatio;
        float frustumLen = FAR_PLANE - NEAR_PLANE;

        Matrix4f projectionMatrix = new Matrix4f();
        projectionMatrix.m00(xscale);
        projectionMatrix.m11(yscale);
        projectionMatrix.m22(-(FAR_PLANE + NEAR_PLANE)/frustumLen);
        projectionMatrix.m23(-1);
        projectionMatrix.m32(-(2 * FAR_PLANE * NEAR_PLANE)/frustumLen);
        projectionMatrix.m33(0);
        return projectionMatrix;
    }
}
