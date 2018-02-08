package engine;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Mesh {
    private int vao;
    private int vbo;

    private int vertexCount;

    public Mesh(){

    }

    public boolean create(float vertices[]){
        vao = glGenVertexArrays();                  /*first we generate an array buffer to store the vbo in*/
        glBindVertexArray(vao);                     /*next we bind the vao, so we can store data into it*/

        vbo = glGenBuffers();                       /*generate the vertex buffer object*/
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0,0);

        glBindVertexArray(0);                       /*we unbind the vao after we're done using it*/

        vertexCount = vertices.length / 3;
        return true;
    }

    public void destroy(){
        glDeleteBuffers(vbo);
        glDeleteVertexArrays(vao);
    }

    public void draw(){
        glBindVertexArray(vao);
        glEnableVertexAttribArray(0);
        glDrawArrays(GL_TRIANGLES, 0, vertexCount);
        glDisableVertexAttribArray(0);
        glBindVertexArray(0);
    }
}
