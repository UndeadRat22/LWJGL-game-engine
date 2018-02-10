package objects.components;

import mesh.Material;
import mesh.Mesh;
import mesh.textures.Texture;

public class Model extends BaseComponent{
    private Mesh mesh;
    private Texture texture;
    private Material material;

    public Model(Mesh m, Texture t, Material material)
    {
        this.mesh = m;
        this.texture = t;
        this.material = material;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public Texture getTexture() {
        return texture;
    }

    public Material getMaterial(){
        return material;
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

    @Override
    public void onAdd() {

    }
}
