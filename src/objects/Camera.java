package objects;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFWKeyCallback;
import primitives.Transform;

import static org.lwjgl.glfw.GLFW.*;

public class Camera {
    private Transform transform;
    private GLFWKeyCallback glfwKeyCallback;

    public Camera(){
        transform = new Transform();
        setUpKeyCallback();
    }

    public Transform getTransform() {
        return transform;
    }

    public GLFWKeyCallback getGlfwKeyCallback() {
        return glfwKeyCallback;
    }

    private void setUpKeyCallback(){
        glfwKeyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if (key == GLFW_KEY_W && action == GLFW_KEY_DOWN){
                    Transform.translate(transform, new Vector3f(0, 0, -0.02f));
                } else if (key == GLFW_KEY_S && action == GLFW_KEY_DOWN){
                    Transform.translate(transform, new Vector3f(0, 0, 0.02f));
                } else if (key == GLFW_KEY_A && action == GLFW_KEY_DOWN){
                    Transform.translate(transform, new Vector3f(-0.02f, 0, 0));
                } else if (key == GLFW_KEY_S && action == GLFW_KEY_DOWN){
                    Transform.translate(transform, new Vector3f(0.02f, 0, 0));
                }
            }
        };
    }
}
