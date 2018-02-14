package objects.components;

import engine.Display;
import engine.Engine;
import engine.RenderManager;
import mesh.Mesh;
import objects.components.BaseComponent;
import objects.components.Model;
import objects.GameObject;
import org.joml.Matrix4f;
import shaders.StaticShader;
import utility.Maths;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class MeshRenderer extends BaseComponent {

    private Model model;

    @Override
    public void start() {
        model = gameObject.getComponent(Model.class);
        if (model == null) {  //we want to quit if our gameobject does not have a model attached
            model.disable();
            this.disable();
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void onAdd() {

    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
