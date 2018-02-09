package objects;

import mesh.Model;
import org.joml.Vector3f;
import primitives.Transform;

public class GameObject {

    private Model model;
    private Transform transform;

    public GameObject(Model model, Vector3f position, Vector3f rotation, Vector3f scale) {
        this.model = model;
        this.transform = new Transform(position, rotation, scale);
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Transform getTransform(){
        return transform;
    }

}
