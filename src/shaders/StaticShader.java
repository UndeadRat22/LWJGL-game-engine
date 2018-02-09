package shaders;

import org.joml.Matrix4f;

public class StaticShader extends ShaderProgram{

    private static final String VERTEX_FILENAME = "src/shaders/vertex_shader.glsl";
    private static final String FRAGMENT_FILENAME = "src/shaders/fragment_shader.glsl";

    private int transformationMatrix;
    private int projectionMatrix;
    private int viewMatrix;

    public StaticShader() {
        super(VERTEX_FILENAME, FRAGMENT_FILENAME);
    }

    @Override
    protected void getAllUniformLocations() {
        transformationMatrix = super.getUniformLocation("transformationMatrix");
        projectionMatrix = super.getUniformLocation("projectionMatrix");
        viewMatrix = super.getUniformLocation("viewMatrix");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureUV");
    }

    public void loadTransformationMatrix(Matrix4f trans){
        super.loadMatrix(transformationMatrix, trans);
    }

    public void loadProjectionMatrix(Matrix4f proj){
        super.loadMatrix(projectionMatrix, proj);
    }

    public void loadViewMatrix(Matrix4f view){
        super.loadMatrix(viewMatrix, view);
    }
}
