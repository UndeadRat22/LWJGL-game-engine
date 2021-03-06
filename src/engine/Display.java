package engine;

import org.joml.Vector2f;
import org.joml.Vector2i;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;

import java.util.Vector;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Display
{
    private long window;    /*Display window handle.*/
    private Vector2i center;

    ///Creates a display of specified width, height and title.
    ///Also opens the window.
    ///To use in any context, need to have an update loop, while the window
    ///is not supposed to close.
    public Display(int width, int height, String title, int maxFPS){
        if(!glfwInit())
            throw new IllegalStateException("Failed to initialize GLFW");

        GLFWErrorCallback.createPrint(System.err).set();                /*set the error output to the System.err stream.*/

        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);             /*opengl 3.2*/
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, 1);
        glfwWindowHint(GLFW_REFRESH_RATE, maxFPS);
        glfwWindowHint(GLFW_VISIBLE, 0);

        window = glfwCreateWindow(width, height, title, 0, 0);
        if (window == 0)
            throw new IllegalStateException("Failed to create window");

        centerWindow();

        glfwMakeContextCurrent(window);
        glfwSetCursorPos(window, center.x, center.y);
        glfwShowWindow(window);

        GL.createCapabilities();
        glClearColor(0.0f, .7f, 1.0f, 0.0f);
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

    public void setWindowKeyInputCallback(GLFWKeyCallback callback){
        glfwSetKeyCallback(window, callback);
    }

    public void setWindowResizeCallback(GLFWWindowSizeCallback callback){
        glfwSetWindowSizeCallback(window, callback);
    }

    public void setWindowMouseMoveCallback(GLFWCursorPosCallback callback){
        glfwSetCursorPosCallback(window, callback);
    }

    public Vector2i getCenter(){
        return center;
    }

    ///centers the window on the primary monitor
    public void centerWindow(){
        updateCenter();
        glfwSetWindowPos(window, center.x, center.y);
    }

    public Vector2i getDimensions(){
        int[] width = new int[1], height = new int[1];
        glfwGetWindowSize(window, width, height);
        return new Vector2i(width[0], height[0]);
    }


    public void updateCenter(){
        long monitor = glfwGetPrimaryMonitor();
        GLFWVidMode mode = glfwGetVideoMode(monitor);
        Vector2i dimensions = getDimensions();
        center = new Vector2i(
                (mode.width()  - dimensions.x)/2,
                (mode.height() - dimensions.y)/2);
    }

}
