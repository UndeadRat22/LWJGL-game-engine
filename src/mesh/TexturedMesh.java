package mesh;

import mesh.textures.Texture;

public class TexturedMesh {
    private Mesh mesh;
    private Texture texture;

    public TexturedMesh(Mesh m, Texture t)
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
}
