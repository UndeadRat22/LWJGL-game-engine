package primitives;

import org.joml.Vector3f;

public class Transform {
    private Vector3f position;
    private Vector3f rotation;
    private Vector3f scale;

    public Transform() {
        position = new Vector3f(0, 0, 0);
        rotation = new Vector3f(0, 0, 0);
        scale = new Vector3f(1, 1, 1);
    }

    public Transform(Vector3f position, Vector3f rotation, Vector3f scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public Vector3f getScale() {
        return scale;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }

    public static void translate(Transform t, Vector3f translation){
        t.position = t.position.add(translation);
    }

    public static void rotate(Transform t, Vector3f rotation){
        t.rotation = t.rotation.add(rotation);
    }

}
