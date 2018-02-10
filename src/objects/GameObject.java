package objects;

import mesh.Model;
import org.joml.Vector3f;
import primitives.Transform;

import java.util.ArrayList;
import java.util.List;

public class GameObject {

    private List<BaseComponent> components;
    private Model model;
    private Transform transform;

    public GameObject(Model model, Vector3f position, Vector3f rotation, Vector3f scale) {
        components = new ArrayList<BaseComponent>();
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

    public void update(){
        for (BaseComponent component : components){
            component.update();
        }
    }

    public void addComponent(BaseComponent component){
        component.setGameObject(this);
        components.add(component);
    }

    public void removecomponent(BaseComponent component){
        //TODO think if this needs anything else after sleep
        components.remove(component);
    }
}
