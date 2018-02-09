package engine;

import org.lwjgl.BufferUtils;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.FloatBuffer;
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

    public static Mesh loadToVAO(float[] vertices){
        int vao = createVAO();
        storeDataInAttributeList(0,3, vertices, false);
        glBindVertexArray(0);                                   /*unbind the currently bound vao*/
        return new Mesh(vao, vertices.length/3);
    }

    ///Creates and binds a vertex array object
    private static int createVAO(){
        int vao = glGenVertexArrays();
        vaos.add(vao);
        glBindVertexArray(vao);
        return vao;
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
