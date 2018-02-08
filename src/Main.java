import engine.Display;

import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;

public class Main {

    public static void main(String[] args) {
        Display.createDisplay(1280, 720);
        Display.close();
    }
}
