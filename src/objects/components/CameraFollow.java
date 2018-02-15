package objects.components;

import org.joml.Vector3f;
import primitives.Transform;

public class CameraFollow extends BaseComponent{

    public Transform target;
    public Vector3f offset;

    @Override
    public void awake() {

    }

    @Override
    public void start() {

    }

    @Override
    public void update() {
        Vector3f newPos = new Vector3f();
        target.getPosition().add(offset, newPos);
        transform.setPosition(newPos);
    }

    @Override
    public void dispose() {

    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
