package mesh;

import org.joml.Vector2f;
import org.joml.Vector3f;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ObjParser {

    public static Mesh parseObjMesh(String filename) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(new File(filename));
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't load file!");
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        List<Vector3f> vertices = new ArrayList<Vector3f>();
        List<Vector2f> uvs = new ArrayList<Vector2f>();
        List<Vector3f> normals = new ArrayList<Vector3f>();
        List<Integer> triangles = new ArrayList<Integer>();
        float[] verticesArray = null;
        float[] normalsArray = null;
        float[] uvsArray = null;
        int[] trianglesArray = null;
        try {
            while (true) {
                line = bufferedReader.readLine();
                String[] currentLine = line.split(" ");
                if (line.startsWith("v ")) {
                    Vector3f vertex = new Vector3f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]),
                            Float.parseFloat(currentLine[3]));
                    vertices.add(vertex);
                } else if (line.startsWith("vt ")) {
                    Vector2f uv = new Vector2f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]));
                    uvs.add(uv);
                } else if (line.startsWith("vn ")) {
                    Vector3f normal = new Vector3f(Float.parseFloat(currentLine[1]), Float.parseFloat(currentLine[2]),
                            Float.parseFloat(currentLine[3]));
                    normals.add(normal);
                } else if (line.startsWith("f ")) {
                    uvsArray = new float[vertices.size() * 2];
                    normalsArray = new float[vertices.size() * 3];
                    break;
                }
            }
            while (line != null) {
                if (!line.startsWith("f ")) {
                    line = bufferedReader.readLine();
                    continue;
                }
                String[] currentLine = line.split(" ");
                String[] vertex1 = currentLine[1].split("/");
                String[] vertex2 = currentLine[2].split("/");
                String[] vertex3 = currentLine[3].split("/");

                convertVertex(vertex1, triangles, uvs, normals, uvsArray, normalsArray);
                convertVertex(vertex2, triangles, uvs, normals, uvsArray, normalsArray);
                convertVertex(vertex3, triangles, uvs, normals, uvsArray, normalsArray);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        verticesArray = new float[vertices.size() * 3];
        trianglesArray = new int[triangles.size()];

        int vertexPointer = 0;
        for (Vector3f vertex : vertices) {
            verticesArray[vertexPointer++] = vertex.x;
            verticesArray[vertexPointer++] = vertex.y;
            verticesArray[vertexPointer++] = vertex.z;
        }
        for (int i = 0; i < triangles.size(); i++) {
            trianglesArray[i] = triangles.get(i);
        }

        return new Mesh(verticesArray, uvsArray, trianglesArray);
    }

    private static void convertVertex(String[] vertexData, List<Integer> triangles, List<Vector2f> uvs,
                                      List<Vector3f> normals, float[] uvsArray, float[] normalsArray) {
        int currentVertexPointer = Integer.parseInt(vertexData[0]) - 1;
        triangles.add(currentVertexPointer);
        Vector2f currentTex = uvs.get(Integer.parseInt(vertexData[1]) - 1);
        uvsArray[currentVertexPointer * 2] = currentTex.x;
        uvsArray[currentVertexPointer * 2 + 1] = 1 - currentTex.y;
        Vector3f currentNorm = normals.get(Integer.parseInt(vertexData[2]) - 1);
        normalsArray[currentVertexPointer * 3] = currentNorm.x;
        normalsArray[currentVertexPointer * 3 + 1] = currentNorm.y;
        normalsArray[currentVertexPointer * 3 + 2] = currentNorm.z;
    }
}
