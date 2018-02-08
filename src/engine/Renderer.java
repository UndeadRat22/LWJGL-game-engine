package engine;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Renderer {
    public void prepare(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void render(Mesh mesh){
        glBindVertexArray(mesh.getVao());
        glEnableVertexAttribArray(0);
        glDrawArrays(GL_TRIANGLES, 0, mesh.getVertexCount());
        glDisableVertexAttribArray(0);
        glBindVertexArray(0);
    }
}
