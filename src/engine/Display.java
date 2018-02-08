package engine;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Display
{
    private static long window;
    public static void createDisplay(int width, int height) {
        if (!glfwInit())
            throw new IllegalStateException("Failed to initialize GLFW");


        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, 1);
        glfwWindowHint(GLFW_VISIBLE, 0);

        long window = glfwCreateWindow(width, height, "Title", 0, 0);

        if (window == 0)
            throw new IllegalStateException("Failed to cretate a window");
        glfwMakeContextCurrent(window);

        GL.createCapabilities();

        GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, (videoMode.width() - width)/ 2, (videoMode.height() - height)/ 2);
        glfwShowWindow(window);
        glClearColor(1, 1, 1, 1);
        Mesh mesh = new Mesh();
        mesh.create(new float[] {
                -1, -1,0,
                0, 1, 0,
                1, -1, 0
        });

        while(!glfwWindowShouldClose(window))
        {
            mesh.draw();
            glfwPollEvents();
            glClear(GL_COLOR_BUFFER_BIT);
            glfwSwapBuffers(window);
        }
        mesh.destroy();
    }


    public static void close(){
        glfwTerminate();
    }
}
