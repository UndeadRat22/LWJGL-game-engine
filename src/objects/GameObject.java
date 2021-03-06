package objects;

import engine.BaseGame;
import objects.components.BaseComponent;
import org.joml.Vector3f;
import primitives.Transform;

import java.util.ArrayList;
import java.util.List;

public class GameObject {

    //STATIC METHODS AND PROPERTIES
    private static List<GameObject> gameObjects = new ArrayList<GameObject>();
    public static GameObject getGameObjectByName(String name){
        for (GameObject go : gameObjects)
            if (go.name == name)
                return go;
        return null;
    }

    public static GameObject[] getGameObjectsByName(String name){
        List<GameObject> result = new ArrayList<>();
        for (GameObject go : gameObjects)
            if (go.name == name)
                result.add(go);
        if (result.isEmpty())
            return null;
        return (GameObject[]) result.toArray();
    }

    public static GameObject getGameObjectByTag(String tag){
        for (GameObject go : gameObjects)
            if (go.tag == tag)
                return go;
        return null;
    }

    public static GameObject[] getGameObjectsByTag(String tag){
        List<GameObject> result = new ArrayList<>();
        for (GameObject go : gameObjects)
            if (go.tag == tag)
                result.add(go);
        if (result.isEmpty())
            return null;
        return (GameObject[]) result.toArray();
    }

    public static GameObject getGameObjectOfType(Class<? extends  BaseComponent> type){
        for (GameObject go : gameObjects)
            for (BaseComponent component : go.components)
                if (component.getClass() == type)
                    return go;
        return null;
    }

    public static GameObject[] getGameObjectsOfType(Class<? extends  BaseComponent> type){
        List<GameObject> objects = new ArrayList<>();
        for (GameObject go : gameObjects)
            for (BaseComponent component : go.components)
                if (component.getClass() == type)
                    objects.add(go);
        if (objects.isEmpty())
            return null;
        return (GameObject[]) objects.toArray();
    }

    private static double prev_time = 0;

    public static void UpdateAll(BaseGame game){
        if (game == null)
            return;
        for (GameObject go : gameObjects)
            go.update();
    }

    public static void Destroy(GameObject go){
        for (BaseComponent c: go.components )
            c.dispose();

        go.disabled = true;
        gameObjects.remove(go);
    }
    //END OF STATIC METHODS AND PROPERTIES

    //INSTANCED PROPERTIES
    private List<BaseComponent> components;
    private Transform transform;

    private boolean disabled;
    public String name = "";
    public String tag = "";

    public GameObject(Vector3f position, Vector3f rotation, Vector3f scale) {
        components = new ArrayList<BaseComponent>();
        this.transform = new Transform(position, rotation, scale);
        gameObjects.add(this);
    }

    public Transform getTransform(){
        return transform;
    }

    public void update(){
        if (disabled) return;
        for (BaseComponent component : components){
            if (component.isDisabled())
                continue;
            component.update();
        }
    }

    public <T> T getComponent(Class<? extends BaseComponent> type){
        for (BaseComponent component : components)
            if (type == component.getClass())
                return (T) component;
        return null;
    }

    public <T> T addComponent(BaseComponent component){
        component.setGameObject(this);
        components.add(component);
        component.awake();
        component.start();
        return (T) component;
    }

    public <T> T detatchComponent(Class<? extends BaseComponent> type){
        for (BaseComponent component : components)
            if (type == component.getClass()) {
                component.dispose();
                components.remove(component);
                return (T) component;
            }
         return null;
    }
}
