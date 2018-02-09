package engine;

import mesh.Mesh;
import mesh.Model;
import objects.GameObject;
import org.joml.Matrix4f;
import shaders.StaticShader;
import utility.Maths;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Renderer {

    private static final float FOV = 70.0f;
    private static final float NEAR_PLANE = .1f;
    private static final float FAR_PLANE = 1000.0f;

    private Display display;

    public Renderer(Display display, StaticShader shader){
        this.display = display;
        shader.start();
        shader.loadProjectionMatrix(createProjectionMatrix());
        shader.stop();
    }

    public void prepare(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void render(GameObject gameObject, StaticShader shader){
        Model model = gameObject.getModel();
        Mesh mesh = model.getMesh();
        glBindVertexArray(mesh.getVao());
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        Matrix4f transformationMatrix =
                Maths.createTransformationMatrix(gameObject.getTransform());
        shader.loadTransformationMatrix(transformationMatrix);
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, model.getTexture().getTextureID());
        glDrawElements(GL_TRIANGLES, mesh.getTriangles(), GL_UNSIGNED_INT, 0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(0);
        glBindVertexArray(0);

    }

    private Matrix4f createProjectionMatrix(){
        Matrix4f projectionMatrix = new Matrix4f();
        //System.out.println("width: " + display.getDimensions().x + ", height: " + display.getDimensions().y);
        float aspecpectRatio = (float) display.getDimensions().x / display.getDimensions().y;
        float yscale = (float) ((1f/Math.tan(Math.toRadians((FOV/2f))))*aspecpectRatio);
        float xscale = yscale / aspecpectRatio;
        float frustumLen = FAR_PLANE - NEAR_PLANE;

        projectionMatrix = new Matrix4f();
        projectionMatrix.m00(xscale);
        projectionMatrix.m11(yscale);
        projectionMatrix.m22(-(FAR_PLANE + NEAR_PLANE)/frustumLen);
        projectionMatrix.m23(-1);
        projectionMatrix.m32(-(2 * FAR_PLANE * NEAR_PLANE)/frustumLen);
        projectionMatrix.m33(0);
        return projectionMatrix;
    }
}
