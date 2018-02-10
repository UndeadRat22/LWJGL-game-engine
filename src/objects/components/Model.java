package objects.components;

import mesh.Mesh;
import mesh.textures.Texture;

public class Model extends BaseComponent{
    private Mesh mesh;
    private Texture texture;

    public Model(Mesh m, Texture t)
    {
        this.mesh = m;
        this.texture = t;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public Texture getTexture() {
        return texture;
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {

    }

    @Override
    public void dispose() {

    }
}
