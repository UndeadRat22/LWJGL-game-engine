package objects.components;

import engine.Input;
import engine.Time;
import objects.components.BaseComponent;
import org.joml.Vector3f;
import primitives.Transform;

import static org.lwjgl.glfw.GLFW.*;

public class Camera extends BaseComponent
{
    private float speed = 2f;

    @Override
    public void start() {

    }

    @Override
    public void update() {
        if (Input.getKey(GLFW_KEY_S)){
            Transform.translate(this.transform, new Vector3f(0, 0, speed * (float)Time.getDeltaTime()));
        }
        else if (Input.getKey(GLFW_KEY_W)){
            Transform.translate(this.transform, new Vector3f(0, 0, -speed * (float)Time.getDeltaTime()));
        }
        else if (Input.getKey(GLFW_KEY_A)){
            Transform.translate(this.transform, new Vector3f(-speed * (float) Time.getDeltaTime(), 0,0));
        }
        else if (Input.getKey(GLFW_KEY_D)){
            Transform.translate(this.transform, new Vector3f(speed * (float) Time.getDeltaTime(), 0,0));
        }
    }

    @Override
    public void dispose() {

    }
}