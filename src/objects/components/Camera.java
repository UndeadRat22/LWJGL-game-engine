package objects.components;

import engine.Input;
import engine.Time;
import org.joml.Vector2f;
import org.joml.Vector3f;
import primitives.Transform;

import static org.lwjgl.glfw.GLFW.*;

public class Camera extends BaseComponent
{
    private float speed = 2f;
    private float sensitivity = 0.05f;

    public boolean disableControls = true;

    @Override
    public void start() {

    }

    @Override
    public void update() {
        if (disableControls) return;
        if (Input.getKey(GLFW_KEY_A)){
            strafe(speed * (float)Time.getDeltaTime());
        } else if (Input.getKey(GLFW_KEY_D)){
            strafe(-speed * (float)Time.getDeltaTime());
        }
        if (Input.getKey(GLFW_KEY_W)){
            move(-speed * (float)Time.getDeltaTime());
        } else if (Input.getKey(GLFW_KEY_S)){
            move(speed * (float)Time.getDeltaTime());
        }
        if (Input.getKey(GLFW_KEY_SPACE)){
            lift(speed * (float)Time.getDeltaTime());
        } else if (Input.getKey(GLFW_KEY_LEFT_SHIFT)){
            lift(-speed * (float)Time.getDeltaTime());
        }
        Vector2f mousepos = Input.getMousePositionDelta().mul(sensitivity);
        rotate(mousepos.x, mousepos.y);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void onAdd() {

    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    private void lift(float distance) {
        Transform.translate(transform, new Vector3f(0, distance, 0));
    }

    // For moving on ground where camera is looking
    private void move(float distance) {
        float yawRadians = (float)Math.toRadians(transform.getRotation().y);
        Vector3f movement = new Vector3f(
                (float)(-(distance * Math.sin(yawRadians))),
                0,
                (float)(distance * Math.cos(yawRadians)));
        Transform.translate(transform, movement);
    }

    // Same as move() except that it moves sideways
    private void strafe(float distance) {
        Vector3f translation = new Vector3f();
        translation.x -= distance * Math.sin(Math.toRadians(transform.getRotation().y + 90));
        translation.z += distance * Math.cos(Math.toRadians(transform.getRotation().y + 90));
        Transform.translate(transform, translation);
    }

    // Rotates the camera by delta offsets
    private void rotate(float deltaYaw, float deltaPitch) {
        Vector3f rotation = new Vector3f(deltaPitch % 360, deltaYaw % 360, 0);
        Transform.rotate(transform, rotation);
    }

}