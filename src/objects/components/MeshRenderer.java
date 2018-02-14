package objects.components;

import engine.RenderManager;

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
        if (model.isDisabled())
            RenderManager.unqueueGameObject(gameObject);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void onAdd() {

    }

    @Override
    protected void onEnable() {
        RenderManager.queueGameObject(gameObject);
    }

    @Override
    protected void onDisable() {
        RenderManager.unqueueGameObject(gameObject);
    }
}
