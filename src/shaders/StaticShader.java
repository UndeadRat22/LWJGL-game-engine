package shaders;

import org.joml.Matrix4f;

public class StaticShader extends ShaderProgram{

    private static final String VERTEX_FILENAME = "src/shaders/vertex_shader.glsl";
    private static final String FRAGMENT_FILENAME = "src/shaders/fragment_shader.glsl";

    private int transformationMatrix;
    private int projectionMatrix;

    public StaticShader() {
        super(VERTEX_FILENAME, FRAGMENT_FILENAME);
    }

    @Override
    protected void getAllUniformLocations() {
        transformationMatrix = super.getUniformLocation("transformationMatrix");
        projectionMatrix = super.getUniformLocation("projectionMatrix");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureUV");
    }

    public void loadTransformationMatix(Matrix4f matrix){
        super.loadMatrix(transformationMatrix, matrix);
    }

    public void loadProjectionMatrix(Matrix4f proj){
        super.loadMatrix(projectionMatrix, proj);
    }
}
