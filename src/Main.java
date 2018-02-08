import engine.Display;

import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;

public class Main {

    public static void main(String[] args) {
        Display display = new Display(1280, 720, "window");
        while (!display.isCloseRequested())
        {
            display.update();
        }
        display.close();
    }
}
