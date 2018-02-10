package engine;

import org.joml.Vector2d;
import org.joml.Vector2f;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class Input {

    private static boolean[] keys =  new boolean[65535];
    private static Vector2f mousePosition = new Vector2f();

    private static GLFWKeyCallback keyCallback = null;
    private static GLFWCursorPosCallback cursorPositionCallback = null;

    public static GLFWKeyCallback getKeyCallback(){
        return keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                keys[key] = action != GLFW_RELEASE;
            }
        };
    }

    public static GLFWCursorPosCallback getCursorPositionCallback(){
        return cursorPositionCallback = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                mousePosition.x = (float)xpos;
                mousePosition.y = (float)ypos;
            }
        };
    }

    public static boolean getKey(int keyCode){
        return keys[keyCode];
    }

    public static Vector2f getMousePosition(){
        return mousePosition;
    }
}
