package objects;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFWKeyCallback;
import primitives.Transform;

import static org.lwjgl.glfw.GLFW.*;

public class Camera {
    private Transform transform;
    private GLFWKeyCallback glfwKeyCallback;
    private float speed = 0.05f;

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
                if (action != GLFW_REPEAT && action != GLFW_PRESS) return;
                switch (key) {
                    case GLFW_KEY_W: {
                        Transform.translate(transform, new Vector3f(0, 0, -speed));
                        break;
                    }
                    case GLFW_KEY_S: {
                        Transform.translate(transform, new Vector3f(0, 0, speed));
                        break;
                    }
                    case GLFW_KEY_A: {
                        Transform.translate(transform, new Vector3f(-speed, 0, 0));
                        break;
                    }
                    case GLFW_KEY_D: {
                        Transform.translate(transform, new Vector3f(speed, 0, 0));
                        break;
                    }
                    default:
                        return;
                }
            }
        };
    }
}
