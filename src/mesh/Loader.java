package mesh;

import mesh.Mesh;
import mesh.textures.Texture;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;


public class Loader {

    private static List<Integer> vaos = new ArrayList<Integer>();
    private static List<Integer> vbos = new ArrayList<Integer>();
    private static List<Integer> textures = new ArrayList<Integer>();

    public static Mesh loadToVAO(float[] vertices, float[] textureUV, float[] normals, int[] triangles){
        int vao = createVAO();
        bindIndicesBuffer(triangles);
        storeDataInAttributeList(0,3, vertices, false);
        storeDataInAttributeList(1,2, textureUV, true);
        storeDataInAttributeList(2,3,normals, true);
        glBindVertexArray(0);                                   /*unbind the currently bound vao*/
        return new Mesh(vao, triangles.length);
    }

    private static void bindIndicesBuffer(int[] triangles){
        int vboId = glGenBuffers();
        vbos.add(vboId);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboId);
        IntBuffer buffer = createIntBuffer(triangles);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
    }

    ///Creates and binds a vertex array object
    private static int createVAO(){
        int vao = glGenVertexArrays();
        vaos.add(vao);
        glBindVertexArray(vao);
        return vao;
    }

    public static Texture loadTexture(String fileName)
    {
        Texture texture = new Texture(fileName);
        textures.add(texture.getTextureID());
        return texture;
    }

    ///Stores data in an attribute list of the currently bound vao
    private static void storeDataInAttributeList(int attributeId,int dataLenght, float[] data, boolean normalized){
        int vbo = glGenBuffers();                                               /*create empty vertex buffer object*/
        vbos.add(vbo);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);                                     /*bind the created vbo, and specify that it's an array buffer*/
        FloatBuffer buffer = createFloatBuffer(data);                           /*create our float buffer of the data*/
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);                  /*store the data*/
        glVertexAttribPointer(attributeId, dataLenght, GL_FLOAT, normalized,0,0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    private static IntBuffer createIntBuffer(int[] data){
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    private static FloatBuffer createFloatBuffer(float[] data)
    {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    public static void discardData(){
        vaos.forEach((vao) -> { glDeleteVertexArrays(vao); });
        vbos.forEach((vbo) -> { glDeleteBuffers(vbo); });
        textures.forEach((texture)->{glDeleteTextures(texture);});
    }

}
