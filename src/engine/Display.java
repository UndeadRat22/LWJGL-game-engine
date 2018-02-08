package engine;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class Display
{
    private long window;
    public Display(int width, int height, String title){
        if(!glfwInit())
            throw new IllegalStateException("Failed to initialize GLFW");

        GLFWErrorCallback.createPrint(System.err).set();

        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, 1);
        glfwWindowHint(GLFW_REFRESH_RATE, 60);

        window = glfwCreateWindow(width, height, title, 0, 0);
        if (window == 0)
            throw new IllegalStateException("Failed to create window");
        glfwMakeContextCurrent(window);
        glfwShowWindow(window);

        GL.createCapabilities();
        glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
    }

    public void update(){
        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    public void close(){
        glfwDestroyWindow(window);
    }

    public boolean isCloseRequested(){
        return glfwWindowShouldClose(window);
    }
}
