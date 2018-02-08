package shaders;

public class StaticShader extends ShaderProgram{

    private static final String VERTEX_FILENAME = "src/shaders/vertex_shader.glsl";
    private static final String FRAGMENT_FILENAME = "src/shaders/fragment_shader.glsl";

    public StaticShader() {
        super(VERTEX_FILENAME, FRAGMENT_FILENAME);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
    }
}
