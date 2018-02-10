package objects.components;

import objects.GameObject;
import primitives.Transform;

public abstract class BaseComponent {
    protected GameObject gameObject = null;
    protected Transform transform = null;

    public abstract void start();
    public abstract void update();
    public abstract void dispose();
    public abstract void onAdd();

    public void setGameObject(GameObject gameObject){
        if (this.gameObject != null)
            return;
        this.gameObject = gameObject;
        this.transform = gameObject.getTransform();
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public Transform getTransform() {
        return transform;
    }
}
