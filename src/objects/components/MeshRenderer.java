package objects.components;

import engine.RenderManager;

public class MeshRenderer extends BaseComponent {

    private Model model;
    private boolean queued;
    private boolean modelDisabled;

    @Override
    public void start() {
        model = gameObject.getComponent(Model.class);
        if (model == null) {  //we want to quit if our gameobject does not have a model attached
            model.disable();
            this.disable();
        }
        queue();
    }

    @Override
    public void update() {
        if (!queued)
            return;
        if (model.isDisabled())
            unqueue();
    }

    @Override
    public void dispose() {

    }

    @Override
    public void awake() {

    }

    @Override
    protected void onEnable() {
        queue();
    }

    @Override
    protected void onDisable() {
        unqueue();
    }

    private void queue(){
        RenderManager.queueGameObject(gameObject);
        queued = false;
    }

    private void unqueue(){
        RenderManager.unqueueGameObject(gameObject);
        queued = false;
    }
}
