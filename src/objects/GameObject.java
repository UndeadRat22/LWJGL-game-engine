package objects;

import objects.components.BaseComponent;
import org.joml.Vector3f;
import primitives.Transform;

import java.util.ArrayList;
import java.util.List;

public class GameObject {

    private List<BaseComponent> components;
    private Transform transform;

    public GameObject(Vector3f position, Vector3f rotation, Vector3f scale) {
        components = new ArrayList<BaseComponent>();
        this.transform = new Transform(position, rotation, scale);
    }

    public Transform getTransform(){
        return transform;
    }

    public void update(){
        for (BaseComponent component : components){
            component.update();
        }
    }

    public BaseComponent getComponent(Class<?> type){
        for (BaseComponent component : components)
            if (type == component.getClass())
                return component;
        return null;
    }
    //TODO: GENERIC addComponent method, aka: learn generics xd
    /*public BaseComponent addComponent(Class<BaseComponent> type){
        BaseComponent component = null;
        try {
            component = type.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (component == null)
            return null;
        components.add(component);
        return component;
    }*/

    public BaseComponent addComponent(BaseComponent component){
        component.setGameObject(this);
        components.add(component);
        return component;
    }

    public void removeComponent(Class<?> type){
        for (BaseComponent component : components)
            if (type == component.getClass()) {
                component.dispose();
                components.remove(component);
                return;
            }
    }
}
