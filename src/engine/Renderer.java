package engine;

import mesh.Mesh;
import mesh.Model;
import objects.GameObject;
import org.joml.Matrix4f;
import primitives.Transform;
import shaders.StaticShader;
import utility.Maths;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Renderer {
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
        shader.loadTransformmationMatrix(transformationMatrix);
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, model.getTexture().getTextureID());
        glDrawElements(GL_TRIANGLES, mesh.getTriangles(), GL_UNSIGNED_INT, 0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(0);
        glBindVertexArray(0);
    }
}
