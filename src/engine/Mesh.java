package engine;
public class Mesh {
    private int vao;

    private int vertexCount;

    public Mesh(int vao, int vertexCount) {
        this.vao = vao;
        this.vertexCount = vertexCount;
    }

    public int getVao(){
        return vao;
    }

    public int getVertexCount(){
        return vertexCount;
    }
}
