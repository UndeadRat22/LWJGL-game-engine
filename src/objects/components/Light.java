package objects.components;

import engine.Time;
import org.joml.Vector3f;
import primitives.Transform;

public class Light extends BaseComponent {

    private Vector3f colour = new Vector3f(1, 1, 1);

    @Override
    public void start() {

    }

    @Override
    public void update() {
        float xspeed = 3;
        float yspeed = 2;
        if (transform.getPosition().x >= 5) {
            xspeed = -3;
        }
        else if (transform.getPosition().x <= -5){
            xspeed = 3;
        }
        if (transform.getPosition().y >= 3) {
            yspeed = -2;
        } else if (transform.getPosition().y <= -3){
            yspeed = 2;
        }
        Transform.translate(transform, new Vector3f(xspeed *
                (float)Time.getDeltaTime(), yspeed *
                (float)Time.getDeltaTime(), 0));
    }

    public void setColour(Vector3f colour){
        this.colour = colour;
    }

    public Vector3f getColour(){
        return colour;
    }
}
