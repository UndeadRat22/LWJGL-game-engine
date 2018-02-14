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
    protected abstract void onEnable();
    protected abstract void onDisable();

    private boolean disabled;

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

    public boolean isDisabled(){
        return disabled;
    }

    public boolean isEnabled(){
        return !disabled;
    }

    public void disable(){
        disabled = true;
        onDisable();
    }

    public void enable(){
        disabled = false;
        onEnable();
    }
}
