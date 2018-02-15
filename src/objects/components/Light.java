package objects.components;

import engine.Input;
import engine.Time;
import org.joml.Vector3f;
import primitives.Transform;

public class Light extends BaseComponent {

    private static Vector3f RED = new Vector3f(1, 0, 0);
    private static Vector3f GREEN = new Vector3f(0, 1, 0);
    private static Vector3f BLUE = new Vector3f(0, 0, 1);
    private static Vector3f WHITE = new Vector3f(1, 1, 1);

    private Vector3f colour = new Vector3f(WHITE);

    @Override
    public void start() {

    }

    @Override
    public void update(){

    }

    @Override
    public void dispose() {

    }

    @Override
    public void awake() {

    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    public void setColour(Vector3f colour){
        this.colour = colour;
    }

    public Vector3f getColour(){
        return colour;
    }
}
