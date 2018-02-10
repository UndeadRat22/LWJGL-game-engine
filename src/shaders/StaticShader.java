package shaders;

import objects.components.Light;
import mesh.Material;
import org.joml.Matrix4f;

public class StaticShader extends ShaderProgram{

    private static final String VERTEX_FILENAME = "src/shaders/vertex_shader.glsl";
    private static final String FRAGMENT_FILENAME = "src/shaders/fragment_shader.glsl";

    private int transformationMatrix;
    private int projectionMatrix;
    private int viewMatrix;
    private int lightPosition;
    private int lightColour;
    private int shineDamper;
    private int reflectivity;

    public StaticShader() {
        super(VERTEX_FILENAME, FRAGMENT_FILENAME);
    }

    @Override
    protected void getAllUniformLocations() {
        transformationMatrix = super.getUniformLocation("transformationMatrix");
        projectionMatrix = super.getUniformLocation("projectionMatrix");
        viewMatrix = super.getUniformLocation("viewMatrix");
        lightPosition = super.getUniformLocation("lightPosition");
        lightColour = super.getUniformLocation("lightColour");
        shineDamper = super.getUniformLocation("shineDamper");
        reflectivity = super.getUniformLocation("reflectivity");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureUV");
        super.bindAttribute(2, "normal");
    }

    public void loadMaterial(Material material){
        super.loadFloat(shineDamper, material.shineDampening);
        super.loadFloat(reflectivity, material.reflectivity);
    }

    public void loadLight(Light light){
        super.loadVector3f(lightPosition, light.getTransform().getPosition());
        super.loadVector3f(lightColour, light.getColour());
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
