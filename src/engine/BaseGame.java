package engine;

import objects.GameObject;
import objects.components.Camera;
import objects.components.Light;
import org.joml.Vector3f;

public abstract class BaseGame {

    protected Camera main;
    protected Light sun;

    public final void initialize(){
        GameObject camera = new GameObject(
                new Vector3f(0, 0, 10),
                new Vector3f(0, 0, 0),
                new Vector3f(0, 0, 0));
        camera.name = "Main Camera";
        camera.tag = "Main";
        main = camera.addComponent(new Camera());
        GameObject light = new GameObject(
                new Vector3f(0, 10, 0),
                new Vector3f(0, 0, 0),
                new Vector3f(0, 0, 0));
        sun = light.addComponent(new Light());
        light.name = "Main Light";
        light.tag = "Sun";
    }

    public abstract void start();
    public abstract void update();

    //DO NOT CALL THIS, CAN ONLY BE CALLED FROM THE ENGINE
    public final void updateGameObjects(){
        GameObject.UpdateAll( this);
    }

    public void draw(){
        RenderManager.renderGameObjects(sun, main);
    }

    public Camera getMainCamera(){
        return main;
    }

    public Light getMainLight(){
        return sun;
    }

}
