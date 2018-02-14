package engine;

import mesh.Material;
import mesh.Mesh;
import mesh.ObjParser;
import mesh.textures.Texture;
import objects.GameObject;
import objects.components.MeshRenderer;
import objects.components.Model;
import org.joml.Vector3f;

public class Game extends BaseGame {

    //Instantiate all of the GameObjects here;
    @Override
    public void start(){
        Texture texture = new Texture("resources/cruiser.png");
        Mesh mesh = ObjParser.parseObjMesh("resources/obj/cruiser.obj");

        Model model = new Model(mesh, texture, new Material(.1f, 1));

        GameObject gameObject = new GameObject(
                new Vector3f(0, 0, -5),
                new Vector3f(0, 0, 0),
                new Vector3f(1f, 1f, 1f));
        gameObject.addComponent(model);
        MeshRenderer meshRenderer = new MeshRenderer();
        gameObject.addComponent(meshRenderer);
        gameObject.name = "modeled object";
    }

    @Override
    public void update(){

    }

}
