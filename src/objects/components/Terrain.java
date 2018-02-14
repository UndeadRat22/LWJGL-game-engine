package objects.components;

import mesh.Material;
import mesh.Mesh;
import mesh.textures.Texture;

public class Terrain extends Model {

    private float size = 800;
    private int vertexCount = 128;

    private Model model;

    public Terrain(Mesh m, Texture t, Material material) {
        super(m, t, material);
    }
}
