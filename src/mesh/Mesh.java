package mesh;

public class Mesh {

    private int vao;
    private int triangles;

    public Mesh(float[] vertices, float[] uv,float[] normals, int[] triangles) {
        Mesh temp = Loader.loadToVAO(vertices, uv, normals, triangles);
        this.vao = temp.vao;
        this.triangles = temp.triangles;
    }

    public Mesh(int vao, int triangles) {
        this.vao = vao;
        this.triangles = triangles;
    }

    public int getVao(){
        return vao;
    }

    public int getTriangles(){
        return triangles;
    }
}
