package objects.components;

import engine.Input;
import engine.Time;
import org.joml.Vector3f;
import primitives.Transform;

public class Light extends BaseComponent {

    private Vector3f colour = new Vector3f(1, 1, 1);

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
